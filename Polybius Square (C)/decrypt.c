/**
Polybius Decryptor
By: Kierson Baxley
CMS-230
*/

// imports for string/io manipulation
#include <stdio.h>
#include <string.h>

// polyChar method takes two ints
// represented as chars, converts them
// to int actuals, and returns the
// corresponding char in the cypher key
char polyChar(char a, char b) {

  // convert each incoming char
  // into its respective int
  int x = a - '0';
  int y = b - '0';

  // establish 2-D array
  // to store cypher key
  char poly[5][5];

  // cypher key
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

  // return
  return poly[x][y];
}

int main(int argc, char *argv[]) {

  // establish buffer for inputted string
  // as well as a few variables used later
  char buf[1024];
  int z = 0;
  int y = 1;
  int x = 0;

  // prompt user for input
  printf("Enter cypher: ");

  // scan-in user input, throw error if
  // something not good happens
  if (fgets(buf, sizeof(buf), stdin) == NULL) {
    perror("Something went wrong");
  }

  // store the length of the inputted string
  while (buf[z] != 00) {
    z++;
  }

  // iterate over each pair in the inputted
  // sequence and convert them using polyChar
  // method storing each char back into the
  // array
  for (int i = 0; i < z / 2; i++) {
    buf[i] = polyChar(buf[x], buf[y]);
    x += 2;
    y += 2;
  }

  // zero out the rest of the array
  for (int i = z / 2; i < z; i++) {
    buf[i] = 00;
  }

  // print the final result
  printf("Decyphered: %s\n", buf);

} // end main
