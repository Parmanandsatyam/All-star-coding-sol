#include <iostream>
#include <algorithm>
#include <cstdio>
#include <bitset>

using namespace std;

int bdrum[40] = {1, 2, 3, 4, 5, 6, 7, 8, 13, 14, 15, 16, 17, 18, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
int wdrum[40] = {9, 10, 11, 12, 5, 6, 7, 8, 13, 14, 15, 16, 19, 20, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
int wped[40], bped[40];

int piesa[25];
bitset<25> capt;
int bmark, wmark;
int bspin, wspin;

void init()
{
    for(int i = 0; i < 14; i++)
    {
        bped[bdrum[i]] = i;
        wped[wdrum[i]] = i;
    }
}

void reset()
{
    for(int i = 0; i < 25; i++)
        piesa[i] = 0;
    capt = 0;
}

void citire()
{
    reset();
    int x;

    scanf("%d, ", &bmark);
    for(int i = 0; i < bmark; i++)
    {
        scanf("%d, ", &x);
        piesa[x] = 1;
    }
    scanf("%d, ", &wmark);
    for(int i = 0; i < wmark; i++)
    {
        scanf("%d, ", &x);
        piesa[x] = 2;
    }
    scanf("%d, %d\n", &bspin, &wspin);
}

void afisare(int crt)
{
    int ok = 0;
    for(int i = 1; i <= 20; i++)
        if(piesa[i] == crt)
    {
        if(ok) printf(", ");
        printf("%d", i);
        if(capt[i]) printf("C");
        ok = 1;
    }

    if(ok == 0)
        printf("NONE");
    printf("\n");
}

void solve(int crt, int spin, int drum[], int ped[], int mark)
{
    if(piesa[drum[13-spin]] == crt)
    {
        piesa[drum[13-spin]] = 0;
        afisare(crt);
        piesa[drum[13-spin]] = crt;
        return;
    }

    for(int i = 1; i <= 20; i++)
        if(piesa[i] == crt && piesa[drum[ped[i]+spin]] == 3-crt)
        {
            piesa[i] = 0;
            piesa[drum[ped[i]+spin]] = crt;
            capt[drum[ped[i]+spin]] = 1;
            afisare(crt);
            piesa[i] = crt;
            piesa[drum[ped[i]+spin]] = 3-crt;
            capt[drum[ped[i]+spin]] = 0;
            return;
        }

    for(int i = 1; i <= 20; i++)
        if(piesa[i] == crt && drum[ped[i]+spin] <= 20 && piesa[drum[ped[i]+spin]] == 0)
        {
            piesa[i] = 0;
            piesa[drum[ped[i]+spin]] = crt;
            afisare(crt);
            piesa[i] = crt;
            piesa[drum[ped[i]+spin]] = 0;
            return;
        }

    if(piesa[drum[spin-1]] == 0 && mark < 5)
    {
        piesa[drum[spin-1]] = crt;
        afisare(crt);
        piesa[drum[spin-1]] = 0;
        return;
    }

    printf("NONE\n");
    return;
}

void debug()
{
    for(int i = 1; i <= 20; i++)
        printf("%d ", piesa[i]);
    printf("\n");
}

int main()
{
    freopen("5.in", "r", stdin);
    init();
    while(!feof(stdin))
    {
        reset();
        citire();
        solve(1, bspin, bdrum, bped, bmark);
        solve(2, wspin, wdrum, wped, wmark);
    }
    return 0;
}
