#include <cstdio>
#include <cstring>

using namespace std;

int line[10][10];
int st[8],start,bet;
char hexa[20];
int a[10][10],scris[10];

void prepare()
{
    line[1][1] = 0;line[1][2] = 0;line[1][3] = 1;line[1][4] = 1;line[1][5] = 0;
    line[2][1] = 0;line[2][2] = -1;line[2][3] = 0;line[2][4] = 0;line[2][5] = 1;
    line[3][1] = 0;line[3][2] = 1;line[3][3] = 1;line[3][4] = -1;line[3][5] = -1;
    line[4][1] = 0;line[4][2] = -1;line[4][3] = -1;line[4][4] = 1;line[4][5] = 1;
    line[5][1] = 0;line[5][2] = 1;line[5][3] = 0;line[5][4] = 0;line[5][5] = -1;
    line[6][1] = 0;line[6][2] = 0;line[6][3] = -1;line[6][4] = -1;line[6][5] = 0;
}

int convertINT(char c)
{
    if('0' <= c && c <= '9') return c - 48;
    return 10 - 'A' + c;
}

void read_init()
{
    scanf("%d%d%s",&start,&bet,hexa);
    int shiet = 6;
    while(start){
        st[shiet--] = start%10;
        start/=10;
    }
    for(int i = 1; i <= 7; ++i)
        scris[i] = convertINT(hexa[i-1]);
}

int bit(int pzc)
{
    int grupa = (pzc-1) / 4 + 1;
    int pozingr = (pzc - 1) % 4 + 1;
    return ((1<<(4 - pozingr)) & scris[grupa]) != 0;
}

void formare()
{
    int pzc = 1;
    for(int i = 1; i <= 5; ++i)
        for(int j = 1; j <= 5; ++j)
        {
            a[i][j] = bit(pzc);
            ++pzc;
        }
    /**for(int i = 1; i <= 5; ++i)
    {
        for(int j = 1; j <= 5; ++j)
            printf("%d ",a[i][j]);
        printf("\n");
    }**/
}
int drawline(int k,int poz)
{
    int x = poz,y = 1, sum = 0, maxi = 0;
    sum += a[x][y];
    for(int i = 2; i <= 5; ++i)
    {
        if(sum > maxi)
            maxi = sum;
        ++y;
        x += line[k][i];
        if(a[x][y] == 0) sum = 0;
        sum += a[x][y];
    }
    if(sum > maxi)
        maxi = sum;
    if(maxi >= 3)
        return bet * ( maxi - 1);
    return 0;
}

void cleaner()
{
    memset(st,0,sizeof(st));
    start = bet = 0;
    memset(hexa,0,sizeof(hexa));
    memset(a,0,sizeof(a));
    memset(scris,0,sizeof(scris));
}

void solve()
{
    int x;
    for(int i = 1; i <= 5; ++i)
    {
        scanf("%d",&x);
        printf("%d\n",drawline(x,st[x]));
    }
}

int main()
{
    freopen("slots.in","r",stdin);

    prepare();
    for(int i = 1; i <= 2; ++i)
    {
        read_init();
        formare();
        solve();
        cleaner();
    }
    return 0;
}
