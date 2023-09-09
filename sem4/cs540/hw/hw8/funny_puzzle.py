import heapq




def get_manhattan_distance(from_state, to_state=[1, 2, 3, 4, 5, 6, 7, 0, 0]):
    """
    TODO: implement this function. This function will not be tested directly by the grader. 

    INPUT: 
        Two states (if second state is omitted then it is assumed that it is the goal state)

    RETURNS:
        A scalar that is the sum of Manhattan distances for all tiles.
    """  
    sum_of_manhattan_distance = 0
    for i in range(9):
        if from_state[i] == 0:
            continue
        sum_of_manhattan_distance += abs(i//3 - (from_state[i]-1)//3) + abs(i%3 - (from_state[i]-1)%3)
    return sum_of_manhattan_distance

    





def print_succ(state):
    """
    TODO: This is based on get_succ function below, so should implement that function.

    INPUT: 
        A state (list of length 9)

    WHAT IT DOES:
        Prints the list of all the valid successors in the puzzle. 
    """
    succ_states = get_succ(state)

    for succ_state in succ_states:
        print(succ_state, "h={}".format(get_manhattan_distance(succ_state)))


def get_succ(state):
    """
    TODO: implement this function.

    INPUT: 
        A state (list of length 9)

    RETURNS:
        A list of all the valid successors in the puzzle (don't forget to sort the result as done below). 
    """
    """
    TODO: implement this function.

    INPUT: 
        A state (list of length 9)

    RETURNS:
        A list of all the valid successors in the puzzle (don't forget to sort the result as done below). 
    """
    succ_states = []
    empty_tile_pos = []
    
    for x in range(len(state)):
        if (state[x] == 0):
            empty_tile_pos.append(x)
    
    for x in empty_tile_pos:
        next = state.copy()

        if (x == 0):
            
            if next[1] != 0:
                next[0], next[1] = next[1], next[0]
                succ_states.append(next)
                next = state.copy()
            
            if next[3] != 0:
                next[0], next[3] = next[3], next[0]
                succ_states.append(next)
                
        elif (x == 1):
        
            if next[0] != 0:
                next[0], next[1] = next[1], next[0]
                succ_states.append(next)
                next = state.copy()
        
            if next[2] != 0:
                next[2], next[1] = next[1], next[2]
                succ_states.append(next)
                next = state.copy()
        
            if next[4] != 0:
                next[4], next[1] = next[1], next[4]
                succ_states.append(next)

        elif (x == 2):
            
            if next[1] != 0:
                next[2], next[1] = next[1], next[2]
                succ_states.append(next)
                next = state.copy()
            
            if next[5] != 0:
                next[2], next[5] = next[5], next[2]
                succ_states.append(next)
        
        elif (x == 3):
            
            if next[0] != 0:
                next[0], next[3] = next[3], next[0]
                succ_states.append(next)
                next = state.copy()
        
            if next[6] != 0:
                next[6], next[3] = next[3], next[6]
                succ_states.append(next)
                next = state.copy()
        
            if next[4] != 0:
                next[4], next[3] = next[3], next[4]
                succ_states.append(next)
                
        if (x == 4):
        
            if next[1] != 0:
                next[1], next[4] = next[4], next[1]
                succ_states.append(next)
                next = state.copy()
        
            if next[3] != 0:
                next[3], next[4] = next[4], next[3]
                succ_states.append(next)
                next = state.copy()
                
        
            if next[5] != 0:
                next[5], next[4] = next[4], next[5]
                succ_states.append(next)
                next = state.copy()
                
        
            if next[7] != 0:
                next[7], next[4] = next[4], next[7]
                succ_states.append(next)

        elif (x == 5):
            
            if next[2] != 0:
                next[2], next[5] = next[5], next[2]
                succ_states.append(next)
                next = state.copy()
        
            if next[8] != 0:
                next[8], next[5] = next[5], next[8]
                succ_states.append(next)
                next = state.copy()
        
            if next[4] != 0:
                next[4], next[5] = next[5], next[4]
                succ_states.append(next)
        
        elif (x == 6):
            
            if next[3] != 0:
                next[6], next[3] = next[3], next[6]
                succ_states.append(next)
                next = state.copy()
            
            if next[7] != 0:
                next[6], next[7] = next[7], next[6]
                succ_states.append(next)
        
        elif (x == 7):
            
            if next[8] != 0:
                next[8], next[7] = next[7], next[8]
                succ_states.append(next)
                next = state.copy()
        
            if next[6] != 0:
                next[6], next[7] = next[7], next[6]
                succ_states.append(next)
                next = state.copy()
        
            if next[4] != 0:
                next[4], next[7] = next[7], next[4]
                succ_states.append(next)
        
        elif (x == 8):
            
            if next[5] != 0:
                next[8], next[5] = next[5], next[8]
                succ_states.append(next)
                next = state.copy()
            
            if next[7] != 0:
                next[8], next[7] = next[7], next[8]
                succ_states.append(next)
                
        
                
    return sorted(succ_states)
    


def solve(state, goal_state=[1, 2, 3, 4, 5, 6, 7, 0, 0]):
    """
    TODO: Implement the A* algorithm here.

    INPUT: 
        An initial state (list of length 9)

    WHAT IT SHOULD DO:
        Prints a path of configurations from initial state to goal state along  h values, number of moves, and max queue number in the format specified in the pdf.
    """
    open_queue = []
    closed_set = []
    
    closed_states = []
    
    parent_indices = []
    
    path_to_goal = []
    g = 0
    
    
    heapq.heappush(open_queue, (get_manhattan_distance(state) + g, state, (g, get_manhattan_distance(state), -1)))
    max_queue_len = 1
    min_queue_len = 0
    
    while open_queue != []:
        min_queue_len += 1
        
        parent = heapq.heappop(open_queue)
        parent_indices.append(parent)
        
        closed_states.append(parent[1])
        
        heapq.heappush(closed_set, parent)
        
        #if n is goal node, exit
        if parent[1] == goal_state:
            path_to_goal.append(parent)
            while parent[2][2] != -1:
                parent = parent_indices[parent[2][2]]
                path_to_goal.append(parent)
                
            reverse_list = path_to_goal[::-1]
            move = 0
            for x in reverse_list:
                print(x[1], "h={}".format(x[2][1]), "moves:",move)
                move+=1
            
            break
        
        successor_states = get_succ(parent[1])
        
        #increment cost from starting state
        g = parent[2][0] + 1
        
        for ss in successor_states:
            if ss in closed_states:
                continue
            
            else:
                h = get_manhattan_distance(ss)
                to_add = (h+g, ss, (g, h, parent_indices.index(parent)))
                
                heapq.heappush(open_queue, to_add)
    
        max_queue_len = max(max_queue_len, len(open_queue))
    print("Max queue length: {}".format(max_queue_len))





if __name__ == "__main__":
    """
    Feel free to write your own test code here to exaime the correctness of your functions. 
    Note that this part will not be graded.
    """
