/*
junior 5 division
XV. Gimnazija
Croatia
*/

#include <cstdio>

using namespace std;


int main(){
double p;
char c[5];
double w, k, b; 



scanf("%lf", &p);

for(int in=0; in<5; in++){
	scanf("%lf %s %lf %lf", &w, c, &k, &b);
	if(c[0] == 'G'){
		if(c[1] == 'N'){
		w*=0.0021;
		}else{
		w*=0.0321;
		}
	}else if(c[0] == 'P'){
		w*=0.05;
	}else if(c[0] == 'O'){
		w*=0.9115;
	}
	if(k==22){
		w*=0.917;
	}else if(k==18){
		w*=0.75;	
	}else if(k==14){
		w*=0.583;
	}else if(k==12){
		w*=0.5;
	}else if(k==8){
		w*=0.333;
	}else if(k==6){
		w*=0.25;
	}else if(k==1){
		w*=0.04167;
	}
	
	w*=p;
	w*=(100-b)/100;
	printf("%.2lf\n", w);
}



return 0;
}
