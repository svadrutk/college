////////////////////////////////////////////////////////////////////////////////
// Main File:        division.c
// This File:        division.c
// Other Files:      
// Semester:         CS 354 Spring 2023
// Instructor:       Deb Deppeler
//
// Author:           Edmund Tan
// Email:            etan9@wisc.edu
// CS Login:         edmund
// GG#:              11
//                   (See https://canvas.wisc.edu/groups for your GG number)
//
/////////////////////////// OTHER SOURCES OF HELP //////////////////////////////
//                   Fully acknowledge and credit all sources of help,
//                   including family, friencs, classmates, tutors,
//                   Peer Mentors, TAs, and Instructor.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   Avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of
//                   of any information you find.
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//
// Copyright 2013,2019-2020
// Posting or sharing this file is prohibited, including any changes/additions.
// Used by permission for Spring 2023
//
////////////////////////////////////////////////////////////////////////////////
#include <signal.h>
#include <sys/types.h>
#include <string.h>
#include <unistd.h>
#include <time.h>
#include <stdlib.h>
#include <stdio.h>

int count = 0;

void handler_SIGFPE() {
    printf("Error: a division by 0 operation was attempted.\nTotal number of operations completed successfully: %d\nThe program will be terminated.\n", count);
    exit(0);
}

void handler_SIGINT() {
    printf("\nTotal number of operations completed successfully: %d\nThe program will be terminated.\n", count);
    exit(0);
}
int main(int argc, char *argv[]){
	char int1[100];
	char int2[100];
	int num;
	int denom;
	
	struct sigaction saFPE;
    memset(&saFPE, 0, sizeof(saFPE));
    saFPE.sa_handler = handler_SIGFPE;
    if (sigaction(SIGFPE, &saFPE, NULL) != 0){
        printf("Error binding SIGFPE handler\n");
        exit(1);
    }

    struct sigaction saINT;
    memset(&saINT, 0, sizeof(saINT));
    saINT.sa_handler = handler_SIGINT;
    if (sigaction(SIGINT, &saINT, NULL) != 0){
        printf("Error binding SIGINT handler\n");
        exit(1);
    }

	while(1){
		printf("Enter first integer: ");
		fgets(int1, 100, stdin);

		printf("Enter second integer: ");
		fgets(int2, 100, stdin);

		num = atoi(int1);
		denom = atoi(int2);
		printf("%d / %d is %d with a remainder of %d\n", num, denom, num / denom, num % denom);
		count++;	
	}
}
