l1=[1,2,4,5,6,7,5,3,1,3,1]

l2=[]
l3=[]
for i in range(len(l1)):
   if l1[i]%2==0:
      l2.append(l1[i])
   else:
      l3.append(l1[i])
      


print("Odd list:",l3)
print("Even list:",l2)