#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#define BUF_SIZE 1024
int main()
{
    char buf[BUF_SIZE];
    int n;
    n = read(STDIN_FILENO, buf, BUF_SIZE);
    if (n < 0)
    {
        perror("read STDIN_FILENO");
        exit(1);
    }
    printf("read size: %d\n", n);
    printf("last buf char's ascii Dec: %d\n", buf[n - 1]);
    char result[] = "result: ";
    int originLength = strlen(buf);
    printf("buf array size: %ld\n", sizeof(buf));
    printf("buf length: %ld\n", strlen(buf));
    printf("result array size: %ld\n", sizeof(result));
    printf("result length: %ld\n", strlen(result));
    strcat(result, buf);
    printf("after result array size: %ld\n", sizeof(result));
    printf("after result length: %ld\n", strlen(result));
    write(STDOUT_FILENO, result, originLength + n);
    return 0;
}