/**
Polybius Encryptor
By: Kierson Baxley
CMS-230
*/

// imports for I/O and
// string manipulation
#include <stdio.h>
#include <string.h>

// All the magic happens in main
int main(int argc, char *argv[]) {

  // implement the cypher key
  char poly[5][5];
  poly[0][0] = 'l';
  poly[0][1] = 'a';
  poly[0][2] = 'b';
  poly[0][3] = 'o';
  poly[0][4] = 'r';
  poly[1][0] = 'c';
  poly[1][1] = 'd';
  poly[1][2] = 'e';
  poly[1][3] = 'f';
  poly[1][4] = 'g';
  poly[2][0] = 'h';
  poly[2][1] = 'i';
  poly[2][2] = 'j';
  poly[2][3] = 'k';
  poly[2][4] = 'm';
  poly[3][0] = 'n';
  poly[3][1] = 'p';
  poly[3][2] = 's';
  poly[3][3] = 't';
  poly[3][4] = 'u';
  poly[4][0] = 'v';
  poly[4][1] = 'w';
  poly[4][2] = 'x';
  poly[4][3] = 'y';
  poly[4][4] = 'z';

  // initialize buffer array
  // for input
  char buf[1024];

  // initialize x to be used
  // to store the length of
  // inputted data
  int x = 0;

  // prompt user
  printf("Enter phrase to encypher: ");

  // scan-in user input, throw error if
  // something not good happens
  if (fgets(buf, sizeof(buf), stdin) == NULL) {
    perror("Something went wrong");
  }

  // establish length of inputted data
  while (buf[x] != 00) {
    x++;
  }

  // iterate over input
  for (int i = 0; i < x; i++) {

    // nested loops to iterate over
    // the cypher key
    for (int y = 0; y < 5; y++) {
      for (int z = 0; z < 5; z++) {

        // if the char matches
        // print out corresponding
        // indices
        if (buf[i] == poly[y][z]) {
          printf("%d", y);
          printf("%d ", z);
        }
      }
    }

    // 'q' is not in the cypher
    // so if a 'q' in the input
    // print out the indices
    // corresponding to 'i'
    if (buf[i] == 'q') {
      printf("%d", 2);
      printf("%d ", 1);
    }
  } // end of for loop
  printf("\n");
} // end of main
