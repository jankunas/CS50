/**
 * dictionary.c
 *
 * Computer Science 50
 * Problem Set 5
 *
 * Implements a dictionary's functionality.
 */
 
//libraries
#include <stdbool.h>
#include <stdio.h>
#include <stdbool.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>

#include "dictionary.h"

//defining a node for a trie type of datastructure
typedef struct Node{
    bool is_word;
    struct Node* children[27];
}
Node;

//prototypes
Node* newNode();
bool free_trie(Node* node);
int getIndex(char c);

//global variables
int words_num = 0;
Node* root = NULL;

/**
 * Returns true if word is in dictionary else false.
 */
bool check(const char* word)
{
    //to create a traversial node and set it to root
    Node* node = root;
    //create counter n for the length of a word
    int n = strlen(word);
    //loop through each letter of the word
    for (int i = 0; i < n; i++){
        //get the index of the current character
        int index = getIndex(word[i]);
        // to check the corresponding element in children[index]. If it is NULL, function returns false as the word is mispelled, otherwise we move to the next letter
        if (node -> children[index] == NULL)
            return false;
        else
            node = node -> children[index];
    }
    //to check whether is_word value is true, if yes - return true, otherwise - false.
    if (node -> is_word == true)
        return true;
    else
        return false;
}

/**
 * Loads dictionary into memory.  Returns true if successful else false.
 */
bool load(const char* dictionary)
{
    //sanity check
    FILE* file = fopen(dictionary, "r");
    if (file == NULL){
        printf("Was not able to open dictionary file!");
        return false;
    }
    //create a root node and set the traversial node to root
    root = newNode();
    Node* node = root;
    
    //iterate throught the file character by character until the last one is reached (eof is reached)
    for (int i = fgetc(file); i != EOF; i = fgetc(file)){
        //to check whether the letter we are scanning is a null operator. If yes, we mark that this is the end of the word(is_word is set to true), increment
        //the total number of words and reset traversal node to root
        if (i == '\n'){
            node->is_word = true;
            words_num++;
            node = root;
        }
        else{
             // to get the index of a current character
            int index = getIndex(i);
            //to check if node does not exist. If it does not, then a newNode is created and children[c] is set to it. We also move the node. 
            if (node -> children[index] == NULL){
                Node* nextNode = newNode();
                node -> children[index] = nextNode;
                node = node -> children[index];
            }
            //if node exists, traversial node is moved
            else
                node = node -> children[index];
        }
    }
    fclose(file);
    return true;
}

/**
 * Returns number of words in dictionary if loaded else 0 if not yet loaded.
 */
unsigned int size(void)
{
    return words_num;
}

/**
 * Unloads dictionary from memory.  Returns true if successful else false.
 */
bool unload(void)
{
    //to set the traversial node to root
    Node* node = root;
    bool end = free_trie(node);
    if (end == true)
        return true;
    else
        return false;
}

// ADDITIONAL FUNCTIONS

//a function to create a new node for a trie by allocating dynamic memory for the newNode, setting is_word value to false and setting all values of 
//newNode -> children[i] to NULL.    
Node* newNode(){
    Node* newNode = malloc(sizeof(Node));
    newNode -> is_word = false;
    for (int i = 0; i < 27; i++)    {
        newNode -> children[i] = NULL;
    }
    return newNode;
}


//a function that uses recursion to free all pointers in children[i] until root note is reached
bool free_trie(Node* node){
    for (int i = 0; i < 27; i++){
        if (node -> children[i] != NULL)
            free_trie(node -> children[i]);
    }
    free(node);
    return true;
}

//a function to get the index of a character within the trie 
int getIndex(char c){
    if (c == '\'') 
        return 26;    
    else 
        return tolower(c) % 'a';
}