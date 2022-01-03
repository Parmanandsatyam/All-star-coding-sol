#include <bits/stdc++.h>
#define pii pair<int,int>
#define x first
#define y second
#define pb push_back

using namespace std;

typedef long long ll;
const char en='\n';
const int MOD=1000000007;
const int N=300010;
int n,dani[]={0,31,28,31,30,31,30,31,31,30,31,30,31};

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	freopen("as3-test.txt","r",stdin);
	for (int tt=0;tt<10;++tt)
	{
		ll d,mon,y,h,m,s;
		char t;
		cin>>y>>t>>mon>>t>>d>>h>>t>>m>>t>>s;
		ll dan=25*45*80,sat=45*80,minu=80; 
		if (y==2019 && mon==5)
		{
			cout<<(d-25)*dan+(h-12)*sat+m*minu+s<<endl;
		}
		else
		{
			ll am=586800;
			int ye=2019,mo=6;
			while (y>ye || mon>mo)
			{
				//cout<<ye<<' '<<mo<<' '<<am<<endl;
				am+=dan*dani[mo];
				if (mo==4 && ye%3==0) am+=dan;
				if (ye%5==0 && mo==9) am+=dan*2;
				if ((ye%7==0 && ye%5 && ye%3) && (mo==11 || mo==6)) am+=dan*3;
				++mo;
				if (mo==13)
				{
					mo=1;
					++ye;
				}
			}
			cout<<am+(d-1)*dan+h*sat+m*minu+s<<endl;
		}
	}
}

