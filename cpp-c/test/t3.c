#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_NUM 100

void print(char const *str);

int main(int argc, char const *argv[])
{
    printf("max_num = %d\n", MAX_NUM);
    print("hello");
#if 0
    printf("hello\n");
#endif
    return EXIT_SUCCESS;
}

void print(char const *str)
{
    puts(str);
}

