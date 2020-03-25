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

# A graph structure and BFS
class Graph(object):
    # create a graph
    def __init__(self,data):
        self.G={line[0]:set(line[1:]) for line in data}
        self.nodes=len(self.G)
        self.A=set()
        self.E={(key,node):1 for key,value in self.G.items() for node in value}
        self.omega={x:0 for x in set(self.G.keys())}
    def BFS(self):
        while len(self.A)<self.nodes-1:
            u=max(self.omega,key=self.omega.get)
            for key,value in self.omega.items():
                if key in self.G[u] and not (key in self.A):
                    self.omega[key]=value+self.E[(key,u)]
            self.A.add(u)
            self.omega.pop(u)
        return u,self.A.pop()
    def merge(self,u,v):
        for node in (self.G[u] & self.G[v]):
            self.E[(node,u)]+=self.E[(node,v)]
            self.E[(u,node)]+=self.E[(v,node)]
        for node in (self.G[v]-self.G[u]):
            self.E[(node,u)]=self.E[(node,v)]
            self.E[(u,node)]=self.E[(v,node)]
        for node in self.G[v]:
            self.E.pop((node,v))
            self.E.pop((v,node))
        if (u,v) in self.E:
            self.E.pop((u,v))
            self.E.pop((v,u))
        self.G={key:(value|self.G[v])-{u,v} if key==u else ((value|{u})-{v} if v in value else value) for key,value in self.G.items()}
        self.G.pop(v)
        self.nodes-=1
        self.A=set()
        self.omega={x:0 for x in set(self.G.keys())}
    def client(self):
        while self.nodes>2:
            u,v=self.BFS()
            self.merge(u,v)
        return self.E

# client
def main():
    data=read('kargerMinCut.txt')
    test=Graph(data)
    mincut=test.client()
    print('The cuts is %s:'%mincut)

# test
if __name__=='__main__':
    main()



