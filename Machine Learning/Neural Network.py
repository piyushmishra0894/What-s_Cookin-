
# coding: utf-8

# In[125]:


import numpy as np
import pandas as pd
from patsy import dmatrices
from sklearn.neural_network import MLPClassifier
from sklearn.model_selection import train_test_split, cross_val_score
from sklearn import metrics
import matplotlib.pyplot as plt
import random


# In[126]:


data = pd.read_csv('./train.csv')


# In[132]:


columns = data.columns[0:-1]
X = data[columns]
y = np.ravel(data['rate'])


# In[133]:


X_train_split, X_test_split, Y_train_split, Y_test_split = train_test_split(X, y, test_size = 0.2, random_state = 0)


# In[134]:


fig = plt.figure()
ax = fig.add_subplot(111) # 1 row, 1 col, 1st plot
cax = ax.matshow(X.corr(), interpolation='nearest')
fig.colorbar(cax)
plt.show()


# In[135]:


model = MLPClassifier(solver='lbfgs', alpha=1e-5, hidden_layer_sizes = (30, 10), random_state = 1, verbose = True)


# In[136]:


model.fit(X_train_split, Y_train_split)


# In[137]:


test_prob = model.predict_proba(X_test_split)


# In[138]:


solution = pd.DataFrame(test_prob, columns=['1_star','2_star','3_star','4_star','5_star'])


# In[139]:


solution


# In[140]:


solution.to_csv('./prediction.csv', index = False)


# In[141]:


pred = model.predict(X_test_split)


# In[142]:


metrics.accuracy_score(pred, Y_test_split)


# In[143]:


conf = metrics.confusion_matrix(pred, Y_test_split)


# In[144]:


print metrics.classification_report(pred, Y_test_split)


# In[145]:


solution

