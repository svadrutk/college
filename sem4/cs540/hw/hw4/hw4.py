import csv
import numpy as np
def load_data(filepath): 
    file = open(filepath, 'r')
    reader = csv.DictReader(file)
    lst = [] 
    for line in reader: 
        lst.append(line)
    return lst

def calc_features(row): 
    arr = np.empty((6,), dtype=np.int64)
    arr[0] = np.int64(row['Attack'])
    arr[1] = np.int64(row['Sp. Atk'])
    arr[2] = np.int64(row['Speed'])
    arr[3] = np.int64(row['Defense'])
    arr[4] = np.int64(row['Sp. Def'])
    arr[5] = np.int64(row['HP'])

    return arr


features_and_names = [(calc_features(row), row['Name']) for row in load_data('Pokemon.csv')[:10]]
