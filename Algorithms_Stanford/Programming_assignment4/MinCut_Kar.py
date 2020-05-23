# !/user/bin/env python3
#-*- coding:utf-8 -*-
# min cuts
import random as r

__author__='Jon Xue'

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
class Mincut_kar(object):
    def __init__(self, data):
        self.unvariable=data
        self.nodes = len(data)
        self.data=[[x for x in line] for line in data]
        self.cuts=list()
    # return two random nodes of an edge
    def rand(self):
        nodeid=r.randint(0,self.nodes-1)
        node=r.choice(self.data[nodeid][1:])
        return nodeid,node
    # merge two random nodes
    def merge(self,nodeid,node):
        nodeh=self.data[nodeid][0]
        self.data=[[node]+[x for x in (line+self.data[nodeid]) if x!=node and x!=nodeh] if line[0]==node else [x if x!=nodeh else node for x in line] for line in self.data]
        self.cuts.append({nodeh,node})
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
        u,v=self.data[0][0],self.data[1][0]
        A,B={u},{v}
        while set(range(1,self.nodes+1))-(A|B)!=set():
            for x in self.cuts:
                if (x & A)!=set():
                    A=A|x
                if (x & B)!=set():
                    B=B|x
        num=len(self.data[0])-1
        return [A,B,num]
    # get the result
    def getCut(self,times):
        mini=2**10
        res=[]
        for i in range(times):
            result=self.deleting(i*11)
            if result[2]<mini:
                mini=result[2]
                res=result[:2]
            self.nodes = len(self.unvariable)
            self.data=[[x for x in line] for line in self.unvariable]
            self.cuts=list()
        return res,mini

# client
def main(times):
    data=read('kargerMinCut.txt')
    test=Mincut_kar(data)
    results,num=test.getCut(times)
    print('The cut sets are: %s'%results)
    print('The minimum cut is %d'%num)

# test
if __name__=='__main__':
    main(50)




