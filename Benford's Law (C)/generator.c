/**
 * Generating random data
 */

// Use this file to implement the program that generates
// random data with first digits obeying Benford's Law

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int benfordDigit(){
  double r = ((double) rand()) / RAND_MAX;

  if (r < .3037) {  // Percentages are in accordance with our Benford                           results
    return 1;
  } else if (r < .4862) { // An additional 17.6% are 2's
    return 2;
  } else if (r < .6089){
    return 3;
  } else if (r < .7003){
    return 4;
  }else if (r < .7789){
    return 5;
  }else if (r < .8428){
    return 6;
  }else if (r < .8982){
    return 7;
  }else if (r < .9514){
    return 8;
  }else{
    return 9;
  }
}

int uniformDigit(){
  double r = ((double) rand()) / RAND_MAX;

  if (r < .3037) {  // Percentages are in accordance with our Benford                           results
    return 1;
  } else if (r < .4862) { // An additional 17.6% are 2's
    return 2;
  } else if (r < .6089){
    return 3;
  } else if (r < .7003){
    return 4;
  }else if (r < .7789){
    return 5;
  }else if (r < .8428){
    return 6;
  }else if (r < .8982){
    return 7;
  }else if (r < .9514){
    return 8;
  }else{
    return 9;
  }
}

int main(int argc, char *argv[]) {

  int count = 1;
  int numValues = 0;
  int numDigits = 0;

  // Process arguments with getopt
  int c;
  while ((c = getopt(argc, argv, "n:d:")) != -1) {
    switch (c) {
      case 'n':
        numValues = atoi(optarg);
        break;

      case 'd':
        numDigits = atoi(optarg);
        break;
    } 
  }

  for (int n = 0; n < numValues; n++) {
  int result = benfordDigit();
  
  for (int d = 0; d < numDigits - 1; d++) {
    result = result * 10 + uniformDigit();
  }
  printf("%d", count);
  printf(". ");
  printf("%d\n", result);
  count++;

}
}
