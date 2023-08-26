"""
Churn and Burn Baby
"""


import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib
matplotlib.use('Agg')
from matplotlib import pyplot as plt

from sklearn import tree
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score


df = pd.read_csv('telco.csv')

df.pop('customerID').values

print(df)

df['Churn_bi'] = np.where(df['Churn'] == 'Yes', 1, 0)
df['Service_bi'] = np.where(df['InternetService'] == 'Fiber optic', 1,0)

churn_senior = pd.crosstab(index=df['Churn_bi'], columns=df['SeniorCitizen'], normalize=True) * 100
print(churn_senior)

churn_senior.plot(kind='bar', stacked=False)
plt.xlabel('Churn')
plt.ylabel('Percent')
plt.ylim([0, 100])
plt.savefig('churn_senior_citizen.pdf', bbox_inches='tight')
plt.close()

churn_service = pd.crosstab(index=df['Churn_bi'], columns=df['InternetService'], normalize=True) * 100
print(churn_service)

churn_service.plot(kind='bar', stacked=False)
plt.xlabel('Churn')
plt.ylabel('Percent')
plt.ylim([0, 100])
plt.savefig('churn_internet_service.pdf', bbox_inches='tight')
plt.close()

# Extract the target variable
y = df.pop('Churn_bi').values

# Split the complete data set into training and testing subsets
X_train, X_test, y_train, y_test = train_test_split(df[['Service_bi', 'SeniorCitizen']], y, test_size=.30)

# Fit the decision tree
clf = tree.DecisionTreeClassifier(max_depth=50)
clf.fit(X_train, y_train)

# Visualize the decision tree
tree.export_graphviz(clf, out_file='tree.dot', feature_names=['Service_bi', 'SeniorCitizen'],
                     class_names=['Churn_bi'],  filled=True, rounded=True, special_characters=True,
                     proportion=True)
