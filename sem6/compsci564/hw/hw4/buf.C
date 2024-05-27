#include <memory.h>
#include <unistd.h>
#include <errno.h>
#include <stdlib.h>
#include <fcntl.h>
#include <assert.h>
#include <iostream>
#include <stdio.h>
#include "page.h"
#include "buf.h"

#define ASSERT(c)  { if (!(c)) { \
		       cerr << "At line " << __LINE__ << ":" << endl << "  "; \
                       cerr << "This condition should hold: " #c << endl; \
                       exit(1); \
		     } \
                   }

//----------------------------------------
// Constructor of the class BufMgr
//----------------------------------------

BufMgr::BufMgr(const int bufs)
{
    numBufs = bufs;

    bufTable = new BufDesc[bufs];
    memset(bufTable, 0, bufs * sizeof(BufDesc));
    for (int i = 0; i < bufs; i++)
    {
        bufTable[i].frameNo = i;
        bufTable[i].valid = false;
    }

    bufPool = new Page[bufs];
    memset(bufPool, 0, bufs * sizeof(Page));

    int htsize = ((((int) (bufs * 1.2))*2)/2)+1;
    hashTable = new BufHashTbl (htsize);  // allocate the buffer hash table

    clockHand = bufs - 1;
}


BufMgr::~BufMgr() {

    // flush out all unwritten pages
    for (int i = 0; i < numBufs; i++)
    {
        BufDesc* tmpbuf = &bufTable[i];
        if (tmpbuf->valid == true && tmpbuf->dirty == true) {

#ifdef DEBUGBUF
            cout << "flushing page " << tmpbuf->pageNo
                 << " from frame " << i << endl;
#endif

            tmpbuf->file->writePage(tmpbuf->pageNo, &(bufPool[i]));
        }
    }

    delete [] bufTable;
    delete [] bufPool;
}


const Status BufMgr::allocBuf(int & frame)
{
    int counter = 0;
    while (counter < 2 * (int)numBufs)
    {
        advanceClock();
        if (bufTable[clockHand].valid == 1) {
            if (bufTable[clockHand].refbit == 1) {
                bufTable[clockHand].refbit = false;
                counter++;
                continue;
            }
            else {
                if (bufTable[clockHand].pinCnt == 0)
                {
                    if (bufTable[clockHand].dirty)
                    {
                        Status status;
                        status = bufTable[clockHand].file->writePage(bufTable[clockHand].pageNo, &bufPool[clockHand]);
                        if (status != OK) return UNIXERR;
                        bufStats.diskwrites++;
                    }
                    frame = clockHand;
                    hashTable->remove(bufTable[clockHand].file, bufTable[clockHand].pageNo);
                    bufTable[clockHand].Clear();
                    return OK;
                }
            }
        }
        else {
            frame = clockHand;
            return OK;
        }
        counter++;
    }
    return BUFFEREXCEEDED;
}


const Status BufMgr::readPage(File* file, const int PageNo, Page*& page)
{
    Status fileStat = OK;
    Status insertStat = OK;
    Status hashStat = OK;
    Status allocStat = OK;

    int fframe;
    int frameNo;

    hashStat = hashTable->lookup(file, PageNo, frameNo);

    if (hashStat == HASHNOTFOUND)
    {
        allocStat = allocBuf(fframe);
        if (allocStat == UNIXERR)
        {
            return UNIXERR;
        }
        else if (allocStat == BUFFEREXCEEDED)
        {
            return BUFFEREXCEEDED;
        }
        else
        {
            fileStat = file->readPage(PageNo, &bufPool[fframe]);
            if (fileStat != OK)
            {
                return fileStat;
            }
            bufStats.diskreads++;
            bufTable[fframe].Set(file, PageNo);
            insertStat = hashTable->insert(file, PageNo, fframe);
            if (insertStat == HASHTBLERROR)
            {
                return HASHTBLERROR;
            }
            else
            {
                page = &bufPool[fframe];
                bufStats.accesses++;
            }
        }
    }
    else if (hashStat == OK)
    {
        bufTable[frameNo].refbit = true;
        bufTable[frameNo].pinCnt++;
        page = &bufPool[frameNo];
        bufStats.accesses++;
    }
    return OK;
}


const Status BufMgr::unPinPage(File* file, const int PageNo,
			       const bool dirty)
{
    int frame;
    Status stat;
    stat = hashTable->lookup(file, PageNo, frame);
    if (stat == HASHNOTFOUND)
    {
        return stat;
    }
    if (bufTable[frame].pinCnt == 0)
    {
        return PAGENOTPINNED;
    }
    bufTable[frame].pinCnt--;
    if (dirty)
    {
        bufTable[frame].dirty = true;
    }
    return OK;
}

const Status BufMgr::allocPage(File* file, int& pageNo, Page*& page)
{
    Status allocStat = OK;
    Status insertStat = OK;
    int fframe = 0;
    file->allocatePage(pageNo);
    allocStat = allocBuf(fframe);
    if (allocStat != OK)
    {
        return allocStat;
    }
    page = &bufPool[fframe];
    bufTable[fframe].Set(file, pageNo);
    insertStat = hashTable->insert(file, pageNo, fframe);
    if (insertStat == HASHTBLERROR)
    {
        return HASHTBLERROR;
    }
    return OK;
}

const Status BufMgr::disposePage(File* file, const int pageNo)
{
    Status stat = OK;
    int fNo = 0;
    stat = hashTable->lookup(file, pageNo, fNo);
    if (stat == OK)
    {
        bufTable[fNo].Clear();
    }
    stat = hashTable->remove(file, pageNo);

    return file->disposePage(pageNo);
}

const Status BufMgr::flushFile(const File* file)
{
  Status status;

  for (int i = 0; i < numBufs; i++) {
    BufDesc* tmpbuf = &(bufTable[i]);
    if (tmpbuf->valid == true && tmpbuf->file == file) {

      if (tmpbuf->pinCnt > 0)
	  return PAGEPINNED;

      if (tmpbuf->dirty == true) {
#ifdef DEBUGBUF
	cout << "flushing page " << tmpbuf->pageNo
             << " from frame " << i << endl;
#endif
	if ((status = tmpbuf->file->writePage(tmpbuf->pageNo,
					      &(bufPool[i]))) != OK)
	  return status;

	tmpbuf->dirty = false;
      }

      hashTable->remove(file,tmpbuf->pageNo);

      tmpbuf->file = NULL;
      tmpbuf->pageNo = -1;
      tmpbuf->valid = false;
    }

    else if (tmpbuf->valid == false && tmpbuf->file == file)
      return BADBUFFER;
  }

  return OK;
}


void BufMgr::printSelf(void)
{
    BufDesc* tmpbuf;

    cout << endl << "Print buffer...\n";
    for (int i=0; i<numBufs; i++) {
        tmpbuf = &(bufTable[i]);
        cout << i << "\t" << (char*)(&bufPool[i])
             << "\tpinCnt: " << tmpbuf->pinCnt;

        if (tmpbuf->valid == true)
            cout << "\tvalid\n";
        cout << endl;
    };
}


