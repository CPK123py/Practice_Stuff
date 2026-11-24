b=int(input("enter the total no of variables:"))
a=[]
for i in range(0,b):
    c=int(input("enter"))
    a.append(c)

print(a)
sum=0

for i in range(len(a)):
    sum=sum+a[i]


print(sum)

