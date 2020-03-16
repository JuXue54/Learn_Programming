# !/user/bin/env python3
#-*- coding:utf-8 -*-
# count inversions including sorting

# read the list
def read(filename='IntegerArray.txt'):
    with open(filename,'r') as f:
        s=f.readlines()
    return list(map(int,s))

# count the total number of inversions
def count(a):
    return counting(a,0,len(a)-1)

# count the number of inversions in list a[low,high+1]
def counting(a, low, high):
    if high<=low:
        return 0
    mid=(high-low)//2+low
    x=counting(a,low,mid)
    y=counting(a,mid+1,high)
    if a[mid+1]>a[mid]:
        return x+y
    z=countsplit(a,low,mid,high)
    return x+y+z

# count the number of inversions that two number in a[low,mid] and a[mid+1,high] respectively
def countsplit(a,low,mid,high):
    aux=[i for i in a[low:high+1]]
    i,j,num,lr=0,mid-low+1,0,0
    for k in range(low,high+1):
        if i>mid-low:
            a[k],j=aux[j],j+1
        elif j>high-low:
            a[k],i,num=aux[i],i+1,num+lr
        elif aux[j]<aux[i]:
            a[k],j,lr=aux[j],j+1,lr+1
        else:
            a[k],i,num=aux[i],i+1,num+lr
    return num

# client
def client():
    data=read('IntegerArray.txt')
    num=count(data)
    #print('The sorted array is \n%8s'%data)
    print('The number of inversions is %d'%num)

# test
if __name__=='__main__':
    client()




