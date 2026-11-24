#include <iostream>
using namespace std;

int main(){
    int s,mid=5;
    int arr[10];

    cout<<"Enter the values for the array:"<<endl;
    for(int i=0; i<10;i++){
        cin>>arr[i];
    }
    cout<<"Enter the search element:"<<endl;
    cin >> s ;
    for(int i=0;i<10;i++){
        if (arr[mid]==s){
            cout<<"Element found at:"<<endl;
            cout<<mid<<endl;
            break;
        }
        else if(arr[mid]<s){
            mid = (mid - 0)/2;  
        }
        else if(arr[mid]>s){
            mid = (10 - mid)/2;
        }
        else if(mid == 0 && arr[mid]!=s){
            cout<<"Element not found in array"<<endl;
            break;
        }
        else{
            cout<<"Error Occured"<<endl;
        }
    }
    return 0;
}