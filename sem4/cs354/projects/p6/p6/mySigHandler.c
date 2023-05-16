////////////////////////////////////////////////////////////////////////////////
// Main File:        mySigHandler.c
// This File:        mySigHandler.c
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
#include <unistd.h> 
#include <signal.h> 
#include <time.h>
#include <string.h> 

// sets global variables for the seconds in between each alarm and a counter
int seconds = 4; 
int count = 0; 

/* Signal handler for the main method that takes SIGINT and SIGUSR1
 *
 * Pre-conditions: If main detects a SIGINT or SIGUSR1, it calls this 
 * function. 
 * signum Holds the signal type value
 * s      Pointer to the first sigaction 
 * d      Pointer to the next sigaction 
 */
static void sigHandler(int signum, siginfo_t *s, void *d) {
		// gets the time 
		time_t current; 
		// cases for each signal 
		switch (signum) {
			case SIGALRM: 
				time(&current); 
				printf("PID: %d ", getpid()); 
			    printf("CURRENT TIME: %s", ctime(&current)); // gets the
		// current time 
				alarm(seconds); 
				return; 
            case SIGINT:
				printf("\n");
				printf("SIGINT handled.\n");
				printf("SIGUSR1 was handled %d times. Exiting now.\n", count);
				exit(0);
		    case SIGUSR1: 
				count++;
				printf("SIGUSR1 handled and counted!\n");
				return;
		}
}
/* 
 * Main method runs an infinite loop that prints out the PID and current 
 * time and detects whether or not there is a signal; if there is, it 
 * passes it to the signal handler. 
 */
int main() {
    // registers a signal handler to handle SIGALRM
	struct sigaction act, oact;
	memset (&act, 0, sizeof(act));
	act.sa_flags = 0;
	act.sa_sigaction = sigHandler;
	// makes sure the signal handler runs if these three signals are passed
	sigaction(SIGINT, &act, &oact);
	sigaction(SIGUSR1, &act, &oact);
	sigaction(SIGALRM, &act, &oact);

    // sets the alarm to go off every four seconds
	alarm(seconds);
	// introduction to function 
	printf("PID and time print every 4 seconds.\nType Ctrl-C to end the program.\n");
	while(1);

}

