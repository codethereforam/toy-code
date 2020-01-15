#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define ONE_MEGABYTE (1024 * 1024)

int main(int argc, char const *argv[])
{
    char *some_memory;
    int megabye = ONE_MEGABYTE;
    some_memory = (char *)malloc(megabye);
    if (some_memory != NULL)
    {
        sprintf(some_memory, "hello world\n");
        printf("%s", some_memory);
        exit(EXIT_SUCCESS);
    }
    exit(EXIT_FAILURE);
}
