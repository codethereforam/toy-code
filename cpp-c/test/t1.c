/* 
 * read, sort and print an array
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * compare two integer, called by qsort  
 */
int compare_integers(void const *a, void const *b)
{
    register int const *pa = a;
    register int const *pb = b;
    return *pa > *pb ? 1 : *pa < *pb ? -1 : 0;
}

int main(int argc, char const *argv[])
{
    int *array;
    int n_values;
    int i;

    // read number of values
    printf("how many values are there: ");
    if (scanf("%d", &n_values) != 1 || n_values <= 0)
    {
        printf("illegal number of values.\n");
        exit(EXIT_FAILURE);
    }

    // allocate memory for these values
    array = malloc(n_values * sizeof(int));
    if (array == NULL)
    {
        printf("can not get enough memory for that many values.\n");
        exit(EXIT_FAILURE);
    }

    // read these values
    for (i = 0; i < n_values; i++)
    {
        printf("? ");
        if (scanf("%d", array + i) != 1)
        {
            printf("error reading value %d\n", i);
            free(array);
            exit(EXIT_FAILURE);
        }
    }

    // sort array
    qsort(array, n_values, sizeof(int), compare_integers);

    // print the array
    for (i = 0; i < n_values; i++)
    {
        printf("%d ", *(array + i));
    }

    // free memory and exit
    free(array);
    return EXIT_SUCCESS;
}