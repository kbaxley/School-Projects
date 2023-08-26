"""
Analyzing the Disputed Federalist Papers
"""


import pandas as pd
import numpy as np

import matplotlib
matplotlib.use('Agg')
from matplotlib import pyplot as plt

from sklearn import tree
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score

# Import the CSV file into a dataframe
df = pd.read_csv('federalist_papers_data.csv')



# Seperate the entire dataframe into two subsets
# One for training, which contains the papers of known authorship
# The other for testing, which is the papers of unknown authorship
train_df = df[df['class'] != 3]
test_df = df[df['class'] == 3]

print(train_df)


# Pull out the class column to use as the target label for the
# decision tree model

y = train_df.pop('class').values
print(y)
print(train_df)

# Fit the decision tree
clf = tree.DecisionTreeClassifier(max_depth=50)
clf.fit(train_df, y)

# Visualize the decision tree
tree.export_graphviz(clf, out_file='tree.dot', feature_names=list(train_df.columns),
                     class_names=['Hamilton', 'Madison'],  filled=True, rounded=True, special_characters=True,
                     proportion=True)

# Use the fitted model to predict the class of the unknown papers
y = test_df.pop('class').values
predicted_labels = clf.predict(test_df)
print(predicted_labels)
