### Implementation of an M/M/1 queue using the discret-event advance strategy
#
# CMS 380

from matplotlib import pyplot as plt
from math import log
from random import random
from statistics import mean

# Priority queue operations
from heapq import heappush, heappop, heapify


def rand_exp(rate):
    
  return -log(random()) / rate

def is_priority(prio_f):

  if random() < prio_f:
    return 0

  return 1



def simulate(arrival_rate, prio_f):

  # Stopping condition
  max_num_arrivals = 50000

  # Basic parameters
  service_rate = 1.0
  time = 0.0
  num_in_queue = [0,0]
    
    # Simulation data lists
  arrival_times = [[],[]]
  enter_service_times = [[],[]]
  departure_times = [[],[]]
  
    # Initialize FEL as an empty list
  future_event_list = []
    
    # Make the first arrival event
  interarrival_time = rand_exp(arrival_rate)
  event_prio = (is_priority(prio_f), time + interarrival_time)
  new_event = (event_prio, 'arrival')

    # Insert with a heap operation
  heappush(future_event_list, new_event)
    
  while len(future_event_list) > 0 and (len(arrival_times[0]) + len(arrival_times[1])) < max_num_arrivals:

    # Pop the next event with a heap operation
    event = heappop(future_event_list)

        # Event attributes
    event_prio = event[0]
    event_type = event[1]
      
        # Advance simulated time
    time = event_prio[1]
        
        ### Process events
        
        # Arrivals
    if event_type == 'arrival' and event_prio[0] == 0:
            
            # Log arrival time
      arrival_times[0].append(time)
            
            # Increment queue size
      num_in_queue[0] += 1
            
            # Generate next arrival
      interarrival_time = rand_exp(arrival_rate)
      event_prio = (is_priority(prio_f), time + interarrival_time)
      new_event = (event_prio, 'arrival')
      heappush(future_event_list, new_event)
            
            # If queue was empty, enter service and generate a future departure event
      if num_in_queue[0] == 1:
                
                # Log enter service time
        enter_service_times[0].append(time)
                
                # Generate new departure event
        service_time = rand_exp(service_rate)
        event_prio = (0, time + service_time)
        new_event = (event_prio, 'departure')
        heappush(future_event_list, new_event)
              
              
        # Departure
    elif event_type == 'departure' and event_prio[0] == 0:
            
            # Log departure time
        departure_times[0].append(time)
            
            # Decrement queue size
        num_in_queue[0] -= 1
            
            # If there are more customers waiting, put the next one into service and generate a departure
        if num_in_queue[0] > 0:
                
                # Log enter service time
                
          enter_service_times[0].append(time)
                
                # Generate new departure event
          service_time = rand_exp(service_rate)
          event_prio = (0, time + service_time)
          new_event = (event_prio, 'departure')
          heappush(future_event_list, new_event)

    elif event_type == 'arrival' and event_prio[0] == 1:

      arrival_times[1].append(time)

      num_in_queue[1] += 1

      interarrival_time = rand_exp(arrival_rate)
      event_prio = (is_priority(prio_f), time + interarrival_time)
      new_event = (event_prio, 'arrival')
      heappush(future_event_list, new_event)

      if (num_in_queue[1] == 1) and (num_in_queue[0] == 0):

        enter_service_times[1].append(time)

        service_time = rand_exp(service_rate)
        event_prio = (1, time + service_time)
        new_event = (event_prio, 'departure')
        heappush(future_event_list, new_event)

    elif event_type == 'departure' and event_prio[0] == 1:

          departure_times[1].append(time)
          num_in_queue[1] -= 1

          if num_in_queue[1] > 0:

            enter_service_times[1].append(time)

            service_time = rand_exp(service_rate)
            event_prio = (1, time + service_time)
            new_event = (event_prio, 'departure')
            heappush(future_event_list, new_event)
                
    
    ### Simulation is over
    #
    # Calculate statistics
    
    # Average residence time

  residence_time_n = [departure_times[1][i] - arrival_times[1][i] for i in range(len(departure_times[1]))]
  average_residence_time_n = mean(residence_time_n)


  if(prio_f == 0):
    average_residence_time = (average_residence_time_n, 0)
    return average_residence_time

  residence_times_f = [departure_times[0][i] - arrival_times[0][i] for i in range(len(departure_times[0]))]
  average_residence_time_f = mean(residence_times_f)

  average_residence_times = (average_residence_time_n, average_residence_time_f)
  return average_residence_times

def simulate_high(arrival_rate):

  prio_f = 0
  high_load_res_f = {}
  high_load_res_n = {}

  while prio_f <= 1.0:
    sim_res_time_n = []
    sim_res_time_f = []

    for i in range(20):
      result = simulate(arrival_rate, prio_f)
      sim_res_time_n.append(result[0])
      sim_res_time_f.append(result[1])

    r_avg_n = mean(sim_res_time_n)
    r_avg_f = mean(sim_res_time_f)

    high_load_res_n[prio_f] = r_avg_n
    high_load_res_f[prio_f] = r_avg_f

    prio_f += .05

  plt.plot(high_load_res_f.keys(), high_load_res_f.values(), color='green')
  plt.plot(high_load_res_n.keys(), high_load_res_n.values(), color='blue')

  intersect = min(high_load_res_n, key = lambda x: abs(high_load_res_n[x] - high_load_res_f[x]))

  plt.plot(intersect, high_load_res_n[intersect], 'bo', markersize=10)

  x_pos = intersect - .1
  y_pos = high_load_res_n[intersect] + .75
  plt.text(x_pos, y_pos, f"({intersect:.2f}, {high_load_res_n[intersect]:.2f})", fontsize=12)

  x1 = list(high_load_res_f.keys())
  y1 = list(high_load_res_f.values())

  x2 = list(high_load_res_n.keys())
  y2 = list(high_load_res_n.values())

  x_start = round(intersect, 2)

  fill_mask = [(x >= x_start) for x in x1]

  plt.fill_between(x1, y1, y2, where=fill_mask, interpolate=True, color='yellow', alpha = .2)

  plt.xlabel('Fastpass Customer Ratio')
  plt.ylabel('Avg. Residence Time (min)')
  plt.legend(['Regular', 'Fastpass'], loc='upper right')
  plt.title("Fastpass Cusotmer Ratio vs Avg. Residence Time\nHigh Load Simulation")
  plt.savefig('Residence_Times_High_Load.pdf')
  plt.clf()

def simulate_low(arrival_rate):
  prio_f = 0
  low_load_res_time_f = {}
  low_load_res_time_n = {}

  while prio_f <= 1.0:
    sim_res_time_n = []
    sim_res_time_f = []

    for i in range(20):
      result = simulate(arrival_rate, prio_f)
      sim_res_time_n.append(result[0])
      sim_res_time_f.append(result[1])

    r_avg_n = mean(sim_res_time_n)
    r_avg_f = mean(sim_res_time_f)

    low_load_res_time_n[prio_f] = r_avg_n
    low_load_res_time_f[prio_f] = r_avg_f

    prio_f += .05

  plt.plot(low_load_res_time_f.keys(), low_load_res_time_f.values(), color = 'green')
  plt.plot(low_load_res_time_n.keys(), low_load_res_time_n.values(), color = 'blue')

  intersect = min(low_load_res_time_n, key=lambda x:abs(low_load_res_time_n[x] 
 - low_load_res_time_f[x]))

  plt.plot(intersect, low_load_res_time_n[intersect], 'bo', markersize=10)

  x_pos = intersect - .1
  y_pos = low_load_res_time_n[intersect] + .125
  plt.text(x_pos, y_pos, f"({intersect:.2f}, {low_load_res_time_n[intersect]:.2f})", fontsize=12)

  x1 = list(low_load_res_time_f.keys())
  y1 = list(low_load_res_time_f.values())

  x2 = list(low_load_res_time_n.keys())
  y2 = list(low_load_res_time_n.values())

  x_start = round(intersect, 2)

  fill_mask = [(x >= x_start) for x in x1]

  plt.fill_between(x1, y1, y2, where = fill_mask, interpolate = True, color = 'yellow', alpha = .2)

  plt.xlabel("Fastpass Customer Ratio")
  plt.ylabel("Avg. Residence Time (min)")
  plt.legend(["Regular", "Fastpass"], loc = "lower right")
  plt.title("Fastpass Customers vs Avg. Residence Time\nLow Load Simulation")
  plt.savefig('Residence_Times_Low_load.pdf')


def main():
  lamda_high = .95
  lamda_low = .5

  simulate_high(lamda_high)
  simulate_low(lamda_low)
  
  
  

if __name__ == '__main__':
    main()
  
