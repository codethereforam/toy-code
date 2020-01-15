#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
int main() {
    close(STDOUT_FILENO);
    int fileDescriptor = open("foo.txt", O_WRONLY|O_CREAT, 0664);
    printf("文件描述符=%d", fileDescriptor);
    return 0;
}