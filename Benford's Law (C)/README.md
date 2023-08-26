These two projects are based on Benford's Law, a statistical anomaly used to verify large data sets.
Benford essentially proved that the smaller the digit, the more likely it is to be the leading digit of a number.

This assignment is broken up into two parts. 

benford.c uses real-life data collected by the US Census (in the benford-data.csv) to demonstrate Benford's Law.
It's input is the csv file and it outputs the occurrence of each leading digit as a percentage.

generator.c sort of does the opposite. It take two inputs as flags, -n is the amount of numbers to be generated
and -d the amount of digits for each number. Then it creates a list of random numbers that obey Benford's Law.

The makefile is setup to make running these programs as streamline as possible.
