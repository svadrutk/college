#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>

#define MAX_ARGS 10

int execute_command(char **args) {
    pid_t pid = fork();
    if (pid < 0) {
        perror("fork failed");
        return 1;
    } else if (pid == 0) {
        execvp(args[0], args);
        perror("execvp failed");
        exit(1);
    } else {
        wait(NULL);
        return 0;
    }
}
// linked list of commands for history
struct Node {
    char *cmd;
    struct Node *next;
    struct Node *prev;
};
int default_history_size = 5;
int history_size = 0;
struct Node *history = NULL;
char * lastCommand = NULL;
void add_to_history(char *cmd) {
    // check if command is a built-in command or a duplicate
    if(strncmp(cmd, "history", 7) == 0 || strncmp(cmd, "exit", 4) == 0 || strncmp(cmd, "cd", 2) == 0 || strncmp(cmd, "export", 6) == 0 || strncmp(cmd, "local", 5) == 0 || strncmp(cmd, "vars", 4) == 0) {
        return;
    }
    if(lastCommand != NULL && strncmp(lastCommand, cmd, strlen(cmd)) == 0) {
        return;
    }
    struct Node *new_node = (struct Node *)malloc(sizeof(struct Node));
    new_node->cmd = strdup(cmd);
    new_node->next = NULL;
    new_node->prev = NULL;

    if (history_size == 0) {
        history_size++;
        history = new_node;
    } else {
        history_size++;
        if (history_size > default_history_size) {
            // go to second to last node
            struct Node *temp = history;
            for (int i = 0; i < default_history_size - 1; i++) {
                temp = temp->next;
            }
            // free last node
            free(temp->next->cmd);
            free(temp->next);

            // set new_node to head
            new_node->next = history;
            history->prev = new_node;
            history = new_node;
            history->prev = NULL;
            history_size--;
        }
        else {
            struct Node *temp = history;
            while (temp->next != NULL) {
                temp = temp->next;
            }
            temp->next = new_node;
            new_node->prev = temp;
        }
    }
    lastCommand = cmd;
}
void shrink_history(int num) {
    struct Node *temp = history;
    for (int i = 0; i < num; i++) {
        temp = temp->next;
    }
    // free all nodes after temp
    while (temp->next != NULL) {
        temp = temp->next;
        free(temp->prev->cmd);
        free(temp->prev);
    }
    free(temp->cmd);
    free(temp);
}

int main(int argc, char *argv[]) {
    if (argc > 1) {
        printf("Usage: wsh\n");
        return 1;
    } else {
        while (1) {
            printf("wsh> ");
            char *cmd = NULL;
            char * toCopy = NULL;
            size_t size = 0;
            getline(&cmd, &size, stdin);
            if (strlen(cmd) > 0 && cmd[strlen(cmd) - 1] == '\n') {
                cmd[strlen(cmd) - 1] = '\0'; // Remove newline character
            }
            toCopy = strdup(cmd);
            //--------------------------
            // Parse command
            //--------------------------
            char *token;
            char *args[MAX_ARGS];
            int arg_count = 0;

            token = strtok(cmd, " ");
            while (token != NULL && arg_count < MAX_ARGS - 1) {
                args[arg_count++] = token;
                token = strtok(NULL, " ");
            }
            args[arg_count] = NULL;

            //--------------------------
            // Execute command
            //--------------------------
            if (strcmp(args[0], "exit") == 0) {
                break;
            } else if (strcmp(args[0], "cd") == 0) {
                if (arg_count == 2) {
                    chdir(args[1]);
                } else {
                    printf("Usage: cd [directory]\n");
                }
            } else if (strcmp(args[0], "history") == 0) {
                struct Node *temp = history;
                int i = 1;
                while (temp != NULL) {
                    printf("%d) %s\n", i, temp->cmd);
                    temp = temp->next;
                    i++;
                }
            }
            else {
                execute_command(args);
            }
            add_to_history(toCopy); // Add command to history
            free(cmd); // Free allocated memory
        }
    }

    return 0;
}
