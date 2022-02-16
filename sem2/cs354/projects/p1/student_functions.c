/* name
 * uw net Id
*/

/* This function takes a string as input and removes 
 * leading and trailing whitespace including spaces
 * tabs and newlines. It also removes multiple internal
 * spaces in a row. Arrays are passed by reference.
 */

#include <stdio.h>
#include <stdlib.h>
#include "student_functions.h"

void Clean_Whitespace(char str[]) {
    // do your work here
    int index = 0;
    int i = 0; 
    // jump to the first word
    while(str[index] == ' ' || str[index] == '\t' || str[index] == '\n') {
        index++; 
    }
    // get rid of the leading spaces 
    while(str[i + index] != '\0')
    {
        str[i] = str[i + index];
        i++;
    }
    str[i] = '\0';
    int j = 0; 
    // get rid of whitespace between the words 
    for(i = 0; str[i]; i++) {
        if(str[i] != ' ' || str[i-1] != ' ') {
            str[j++] = str[i];
        }
    }
    str[j] = '\0';
    // get rid of trailing whitespace
    while(str[j] == ' ' || str[j] == '\t' || str[j] == '\n') {
        j--; 
    }
    str[j+1] = '\0'; 
    return;
}

/* This function takes a string and makes the first
 * letter of every word upper case and the rest of the
 * letters lower case
 */ 
void Fix_Case(char str[]) {
    // do your work here
    if(str[0] >= 97 && str[0] <= 122) {
        str[0] -= 32; 
    }
    int i = 1; 
    while(str[i] != '\0') {
        if(str[i-1] == ' ') {
            if(str[i] >= 97 && str[i] <= 122) {
                str[i]-= 32; 
            }
        }
        else {
            if(str[i] >= 65 && str[i] <= 90) {
                str[i] += 32; 
            }
        }
        i++;
    }
    return;
}

/* this function takes a string and returns the 
 * integer equivalent
 */
int String_To_Year(char str[]) {
    // do your work here
    return atoi(str);
}


/* this function takes the name of a 
 * director as a string and removes all but
 * the last name.  Example:
 * "Bucky Badger" -> "Badger"
 */
void Director_Last_Name(char str[]) {
    // do your work here
    Clean_Whitespace(str); 
    Fix_Case(str); 
    int i = 0; 
    int counter = 0; 
    for(int j = 0; str[j]; j++) {
        if(str[j - 1] == ' ') {
           i = j; 
        }
    }
    while(str[i] != '\0') {
        str[counter++] = str[i++]; 
    }
    str[counter] = '\0';
    return;
}


/* this function takes the a string and returns
 * the floating point equivalent
 */
float String_To_Rating(char str[]) {
    // do your work here
    return atof(str);
}


/* this function takes a string representing
 * the revenue of a movie and returns the decimal
 * equivalent. The suffix M or m represents millions,
 * K or k represents thousands.
* example: "123M" -> 123000000 
*/
long long String_To_Dollars(char str[])  {
    // do your work here
    char lastChar;
    int i = 0;
    while(str[i+1] != '\0') {
        lastChar = str[i];
        i++;
    }
    if(lastChar == 'm' || lastChar == 'M') {
        return (atoll(str) * 1000000); 
    }
    else if(lastChar == 'k' || lastChar == 'K') {
        return (atoll(str) * 1000); 
    }
    else {
        return atoll(str);
    }
}


/* This function takes the array of strings representing 
 * the csv movie data and divides it into the individual
 * components for each movie.
 * Use the above helper functions.
 */
void Split(char csv[10][1024], int num_movies, char titles[10][1024], int years[10], char directors[10][1024], float ratings[10], long long dollars[10]) {
    // do your work here
    char strYears[10][1024]; 
    char strFloats[10][1024]; 
    char strDollars[10][1024]; 

    for(int i = 0; i < num_movies; i++) {
        int j = 0; 
        int k = 0; 
        // title
        while(csv[i][j] != ',') {
            titles[i][k] = csv[i][j];
            j++; 
            k++;
        }
        Clean_Whitespace(titles[i]);
        Fix_Case(titles[i]);
        j++; 
        k = 0; 
        // years
        while(csv[i][j] != ',') {
            strYears[i][k] = csv[i][j];
            j++; 
            k++;
        }
        // printf(strYears[i]);
        // printf("\n");
        Clean_Whitespace(strYears[i]); 
        int year = String_To_Year(strYears[i]); 
        years[i] = year;         
        j++; 
        // skip the runtime 
        while(csv[i][j] != ',') {
            j++; 
        }
        j++; 
        k = 0; 
        // directors
        while(csv[i][j] != ',') {
            directors[i][k] = csv[i][j];
            j++; 
            k++; 
        }
        Clean_Whitespace(directors[i]); 
        Director_Last_Name(directors[i]); 
        Fix_Case(directors[i]);
        j++; 
        // floats
        k = 0; 
        while(csv[i][j] != ',') {
            strFloats[i][k] = csv[i][j];
            j++; 
            k++;
        }
        Clean_Whitespace(strFloats[i]); 
        float rating = String_To_Rating(strFloats[i]); 
        ratings[i] = rating; 
        j++; 
        // dollars
        k = 0; 
        while(csv[i][j]) {
            strDollars[i][k] = csv[i][j];
            j++; 
            k++;
        }
        Clean_Whitespace(strDollars[i]);
        long long dollar = String_To_Dollars(strDollars[i]); 
        dollars[i] = dollar; 
    }
    return;
}




/* This function prints a well formatted table of
 * the movie data 
 * Row 1: Header - use name and field width as below
 * Column 1: Id, field width = 3, left justified
 * Column 2: Title, field width = lenth of longest movie + 2 or 7 which ever is larger, left justified, first letter of each word upper case, remaining letters lower case, one space between words
 * Column 3: Year, field with = 6, left justified
 * Column 4: Director, field width = length of longest director last name + 2 or 10 (which ever is longer), left justified, only last name, first letter upper case, remaining letters lower case
 * column 5: Rating, field width = 6, precision 1 decimal place (e.g. 8.9, or 10.0), right justified
 * column 6: Revenue, field width = 11, right justified
 */
void Print_Table(int num_movies, char titles[10][1024], int years[10], char directors[10][1024], float ratings[10], long long dollars[10]) {
    // do your work here
    printf("Id "); 
    // find longest movie length
    int titleCounter = 7; 
    int counterB = 0; 
    int i = 0; 
    while(i < num_movies) {
        counterB = 0;
        int j = 0; 
        while(titles[i][j]) {
            counterB++; 
            j++;
        }
        if(counterB > titleCounter) {
            titleCounter = counterB; 
        }
        i++;
    }
    if(titleCounter == 7) {
        printf("%-7s", "Title"); 
    } else {
        titleCounter += 2; 
        printf("%-*s",titleCounter, "Title");
    }
    // year column
    printf("Year  "); 
    // director column 
    int dirCounter = 0; 
    counterB = 0; 
    i = 0; 
    while(i < num_movies) {
        counterB = 0;
        int j = 0; 
        while(directors[i][j]) {
            counterB++; 
            j++;
        }
        if(counterB > dirCounter) {
            dirCounter = counterB; 
        }
        i++;
    }
    if(dirCounter == 10) {
        printf("%-10s", "Director");
    } else {
        dirCounter += 2; 
        printf("%-*s", dirCounter, "Director");
    }
    printf("Rating"); 
    printf("    Revenue");
    printf("\n");
    // do the actual table
    for(int i = 0; i < num_movies; i++) {
        printf("%d", i+1);
        printf("  ");
        if(titleCounter == 7) {
        printf("%-7s", titles[i]); 
        } else {
        printf("%-*s",titleCounter-2, titles[i]);
        }
        printf("  ");
        printf("%d", years[i]);
        printf("  "); 
        if(dirCounter == 10) {
        printf("%-10s",directors[i]);
        } else {
        printf("%-*s", dirCounter-2, directors[i]);
        }
        if(dirCounter != 10) {
            printf("  ");
        }
        printf("%6.1f", ratings[i]);
        printf("%11ld", dollars[i]);
        printf("\n");

    }
    return;
}


