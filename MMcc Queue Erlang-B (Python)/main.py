### Implementation of an M/M/c/c queue using the discret-event advance strategy
#
# CMS 380

from math import log, factorial
from statistics import mean
from random import random

# Priority queue operations
from heapq import heappush, heappop, heapify

def erlang_b_formula(m, e):
   
    # This function calculates the blocking probability of a system
    # with `num_servers` servers and a traffic intensity of `traffic_intensity`
    # using the Erlang-B formula.
    numerator = (e ** m) / factorial(m)
    denominator = sum([(e ** i) / factorial(i) for i in range(m + 1)])
    return numerator / denominator


def rand_exp(rate):
    
    """ Generate an exponential random variate
    
        input: rate, the parameter of the distribution
        returns: the exponential variate
    """
    
    return -log(random()) / rate

def erlang_rate():

  total = rand_exp(1/.5) + rand_exp(1/.5)
  return total
    

def simulate(arrival_rate, num_servers, service_type):

    """ Simulate the M/M/1 queue, discrete-event style
    
        input: arrival_rate  the system's arrival rate
        returns: the average simulated residence time
    """

    # Stopping condition
    max_num_arrivals = 50000

    # Basic parameters
    time = 0.0
    num_in_queue = 0
    service_rate = 1.0
    
    # Simulation data lists
    arrival_times = []
    enter_service_times = []
    departure_times = []

    # Initialize FEL as an empty list
    future_event_list = []
    num_dropped = 0
    num_entered = 0
    
    # Make the first arrival event
    interarrival_time = rand_exp(arrival_rate)
    new_event = (time + interarrival_time, 'arrival')
    
    # Insert with a heap operation
    heappush(future_event_list, new_event)
    
    while len(future_event_list) > 0 and num_entered < max_num_arrivals:
        
        # Pop the next event with a heap operation
        event = heappop(future_event_list)
        
        # Event attributes
        event_time = event[0]
        event_type = event[1]
        
        # Advance simulated time
        time = event_time
        
        ### Process events
        
        # Arrivals
        if event_type == 'arrival':
            num_entered += 1
            if num_in_queue < num_servers:
            
              # Log arrival time
              arrival_times.append(time)
              
              # Increment queue size
              num_in_queue += 1
              
              # Generate next arrival
              interarrival_time = rand_exp(arrival_rate)
              new_event = (time + interarrival_time, 'arrival')
              heappush(future_event_list, new_event)
              
              # If queue was empty, enter service and generate a future departure event
              if num_in_queue <= num_servers and num_in_queue > 0:
                  
                  # Log enter service time
                  enter_service_times.append(time)
                  
                  # Generate new departure event
                  if service_type == False:
                    service_time = rand_exp(service_rate)
                  else: 
                    service_time = erlang_rate()
                  new_event = (time + service_time, 'departure')
                  heappush(future_event_list, new_event)
            else:
              # Generate next arrival
              num_dropped += 1
              interarrival_time = rand_exp(arrival_rate)
              new_event = (time + interarrival_time, 'arrival')
              heappush(future_event_list, new_event)
              
              
        # Departure
        elif event_type == 'departure':
            
            # Log departure time
            departure_times.append(time)
            
            # Decrement queue size
            num_in_queue -= 1
            
            # If there are more customers waiting, put the next one into service and generate a departure
            if num_in_queue >= num_servers:
                
                # Log enter service time
                enter_service_times.append(time)
                
                # Generate new departure event
                if service_type == False:
                  service_time = rand_exp(service_rate)
                else: 
                  service_time = erlang_rate()
                new_event = (time + service_time, 'departure')
                heappush(future_event_list, new_event)
    
    
  
    # print(num_entered, num_dropped, num_in_queue)
    return num_dropped/num_entered
    


def main():

  trials = []

  print("Trials w/ service rate = 1")
  print("---------------------------")

  # Trials with service rate 1
  # 13 servers
  for trial in range(5):
    trials.append(simulate(10, 13, False))

  print("\nSimulated Average of 13 servers:\t\t", mean(trials))
  print("Erlang-B Formula Outcome w/ 13 Servers:\t", erlang_b_formula(13, 10))
  trials.clear()

  # 18 servers
  for trial in range(5):
    trials.append(simulate(10, 18, False))

  print("\nSimulated Average of 18 servers:\t\t", mean(trials))
  print("Erlang-B Formula Outcome w/ 18 Servers:\t", erlang_b_formula(18, 10))
  trials.clear()

  # 21 servers
  for trial in range(5):
    trials.append(simulate(10, 21, False))

  print("\nSimulated Average of 21 servers:\t\t", mean(trials))
  print("Erlang-B Formula Outcome w/ 21 Servers:\t", erlang_b_formula(21, 10))
  trials.clear()


  # Trials using 2-stage Erlang Distribution Service Rate
  print("\n\nTrials w/ 2-Stage Erlang Distribution Service Rate")
  print("------------------------------------------------------")
  
  for i in range(15, 21):
    for trial in range(5):
      trials.append(simulate(10, i, True))

    print("Avg. Blocking Rate for", i, " server(s): ", mean(trials))
    trials.clear()
  
          


if __name__ == '__main__':
    main()
