#include <stdio.h>
#include <cs50.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int main(int argc, string argv[])
{
   string key = argv[1];
   string text = NULL;
   int lastc = 0;
   int counter = 0;
   
   if (argc != 2)
   {
   printf ("Enter a single command line argument\n");
   return 1;
   }
   
   for (int j = 0, n = strlen(key); j < n; j++)
    {
        if (!isalpha(argv[1][j]))
        {
            printf("Enter alphabetical characters\n");
            return 1;
        }
    }
    do 
    {
    text = GetString();
    }
    while (text == '\0');
    
    
    for (int i = 0, n = strlen(text); i < n; i++)
    {
      if (isalpha(text[i]))
      {
        counter = lastc % strlen(key);
        if (islower(text[i]))
        {
            if (islower(key[counter]))
            {
           text[i] = ((text[i] - 'a' + key[counter] - 97)%26) +97;
           printf("%c", text[i]); 
        }
        else
        {
            text[i] = ((text[i]- 'a' + key[counter] - 65) % 26) +97;
            printf("%c", text[i]);
        }
       } 
        if (isupper(text[i]))
        {
            if (islower(key[lastc]))
            {
                text[i] = ((text[i] - 'A' + key[counter] - 97) % 26) + 65;
                printf("%c", text[i]);
            }
            else 
            {
                text[i] = ((text[i]- 'A' + key[counter] - 65) % 26) + 65;
                printf("%c", text[i]);
            }
        }  
        lastc++;
     }   
     else 
     {
        printf("%c", text[i]);
     }
    }    
    
    printf("\n");
    return 0;
    
}
