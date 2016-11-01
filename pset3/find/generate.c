/**
 * generate.c
 *
 * Computer Science 50
 * Problem Set 3
 *
 * Generates pseudorandom numbers in [0,LIMIT), one per line.
 *
 * Usage: generate n [s]
 *
 * where n is number of pseudorandom numbers to print
 * and s is an optional seed
 */
 
#define _XOPEN_SOURCE

#include <cs50.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// constant
#define LIMIT 65536

int main(int argc, string argv[])
{
    //if condition checks whether user has entered any other number of arguments than 2 or 3. If user did enter more than 3 or less than 2 arguments, we get a printed message: Usage: generate n [s]   
    //and quits
    if (argc != 2 && argc != 3)
    {
        printf("Usage: generate n [s]\n");
        return 1;
    }

    // we take the second argument value and convert it from a string to integer and then assign that integer value to a declared integer variable n.
    int n = atoi(argv[1]);

    // if 3 arguments were used when program was run then we take the third argument, convert it from string to int, converting this int value to long int and pass it to the function srand48 as seed .
    // if 2 arguments were used then we pass NULL value to function time and then a random number depending on our current time is converted to long int type and is passed to srand48 as seed.
    if (argc == 3)
    {
        srand48((long int) atoi(argv[2]));
    }
    else
    {
        srand48((long int) time(NULL));
    }

    // for loop starts at value 0 and repeats until we reach the value of the second argument which was used when program was run
    //at each iteration we print out an integer value which is equal to a nonnegative double - precision floating point value from 0.0 to 1.0 multiplied by a previously defined value of 65536
    for (int i = 0; i < n; i++)
    {
        printf("%i\n", (int) (drand48() * LIMIT));
    }

    // success
    return 0;
}