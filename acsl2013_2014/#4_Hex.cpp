#include <iostream>
#include <cstdio>
#include <algorithm>
#include <cstring>
#include <set>

using namespace std;

char nr[100];
int n, ndec;
set<string> hz;

void mult16(char a[], int na)
{
    int t = 0, aux;
    for(int i = 0; i < na; i++)
    {
        aux = (a[i]-'0')*16 + t;
        t = aux / 10;
        a[i] = aux % 10 + '0';
    }
    while(t)
    {
        a[na++] = t%10 + '0';
        t = t/10;
    }
    for(int i = na; i < 100; i++)
        a[i] = 0;
}

void afis(char *s)
{
    if(*s == NULL)
        printf("0");
    else if(strlen(s) == 1)
        printf("%c", *s);
    else printf("%c", s[0]-'0'+'A');
}

void solve()
{
    char aux[10];
    memset(aux, 0, sizeof(aux));
    string st;
    for(int i = 0; i < 10; i++)
    {
        mult16(nr, n);
        strcpy(aux, nr + ndec);
        nr[ndec] = 0;
        st = string(nr);
        afis(aux);
        if(hz.find(st) != hz.end())
            return ;
        hz.insert(st);
    }
}

int main()
{
    char s[100];
    freopen("4.in", "r", stdin);

    while(!feof(stdin))
    {
        hz.clear();
        memset(s, 0, sizeof(s));
        memset(nr, 0, sizeof(nr));
        gets(s);
        scanf("\n");
        strcpy(s, s+1);
        strrev(s);
        n = strlen(s);
        ndec = n;
        strcpy(nr, s);
        printf(".");
        hz.insert(string(nr));
        string st = "";
        for(int i = 0; i < 30; i++)
        {
            hz.insert(st);
            st += "0";
        }
        solve();
        printf("\n");
    }

    return 0;
}
