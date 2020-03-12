# !/user/bin/env python3
#-*- coding:utf-8 -*-
# count inversions including sorting
def read(filename='IntegerArray.txt'):
    with open(filename,'r') as f:
        s=f.readlines()
    return list(map(int,s))

def count(a):
    aux=[x for x in a]
    return counting(a,aux,0,len(a)-1)

def counting(a, aux, low, high):
    if high<=low:
        return 0
    mid=(high-low)//2+low
    x=counting(a,aux,low,mid)
    y=counting(a,aux,mid+1,high)
    if a[mid+1]>a[mid]:
        return x+y
    z=countsplit(a,aux,low,mid,high)
    return x+y+z

def countsplit(a,aux,low,mid,high):
    aux[low:high+1]=[i for i in a[low:high+1]]
    i,j,num,lr=low,mid+1,0,0
    for k in range(low,high+1):
        if i>mid:
            a[k]=aux[j]
            j+=1
        elif j>high:
            a[k]=aux[i]
            i+=1
            num+=lr
        elif aux[j]<aux[i]:
            a[k]=aux[j]
            j+=1
            lr+=1
        else:
            a[k]=aux[i]
            i+=1
            num+=lr
    return num

def exch(a,i,j):
    temp=a[i]
    a[i]=a[j]
    a[j]=temp

def client():
    data=read('IntegerArray.txt')
    num=count(data)
    #print('The sorted array is \n%8s'%data)
    print('The number of inversions is %d'%num)

client()




