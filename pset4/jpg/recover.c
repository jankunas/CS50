/**
 * recover.c
 *
 * Computer Science 50
 * Problem Set 4
 *
 * Recovers JPEGs from a forensic image.
 */
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>

int main(void)
{
    //opening the card.raw file and checking whether the opening was successful
    FILE* file = fopen("card.raw", "r");
    if (file == NULL){
        printf("Not able to open the file, try running the program again");
        fclose(file);
        return 1;
    }
    //creating starting bytes of a jpeg file for checking
    uint8_t startjpg1[4] = {0xff,0xd8,0xff,0xe0}; 
    uint8_t startjpg2[4] = {0xff,0xd8,0xff,0xe1};
    
    //counter variable to count the number of jpg files
    int count = 0;
    
    //creating a buffer and reading 512 byte blocks
    uint8_t buffer[512];
	uint8_t startcheck[4];
	fread(buffer, 512, 1, file);
    
    //creating and opening the outfile pointer
    int open = 0;
    FILE* outfile;
    
    //going throught the card.raw file
    while(fread(buffer,512,1,file) > 0){
        //adding first 4 bytes into the startcheck array
        for(int i = 0;i < 4; i++)
            startcheck[i] = buffer[i];
        //check for the start of the signature first four bytes of a jpg image
        if((memcmp(startjpg1, startcheck, 4) == 0 ) || (memcmp(startjpg2, startcheck, sizeof(startcheck)) == 0)){
            //create a file name
            char name[10];
            sprintf(name, "%03d.jpg", count);
            if (open == 0){
                outfile = fopen(name,"w");
                fwrite(buffer,sizeof(buffer),1,outfile);
                open = 1;
            }
            if (open == 1){
                fclose(outfile);
                outfile = fopen(name,"w");
                fwrite(buffer,sizeof(buffer),1,outfile);
                count++;
            }
        }
        else{
            if (open == 1)
                fwrite(buffer, sizeof(buffer), 1, outfile);
        }
    }
    if(outfile){
        fclose(outfile);
    }
    fclose(file);
}
