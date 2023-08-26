
import random

#function for random value generator for placement on grid
def random_badguys():
    '''This function establishes the five places in the grid where our
    bad guys are located'''
    badguys = random.sample(total_grid_list, 20)
    return badguys

def play_bombs_away():
    '''Bombs Away is a game where the user enters a coordinate in an
    attempt the guess the places where the bad guys are, you win by
    guessing the grid placement or lose if you guess wrong more than
    five times'''
    user_in = input("Enter a target coordinate:")
    badguys = random_badguys()
    rounds = 0
    goodguys_hit = 0
    #user is run through a while loop and is compared to the bad guys location
    while badguys != [] and goodguys_hit != 4:
       
        if user_in in badguys:
            badguys.remove(user_in)
            rounds += 1
            print("Bad guys taken out!")
            total_grid_list.remove(user_in)
            print(total_grid_list)
           
        else:
            print("Oh No! Friendly Fire!")
            goodguys_hit += 1
            print("You have hit", goodguys_hit, "good guys.")
            rounds += 1
            total_grid_list.remove(user_in)
            print(total_grid_list)
           
        user_in = input("Enter a target coordinate: ")
    #after the user kills 5 good guys or guesses the location of all the bad guys the game is ended
    if goodguys_hit == 5:
        print("You killed all the good guys, shame on you!")
    else:
        print("All the bad guys are eliminated, you saved the day")
        print("You did it in", rounds, "rounds.")
   
    return rounds
   
#define list for grid for bomb placement      
total_grid_list = ["A1", "A2", "A3", "A4", "A5", "B1", "B2", "B3", "B4",
                   "B5", "C1", "C2", "C3", "C4", "C5", "D1", "D2", "D3", "D4", "D5", "E1",
                   "E2", "E3", "E4", "E5"]
print(total_grid_list)

if __name__ == '__main__':
    random_badguys()
    play_bombs_away()
