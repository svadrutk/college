////////////////////////////////////////////////////////////////////////////////
// Main File:        sendsig.c 
// This File:        sendsig.c
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
#include <sys/types.h>
#include <signal.h> 
#include <string.h> 
/* 
 * Main method that gets command-line arguments and based on that sends 
 * a signal to a PID
 */ 
int main(int argc, const char *argv[]) {
	// check for valid number of args 
	if (argc != 3) {
		printf("Invalid no. of arguments."); 
		return(0); 
	}
	// gets the two arguments 
    char sigType = argv[1][1]; 
	int pid = atoi(argv[2]); 
	// sends the signal using kill based on the two args 
	if(sigType == 'i') {
		kill(pid, SIGINT); 
	}	
    else if(sigType == 'u') {
		kill(pid, SIGUSR1); 
	}	
	else {
		printf("Wrong signal.\n"); 
	}
	return 0; 
}
