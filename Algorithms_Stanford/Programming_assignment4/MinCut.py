# !/user/bin/env python3
#-*- coding:utf-8 -*-
# min cuts
import random as r

# read the graph
def read(filename):
    with open(filename,'r') as f:
        lines=f.readlines()
    data=[]
    for line in lines:
        temp=line[:-2].split('\t')
        data.append(list(map(int,temp)))
    return data

# compute the mincut
class Mincut(object):
    def __init__(self, data):
        self.unvariable=data
        self.nodes = len(data)
        self.data={line[0]:set(line[1:]) for line in data}
        self.edges=0
    # return two random nodes of an edge
    def rand(self):
        nodeA=r.choice(list(self.data.keys()))    #delete the node
        nodeB=r.choice(list(self.data[nodeA]))    #delte the edge nodeB-nodeA
        return nodeA,nodeB
    # merge two random nodes
    def merge(self,nA,nB):
        self.edges+=len(self.data[nA] & self.data[nB])
        self.data={key:(value|self.data[nA])-{nA,nB} if key==nB else ((value|{nB})-{nA} if nA in value else value) for key,value in self.data.items()}
        self.data.pop(nA)
        self.nodes-=1
    # deleting random nodes until there are only two nodes
    def deleting(self,seed):
        r.seed(seed)
        while(self.nodes>2):
            nA,nB=self.rand()
            self.merge(nA,nB)
        num=self.edges
        return self.data.keys(),num
    # get the result
    def get(self,times):
        mini=200
        res=[]
        for i in range(times):
            nodes,n=self.deleting(i*11)
            if n<mini:
                mini=n
            res.append((nodes,n))
            self.nodes = len(self.unvariable)
            self.data={line[0]:set(line[1:]) for line in self.unvariable}
            self.edges=0
        return res,mini

# client
def client(times):
    data=read('kargerMinCut.txt')
    test=Mincut(data)
    results,num=test.get(times)
    print('The cuts include:')
    for cuts in results:
        print("The remaining nodes are %s, The cuts are %d"%(cuts[0],cuts[1]))
    print('The final minimum cut is %d'%num)

# test
if __name__=='__main__':
    client(50)




