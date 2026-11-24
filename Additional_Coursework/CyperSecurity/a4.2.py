l1=[1,2,4,5,6,7,5,3,1,3,1]
l2=[]
for i in range(len(l1)):
   if l1[i] not in l2:
      l2.append(l1[i])

print("list:",l2)