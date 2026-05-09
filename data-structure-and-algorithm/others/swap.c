#include<stdio.h>
void swap(char *str1, char *str2)
{
char temp = *str1;
*str1 = *str2;
*str2 = temp;
}
void swap2(int *a, int *b)
{
int temp = *a;
*a = *b;
*b = temp;
}
int main()
{
char *str1 = "geeks";
char *str2 = "forgeeks";
swap(str1, str2);
printf("str1 is %s, str2 is %s", str1, str2);
getchar();

int a = 2;
int b = 4;
swap2(&a, &b);
printf("a is %d, b is %d", a, b);
getchar();

return 0;
}

