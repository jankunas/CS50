/**
 * helpers.c
 *
 * Computer Science 50
 * Problem Set 3
 *
 * Helper functions for Problem Set 3.
 */
       
#include <cs50.h>

#include "helpers.h"

/**
 * Returns true if value is in array of n values, else false.
 */
bool search(int value, int values[], int n)
{
   int first = 0;
   int last = n - 1;
   int middle = (first+last)/2;
 
   while (first <= last) {
      if (values[middle] < value)
         first = middle + 1;    
      else if (values[middle] == value) {
         break;
      }
      else
         last = middle - 1;
 
      middle = (first + last)/2;
   }
   if (first > last)
     return false;
    else return true;
 
   return 0;   
}

/**
 * Sorts array of n values.
 */
void sort(int values[], int n)
{
    int c,d, position,swap;
    for ( c = 0 ; c < ( n - 1 ) ; c++ ){
      position = c;
      for ( d = c + 1 ; d < n ; d++ )
      {
         if ( values[position] > values[d] )
            position = d;
      }
      if ( position != c )
      {
         swap = values[c];
         values[c] = values[position];
         values[position] = swap;
      }
   }
    
    
}