# project: p5
# submitter: kukunooru
# partner: none
# hours: 10
import sklearn
import sklearn.compose
import sklearn.pipeline
import sklearn.preprocessing
import sklearn.impute
import sklearn.linear_model
import sklearn.metrics

class UserPredictor: 
    def __init__(self): 
        self.model = None
    def fit(self, users, logs, y):
        y = y['y']
        users['badge'] = users['badge'].replace({'gold': 1, 'silver': 2, 'bronze': 3})
        ##############
        user_seconds = logs.groupby('user_id')['seconds'].sum().reset_index()
        user_seconds_dict = dict(zip(user_seconds['user_id'], user_seconds['seconds']))
        all_user_ids = users['user_id']
        # result_list = [seconds if user_id in user_seconds['user_id'].values else 0 for user_id, seconds in user_seconds_list]
        result_list = [] 
        for user_id in all_user_ids: 
            if user_id in user_seconds['user_id'].values: 
                result_list.append(user_seconds_dict[user_id])
            else: 
                result_list.append(0)
        users['seconds'] = result_list
        ##############
        users = users.loc[:, 'age':'seconds']
        print(users.shape, y.shape)
        x_train, x_test, y_train, y_test = sklearn.model_selection.train_test_split(users, y)
        continuous = ["age", "badge", "past_purchase_amt", "seconds"]
        imputer_c = sklearn.impute.SimpleImputer(strategy = "median")
        imputer_d = sklearn.impute.SimpleImputer(strategy = "constant", fill_value = "None")
        transformer_c = sklearn.preprocessing.StandardScaler()
        transformer_d = sklearn.preprocessing.OneHotEncoder()
        steps_c = sklearn.pipeline.Pipeline(steps = [("ic", imputer_c), ("tc", transformer_c)])
        steps_d = sklearn.pipeline.Pipeline(steps = [("id", imputer_d), ("td", transformer_d)])
        pre = sklearn.compose.ColumnTransformer(transformers = [("c", steps_c, continuous)])
        model = sklearn.pipeline.Pipeline(steps = [("pre", pre), ("clf", sklearn.linear_model.LogisticRegression())])
        model.fit(users, y)
        self.model = model 
        return model 
    def predict(self, users, logs): 
        users['badge'] = users['badge'].replace({'gold': 1, 'silver': 2, 'bronze': 3})
        ##############
        user_seconds = logs.groupby('user_id')['seconds'].sum().reset_index()
        user_seconds_dict = dict(zip(user_seconds['user_id'], user_seconds['seconds']))
        all_user_ids = users['user_id']
        # result_list = [seconds if user_id in user_seconds['user_id'].values else 0 for user_id, seconds in user_seconds_list]
        result_list = [] 
        for user_id in all_user_ids: 
            if user_id in user_seconds['user_id'].values: 
                result_list.append(user_seconds_dict[user_id])
            else: 
                result_list.append(0)
        users['seconds'] = result_list
        ##############
        users = users.loc[:, 'age':'seconds']
        return self.model.predict(users)


        