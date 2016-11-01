#include<stdio.h>
#include<cs50.h>
int main(void){
    int n;
    printf("Please enter how many minutes you spend in shower");
    n = GetInt();
    printf("%d",n*12);
    
}