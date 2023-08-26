/**
 * Example ArrayList implementation using C structs
 */

#include <stdio.h>
#include <stdlib.h>

#define INITIAL_CAPACITY 5

// Declare ArrayList struct
//
// It has three fields
//   int* array: a pointer to an array allocated by malloc
//   int size: the number of elements inserted into the list
//   int capacity: the length of the current array
typedef struct {
  int *array;
  int size;     // Number of inserted elements
  int capacity; // Size of allocated array
} ArrayList;

/**
 * Construct a new ArrayList and return
 * a pointer to it
 */
ArrayList *arrayListInit() {

  // Allocate a struct for the new list
  ArrayList *newList = malloc(sizeof(ArrayList));

  // Check the return from malloc
  if (newList == 0) {
    perror("arrayListInit");
  }

  // Initialize the struct's fields
  newList->array = malloc(INITIAL_CAPACITY * sizeof(int));
  newList->size = 0;
  newList->capacity = INITIAL_CAPACITY;

  // Return the pointer to the new list
  return newList;
}

/**
 * Return the size of an ArrayList
 */
int arrayListSize(ArrayList *a) { return a->size; }

/**
 * Print the contents of an ArrayList
 */
void arrayListPrint(ArrayList *a) {
  printf("[");
  for (int i = 0; i < a->size; i++) {
    printf("%d", a->array[i]);
    if (i < a->size - 1) {
      printf(", ");
    }
  }
  printf("]\n");
}

/**
 * Append a value to the end of the array list
 */
void arrayListAppend(ArrayList *a, int value) {

  // If the array is full, allocate a new array and
  // copy the elements over to it

  if (a->size == a->capacity) {
    // Allocate a new array that holds
    // 2 * a->capacity * sizeof(int) bytes
    int *arr;
    arr = malloc(2 * a->capacity * sizeof(int));
    // Copy the values from the old array to the new array
    for (int i = 0; i < a->size; i++) {
      arr[i] = a->array[i];
    }
    // Free the old array
    free(a->array);
    // Set a->array to point to the new array
    a->array = arr;
  }

  // Put the new value in the next position in a->array
  // Tip: if you're keeping track correctly, the next
  // free position is always a->array[a->size]
  a->array[a->size] = value;

  // Increment a->size
  a->size++;
}

/**
 * Return the value at the given index position in list a
 */
int arrayListGet(ArrayList *a, int index) { return a->array[index]; }

/**
 * Insert an element at a given position
 */
void arrayListInsert(ArrayList *a, int value, int index) {

  int k = a->size;
  int x = 0;

  int arr[a->size - index];

  for (int i = index; i < a->size; i++) {
    arr[x] = a->array[i];
    x++;
  }

  a->size = index;
  arrayListAppend(a, value);

  for (int i = 0; i < k - index; i++) {
    arrayListAppend(a, arr[i]);
  }
}

/**
 * Remove and return the element at the given position
 */
int arrayListRemove(ArrayList *a, int index) {
  int k = a->array[index];
  // If index is invalid (break)
  if (index < 0 || index > a->size - 1) {
    printf("%d\ninvalid index: choose 1  to ", a->size);
  }

  // Get value next to index value
  for (int i = index; i < (a->size) - 1; i++) {
    a->array[i] = a->array[i + 1];
  }

  a->array[(a->size)-1] = 0;


  return k;
}

int main(void) {
  ArrayList *a = arrayListInit();

  // Test 1: print the size of a list
  // Prints 0
  printf("%d\n", arrayListSize(a));

  // Test 2: append a few values and print
  // Prints [1, 2, 3]
  arrayListAppend(a, 1);
  arrayListAppend(a, 2);
  arrayListAppend(a, 3);
  arrayListPrint(a);

  // Test 3: append more values
  // Requires reallocating and copying the backing array
  // Prints [1, 2, 3, ..., 48, 49, 50]
  for (int i = 4; i <= 50; i++) {
    arrayListAppend(a, i);
  }
  arrayListPrint(a);

  // Test 4: verify that size has increased
  // prints 50
  printf("%d\n", arrayListSize(a));

  // Test 5: get values
  // Prints 1, 50, and 26
  printf("%d\n", arrayListGet(a, 0));
  printf("%d\n", arrayListGet(a, arrayListSize(a) - 1));
  printf("%d\n", arrayListGet(a, arrayListSize(a) / 2));

  //Test 6: write your own test for arrayListInsert
  arrayListInsert(a, 3, 9);
  arrayListPrint(a);

  // Test 7: write your own test for arrayListRemove
  printf("%d\n", arrayListRemove(a, 3));
}
