import pandas as pd
import numpy as np
from matplotlib_venn import venn2, venn3
import mca
import matplotlib.pyplot as plt

###################################
## The main class
###################################


class Compare:
    """ 
    Compare -- plot collections for comparison purposes.
    
    Description:
        The purpose of this class is to take a series of collections and plot them so to show how they match.
        If the series is a dictionary, the keys will be used to create plot names.
        If the series contains two or three collections, then the plot will show venn diagrams and return a venn object
        that can be used for other purposes.
        If the series is greater than three, the plot will show the collections in a scatter plot based on correspondence
        scores.
    
    Parameters: 
        collections (required):  A list of lists or a dict() of size 2 or greater for comparison purposes. 
        names:  An optional list of names for the collections.  Must be equal in size to collections. If collections is 
            a dict, this parameter will be overwritten.
        index:  A list of index keys to create a sublist for the collections.
        var: An optional list for further categorization of the collection data (not fully implemented yet).
        REMOVE_SINGLES: (Default:True) For 4 collections or more, remove from the analysis any data points that are
            members of only one collection. This reduces the chance that a disproportionately large collection
            will be seen as an outlier merely because it is disproportionately large.
            
    """
    
    def __init__ (self, collections, names=[], index=[], var=[], REMOVE_SINGLES=True, LABEL_BOTH_FACTORS=False):
        self.collection_names = names
        self.index = index
        self.collections = collections
        self.REMOVE_SINGLES = REMOVE_SINGLES
        self.LABEL_BOTH_FACTORS = LABEL_BOTH_FACTORS
        self.dimensions = None
        #self.cur_depth = self.recur_len(self.collections)
        if isinstance(self.collections, dict):
            # print("dict passed")
            self.collection_names = [x for x in self.collections.keys()]
            self.collections = [x for x in self.collections.values()]
        #print(type([y[0] for y in self.collections][0]))
        # if a dictionary is inputed, then get names from dictionary
        if type([y[0] for y in self.collections][0]) is tuple: #will need to include checks for size of sample 
            print ("yay mca")
            self.collection_names = list(set([x[0] for y in self.collections for x in y]))            
            if self.index:
                self.collections = self.sublist(self.collections, self.index)
                self.collection_names = self.sublist(self.collection_names, self.index)
            self.mca(self.collections, self.collection_names)
        else:            
            #self.collections = dict([(x[0], x[1]) for y in self.collections for x in y])
            if not self.collection_names:
                self.collection_names = range(1, len(self.collections)+1)
            # if index var is provided, use index to filter collection list
            if self.index:
                self.collections = self.sublist(self.collections, self.index)
                self.collection_names = self.sublist(self.collection_names, self.index)
        #two sample venn
            if len(self.collections) == 2:
                self.response = self.two_venn(self.collections)
        #three sample venn
            elif len(self.collections) == 3:
                self.response = self.three_venn(self.collections)
        #use mca for greater than three
            elif len(self.collections) >3:
                if var:
                    self.var = var
                else: 
                    self.var = []
                self.ca = self.ca(self.collections, self.collection_names)
            else:
                self.no_compare()
    def recur_len(self, L):
        return sum(L + recur_len(item) if isinstance(item,list) else L for item in L)   
    def no_compare(self):
        return ("Need at least two collections to compare results.")
    #get a sublist from a list of indices
    def sublist (self, list1, list2):
        return([list1[x] for x in list2])        
    #get set of all items (unduplicated)
    def unionize (self, sets_list):
        return set().union(*sets_list)
    def two_venn (self, collections):
        self.V2_AB = set(collections[0]).intersection(set(collections[1]))
        return  (venn2([set(x) for x in collections], set_labels=self.collection_names))
    def three_venn (self, collections):
        self.V3_ABC = set(collections[0]) & set(collections[1]) & set(collections[2]) 
        self.V3_AB = set(collections[0]) & set(collections[1]) - self.V3_ABC
        self.V3_BC = set(collections[1]) & set(collections[2]) - self.V3_ABC
        self.V3_AC = set(collections[0]) & set(collections[2]) - self.V3_ABC
        self.V3_A = set(collections[0]) - (self.V3_ABC | self.V3_AB | self.V3_AC )
        self.V3_B = set(collections[1]) - (self.V3_ABC | self.V3_AB | self.V3_BC )
        self.V3_C = set(collections[2]) - (self.V3_ABC | self.V3_BC | self.V3_AC )
        return  (venn3([set(x) for x in collections], set_labels=self.collection_names))
    def ca(self, collections, names):
        # use dd to create a list of all websites in the collections
        print (names)
        dd = self.unionize(collections)
        d = [] #create
        e = []
        #labels
        fs, cos, cont = 'Factor Score', 'Squared cosines', 'Contributions x 1000'
        #populate table with matches for actors (weblists)
        for y in collections:
            d.append({x: x in y for x in dd})
        #if self.var:
        #    e = ({x.split(".")[0]: x.split(".")[1] for x in dd })
        df = pd.DataFrame(d, index=names)       
        if self.REMOVE_SINGLES:
            df = df.loc[:, df.sum(0) >1 ]
        #if self.var:
        #    df.loc[:,"SUFFIX"] = pd.Series(e, index=df.index)
        self.response = df.T
        counts = mca.mca(df)
        self.dimensions = counts.L
        data = pd.DataFrame(columns=df.index, index=pd.MultiIndex
                      .from_product([[fs, cos, cont], range(1, 3)]))
        data.loc[fs,    :] = counts.fs_r(N=2).T
        points = data.loc[fs].values
        urls = counts.fs_c(N=2).T
        clabels = data.columns.values
        plt.figure(figsize=(10,10))
        plt.margins(0.1)
        plt.axhline(0, color='gray')
        plt.axvline(0, color='gray')
        plt.xlabel('Factor 1 (' + str(round(self.dimensions[0], 3)*100) + '%)') 
        plt.ylabel('Factor 2 (' + str(round(self.dimensions[1], 3)*100) + '%)')
        plt.scatter(*points,  s=120, marker='o', c='r', alpha=.5, linewidths=0)
        plt.scatter(*urls,  s=120, marker='s', c='b', alpha=.5, linewidths=0)
        for label, x, y in zip(clabels, *points):
            plt.annotate(label, xy=(x, y), xytext=(x + .03, y + .03))
        if self.LABEL_BOTH_FACTORS:
            rlabels = df.T.index
            for label, x, y in zip(rlabels, *urls):
                plt.annotate(label, xy=(x, y), xytext=(x + .03, y + .03))
        plt.show()
        return(data.T)
    
    def mca(self, collections, names):
        #print ([x[2] for y in collections for x in y][0:3])
        default = defaultdict(list)
        coll = defaultdict(list)
        src_index, var_index, d = [], [], []
        for x in collections:
            for y,k,v in x:
                default[y+'%'+k].append(v)
        #print(list(default)[0:3])
        dd = self.unionize([j for y, j in default.items()])
        #print (dd)
        for key, val in default.items():
            #print (key)
            keypair = key.split("%")
            collect, year = keypair[0], keypair[1]
            coll[collect].append(year)
            d.append({url: url in val for url in dd})
        for happy, sad in coll.items():
            src_index = (src_index + [happy] * len(sad))
        #src_index = (happy * len(sad) for happy, sad in coll.items())
            var_index = (var_index + sad)
        col_index = pd.MultiIndex.from_arrays([src_index, var_index], names=["Collection", "Date"])
        #X = {x for x in (self.unionize(collections))}
        table1 = pd.DataFrame(data=d, index=col_index, columns=dd)
        if self.REMOVE_SINGLES:
            table1 = table1.loc[:, table1.sum(0) >1 ]
        table2 = mca.mca(table1)
        self.response = table1
        self.dimensions = table2.L 
        #print(table2.inertia)
        fs, cos, cont = 'Factor score','Squared cosines', 'Contributions x 1000'
        data = pd.DataFrame(columns=table1.index, index=pd.MultiIndex
                      .from_product([[fs, cos, cont], range(1, 3)]))
        data.loc[fs,    :] = table2.fs_r(N=2).T
        #print(data.loc[fs, :])

        #print(points)
        urls = table2.fs_c(N=2).T
        plabels = var_index
        
        noise = 0.07 * (np.random.rand(*data.T[fs].shape) - 0.5)
        fs_by_source = data.T[fs].add(noise).groupby(level=['Collection'])
        fs_by_date = data.T[fs]
        dpoints = data.loc[fs].values
        fig, ax = plt.subplots(figsize=(10,10))

        plt.margins(0.1)
        plt.axhline(0, color='gray')
        plt.axvline(0, color='gray')
        plt.xlabel('Factor 1 (' + str(round(self.dimensions[0], 3)*100) + '%)')
        plt.ylabel('Factor 2 (' + str(round(self.dimensions[1], 3)*100) + '%)')
        ax.margins(0.1)
        markers = '^', 's', 'o', 'o', 'v', "<", ">", "p", "8", "h"
        colors = 'r', 'g', 'b', 'y', 'orange', 'peachpuff', 'm', 'c', 'k', 'navy'
        for fscore, marker, color in zip(fs_by_source, markers, colors):
            #print(type(fscore[1]))
            label, points = fscore
            ax.plot(*points.T.values, marker=marker, color=color, label=label, linestyle='', alpha=.5, mew=0, ms=12)
            for plabel, x, y in zip(plabels, *dpoints):
                #print(plabel)
                #print(xy)
                plt.annotate(plabel, xy=(x, y), xytext=(x + .15, y + .15))
        ax.legend(numpoints=1, loc=4)
        plt.show()
        
if __name__ == "__main__":
	cp = Compare(collections)


