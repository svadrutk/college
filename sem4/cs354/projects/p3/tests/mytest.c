// first pointer returned is 8-byte aligned
#include <assert.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdio.h>
#include "p3Heap.h"

void wait()
{
	//wait for user input
	int x;
	fprintf(stdout, "enter integer to continue\n");
	scanf("%d", &x);
}

int main() {
    assert(init_heap(4096) == 0);
	
	//start tests
	disp_heap();
	fprintf(stdout, "Display heap at start to compare\n\n\n\n");
	wait();

	void* ptr1;
	void* ptr2;
	void* ptr3;
	void* ptr4;
	void* ptr5;

	fprintf(stdout, "Alloc blocks of size 4, 31, 48, 15, and 7\n");
	ptr1 = balloc(4);
	ptr2 = balloc(31);
	ptr3 = balloc(48);
	ptr4 = balloc(15);
	ptr5 = balloc(7);
	disp_heap();
	fprintf(stdout, "Heap should have blocks of 8, 40, 56, 24, and 16 in that order\n\n\n\n");
	wait();
	
	fprintf(stdout, "Free ptr1 and ptr3\n");
	bfree(ptr1);
	bfree(ptr3);
	disp_heap();
	fprintf(stdout, "Should have free block of size 8 and size 56 seperated by alloc'd block of size 40\n\n\n\n");
	wait();
	
	fprintf(stdout, "Coalesce\n");
	coalesce();
	disp_heap();
	fprintf(stdout, "Heap shouldn't change\n\n\n\n");
	wait();
	
	fprintf(stdout, "Alloc ptr1 to size 8 and ptr3 to size 1\n");
	ptr1 = balloc(8);
	ptr3 = balloc(1);
	disp_heap();
	fprintf(stdout, "Block of size 56 should be split by block of size 16 and first block should be alloc'd\n\n\n\n");
	wait();
	
	fprintf(stdout, "Free ptr2, ptr4, and ptr5\n");
	bfree(ptr2);
	bfree(ptr4);
	bfree(ptr5);
	disp_heap();
	fprintf(stdout, "All but block of size 8 and block of size 16 should be freed\n\n\n\n");
	wait();

	fprintf(stdout, "Coalesece\n");
	coalesce();
	disp_heap();
	fprintf(stdout, "Heap should have block of size 8 followed by free block then block of size 16\n\n\n\n");
	wait();
	
	fprintf(stdout, "Free all pointers\n");
	bfree(ptr1);
	bfree(ptr3);
	disp_heap();
	fprintf(stdout, "Should have 3 free blocks\n\n\n\n");
	wait();

	fprintf(stdout, "Coalesce\n");	
	coalesce();
	disp_heap();
	fprintf(stdout, "Should be singular free block of size 4088\n\n\n\n");
	wait();

	fprintf(stdout, "Check if can free freed pointer\n");
	
	fprintf(stdout, "%i\n", bfree(ptr1));

	fprintf(stdout, "Check if can free nullptr\n");

	ptr2 = NULL;
	fprintf(stdout, "%i\n", bfree(ptr2));
	
    exit(0);
}
