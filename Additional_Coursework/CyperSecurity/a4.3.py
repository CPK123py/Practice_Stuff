l1=[1,2,4,5,6,7,5,3,1,3,1]
l2=[1,2,3,4,5,6,7,9,10]
l3=[]
for i in range(len(l1)):
   if l1[i] in l3:
      None
   elif l1[i] in l2:
      l3.append(l1[i])




print("common list:",l3)