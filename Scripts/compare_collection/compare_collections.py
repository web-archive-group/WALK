
# coding: utf-8

# In[1]:

import pandas as pd
import pylab as plt
import numpy as np
from matplotlib_venn import venn2


# In[ ]:

class Compare:
    def __init__ (self, collections):
        self.collections = collections
        if not isinstance(self.collections, list):
            self.collections = [self.collections]
        if len(self.collections) == 2:
            self.response = self.two_venn(self.collections)
        elif len(self.collections) == 3:
            self.three_venn(self.collections)
        elif len(self.collections >3):
            self.ca(self.collections)
    # Venn diagram of two collections
    def two_venn (self, collections):
        result = venn2([set(collections[0]), set(collections[1])])
        return(result)

if __name__ == "__main__":

	# In[ ]:

	collection1 = ["google", "apple", "microsoft", "msn"]
	collection2 = ["google", "pear", "thebeatles", "thepogues", "napster", "apple"]
	collection3 = ["google", "apple", "msn", "skunk", "beaver", "wolf"]
	collection4 = ["apple", "jump", "walk", "run", "saunter", "skunk", "napster"]
	collection5 = ["pear", "wolf", "jive", "tango"]

	one_collect = [collection1]
	two_collect = [collection1, collection2]
	three_collect = [collection1, collection2, collection3]
	all_collect = [collection1, collection2, collection3, collection4, collection5]


# In[ ]:

	compare = Compare(two_collect)


# In[ ]:

	plt.show()




