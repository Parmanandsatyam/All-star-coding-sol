/*
Junior 5 division
XV. Gimnzija
Croatia
*/

#include <cstdio>
#include <iostream>
#include <string>

using namespace std;

int mn(int a){
	int sol=a;
	sol*=2;
	sol%=10;
	if(a>=5)
		sol++;
	return sol;
}

bool check(string s){
	int sum=0;
	for(int i=s.length()-2; i>=0; i--){
		if((s.length() - i)%2==0){
			sum+=mn(s[i] - '0');	
		}else{
			sum+=s[i] - '0';
		}
	}
	sum*=9;
	return sum%10==s[s.length()-1] - '0';	
}

int main(){
string s;
for(int in=0; in<5; in++){
	cin >> s;
	
	if(check(s))
		printf("VALID\n");
	else{
		for(int i=0; i<s.length(); i++){
			if(s[i] == '9'){
				s[i] = '0';
				if(check(s)){
					cout << s << endl;
					break;
				}
				s[i] = '9';			
			}else{
				s[i]++;
				if(check(s)){
					cout << s << endl;
					break;		
				}
				s[i]--;
			}
			
			if(s[i] == '0'){
				s[i] == '9';
				if(check(s)){
					cout << s << endl;
					break;		
				}
				s[i] == '0';
			}else{
				s[i]--;
				if(check(s)){
					cout << s << endl;
					break;		
				}
				s[i]++;
			}
		}
	
	}
}




}
