/**
 * Validating Benford's Law
 */

// Use this file to implement the program that reads
// Census data and compares the distribution of population
// digits to Benford's Law

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) {

  // Open the file
  FILE *f = fopen("co-est2021-alldata.csv", "r");

  // Check the return code
  if (f == NULL) {
    perror("fopen");
    exit(1);
  }

  // Declare character buffer
  char buf[1024];

  int firstLine = 1;
  float linecount = 0;
  
  int counts[10];
  for (int y = 0; y < 10; y++) {
    counts[y] = 0;
  }
  // Loop over the remaining lines that have data
  while (fgets(buf, sizeof(buf), f) != NULL) {

    // Skip the first line that contains the field names
    if (firstLine) {
      firstLine = 0;
      continue;
    }
    
    //counts the lines in the data sheet
    linecount ++;

    // Print the line
    //printf("%s", buf);
    // Use strtok to get the first value
    char *token;
    token = strtok(buf, ",");
    //printf("%s\n", token);
    
    // Get the second token -- strtok keep processing the same 
    //string if its 
    // first input is NULL
    token = strtok(NULL, ",");
    //printf("%s\n", token);
    
    for (int x = 0; x < 8 ;x++) {
      token = strtok(NULL, ",");
      
    }
    //printf("%s\n", token);
    
    // Get the first digit
    char firstDigit = *token;
    
    int value = firstDigit - '0';
    counts[value] ++;
  }

  for(int i = 0; i < 10; i++){
    printf("Fraction of leading ");
    printf("%d", i);
    printf("'s");
    printf(" = ");
    printf("%.4f\n", (counts[i] / linecount));
    
  }
}
