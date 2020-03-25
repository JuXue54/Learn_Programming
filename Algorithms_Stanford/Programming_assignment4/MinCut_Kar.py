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
        self.data=[[x for x in line] for line in data]
    # return two random nodes of an edge
    def rand(self):
        nodeid=r.randint(0,self.nodes-1)
        node=r.choice(self.data[nodeid][1:])
        return nodeid,node
    # merge two random nodes
    def merge(self,nodeid,node):
        nodeh=self.data[nodeid][0]
        self.data=[[node]+[x for x in (line+self.data[nodeid]) if x!=node and x!=nodeh] if line[0]==node else [x if x!=nodeh else node for x in line] for line in self.data]
        self.data.pop(nodeid)
        self.nodes-=1
    # deleting random nodes until there are only two nodes
    def deleting(self,seed):
        r.seed(seed)
        while(self.nodes>2):
            nodeid,node=self.rand()
            self.merge(nodeid,node)
        if self.data[0][0]!=self.data[1][1] or self.data[0][1]!=self.data[1][0] or len(self.data[0])!=len(self.data[1]):
            raise ValueError('something is wrong')
        num=len(self.data[0])-1
        result=[self.data[0][0],self.data[1][0],num]
        return result
    # get the result
    def get(self,times):
        mini=200
        res=[]
        for i in range(times):
            n=self.deleting(i*11)
            if n[2]<mini:
                mini=n[2]
            res.append(n)
            self.nodes = len(self.unvariable)
            self.data=[[x for x in line] for line in self.unvariable]
        return res,mini

# client
def client(times):
    data=read('kargerMinCut.txt')
    test=Mincut(data)
    results,num=test.get(times)
    print('The cuts include:')
    for cuts in results:
        print("The remaining nodes are %s, The cuts are %d"%(cuts[:2],cuts[2]))
    print('The final minimum cut is %d'%num)

# test
if __name__=='__main__':
    client(50)




