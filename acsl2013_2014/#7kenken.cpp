#include <cstdio>
#include <vector>
#include <algorithm>
#include <cstring>

using namespace std;
vector<pair<int,int> > cc[30];
int N,gata;
int sumcc[30];
int a[5][5];
int dime ;
int usedL[6][6];
int usedC[6][6];

pair<int,int> coord(int k,int dim)
{
    int x,y;
    x = (k - 1) / dim + 1;
    y = (k - 1) % dim + 1;
    pair<int,int> p;
    p = make_pair(x,y);
    return p;
}

void sol()
{
    pair<int,int> poz;
    int x;
    for(int i = 1; i <= 5; ++i)
    {
        scanf("%d",&x);
        poz = coord(x,dime);
        printf("%d\n",a[poz.first][poz.second]);
    }
    /**printf("\n----\n");
    for(int i = 1; i <= dime; ++i)
    {
        for(int j = 1; j <= dime; ++j)
            printf("%d ",a[i][j]);
        printf("\n");
    }
    printf("\n----\n");
    **/
}

void back(int comp,int poz,int sum)
{
    if(comp > N) {
        sol();
        gata = 1;
        return;
    }
    if(poz == cc[comp].size())
    {
        if(sum == sumcc[comp])
        {
            back(comp+1,0,0);
            if(gata)
                return;
        }
        return;
    }
    int X,Y;
    X = cc[comp][poz].first;
    Y = cc[comp][poz].second;
    for(a[X][Y] = 1;a[X][Y] <=  sumcc[comp];a[X][Y]++ )
    {
        if(!usedL[X][a[X][Y]] &&
           !usedC[Y][a[X][Y]] &&
           sum + a[X][Y] <= sumcc[comp] )
        {
            usedL[X][a[X][Y]] = 1;
            usedC[Y][a[X][Y]] = 1;
            back(comp,poz+1,sum + a[X][Y]);
            if(gata)
                return;
            usedL[X][a[X][Y]] = 0;
            usedC[Y][a[X][Y]] = 0;
        }
    }

}
int e_numar(char s[10])
{
    for(int i = 0; i < strlen(s); ++i)
        if(!('0' <= s[i] && s[i] <= '9'))
            return 0;
    return 1;
}

int fa_numar(char s[10])
{
    int ans = 0;
    for(int i = 0; i < strlen(s); ++i)
        if('0' <= s[i] && s[i] <= '9')
            ans = ans * 10 + (s[i]-48);
    return ans;
}


void read(int dim)
{
    char s[10];
    scanf("%d",&N);
    /**if(dim == 3)
    {
        for(int i = 1; i <= N; ++i)
        {
            while(1)
            {
                scanf("%s",s);
                if(strlen(s) == 1)
                    cc[i].push_back(coord(s[0]-'0',dim));
                else
                    {
                        if(s[1] == '+')
                            sumcc[i] = s[0] - 48;
                        else
                        {
                            a[cc[i][0].first][cc[i][0].second] = s[0]-48;
                            usedL[cc[i][0].first][s[0]-48] = 1;
                            usedC[cc[i][0].second][s[0]-48] = 1;
                            cc[i].pop_back();
                            --i;
                            --N;
                        }
                        break;
                    }
            }
        }
    }
    else
    **/{
         for(int i = 1; i <= N; ++i)
         {
            while(1)
            {
                scanf("%s",s);
                if(e_numar(s))
                    cc[i].push_back(coord(fa_numar(s),dim));
                else
                    {
                        if(s[strlen(s)-1] == '+')
                            sumcc[i] = fa_numar(s);
                        else
                        {
                            a[cc[i][0].first][cc[i][0].second] = fa_numar(s);
                            usedL[cc[i][0].first][fa_numar(s)] = 1;
                            usedC[cc[i][0].second][fa_numar(s)] = 1;
                            cc[i].pop_back();
                            --i;
                            --N;
                        }
                        break;
                    }
            }
        }
    }
    back(1,0,0);
}

void cleaner()
{
    for(int i = 0; i < 30; ++i)cc[i].clear();
    N = gata = dime = 0;
    memset(sumcc,0,sizeof(sumcc));
    memset(a,0,sizeof(a));
    memset(usedL,0,sizeof(usedL));
    memset(usedC,0,sizeof(usedC));
}

int main()
{
    freopen("kenken.in","r",stdin);

    dime = 3;
    read(dime);
    cleaner();
    dime = 4;
    read(dime);
    return 0;
}
