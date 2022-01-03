#include <iostream>
#include <cstring>
#include <algorithm>
#include <fstream>

using namespace std;

ifstream fin("ascending.in");

char s[200];
int viz[200];
int nr;
int ok=1;

void reset()
{
    ok = 1;
    memset(s,0,sizeof(s));
    memset(viz,0,sizeof(viz));
    nr = 0;
}

void gen_next(int k, int curent, int prec, int &solutie)
{
    if(k==nr+1)
    {
        if(curent > prec)
        {
            solutie = curent;
            ok = 0;
            return;
        }
    }
    for(int i=1; i<strlen(s) && ok; i++)
        if(viz[i] == 0)
        {
            if(s[i]=='0' && k == 1)
                continue;

            viz[i] = 1;
            gen_next(k+1, curent*10 + s[i] - '0',prec, solutie);
            if(ok == 0)
                return;
            viz[i] = 0;
        }
}

void rezolvare()
{
    fin>>s;
    nr = s[0] - '0';
    sort(s+1,s+strlen(s));
    int numere = strlen(s+1)/nr;
    int prec = 0,rez;
    for(int i=1; i<=numere; i++)
    {
        gen_next(1,0,prec,rez);
        if(rez == prec)
            break;
        prec = rez;
        ok = 1;
        cout<<rez<<" ";
    }
    cout<<endl;
}

int main()
{
    for(int i=1; i<=10; i++)
    {
        rezolvare();
        reset();
    }
    return 0;
}
