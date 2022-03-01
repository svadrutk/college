#include "enigma.h"

const char *ROTOR_CONSTANTS[] = {
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ", // Identity Rotor (index 0 - and useful for testing):
        "EKMFLGDQVZNTOWYHXUSPAIBRCJ",
        "AJDKSIRUXBLHWTMCQGZNPYFVOE",
        "BDFHJLCPRTXVZNYEIWGAKMUSQO",
        "ESOVPZJAYQUIRHXLNFTGKDCMWB",
        "VZBRGITYUPSDNHLXAWMJQOFECK",
        "JPGVOUMFYQBENHZRDKASXLICTW",
        "NZJHGRCXMYSWBOUFAIVLPEKQDT",
        "FKQHTLXOCBJSPDZRAMEWNIUYGV",
};

// This method reads a character string from the keyboard and 
// stores the string in the parameter msg.
// Keyboard input will be entirely uppercase and spaces followed by 
// the enter key.  
// The string msg should contain only uppercase letters spaces and 
// terminated by the '\0' character
// Do not include the \n entered from the keyboard
void Get_Message(char msg[]){
    // read in data
    fgets(msg, 80, stdin); 
    // remove newline
    char* pointer = msg; 
    while(*pointer != '\n') {
        pointer++; 
    }
    *pointer = '\0';
}

// This function reads up to 4 characters from the keyboard
// These characters will be only digits 1 through 8. The user
// will press enter when finished entering the digits.
// The rotor string filled in by the function should only contain 
// the digits terminated by the '\0' character. Do not include
// the \n entered by the user. 
// The function returns the number of active rotors.
int Get_Which_Rotors(char which_rotors[]){ 
    fgets(which_rotors, 80, stdin);
    char* pt = which_rotors; 
    int i = 0; 
    while(*pt != '\n') {
        pt++; 
        i++; 
    }
    *pt = '\0';
    return i; 
} 

// This function reads an integer from the keyboard and returns it 
// This number represents the number of rotations to apply to the 
// encryption rotors.  The input will be between 0 and 25 inclusive
int Get_Rotations(){
    int a; 
    scanf("%d", &a); 
    return a;
}

// TODO 

// This function copies the rotors indicated in the which_rotors string 
// into the encryption_rotors.  For example if which_rotors contains the string 
// {'3', '1', '\0'} Then this function copies the third and first rotors into the 
// encryption rotors array in positions 0 and 1.  
// encryptions_rotors[0] = "BDFHJLCPRTXVZNYEIWGAKMUSQO"
// encryptions_rotors[1] = "EKMFLGDQVZNTOWYHXUSPAIBRCJ"
void Set_Up_Rotors(char encryption_rotors[4][27], char which_rotors[5]) {
    char *p; 
    for(p = which_rotors; *p; p++) {
        int b = *p - '0'; 
        int c; 
        strcpy(encryption_rotors[c], ROTOR_CONSTANTS[b]);
        c++;
    }
}

// This function rotates the characters  each of the active encryption rotors
// to the right by rotations.  For example if rotations is 3 encryption_rotors[0]
// contains "BDFHJLCPRTXVZNYEIWGAKMUSQO" then after rotation this row will contain
// SQOBDFHJLCPRTXVZNYEIWGAKMU.  Apply the same rotation to all for strings in 
// encryption_rotors
void Apply_Rotation(int rotations, char encryption_rotors[4][27]) {
    for(int i = 0; i < rotations; i++) {
        for(int j = 0; j < 4; j++) {
            char c = encryption_rotors[j][25];
            char tmp = encryption_rotors[j][0];
            for(int i = 25; i > 0; i--) {
                encryption_rotors[j][i] = encryption_rotors[j][i - 1];
            }
            encryption_rotors[j][0] = c; 
            encryption_rotors[j][26] = '\0';
        }
    }
    return;
}

// This function takes a string msg and applys the enigma machine to encrypt the message
// The encrypted message is stored in the string encryped_msg 
// Do not change spaces, make sure your encryped_msg is a \0 terminated string
void Encrypt(char encryption_rotors[4][27], int num_active_rotors, char msg[], char encrypted_msg[]){
    int a = 0; 
    encrypted_msg[strlen(msg)];
    while(msg[a] != '\0') { // while loop that loops through msg
        if(msg[a] == ' ') {
            encrypted_msg[a] = ' '; 
            a++; 
        }
        else {
        char tmp = msg[a]; // temporary char
        for(int i = 0; i < num_active_rotors; i++) { // while string exists
            
            int b = tmp - 'A'; // make integer that calculates place in array 

            tmp = encryption_rotors[i][b]; // go through each encryption rotor and find the corresponding char
        }
        encrypted_msg[a] = tmp; // 
        a++; 
        }
    }
    encrypted_msg[a] = '\0';
    return;
}


// This function takes a string msg and applys the enigma machine to decrypt the message
// The encrypted message is stored in the string encryped_msg and the decrypted_message 
// is returned as a call by reference variable
// remember the encryption rotors must be used in the reverse order to decrypt a message
// Do not change spaces, make sure your decrytped_msg is a \0 terminated string
void Decrypt(char encryption_rotors[4][27], int num_active_rotors, char encrypted_msg[], char decrypted_msg[]) {
    int a = 0; 
    decrypted_msg[strlen(encrypted_msg)]; 
    while(encrypted_msg[a] != '\0') {
        if(encrypted_msg[a] == ' ') {
            decrypted_msg[a] = ' '; 
            a++; 
        }
        else {
            char tmp = encrypted_msg[a]; 
            int index = num_active_rotors - 1; 
            int counter; 
            for(index; index > -1; index--) {
                char * p = strchr(encryption_rotors[index], tmp);
                counter = p - encryption_rotors[index];
                tmp = ROTOR_CONSTANTS[0][counter]; 
            }
            decrypted_msg[a] = tmp;
            a++; 
        }
    }
    decrypted_msg[a] = '\0'; 
    return;
}


