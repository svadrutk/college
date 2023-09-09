import torch
import torch.nn as nn
import torch.nn.functional as F
import torch.optim as optim
from torchvision import datasets, transforms

# Feel free to import other packages, if needed.
# As long as they are supported by CSL machines.


def get_data_loader(training = True):
    """
    TODO: implement this function.

    INPUT: 
        An optional boolean argument (default value is True for training dataset)

    RETURNS:
        Dataloader for the training set (if training = True) or the test set (if training = False)
    """
    custom_transform= transforms.Compose([transforms.ToTensor(),transforms.Normalize((0.1307,), (0.3081,))])
    if(training):
        usedSet=datasets.FashionMNIST('./data', train=True, download=True,transform=custom_transform)
    else:
        usedSet=datasets.FashionMNIST('./data', train=False, transform=custom_transform)
    loader = torch.utils.data.DataLoader(usedSet, batch_size = 64)
    return loader



def build_model():
    """
    TODO: implement this function.

    INPUT: 
        None

    RETURNS:
        An untrained neural network model
    """
    model = nn.Sequential(nn.Flatten(),
            nn.Linear(784, 128),
            nn.ReLU(),
            nn.Linear(128, 64),
            nn.ReLU(),
            nn.Linear(64, 10)
    )
    return model
    




def train_model(model, train_loader, criterion, T):
    """
    TODO: implement this function.

    INPUT: 
        model - the model produced by the previous function
        train_loader  - the train DataLoader produced by the first function
        criterion   - cross-entropy 
        T - number of epochs for training

    RETURNS:
        None
    """
    model.train()
    optimizer = optim.SGD(model.parameters(), lr=0.001, momentum=0.9)
    for epoch in range(T): 
        running_loss = 0.0
        tot = 0 
        corr = 0
        for i, data in enumerate(train_loader, 0):
        # get the inputs; data is a list of [inputs, labels]
            inputs, labels = data

            # zero the parameter gradients
            optimizer.zero_grad()

            # forward + backward + optimize
            outputs = model(inputs)
            loss = criterion(outputs, labels)
            loss.backward()
            optimizer.step()

            # print statistics
            running_loss += (loss.item() * inputs.size(0))
            _, predicted = torch.max(outputs.data, 1)
            tot += labels.size(0)
            corr += (predicted == labels).sum().item()
            percentage = (corr / tot) * 100
        print(f'Train Epoch: {epoch} Accuracy: {corr}/{tot}({percentage:.2f}%) Loss: {running_loss / tot:.3f}')


    


def evaluate_model(model, test_loader, criterion, show_loss = True):
    """
    TODO: implement this function.

    INPUT: 
        model - the the trained model produced by the previous function
        test_loader    - the test DataLoader
        criterion   - cropy-entropy 

    RETURNS:
        None
    """
    model.eval() 
    corr = 0 
    tot = 0 
    running_loss = 0.0 
    with torch.no_grad(): 
        for data in test_loader: 
            images, labels = data
            outputs = model(images)
            _, predicted = torch.max(outputs.data, 1)
            tot += labels.size(0) 
            corr += (predicted == labels).sum().item() 
            loss = criterion(outputs, labels) 
            running_loss += loss.item() * images.size(0) 
    percentage = (corr / tot) * 100 
    if show_loss:
        print(f'Average loss: {running_loss / tot:.4f}')
        print(f'Accuracy: {percentage:.2f}%')
    else:
        print(f'Accuracy: {percentage:.2f}%')
    


def predict_label(model, test_images, index):
    """
    TODO: implement this function.

    INPUT: 
        model - the trained model
        test_images   -  a tensor. test image set of shape Nx1x28x28
        index   -  specific index  i of the image to be tested: 0 <= i <= N - 1


    RETURNS:
        None
    """
    class_names = ['T-shirt/top','Trouser','Pullover','Dress','Coat','Sandal','Shirt','Sneaker','Bag','Ankle Boot']
    log = model(test_images[index])
    probability = F.softmax(log, dim=1)
    probability_lst = probability[0].tolist()
    zipper = list(zip(class_names, probability_lst))
    zipper = sorted(zipper, key = lambda x:x[1], reverse=True)
    for value in zipper[:3]:
        print(f'{value[0]}: {value[1] * 100:.2f}%')


if __name__ == '__main__':
    '''
    Feel free to write your own test code here to exaime the corrness of your functions. 
    Note that this part will not be graded.
    '''
    train_loader = get_data_loader()
    test_loader = get_data_loader(training = False)
    model = build_model()
    criterion = nn.CrossEntropyLoss()
    train_model(model, train_loader, criterion, 5)
    evaluate_model(model, test_loader, criterion)
    pred_set, _ = next(iter(test_loader))
    predict_label(model, pred_set, 1)

