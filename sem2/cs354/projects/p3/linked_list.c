// do not include other libraries
#include <stdio.h>
#include <stdlib.h>

// ***************************************
// *** struct definitions ****************
// *** do not change these ***************
// ***************************************
typedef struct NODE {int data; struct NODE* next;} NODE; // nodes of the linked list
typedef struct LINKED_LIST {struct NODE *head;} LINKED_LIST; // struct to act as the head of the list


// ***************************************
// *** provided functions  ****************
// ***************************************

// this function returns a LINKED_LIST struct to
// act as the head of the linked list.
// do not change this function
LINKED_LIST Create_List(void) {LINKED_LIST list = {NULL}; return list;}

// call this function to verify malloc worked when you create new nodes
void Verify_Malloc(NODE *node) {if (node == NULL) {printf("Malloc Failed\n"); exit(1);} return;}

// do not change this function
// this function prints out all of the nodes in the linked list
void Print_List(FILE *out, LINKED_LIST list) {
    if (list.head == NULL) {printf("\n"); return;} //empty list
    NODE *current = list.head;
    while (current->next != NULL) {
        fprintf(out, "%d -> ",current->data);
        current = current->next;
    }
    fprintf(out, "%d\n",current->data);
    return;
}

// ******************************************************
// *** Complete the following functions  ****************
// ******************************************************

// this function returns the number 
// of elements in the linked list
int Size(LINKED_LIST list){
    int counter = 0; // counter 
    if(list.head == NULL) {
        return counter;
    }
    else {
        struct NODE *p = list.head; // pointer to start of linked list
        while(p != NULL) { // while the current element isn't null
            counter++; // counter + 1 
            p = p->next; // go to next element of linked list
        }
        return counter; // return counter
    }
}
// this function adds an element to
// the front of the list
void Push_Front(LINKED_LIST *list, int data){
        struct NODE* new = (struct NODE*) malloc(sizeof(struct NODE)); // allocate memory to the new node 
        new->data = data; // make the data in node the one from the parameter
        struct NODE* next = list->head; // workaround cause C is dumb 
        new->next = next; // make the the thing the node points to the beginning of the list
        list->head = new; // bring the head back one. 
    return;
}

// this function adds an element 
// to the end of the linked list 
void Push_Back(LINKED_LIST *list, int data) {
    if(list->head == NULL) {
        Push_Front(list, data);   
        return;
    }
    else {
        struct NODE *p = list->head; // point to beginning of list 
        while(p->next != NULL) { // go to the last element of the list 
            p = p->next; 
        }
        struct NODE* new = (struct NODE*) malloc(sizeof(struct NODE)); // make a new node 
        new->data = data; // put the data in the new node 
        p->next = new; // add to the end of the lsit 
    }
    return;
}


// if the list is not empty
// the value of the first element of the list is returned by reference in the parameter data
// the first element of the list is deleted
// returns 0 if the list is empty otherwise returns 1
// remember to free the deleted node
int Pop_Front(LINKED_LIST *list, int *data) {
    if(list->head != NULL) {
        *data = list->head->data;
        struct NODE *temp = list->head; 
        list->head = list->head->next;
        free(temp);
        return 1;
    }
    return 0;
}


// if the list is not empty
// the value of the last element of the list is returned by reference in the parameter data
// the last element of the list is deleted
// returns 0 if the list is empty otherwise returns 1
// remember to free the deleted node
int Pop_Back(LINKED_LIST *list, int *data) {
   if(list->head != NULL) {
       struct NODE *secondLast = list->head;
       while(secondLast->next->next != NULL) {
           secondLast = secondLast->next;
       }
       *data = secondLast->next->data;
       struct NODE* temp = secondLast->next;
       secondLast->next = NULL;
       free(temp);
       return 1;
   }
   return 0;
}

// this function returns the number 
// of times that the value of the parameter 
// data appears in the list
int Count_If(LINKED_LIST list, int data) {
    int counter = 0; 
        struct NODE *p = list.head;
        while(p != NULL) {
            if(p->data == data) {
                counter++;
            }
            p = p->next;
        }
        return counter;
}

// delete the first node containing the data value
// return 1 if something was deleted otherwise 0
// remember to free the deleted node
int Delete(LINKED_LIST *list, int data) {
    if(list->head != NULL) {
        struct NODE *p = list->head; 
        while(p != NULL) {
            if(p->next->data == data) {
                struct NODE *temp = p->next;
                p->next = p->next->next;
                free(temp);
                return 1;
            }
            p = p->next;
        }
    }
    return 0;
}

// return 1 if the list is empty otherwise returns 0
int Is_Empty(LINKED_LIST list) {
    if(list.head == NULL) {
        return 1;
    }
    return 0;
}

// delete all elements of the list
// remember to free the nodes
void Clear(LINKED_LIST *list) {
    struct NODE *p = list->head; 
    struct NODE *n; 
    if(list->head != NULL) {
        while(p != NULL) {
            n = p->next;
            free(p);
            p = n;
        }
        list->head = NULL;
    }
    return;
}


// if an element appears in the list 
// retain the first occurance but
// remove all other nodes with the same 
// data value
void Remove_Duplicates(LINKED_LIST *list) {
    // copy the list
    
        int size = Size(*list);  
        LINKED_LIST newList = Create_List(); 
        NODE *p = list->head; 
        while(p != NULL) {
            if(Count_If(newList, p->data) != 0) {
                p = p->next; 
            }
            else {
                Push_Back(&newList, p->data);
                p = p->next;
            }
        }
        *list = newList;
    
    return;
}

// modify main to completely test your functions 
int main() {
    // create a linked list
    printf("creating linked list\n");
    LINKED_LIST list = Create_List();
   
    // add some data (hardcoded for testing)
    printf("hardcoding some data\n");
    NODE *first  = malloc(sizeof(NODE)); Verify_Malloc(first);
    NODE *second = malloc(sizeof(NODE)); Verify_Malloc(second);
    first->data  = 1;
    second->data = 2;
    list.head = first;
    first->next = second;
    second->next = NULL;
    // print the list
    printf("Testing Print_List\n");
    Print_List(stdout, list);
    // write a better test for Size
    printf("Testing Size\n");
    printf("size = %d\n",Size(list));
    // write a better test for Push_Front
    printf("Testing Push_Front\n");
    Push_Front(&list, 0);
    Print_List(stdout, list);
    
    // write a better test for Push_Back
    printf("Testing Push_Back\n");
    Push_Back(&list, 3);
    Print_List(stdout, list);
    // write a better test for Pop_Front
    printf("Testing Pop_Front\n");
    {
        int x; 
        int not_empty = Pop_Front(&list, &x);
        if (not_empty) {
            printf("Element popped was %d\n",x);
            Print_List(stdout,list);
            printf("size = %d\n",Size(list));
        }
        else 
            printf("List was empty\n");
    }
    // write a better test for Pop_Back
    printf("Testing Pop_Back\n");
    {
        int x; 
        int not_empty = Pop_Back(&list, &x);
        if (not_empty) {
            printf("Element popped was %d\n",x);
            Print_List(stdout,list);
            printf("size = %d\n",Size(list));
        }
        else 
            printf("List was empty\n");
    }
    // write a beter test for Count_If
    Push_Front(&list, 5);
    Push_Front(&list, 5);
    Print_List(stdout, list);
    printf("5 count = %d\n",Count_If(list, 5));
    
    // write a test for Delete 
    printf("Testing Delete\n");
    Print_List(stdout, list);
    Delete(&list, 1); 
    Print_List(stdout, list);
    // write a better test for Is_Empty
    printf("Testing Is_Empty\n");
    if (Is_Empty(list)) printf("List is Empty\n"); else printf("List is not empty\n");
    
    // write a better test for Clear
    Clear(&list);
    if (Is_Empty(list)) printf("List is Empty\n"); else printf("List is not empty\n");
    // write a better test for Remove_Duplicates
    Push_Back(&list, 1);
    Push_Back(&list, 1);
    Push_Back(&list, 1);
    Push_Back(&list, 2);
    Push_Back(&list, 2);
    Push_Back(&list, 3);
    Push_Back(&list, 3);
    Push_Back(&list, 3);
    Remove_Duplicates(&list);
    Print_List(stdout, list);
    return 0;
}
