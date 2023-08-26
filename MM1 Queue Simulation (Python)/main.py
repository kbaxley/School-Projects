from math import log
from math import sqrt
from random import random
from statistics import mean
from statistics import stdev 
from matplotlib import pyplot as plt


#--- Generate an exponential random variate
#
# Input: mu, the parameter of the exponential distribution
# Output: a value x drawn from the exponential distribution with rate mu
def rand_exp(mu):

  # TODO: fill in code to generate and return an exponential RV
  #
  # Look at the inverse CDF examples
  return -log(random()) / mu
  
#--- Simulate the M/M/1 queue
#
# Inputs:
#    arrival_rate
#    avg_service_time
#    n: number of simulated customers
#
# Output: the average residence time of customer in the queue

def simulate(arrival_rate, avg_service_time, n):

  # Generate interarrival times
  i_arrival_times = []
  for i in range(0, n - 1):
    i_arrival_times.append(rand_exp(arrival_rate))
 
  # Generate service times
  service_times = []
  for i in range(0,n):
    service_times.append(rand_exp(1/avg_service_time))

      
  # Calculate arrival times
  arrival_times = []
  arrival_times.append(1)
  for i in range(1,n):
    arrival_times.append(arrival_times[i-1] + i_arrival_times[i-1])
      
  # Initialize other lists
  enter_service_times = [0] * n
  departure_times = [0] * n
  residence_times = [0] * n
    
  # Setup for first arrival
  enter_service_times[0] = arrival_times[0]
  departure_times[0] = enter_service_times[0] + service_times[0]
    
  # Loop over all other arrivals
  for i in range(1, n):
        
    calculate enter_service_times[i]
    enter_service_times[i] = max(arrival_times[i], departure_times[i - 1])
    calculate departure_times[i]
    departure_times[i] = enter_service_times[i] + service_times[i]
    
  # Calculate residence times
  calculate list of residence times
  for i in range(0,n):
    residence_times[i] = departure_times[i] - arrival_times[i]
  return average residence time
  return mean(residence_times)

### Main
avg_service_time = 1.0
n = 5000
arrival_rate = .05
avg_residence_times = []
utilizations = []

while(arrival_rate < 1):
  avg_residence_times.append(simulate(arrival_rate, avg_service_time, n))
  utilizations.append(arrival_rate * avg_service_time)
  arrival_rate += .05

n = 1000
replications = []
Y_bars = []
s_list = []
UCL_list = []
LCL_list = []
arrival_rate = .05
for i in range(0, len(utilizations)):
  for j in range(5):
    replications.append(simulate(arrival_rate, avg_service_time, n))
  Y_bars.append(mean(replications))
  s_list.append(stdev(replications))
  UCL_list.append(Y_bars[i] + 2.776 * s_list[i] / sqrt(5))
  LCL_list.append(Y_bars[i] - 2.776 * s_list[i] / sqrt(5))
  replications.clear()
  arrival_rate += .05



plt.scatter(utilizations, UCL_list, c= '#FF0000',marker = '^')
plt.scatter(utilizations, LCL_list, c= '#FFEA00',marker = 's')
plt.scatter(utilizations, avg_residence_times, c='#0000FF', marker='o')
plt.xlabel('Utilization/Residence Time')
plt.ylabel('Average Residence Times')
plt.xlim(0,1.1)
plt.ylim(0, max(avg_residence_times) + 5)
plt.legend(['Upper Confidence Level', 'Lower Confidence Level', 'Utilizations'])
plt.title('M/M/1 with Confidence Intervals')
plt.savefig("util_avg_res_times.pdf", bbox_inches = 'tight')
