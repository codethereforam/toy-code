#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

int main(int argc, char const *argv[])
{
    pid_t pid;
    char *message;
    int n;
    printf("fork program starting\n");
    pid = fork();
    switch (pid)
    {
    case -1:
        perror("fork failed");
        exit(EXIT_FAILURE);
    case 0:
        message = "this is the child";
        n = 5;
        break;
    default:
        message = "this is the parent";
        n = 3;
        break;
    }
    for (; n > 0; n--)
    {
        puts(message);
        sleep(1);
    }
    return 0;
}
