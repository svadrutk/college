#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define ASCII_RANGE 128

typedef struct word {
  char *contents;
  int numChars;
  int frequency;
  int orderAppeared;
  struct word *nextWord;
  struct word *prevWord;
} WORD;

void ErrorChecker(int argc, char *argv[], FILE * output) {
  // Error Handling
  // check if there are enough arguments
  if (argc < 3) {
    fprintf(output,"USAGE:\n\t./MADCounter -f <input file> -o <output file> -c -w -l "
           "-Lw -Ll\n\t\tOR\n\t./MADCounter -B <batch file>\n");
    exit(1);
  }
  // check if all arguments are valid
  int hasInputFlag = 0;
  char *checker = NULL;
  FILE *fp = NULL;
  for (int i = 1; i < argc; i++) {
    if (strcmp(argv[i], "-c") == 0 || strcmp(argv[i], "-w") == 0 ||
        strcmp(argv[i], "-l") == 0 || strcmp(argv[i], "-Lw") == 0 ||
        strcmp(argv[i], "-Ll") == 0) {
      continue;
    } else if (strcmp(argv[i], "-f") == 0) {
      checker = argv[i + 1];
      if (checker[0] == '-') {
        fprintf(output,"ERROR: No Input File Provided\n");
        exit(1);
      } else {
        i++;
        hasInputFlag = 1;
        continue;
      }
    } else if (strcmp(argv[i], "-o") == 0) {
      checker = argv[i + 1];
      if (checker[0] == '-') {
        fprintf(output,"ERROR: No Output File Provided\n");
        exit(1);
      } else {
        i++;
        continue;
      }
    } else if (strcmp(argv[i], "-B") == 0) {
      i++;
      hasInputFlag = 1;
      continue;
    } else {
      fprintf(output,"ERROR: Invalid Flag Types\n");
      exit(1);
    }
  }
  if (hasInputFlag == 0) {
    fprintf(output,"ERROR: No Input File Provided\n");
    exit(1);
  }
  if (strcmp(argv[1], "-B") == 0) {
    char *file = argv[2];
    fp = fopen(file, "r");
    // check if batch file exists
    if (fp == NULL) {
      fprintf(output,"ERROR: Can't open batch file\n");
      exit(1);
    } else {
      // check if batch file is empty
      fseek(fp, 0, SEEK_END);
      int size = ftell(fp);

      if (size == 0) {
        fprintf(output,"ERROR: Batch File Empty\n");
        fclose(fp);
        exit(1);
      }
      fclose(fp);
    }
  } else if (strcmp(argv[1], "-f") == 0) {
    char *file = argv[2];
    fp = fopen(file, "r");
    // check if input file exists
    if (fp == NULL) {
      fprintf(output,"ERROR: Can't open input file\n");
      exit(1);
    } else { // check if file is empty
      fseek(fp, 0, SEEK_END);
      int size = ftell(fp);

      if (size == 0) {
        fprintf(output,"ERROR: Input File Empty\n");
        fclose(fp);
        exit(1);
      }
      fclose(fp);
    }
  }
}

int ErrorCheckerWithoutExiting(int argc, char *argv[], FILE * output) {
  // Error Handling
  // check if there are enough arguments
  if (argc < 3) {
    fprintf(output,"USAGE:\n\t./MADCounter -f <input file> -o <output file> -c -w -l "
           "-Lw -Ll\n\t\tOR\n\t./MADCounter -B <batch file>\n");
    return 1;
  }
  // check if all arguments are valid
  int hasInputFlag = 0;
  char *checker;
  for (int i = 1; i < argc; i++) {
    if (strcmp(argv[i], "-c") == 0 || strcmp(argv[i], "-w") == 0 ||
        strcmp(argv[i], "-l") == 0 || strcmp(argv[i], "-Lw") == 0 ||
        strcmp(argv[i], "-Ll") == 0) {
      continue;
    } else if (strcmp(argv[i], "-f") == 0) {
      checker = argv[i + 1];
      if (checker[0] == '-') {
        fprintf(output,"ERROR: No Input File Provided\n");
        return 1;
      } else {
        i++;
        hasInputFlag = 1;
        continue;
      }
    } else if (strcmp(argv[i], "-o") == 0) {
      checker = argv[i + 1];
      if (checker[0] == '-') {
        fprintf(output,"ERROR: No Output File Provided\n");
        return 1;
      } else {
        i++;
        continue;
      }
    } else if (strcmp(argv[i], "-B") == 0) {
      i++;
      hasInputFlag = 1;
      continue;
    } else {
      fprintf(output,"ERROR: Invalid Flag Types\n");
      return 1;
    }
  }
  if (hasInputFlag == 0) {
    fprintf(output,"ERROR: No Input File Provided\n");
    return 1;
  }
  if (strcmp(argv[1], "-B") == 0) {
    char *file = argv[2];
    FILE *fp = fopen(file, "r");
    // check if batch file exists
    if (fp == NULL) {
      fprintf(output,"ERROR: Can't open batch file\n");
      return 1;
    } else {
      // check if batch file is empty
      fseek(fp, 0, SEEK_END);
      int size = ftell(fp);

      if (size == 0) {
        fprintf(output,"ERROR: Batch File Empty\n");
        fclose(fp);
        return 1;
      }
      fclose(fp);
    }
  } else if (strcmp(argv[1], "-f") == 0) {
    char *file = argv[2];
    FILE *fp = fopen(file, "r");
    // check if input file exists
    if (fp == NULL) {
      fprintf(output,"ERROR: Can't open input file\n");
      return 1;
    } else { // check if file is empty
      fseek(fp, 0, SEEK_END);
      int size = ftell(fp);

      if (size == 0) {
        fprintf(output,"ERROR: Input File Empty\n");
        fclose(fp);
        return 1;
      }
      fclose(fp);
    }
  }
  return 0;
}

void ASCIIProcessor(char *file, FILE * output) {
  FILE *fp = fopen(file, "r");
  // get total number of characters
  int totalChars = 0;
  int currentChar = 0;
  while ((currentChar = fgetc(fp)) != EOF) {
    totalChars++;
  }
  fclose(fp);
  fprintf(output,"Total Number of Chars = %d\n", totalChars);
  /////////////////////////////
  // get total unique characters
  int uniqueChars = 0;
  int asciiArray[ASCII_RANGE] = {0};
  fp = fopen(file, "r");
  while ((currentChar = fgetc(fp)) != EOF) {
    asciiArray[currentChar]++;
  }
  fclose(fp);
  for (int i = 0; i < ASCII_RANGE; i++) {
    if (asciiArray[i] != 0) {
      uniqueChars++;
    }
  }
  fprintf(output,"Total Unique Chars = %d\n", uniqueChars);
  /////////////////////////////
  int charTracker[ASCII_RANGE][2];
  memset(charTracker, -1, sizeof(charTracker));
  int charTrackerIndex = -1;
  fp = fopen(file, "r");
  while ((currentChar = fgetc(fp)) != EOF) {
    charTrackerIndex++;
    int asciiVal = (int)currentChar;
    if (asciiVal < 0 || asciiVal > 127) {
      fprintf(output,"ERROR: Detecting Ascii Character %d at position %d\n", asciiVal,
             charTrackerIndex);
      continue;
    }
    charTracker[asciiVal][0]++;
    if (charTracker[asciiVal][1] == -1) {
      charTracker[asciiVal][1] = charTrackerIndex;
    }
  }
  fprintf(output,"\n");
  /////////////////////////////
  for (int i = 0; i < ASCII_RANGE; i++) {
    if (charTracker[i][0] != -1) {
      fprintf(output,"Ascii Value: %d, Char: %c, Count: %d, Initial Position: %d\n", i,
             (char)i, charTracker[i][0] + 1, charTracker[i][1]);
    }
  }
  fclose(fp);
}

WORD *WordProcessor(char *file) {
  FILE *fp = fopen(file, "r");
  char word[256];
  int wordIndex = 0;
  WORD *head;
  while (fscanf(fp, "%s", word) == 1) {
    WORD *newWord = (WORD *)malloc(sizeof(WORD));
    newWord->contents = (char *)malloc(sizeof(char) * strlen(word));
    strcpy(newWord->contents, word);
    newWord->numChars = strlen(word);
    newWord->frequency = 1;
    newWord->orderAppeared = wordIndex;
    newWord->nextWord = NULL;
    newWord->prevWord = NULL;
    if (wordIndex == 0) {
      head = newWord;
    } else if (wordIndex == 1) {
      if (strcmp(head->contents, word) == 0) {
        head->frequency++;
        free(newWord->contents);
        free(newWord);

      } else if (strcmp(word, head->contents) < 0) {
        newWord->nextWord = head;
        head->prevWord = newWord;
        head = newWord;
      } else {
        head->nextWord = newWord;
        newWord->prevWord = head;
      }
    } else {
      int done = 0;
      WORD *currentWord = head;
      while (currentWord != NULL) {
        done = 0;
        if (strcmp(currentWord->contents, word) == 0) {
          currentWord->frequency++;
          free(newWord->contents);
          free(newWord);
          done = 1;

          break;
        } else if (strcmp(word, currentWord->contents) < 0) {
          newWord->prevWord = currentWord->prevWord;
          currentWord->prevWord = newWord;
          newWord->nextWord = currentWord;
          if (newWord->prevWord != NULL) {
            newWord->prevWord->nextWord = newWord;
          } else {
            head = newWord;
          }
          done = 1;
          break;
        }
        if (currentWord->nextWord != NULL) {
          currentWord = currentWord->nextWord;
        } else {
          break;
        }
      }
      if (done == 1) {
        wordIndex++;
        continue;
      } else {
        currentWord->nextWord = newWord;
        newWord->prevWord = currentWord;
      }
    }
    wordIndex++;
  }
  fclose(fp);
  return head;
}
void printWords(WORD *head, FILE * output) {
  // get total number of words
  int totalWords = 0;
  int uniqueWords = 0;
  WORD *currentWord = head;
  while (currentWord != NULL) {
    totalWords += currentWord->frequency;
    uniqueWords++;
    currentWord = currentWord->nextWord;
  }
  fprintf(output,"Total Number of Words: %d\n", totalWords);
  fprintf(output,"Total Unique Words: %d\n", uniqueWords);
  fprintf(output,"\n");
  // print out words
  currentWord = head;
  while (currentWord != NULL) {
    fprintf(output,"Word: %s, Freq: %d, Initial Position: %d\n", currentWord->contents,
           currentWord->frequency, currentWord->orderAppeared);
    currentWord = currentWord->nextWord;
  }
}

void freeWord(WORD *word) {
    free(word->contents);
    free(word);
}


WORD *LineProcessor(char *file) {
  FILE *fp = fopen(file, "r");
  char line[256];
  int lineIndex = 0;
  WORD *head;
  while (fgets(line, sizeof(line), fp) != NULL) {
    WORD *newLine = (WORD *)malloc(sizeof(WORD));
    newLine->contents = (char *)malloc(sizeof(char) * strlen(line));
    line[strlen(line) - 1] = '\0'; // remove newline character from fgets
    strcpy(newLine->contents, line);
    newLine->numChars = strlen(line);
    newLine->frequency = 1;
    newLine->orderAppeared = lineIndex;
    newLine->nextWord = NULL;
    newLine->prevWord = NULL;
    if (lineIndex == 0) {
      head = newLine;
    } else if (lineIndex == 1) {
      if (strcmp(head->contents, line) == 0) {
        head->frequency++;
        free(newLine->contents);
        free(newLine);

      } else if (strcmp(line, head->contents) < 0) {
        newLine->nextWord = head;
        head->prevWord = newLine;
        head = newLine;
      } else {
        head->nextWord = newLine;
        newLine->prevWord = head;
      }
    } else {
      int done = 0;
      WORD *currentLine = head;
      while (currentLine != NULL) {
        done = 0;
        if (strcmp(currentLine->contents, line) == 0) {
          currentLine->frequency++;
          free(newLine->contents);
          free(newLine);

          done = 1;
          break;
        } else if (strcmp(line, currentLine->contents) < 0) {
          newLine->prevWord = currentLine->prevWord;
          currentLine->prevWord = newLine;
          newLine->nextWord = currentLine;
          if (newLine->prevWord != NULL) {
            newLine->prevWord->nextWord = newLine;
          } else {
            head = newLine;
          }
          done = 1;
          break;
        }
        if (currentLine->nextWord != NULL) {
          currentLine = currentLine->nextWord;
        } else {
          break;
        }
      }
      if (done == 1) {
        lineIndex++;
        continue;
      } else {
        currentLine->nextWord = newLine;
        newLine->prevWord = currentLine;
      }
    }
    lineIndex++;
  }
  fclose(fp);
  return head;
}

void freeLine(WORD *line) {
    free(line->contents);
    free(line);
}

void printLines(WORD *head, FILE * output) {
  // get total number of words
  int totalLines = 0;
  int uniqueLines = 0;
  WORD *currentLine = head;
  while (currentLine != NULL) {
    totalLines += currentLine->frequency;
    uniqueLines++;
    currentLine = currentLine->nextWord;
  }
  fprintf(output,"Total Number of Lines: %d\n", totalLines);
  fprintf(output,"Total Unique Lines: %d\n", uniqueLines);
  fprintf(output,"\n");
  // print out words
  currentLine = head;
  while (currentLine != NULL) {
    fprintf(output,"Line: %s, Freq: %d, Initial Position: %d\n", currentLine->contents,
           currentLine->frequency, currentLine->orderAppeared);
    currentLine = currentLine->nextWord;
  }
}

void longestWords(WORD *head, FILE * output) {
  WORD *currentWord = head;
  int longestWordLength = 0;
  while (currentWord != NULL) {
    if (currentWord->numChars > longestWordLength) {
      longestWordLength = currentWord->numChars;
    }
    currentWord = currentWord->nextWord;
  }
  fprintf(output,"Longest Word is %d characters long:\n", longestWordLength);
  currentWord = head;
  while (currentWord != NULL) {
    if (currentWord->numChars == longestWordLength) {
      fprintf(output,"\t%s\n", currentWord->contents);
    }
    currentWord = currentWord->nextWord;
  }
}

void longestLines(WORD *head, FILE * output) {
  WORD *currentLine = head;
  int longestLineLength = 0;
  while (currentLine != NULL) {
    if (currentLine->numChars > longestLineLength) {
      longestLineLength = currentLine->numChars;
    }
    currentLine = currentLine->nextWord;
  }
  fprintf(output,"Longest Line is %d characters long:\n", longestLineLength);
  currentLine = head;
  while (currentLine != NULL) {
    if (currentLine->numChars == longestLineLength) {
      fprintf(output,"\t%s\n", currentLine->contents);
    }
    currentLine = currentLine->nextWord;
  }
}

void CodeRunner(int argc, char *argv[], int batchTrue) {
  // get input and output files
  char *inputFile = NULL;
  char *outputFile = NULL;
  for (int i = 1; i < argc; i++) {
    if (strcmp(argv[i], "-f") == 0) {
      inputFile = argv[i + 1];
      i++;
      continue;
    } else if (strcmp(argv[i], "-o") == 0) {
      outputFile = argv[i + 1];
      i++;
      continue;
    }
  }
  FILE * output;
  if (outputFile == NULL) {
    output = stdout;
  }
  else {
    output = fopen(outputFile, "w");
  }
  int res = 0;
  if (batchTrue == 0) {
    ErrorChecker(argc, argv, output);
  } else {
    res = ErrorCheckerWithoutExiting(argc, argv, output);
  }
  if (res == 1) {
    return;
  }

  // run code
  WORD *words = NULL;
  WORD *lines = NULL;
  int atLeastOneFlag = 0;
  for (int i = 1; i < argc; i++) {
    if (strcmp(argv[i], "-c") == 0) {
      if (atLeastOneFlag == 1) {
        fprintf(output,"\n");
      }
      ASCIIProcessor(inputFile, output);
      atLeastOneFlag = 1;
      continue;
    } else if (strcmp(argv[i], "-w") == 0) {
      if (atLeastOneFlag == 1) {
        fprintf(output,"\n");
      }
      if (words == NULL) {
        words = WordProcessor(inputFile);
      }
      printWords(words, output);
      atLeastOneFlag = 1;
      // free memory
      continue;
    } else if (strcmp(argv[i], "-l") == 0) {
      if (atLeastOneFlag == 1) {
        fprintf(output,"\n");
      }
      if (lines == NULL) {
        lines = LineProcessor(inputFile);
      }
      printLines(lines, output);

      atLeastOneFlag = 1;

      continue;
    } else if (strcmp(argv[i], "-Lw") == 0) {
      if (atLeastOneFlag == 1) {
        fprintf(output,"\n");
      }
      if (words == NULL) {
        words = WordProcessor(inputFile);
      }
      longestWords(words, output);
      atLeastOneFlag = 1;

      continue;
    } else if (strcmp(argv[i], "-Ll") == 0) {
      if (atLeastOneFlag == 1) {
        fprintf(output,"\n");
      }
      if (lines == NULL) {
        lines = LineProcessor(inputFile);
      }
      longestLines(lines, output);
      atLeastOneFlag = 1;
      continue;
    }
  }
  // free memory
  if (words != NULL) {
    WORD *currentWord = words;
    while (currentWord != NULL) {
        WORD *temp = currentWord;
        currentWord = currentWord->nextWord;
        freeWord(temp);
    }
  }
  if (lines != NULL) {
    WORD *currentLine = lines;
    while (currentLine != NULL) {
      WORD *temp = currentLine;
      currentLine = currentLine->nextWord;
      freeLine(temp);
    }
  }
  if (outputFile != NULL) {
    fclose(output);
  }
}

int main(int argc, char *argv[]) {
  ErrorChecker(argc, argv, stdout);

  if (strcmp(argv[1], "-B") == 0) {
    FILE *fp = fopen(argv[2], "r");
    char line[256];
    while (fgets(line, sizeof(line), fp) != NULL) {
      // set last non-white space character to null
      int a = strlen(line) - 1;
      while (line[a] == ' ' || line[a] == '\t' || line[a] == '\n') {
        line[a] = '\0';
        a--;
      }
      char *token = strtok(line, " ");
      char *args[10];
      args[0] = "./MADCounter";
      int i = 1;
      while (token != NULL) {
        args[i] = token;
        token = strtok(NULL, " ");
        i++;
      }
      // print out args
      CodeRunner(i, args, 1);
    }
    fclose(fp);
  } else {
    CodeRunner(argc, argv, 0);
  }
  return 0;
}
