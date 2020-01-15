#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(int argc, char const *argv[])
{
    pid_t pid;
    char *message;
    int n;
    printf("fork program starting\n");
    pid = fork();
    int exit_code;
    switch (pid)
    {
    case -1:
        perror("fork failed");
        exit(EXIT_FAILURE);
    case 0:
        message = "this is the child";
        n = 5;
        exit_code = 37;
        break;
    default:
        message = "this is the parent";
        n = 3;
        exit_code = 0;
        break;
    }
    for (; n > 0; n--)
    {
        puts(message);
        sleep(1);
    }
    if (pid != 0)
    {
        int start_val;
        pid_t child_pid;
        child_pid = wait(&start_val);
        printf("Child has finished: PID = %d\n", child_pid);
        printf("start_val=%d\n", start_val);
        if (WIFEXITED(start_val))
        {
            printf("Child exited with code %d\n", WEXITSTATUS(start_val));
        }
        else
        {
            printf("Child terminated abnormally\n");
        }
    }
    exit(exit_code);
}
