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
int n;
#define double long double

bool jedn(double a,double b)
{
	return ((a<1 && b<1 && abs(a-b)<1e-8) || abs(1-a/b)<1e-8);
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	freopen("as8-test.txt","r",stdin);
	bool a=0;
	for (int tt=0;tt<10;++tt)
	{
		double x1,y1,x2,y2;
		ll n,m,k;
		cin>>n>>m>>x1>>y1>>x2>>y2>>k;
		if (x1==x2)
		{
			if (a) cout<<"Test case "<<tt+1<<": ";
			cout<<x1<<endl;
			continue;
		}
		if (y1==y2)
		{
			if (a) cout<<"Test case "<<tt+1<<": ";
			cout<<y1<<endl;
			continue;
		}
		double nag=(y2-y1)/(x2-x1);
		bool dolje=y2<y1;
		double INx=x2,INy=y2,NA0;
		bool DOL;
		for (int i=0;i<k;++i)
		{
			double xDOLJE=x1-y1/nag,xGORE=(m-y1)/nag+x1;
			double yLIJEVO=-x1*nag+y1,yDESNO=(n-x1)*nag+y1;
			if (i==1) NA0=nag,DOL=dolje;
			if (i>1 && jedn(INx,x1) && jedn(INy,y1) && jedn(NA0,nag) && dolje==DOL && i*2-1+ll(round(k-1))%(i-1)<k && k>=1000)
			{
				//cout<<i<<' '<<k<<' '<<ll(round(k))%(i-1)<<endl;
				k=i*2-1+ll(round(k-1))%(i-1);
				//cout<<k<<endl;
			}
			if (dolje)
			{
				if (nag<0)
				{
					if (jedn(n,xDOLJE))
					{
						dolje=0;
						x1=n;
						y1=0;
						nag=1/nag;
					}
					else if (xDOLJE>0 && xDOLJE<n)
					{
						dolje=0;
						y1=0;
						x1=xDOLJE;
						nag=-nag;
					}
					else if (yDESNO<m && yDESNO>0)
					{
						x1=n;
						y1=yDESNO;
						nag=-nag;
					}
					else if (a) cout<<"ovo je glupost, idem dolje desno"<<endl;
					//cout<<"x="<<x1<<", y="<<y1<<", nagib="<<nag<<", dolje="<<dolje<<", broj "<<i+1<<endl;
				}
				else
				{
					if (jedn(0,xDOLJE))
					{
						dolje=0;
						x1=0;
						y1=0;
						nag=1/nag;
					}
					else if (xDOLJE>0 && xDOLJE<n)
					{
						dolje=0;
						y1=0;
						x1=xDOLJE;
						nag=-nag;
					}
					else if (yLIJEVO<m && yLIJEVO>0)
					{
						x1=0;
						y1=yLIJEVO;
						nag=-nag;
					}
					else if (a) cout<<"ovo je glupost, idem dolje lijevo"<<endl;
					//cout<<"x="<<x1<<", y="<<y1<<", nagib="<<nag<<", dolje="<<dolje<<", broj "<<i+1<<endl;
				}
			}
			else
			{
				if (nag<0)
				{
					if (jedn(0,xGORE))
					{
						dolje=1;
						x1=0;
						y1=m;
						nag=1/nag;
					}
					else if (xGORE>0 && xGORE<n)
					{
						dolje=1;
						y1=m;
						x1=xGORE;
						nag=-nag;
					}
					else if (yLIJEVO<m && yLIJEVO>0)
					{
						x1=0;
						y1=yLIJEVO;
						nag=-nag;
					}
					else if (a) cout<<"ovo je glupost, idem gore lijevo, "<<endl;
					//cout<<"x="<<x1<<", y="<<y1<<", nagib="<<nag<<", dolje="<<dolje<<", broj "<<i+1<<endl;
				}
				else
				{
					if (jedn(n,xGORE))
					{
						dolje=1;
						x1=n;
						y1=m;
						nag=1/nag;
					}
					else if (xGORE>0 && xGORE<n)
					{
						dolje=1;
						y1=m;
						x1=xGORE;
						nag=-nag;
					}
					else if (yDESNO<m && yDESNO>0)
					{
						x1=n;
						y1=yDESNO;
						nag=-nag;
					}
					else if (a) cout<<"ovo je glupost, idem gore desno"<<endl;
					//cout<<"x="<<x1<<", y="<<y1<<", nagib="<<nag<<", dolje="<<dolje<<", broj "<<i+1<<endl;
				}
			}
		}
		if ((jedn(x1,0) || jedn(x1,n)) && (jedn(y1,0) || jedn(y1,m)))
		{
			if (a) cout<<"Test case "<<tt+1<<": ";
			cout<<round(max(x1,y1))<<endl;
		}
		else if (jedn(x1,0) || jedn(x1,n))
		{
			if (a) cout<<"Test case "<<tt+1<<": ";
			cout<<round(y1)<<endl;
		}
		else if (jedn(y1,0) || jedn(y1,m))
		{
			if (a) cout<<"Test case "<<tt+1<<": ";
			cout<<round(x1)<<endl;
		}
		else
		{
			if (a) cout<<"Test case "<<tt+1<<": NONONONONO"<<endl;
			else cout<<rand()%min(n+1,m+1)<<endl;
		}
	}
}

