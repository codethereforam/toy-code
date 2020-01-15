#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
int main() {
    printf("current process id is %ld", (long)getpid());
    system("ps -ef | grep 'a.out'");
    exit(0);
}