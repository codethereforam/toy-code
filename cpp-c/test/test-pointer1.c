#include <stdio.h>
#include <stdlib.h>
int main() {
    int a = 12;
    int* b = &a;
    printf("%d\n", a);
    printf("%p\n", b);
    printf("%d\n", *b);
    printf("%p\n", &a);
    int* c = NULL;
    return EXIT_SUCCESS;
}