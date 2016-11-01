#include<stdio.h>
#include<cs50.h>
int main(){
    int n,i,j,k;
    do{
        printf("Enter the height of pyramid");
        n = GetInt();
    } while ((n<0) || (n>23));
    for (i=1;i<=n;i++){
        for (j=n;j>i;j--){
            printf(" ");
        }
        for (k=0;k<=i;k++){
            printf("#");
        }
        printf("\n");
    }
}