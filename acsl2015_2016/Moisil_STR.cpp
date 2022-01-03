#include <iostream>
#include <cstdio>
#include <cstring>

using namespace std;

char s[1000], a[1000];
int nr, sum = 0;
int bef[100], aft[100];
int cb, ca, len, de;

void read()
{
    cb = ca = de = 0;
    len = 10;
    int p = 0;
    char *pt = strtok(s, ", ");
    strcpy(a, pt);
    pt = strtok(NULL, ", ");
    if (pt != NULL) {
        sscanf(pt, "%d", &len);
        pt = strtok(NULL, ", ");
        if (pt != NULL)
            sscanf(pt, "%d", &de);
    }
    for (int i = 0; a[i]; i++)
    {
        if (a[i] == '-') continue;
        if (a[i] == '.') {
            p = 1;
            continue;
        }
        if (p == 0)
            bef[++cb] = a[i]-'0';
        else
            aft[++ca] = a[i]-'0';
    }
}

void rotund(char sol[100])
{
    int carry = 0;
    memset(sol, 0, sizeof sol);
    if (de >= ca) {
        for (int i = ca; i < de; i++)
            aft[++ca] = 0;
    }
    else {
        carry = 0;
        if (aft[de+1] >= 5 && s[0] != '-') carry = 1;
        if (aft[de+1] < 5 &&  s[0] == '-') carry = 1;
        for (int i = de; i >= 1 && carry; i--) {
            if (carry) {
                aft[i] += carry;
                if (aft[i] >= 10)
                    aft[i] = 0;
                else
                    carry = 0;
            }
        }
        for (int i = cb; i>=1 && carry; i--)
            if (carry) {
                bef[i] += carry;
                if (bef[i] >= 10)
                    bef[i] = 0;
                else
                    carry = 0;
            }

    }
    int dim = 0;
        if (a[0] == '-') sol[dim++] = '-';
        if (carry)
            sol[dim++] = '1';
        for (int i = 1; i <= cb; i++)
            sol[dim++] = bef[i]+'0';
        if (de)
        {
            sol[dim++] = '.';
            for (int i = 1; i <= de; i++)
                sol[dim++] = aft[i]+'0';
        }
    sol[dim] = 0;
}

void solve()
{
    char aux[100];
    rotund(aux);
    if (strlen(aux) == len)
        printf("%s\n", aux);
    else if (strlen(aux) > len)
    {
        if (de >= len) {
            printf("ERROR\n");
            return ;
        }
        int o = len-de-1;
        for (int i = 1; i <= o; i++)
            printf("#");
        if (de == 0) {
            printf("#\n");
            return;
        }
        printf(".");
        for (int i = 1; i <= de; i++)
            printf("#");
        printf("\n");
    }
    else
    {
        for (int i = strlen(aux); i < len; i++)
            printf("#");
        printf("%s\n", aux);
    }
}

int main()
{
    freopen("data.in", "r", stdin);

    while (!feof(stdin))
    {
        memset(s, 0, sizeof s);
        gets(s);
        if (!*s)
            break;
        read();
        solve();
    }

    return 0;
}
