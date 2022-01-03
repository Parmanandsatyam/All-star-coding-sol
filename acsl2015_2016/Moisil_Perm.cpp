#include <iostream>
#include <cstdio>
#include <cstring>
#include <algorithm>
#define ULL unsigned long long

using namespace std;

char s[1000];
char sated[1000];
unsigned long long fact[50];

void read()
{
    strcpy(sated, s);
    sort(sated, sated+strlen(s));
}
/*
LONG LONG BAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
LONG LONG BAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
LONG LONG BAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
LONG LONG BAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
LONG LONG BAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
LONG LONG BAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
LONG LONG BAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
LONG LONG BAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
LONG LONG BAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
*/



ULL sum(char *crt)
{
    int viz[100];
    memset(viz, 0, sizeof viz);
    for (char *p = crt; p && *p; ++p)
        viz[*p-'a']++;
    int sus = strlen(crt);
    ULL rez = fact[sus];
    for (int i = 0; i <= 'z'-'a'; i++)
        rez /= fact[viz[i]];
    return rez;
}

ULL cnt(char *crt)
{
    ULL toReturn = sum(crt+1);
    char next = *crt;
    for (char *p = crt+1; p && *p; p++)
        if (*p > next) {
            swap(*crt, *p);
            break;
        }
    sort(crt+1, crt+strlen(crt));
    return toReturn;

}

ULL solve(char *goal, char *crt)
{
    if (goal == 0 || *goal == 0)
        return 0;
    if (*goal == *crt)
        return solve(goal+1, crt+1);
    return cnt(crt) + solve(goal, crt);
}

int main()
{
    freopen("data.in", "r", stdin);

    fact[0] = 1;
    for (int i = 1; i <= 50; i++)
        fact[i] = fact[i-1] * i;

    while (!feof(stdin))
    {
        memset(s, 0, sizeof s);
        gets(s);
        if (!*s)
            break;
        read();
        printf("%lld\n", solve(s, sated)+1);
    }

    return 0;
}
