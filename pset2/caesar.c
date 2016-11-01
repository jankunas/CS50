#include <stdio.h>
#include <cs50.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int main(int argc, string argv[])
{
   int key = 0;
   string text;
   
   if (argc != 2 || atoi(argv[0]) < 0 || atoi(argv[1]) < 0)
   {
   printf ("Enter a single commnad line argument which must be a positive int\n");
   return 1;
   }
   else 
   {
   key = atoi(argv[1]);
   } 
    text = GetString();
    

    for (int i = 0, n = strlen(text); i < n; i++)
    {
        if (islower(text[i]) && isalpha(text[i]))
        {
            text[i] = (text[i] - 'a' + key) % 26 + 97;
        }
        else if(isupper(text[i]) && isalpha(text[i]))
        {
            text[i] = ((text[i]) - 'A' + key) % 26 + 65;
        }
    
    printf("%c", text[i]);
    }
    printf("\n");
    return 0;
}
