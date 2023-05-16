////////////////////////////////////////////////////////////////////////////////
// Main File:        mySigHandler.c
// This File:        mySigHandler.c
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

void handler_SIGALRM() {
	int pid = getpid();
	time_t tm;
	time(&tm);
	printf("PID: %d CURRENT TIME: %s", pid, ctime(&tm));
	alarm(4);	
	return;
}

void handler_SIGUSR1() {
	count++;
	printf("SIGUSR1 handled and counted!\n");
	return;
}

void handler_SIGINT() {
	printf("SIGINT handled.\nSIGUSR1 was handled %d times. Exiting now.\n", count);
	exit(0);
}

int main(){
	// SET ALARM
	alarm(4);

	// REGISTER SIG HANDLER
	struct sigaction sa;
	memset(&sa, 0, sizeof(sa));
	sa.sa_handler = handler_SIGALRM;
	if (sigaction(SIGALRM, &sa, NULL) != 0){
		printf("Error binding SIGALRM handler\n");
		exit(1);
	}

	struct sigaction saU1;
	memset(&saU1, 0, sizeof(saU1));
	saU1.sa_handler = handler_SIGUSR1;
	if (sigaction(SIGUSR1, &saU1, NULL) != 0){
		printf("Error binding SIGUSR1 handler\n");
		exit(1);
	}
	
	struct sigaction saINT;
	memset(&saINT, 0, sizeof(saINT));
	saINT.sa_handler = handler_SIGINT;
	if (sigaction(SIGINT, &saINT, NULL) != 0){
		printf("Error binding SIGINT handler\n");
		exit(1);
	}

	printf("PID and time print every 4 seconds.\nType Ctrl-C to end the program.\n");
	while(1){
	}
}
