// Tales from the Crypt
#include <crypt.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

// Decrypt takes a hashcode string and a file
// name. It cycles through the file and hashes
// each word until it matches the input hashcode
char *decrypt(char *a, char *file) {

  // Open dictionary file
  FILE *d = fopen(file, "r");

  // Exit if file error
  if (d == NULL) {
    perror("dopen");
    exit(1);
  }

  // Initialize buffer
  char dbuf[1024];

  // Begin reading through file
  while (fgets(dbuf, sizeof(dbuf), d) != NULL) {

    // Initialize variables
    char *word;
    char *hash;

    // Tokenize each line of
    // input file, cutting off
    // at new line
    word = strtok(dbuf, "\n");

    // Hash each word
    hash = crypt(word, "$1$");

    // Store comparison results
    // and return word if true
    int cmp = strcmp(hash, a);
    if (cmp == 0) {
      return word;
    }
  } // End While

  fclose(d);   // Close file
  return NULL; // Return NULL if no match
} // End Decrypt

// decryptC functions same as decrypt
// but toggles opposite case of first
// char of input file string before
// hashing and matching
char *decryptC(char *a, char *file) {

  // Open dictionary file
  FILE *d = fopen(file, "r");

  // Exit if file error
  if (d == NULL) {
    perror("dopen");
    exit(1);
  }

  // Initialize buffer
  char dbuf[1024];

  // Iterate through file
  while (fgets(dbuf, sizeof(dbuf), d) != NULL) {

    // Initialize variables
    char *word;
    char *hash;
    char start;

    // Tokenize each line
    word = strtok(dbuf, "\n");

    // Grab first char
    // Toggle case
    // Put it back
    start = word[0];
    start = start ^ ' ';
    word[0] = start;

    // Hash'n'Match
    hash = crypt(word, "$1$");
    int cmp = strcmp(hash, a);
    if (cmp == 0) {
      return word;
    }
  } // End While

  fclose(d);   // Close file
  return NULL; // Return NULL if no match
} // End DecryptC

// decryptN functions same as decrypt but
// appends one number (0-9) to the end of
// each string inputted
char *decryptN(char *a, char *file) {

  // Open dictionary file
  FILE *d = fopen(file, "r");

  // Exit if error
  if (d == NULL) {
    perror("dopen");
    exit(1);
  }

  // Initialize buffer
  char dbuf[1024];

  // Iterate through file
  while (fgets(dbuf, sizeof(dbuf), d) != NULL) {

    // Loop through ints 0-9
    for (int i = 0; i < 10; i++) {

      // Initialize variables
      char *word;
      char *hash;

      // Turn int into char
      char x = i + '0';

      // Tokenize input
      word = strtok(dbuf, "\n");

      // Allocate space for new string
      char *newWord = malloc(strlen(word) + 2);

      // Copy old input
      strcpy(newWord, word);

      // Toss int on the end
      newWord[strlen(word)] = x;

      // Hash'n'Match
      hash = crypt(newWord, "$1$");
      int cmp = strcmp(hash, a);
      if (cmp == 0) {
        return newWord;
      }

      free(newWord); // Be free
    }                // End For
  }                  // End While

  fclose(d);   // close file
  return NULL; // return NULL if no match
} // End Decrypt

// decryptNC is literally just a combination
// of decryptN and decryptC
char *decryptNC(char *a, char *file) {

  // Open dictionary file
  FILE *d = fopen(file, "r");

  // Return error if no file open
  if (d == NULL) {
    perror("dopen");
    exit(1);
  }

  // Initialize buffer
  char dbuf[1024];

  // Iterate through input file
  while (fgets(dbuf, sizeof(dbuf), d) != NULL) {

    // Iterate through ints 0-9
    for (int i = 0; i < 10; i++) {

      // Initialize variables
      char *word;
      char *hash;
      char start;
      // Flip int to char
      char x = i + '0';

      // Token each line
      word = strtok(dbuf, "\n");

      // Allocate for new string
      char *newWord = malloc(strlen(word) + 2);

      // Copy old string
      strcpy(newWord, word);

      // Toss int on end
      newWord[strlen(word)] = x;

      // Grab first char
      // of string
      start = newWord[0];

      // flip case
      start = start ^ ' ';

      // put it back
      newWord[0] = start;

      // Hash'n'Match
      hash = crypt(newWord, "$1$");
      int cmp = strcmp(hash, a);
      if (cmp == 0) {
        return newWord;
      }

      free(newWord); // Au Revoir
    }                // End For
  }                  // End While

  fclose(d);   // close file
  return NULL; // return NULL if no match
} // End Decrypt

// Welcome to Hash'n'Match
int main(int argc, char *argv[]) {

  //*** Process input arguments ***//
  char *input = NULL;
  char *output = NULL;
  char *dictionary = NULL;
  int n = 0;
  int c = 0;
  char a;

  while ((a = getopt(argc, argv, "i:o:d:nc")) != -1) {
    if (a == 'i') {
      input = optarg;
    }
    if (a == 'o') {
      output = optarg;
    }
    if (a == 'd') {
      dictionary = optarg;
    }
    if (a == 'n') {
      n = 1;
    }
    if (a == 'c') {
      c = 1;
    }
  }

  //*** You do the rest ***//

  //........ ok.//

  // Open the input files
  FILE *f = fopen(input, "r");

  // Create output file
  FILE *out = fopen(output, "a");

  // Check if it went well
  if (f == NULL) {
    perror("fopen");
    exit(1);
  }

  if (out == NULL) {
    perror("outopen");
    exit(1);
  }

  // Initialize buffers
  char fbuf[1024];

  // Iterate over input file
  while (fgets(fbuf, sizeof(fbuf), f) != NULL) {

    // Initialize variables
    char *hashIn;
    char *user;

    // If no c or n flags
    // decrypt protocol
    if (c == 0 && n == 0) {

      // tokenize what we dont need
      user = strtok(fbuf, ":");

      // tokenize what we do need
      hashIn = strtok(NULL, "\n");

      // toss the trash
      user = "";

      // it wouldn't let me run it without
      // giving char a job
      fprintf(out, "%s", user);
      fprintf(out, "%s\n", decrypt(hashIn, dictionary));
    }

    // If only c flagged, proceed with
    // decryptC protocol
    else if (c == 1 && n == 0) {
      user = strtok(fbuf, ":");
      hashIn = strtok(NULL, "\n");
      user = "";
      fprintf(out, "%s", user);
      fprintf(out, "%s\n", decryptC(hashIn, dictionary));
    }

    // If only n flagged, proceed with
    // decryptN protocol
    else if (c == 0 && n == 1) {
      user = strtok(fbuf, ":");
      hashIn = strtok(NULL, "\n");
      user = "";
      fprintf(out, "%s", user);
      fprintf(out, "%s\n", decryptN(hashIn, dictionary));
    }

    // If n and c flagged, proceed with
    // decryptNC protocol
    else if (c == 1 && n == 1) {
      user = strtok(fbuf, ":");
      hashIn = strtok(NULL, "\n");
      user = "";
      fprintf(out, "%s", user);
      fprintf(out, "%s\n", decryptNC(hashIn, dictionary));
    } else {
      return 1; // If all else fails, return 1
    }
  } // End While

  fclose(f); // Close input file
} // End Main
