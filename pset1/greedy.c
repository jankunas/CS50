#include<stdio.h>
#include<cs50.h>
#include<math.h>

int main(){
    float n,f;
    int m,c;
    do{
        printf("How much change, man?\n");
        n = GetFloat();
    } while (n<0);
    f = n * 100;
    m = round(f);
    c= 0;
    while (m>=25){
        c++;
        m = m-25;
    }
    while (m>=10){
        c++;
        m = m-10;
    }
    while (m>=5){
        c++;
        m = m-5;
    }
    while (m>=1){
        c++;
        m = m-1;
    }
    printf("%d\n",c);
}