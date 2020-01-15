#include <stdio.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{
    printf("running ps with system\n");
    system("ifconfig &");
    printf("done.\n");
    exit(0);
}
