# !/user/bin/env python3
#-*- coding:utf-8 -*-
# min cuts

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
        self.__G={line[0]:set(line[1:]) for line in data}
        self.__nodes=len(self.__G)
        self.__A=set()
        self.__weight={(key,node):1 for key,value in self.__G.items() for node in value if key<node}
        self.__omega={x:0 for x in set(self.__G.keys())}
    def get_w(self,u,v):
        if (u,v) in self.__weight:
            return self.__weight[(u,v)]
        elif (v,u) in self.__weight:
            return self.__weight[(v,u)]
        else:
            return 0
    def set_w(self,u,v,value=0):
        if u<v and value>0:
            self.__weight[(u,v)]=value
        elif u>v and value>0:
            self.__weight[(v,u)]=value
        elif (u,v) in self.__weight:
            self.__weight.pop((u,v))
        elif (v,u) in self.__weight:
            self.__weight.pop((v,u))
    def BFS(self):
        while len(self.__A)<self.__nodes-1:
            u=max(self.__omega,key=self.__omega.get)
            for key in self.__omega:
                if key in self.__G[u]:
                    self.__omega[key]+=self.get_w(u,key)
            self.__omega.pop(u)
            self.__A.add(u)
        v=list(self.__omega.keys())[0]
        return u,v
    def merge(self,u,v):
        for node in (self.__G[u] & self.__G[v]):
            self.set_w(node,u,self.get_w(node,u)+self.get_w(node,v))
        for node in (self.__G[v]-self.__G[u]-{u}):
            self.set_w(node,u,self.get_w(node,v))
        for node in self.__G[v]:
            self.set_w(node,v)
        self.__G={key:(value|self.__G[v])-{u,v} if key==u else ((value|{u})-{v} if v in value else value) for key,value in self.__G.items()}
        self.__G.pop(v)
        self.__nodes-=1
    def client(self):
        cut=float('inf')
        while self.__nodes>2:
            u,v=self.BFS()
            self.merge(u,v)
            if self.__omega[v]<cut:
                cut=self.__omega[v]
            self.__A=set()
            self.__omega={x:0 for x in set(self.__G.keys())}
        return cut

# client
def main():
    data=read('kargerMinCut.txt')
    test=Graph(data)
    mincut=test.client()
    print('The cuts is %s'%(mincut))

# test
if __name__=='__main__':
    main()

