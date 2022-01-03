#include <cstdio>
#include <algorithm>
#include <cstring>
using namespace std;

char c[110];

pair <char,int> a[30],b[30],a1[30],b1[30];

bool x[30];

int temp[30];

bool cmp (pair <char,int> a,pair <char,int> b) {
	if (a.second>b.second) return 0;
	if (a.second<b.second) return 1;
	return a.first>b.first;
}

int main () {
for (int i1=0; i1<2; ++i1) {
	for (int i=0; i<26; ++i) a[i].first=b[i].first=a1[i].first=b1[i].first=i+'A';
	for (int i=0; i<26; ++i) a[i].second=b[i].second=a1[i].second=b1[i].second=temp[i]=0;
	for (int i=0; i<26; ++i) x[i]=0;
	gets(c);
	int n=strlen(c);
	for (int i=0; i<n; ++i) if (c[i]>='a' && c[i]<='z') c[i]-=('a'-'A');
	for (int i=0; i<=n; ++i) {
		if (c[i]<'A' || c[i]>'Z' || i==n) for (int j=0; j<26; ++j) {
			a[j].second+=temp[j];
			if (temp[j]) ++b[j].second;
			if (temp[j]>1) x[j]=1;
			temp[j]=0;
		}
		if (c[i]>='A' && c[i]<='Z') ++temp[c[i]-'A'];
	}
	for (int i=0; i<26; ++i) {
		a1[i]=a[i];
		b1[i]=b[i];
	}
	sort(a,a+26,cmp);
	sort(b,b+26,cmp);
	printf("%c %d\n",a[25].first,a[25].second);
	printf("%c %d\n",b[25].first,b[25].second);
	for (int i=25; i>-1; --i) if (b1[i].second) printf("%c",b1[i].first);
	printf("\n");
	for (int i=0; i<26; ++i) if (x[i]) printf("%c",i+'A');
	printf("\n");
	for (int i=25; i>-1; --i) if (a[i].second>1) printf("%c",a[i].first);
	printf("\n");
}
	return 0;
}
