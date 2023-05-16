////////////////////////////////////////////////////////////////////////////////
// Main File:        division.c
// This File:        division.c
// Other Files:      (name of all other files if any)
// Semester:         CS 354 Lecture 002 Spring 2023
// Instructor:       deppeler
// 
// Author:           Svadrut Kukunooru
// Email:            kukunooru@wisc.edu
// CS Login:         svadrut
//
/////////////////////////// OTHER SOURCES OF HELP //////////////////////////////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide ///////////////////////////////////
#include <stdio.h> 
#include <stdlib.h> 
#include <signal.h> 
#include <sys/types.h> 
#include <limits.h> 

#define SIZE 100 
int success = 0; 

/* A signal handler that is called when main detects it
 *      CASE 1: DIVIDE BY 0. Reports number of operations successfully 
 *      completed and terminates the program
 *      CASE 2: CTRL-C TERMINATION. Does the same thing, just for a
 *      different signal. . 
 * signum Signal type value
 * s pointer to first sigaction struct 
 * d pointer to second sigaction struct
 */
static void sigHandler(int signum, siginfo_t *s, void *d) {
	switch (signum) {
		// Ctrl-C
		case SIGINT: 
			printf("\nTotal number of operatiosn successfully completed: %d\nThe program will be terminated.\n", success);
		exit(0); 
		// divide by 0 	
		case SIGFPE: 
			printf("Error: a division by 0 operation was attempted.\nTotal number of operations completed successfully: %d\nThe program will be terminated.\n", success); 
			exit(0); 
	}
}

/* 
 * Runs an infinite loop that constantly asks the user for input (num1 
 * and num2), divides them, and returns the quotient and remainder. If
 * there is a divide by 0 or Ctrl-C detected, it calls the signal handler. 
 */
int main (int argc, const char *argv[]) {
	// creates the two signal action function
	struct sigaction act, oact; 
	// used to help with processing input 
	char buff[SIZE]; 
	// all the variables needed to calculate division 
	int num1, num2, quotient, remainder; 
	// sets the flags 
	act.sa_flags = SA_SIGINFO | SA_ONSTACK | SA_RESTART; 
	// sets the signal handler function used to sigHandler
	act.sa_sigaction = sigHandler; 
	// makes sure that the function we wrote processes these two signals	
	sigaction(SIGINT, &act, &oact); 
	sigaction(SIGFPE, &act, &oact); 
    // loop that gets the values, divides them, and prints out the 
    // quotient and remainder. 	
	while (1) {
		printf("Enter first integer: "); 
		if(fgets(buff, SIZE, stdin) != NULL) {
			num1 = atoi(buff);
		}
		printf("Enter second integer: "); 
		if(fgets(buff, SIZE, stdin) != NULL) {
			num2 = atoi(buff); 
		}
		
		quotient = num1 / num2; 
		remainder = num1 % num2; 
		printf("%d / %d is %d with a remainder of %d\n", num1, num2, quotient, remainder);
		// increments the success counter. 
		success++; 
	}

	return 0; 

}

