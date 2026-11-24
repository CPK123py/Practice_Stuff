b=int(input("enter the total no of variables:"))
a=[]
co=0
c=0
p=0


for i in range(0,b):
    c=int(input("enter"))
    a.append(c)
print(a)
for j in range(len(a)):
    if a[j]%2==0 or a[j]%3==0:
        co+=1
        print("cons",co)
    else:
        p+=1
        print("prime",p)

print("prime",p)
print("con",co)