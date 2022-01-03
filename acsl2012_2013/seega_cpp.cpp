#include <iostream>
#include <cstdio>
#include <algorithm>
#include <cstring>

using namespace std;

int a[10][10], n;
int dx[4] = {-1, 0, 0, 1};
int dy[4] = {0, -1, 1, 0};
int lose;

void citire()
{
    scanf("%d,", &n);
    int i, x;
    for(i = 0; i < n; i++)
    {
        scanf("%d,", &x);
        a[(x-1)/5+1][(x-1)%5 +1] = 1;
    }
    for(i = 0; i < n; i++)
    {
        scanf("%d,", &x);
        a[(x-1)/5+1][(x-1)%5 +1] = 2;
    }
    for(i = 0; i <= 6; i++)
        a[0][i] = a[i][0] = a[6][i] = a[i][6] = -1;
    scanf("\n");
}

void afisare()
{
    int i, j;
    for(i = 0; i <= 6; i++)
    {
        for(j = 0; j <= 6; j++)
            printf("%d\t", a[i][j]);
        printf("\n");
    }
    printf("\n");
}

bool eliberare(int i, int j)
{
    int k;
    for(k=0;k<4;k++)
        if(a[i+dx[k]][j+dy[k]]==1)
           return true;
    return false;

}

int oponent(int eu)
{
    return !(eu-1)+1;
}

int ajunge(int x, int y, int piesa)
{
    int i, cat = 0;
    if(a[x][y] != 0)
        return 0;
    for(i = 0; i < 4; i++)
        if(a[x+dx[i]][y+dy[i]] == piesa)
            cat++;
    return cat;
}

bool ajunge(int x, int y, int i, int j)
{
    int k;
    if(a[x][y] != 0)
        return false;
    for(k = 0; k< 4; k++)
        if(x+dx[k] == i && y+dy[k] == j)
            return true;
    return false;
}

int poz(int i, int j)
{
    return 5*(i-1)+j%5;
}
int pericol(int x, int y)
{
    int i, j, k, ajg;
    for(i = 3; i >= 0; i--)
        if(a[x+dx[i]][y+dy[i]] > 0 && a[x+dx[i]][y+dy[i]] != a[x][y])
            if(ajunge(x-dx[i], y-dy[i], oponent(a[x][y])))
            {
                return poz(x-dx[i], y-dy[i]);
            }
    return -1;
}

bool unu(int i, int j)
{
    if(ajunge(3, 3, i, j))
        return true;
    return false;
}

int doi(int i, int j, int eu)
{
    int op = oponent(eu);
    int k;
    int x, y;

    for(k = 0; k < 4; k++)
    {
        x = i+dx[k];
        y = j+dy[k];
        if(a[x][y] == 0)
            if(a[x-1][j]==a[x+1][j] && a[x+1][j] == op || a[i][y-1]==a[i][y+1] && a[i][y+1] == op)
            {
                return poz(x, y);
            }
    }
    return -1;
}

int muta(int i, int j)
{
    int k;
    if(a[i][j+1] == 0)
        return poz(i, j+1);
    if(a[i][j-1] == 0)
        return poz(i, j-1);
    if(a[i+1][j] == 0)
        return poz(i+1, j);
    if(a[i-1][j] == 0)
        return poz(i-1, j);
    return -1;
}

void solve(int eu)
{
    int op = oponent(eu);
    int i, j, k, poz;
    // A
    for(i = 1; i <= 5; i++)
        for(j = 1; j <= 5; j++)
            if(a[i][j] == op)
        {
            poz = pericol(i, j);
            if(poz >= 0 && (!lose || eliberare(i, j)))
            {
                printf("%d\n", poz);
                return;
            }
        }
    //B
    int x, y;
    for(i = 1; i <= 5; i++)
        for(j = 1; j <= 5; j++)
            if(a[i][j] == eu)
                if(pericol(i, j) >= 0 && unu(i,j) && (!lose || eliberare(i, j)))
                {
                    printf("13\n");
                    return;
                }
    int p;
    for(i = 1; i <= 5; i++)
        for(j = 1; j <= 5; j++)
            if(a[i][j] == eu)
                if(pericol(i, j) >= 0 && (p = doi(i,j, eu)) >= 0 && (!lose || eliberare(i, j)))
                {
                    printf("%d\n", p);
                    return;
                }

    for(i = 1; i <= 5; i++)
        for(j = 1; j <= 5; j++)
            if(a[i][j] == eu)
                if(pericol(i, j) >= 0 && (!lose || eliberare(i, j)))
                {
                    p = muta(i, j);
                    printf("%d\n", p);
                    return ;
                }

    //C

    for(i = 1; i <= 5; i++)
        for(j = 1; j <= 5; j++)
            if(a[i][j] == eu)
                if(unu(i,j) && (!lose || eliberare(i, j)))
                {
                    printf("13\n");
                    return;
                }

    for(i = 1; i <= 5; i++)
        for(j = 1; j <= 5; j++)
            if(a[i][j] == eu)
                if((p = doi(i,j, eu)) >= 0 && (!lose || eliberare(i, j)))
                {
                    printf("%d\n", p);
                    return;
                }


    for(i = 1; i <= 5; i++)
        for(j = 1; j <= 5; j++)
            if(a[i][j] == eu)
            {
                p = muta(i, j);
                if(p >= 0 && (!lose || eliberare(i, j)))
                {
                    printf("%d\n", p);
                    return ;
                }
            }


    //D
    printf("LOSE A TURN\n");
    lose = 1;
}

int main()
{
    freopen("data.in", "r", stdin);
    int i, j, k;
    while(!feof(stdin))
    {
        memset(a, 0, sizeof(a));
        lose = 0;
        citire();
       // afisare();
        solve(1);
        solve(2);

    }
    return 0;
}
