#include "mem.h"                      
extern BLOCK_HEADER* first_header;

// return a pointer to the payload
// if a large enough free block isn't available, return NULL
void* Mem_Alloc(int size){
    // find a free block that's big enough
    if(size <= 0) {
        return NULL; // return null when there's an invalid input
    }
    BLOCK_HEADER* current = first_header; 
    // int block_size = current->size_alloc & 0xFFFE; 
    int padding = 0; 
    int total_block_size = size + 8; // add the block header size to size 
    while(total_block_size + padding % 16) { // add padding to make size a multiple of 16; also splits it somehow 
        padding++; 
    }
    total_block_size += padding; // make total_block_size = size + padding + header_size
    while((current->size_alloc) != 1) { // go till the end of all blocks
        if((current->size_alloc&1) == 0) { // if the block is free
            if((current->size_alloc&0xFFFFFFFE) >= total_block_size) { // if the current block of memory is greater than the size 
                int total_alloc = current->size_alloc; 
                current->size_alloc = total_block_size + 1; // allocate the values c
                if((total_alloc - current->size_alloc - 1) >= 16) {
                    BLOCK_HEADER* next_header = (BLOCK_HEADER*) ((unsigned long)current + total_block_size);
                    next_header->size_alloc = total_alloc - current->size_alloc - 1;
                    next_header->payload = total_alloc - current->size_alloc - 1 - 8 ;
                }  
                return (void*) (current + 1); 
            }
        }

        current = (BLOCK_HEADER*) ((unsigned long)current + current->size_alloc);
    }
    
    return NULL;
}


// for inside the while loop 

// set to size 
// do some next header shitjjjjjq



// return 0 on success
// return -1 if the input ptr was invalid
int Mem_Free(void *ptr){
    // traverse the list and check all pointers to find the correct block 
    // if you reach the end of the list without finding it return -1
    
    // free the block 

    // coalesce adjacent free blocks

    return -1;
}

