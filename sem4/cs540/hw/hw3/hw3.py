from scipy.linalg import eigh
import numpy as np
import matplotlib.pyplot as plt

def load_and_center_dataset(filename):
    # Your implementation goes here!
    x = np.load(filename)
    mean = np.mean(x, axis=0)
    x = x - mean
    return x

def get_covariance(dataset):
    # Your implementation goes here!
    cov = np.cov(np.transpose(dataset))
    return cov
    


def get_eig(S, m):
    # Your implementation goes here!
    vals, vectors = eigh(S, eigvals_only=False, subset_by_index=[len(S) - m, len(S) - 1])
    vals = vals[::-1]
    vals = np.diagflat(vals)
    vectors = vectors[:,::-1]
    return vals, vectors


def get_eig_prop(S, prop):
    # Your implementation goes here!
    vals, vectors = eigh(S, eigvals_only=False, subset_by_value=[prop * S.trace(), np.inf])
    vals = vals[::-1]
    vals = np.diagflat(vals)
    vectors = vectors[:,::-1]
    return vals, vectors


def project_image(image, U):
    projection = []
    transposedU = np.transpose(U)
    for i in range(len(U)): 
        pca = np.dot(transposedU, np.transpose(image))
        dot = np.dot(pca, U[i])
        projection.append(dot)
    return np.array(projection)



def display_image(orig, proj):
    # Your implementation goes here!
    fig, (s1, s2) = plt.subplots(nrows=1, ncols=2)
    original = s1.imshow(np.transpose(orig.reshape(32, 32)), aspect='equal')
    fig.colorbar(original, ax=s1)
    s1.set_title("Original")
    projection = s2.imshow(np.transpose(proj.reshape(32, 32)), aspect='equal')
    fig.colorbar(projection, ax=s2)
    s2.set_title("Projection")
    plt.show()


