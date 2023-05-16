////////////////////////////////////////////////////////////////////////////////
// Main File:        sendsig.c
// This File:        sendsig.c
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

int main(int argc, char *argv[]){
	char sigType;
	int pid;
	if (argc != 3){
		printf("Usage: sendsig <signal type> <pid>\n");
	} else {
		sigType = argv[1][1];
		pid = atoi(argv[2]);
		if (sigType == 'u'){
			kill(pid, SIGUSR1);
		} else if (sigType == 'i'){
			kill(pid, SIGINT);
		}
	}
}
