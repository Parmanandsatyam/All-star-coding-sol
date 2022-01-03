#include <iostream>
#include <cstdio>
#include <cstring>

using namespace std;

char s[1000];
int nrb, sum = 0;
struct operatie
{
    char op;
    char nume[100];
    int val;
    operatie(char x = 'A', char y = '1')
    {
        op = x;
        memset(nume, 0, sizeof nume);
        if (op == 'A') strcpy(nume, "AND");
        if (op == 'R') strcpy(nume, "OR");
        if (op == 'X') strcpy(nume, "XOR");
        nume[strlen(nume)] = y;
        val = y-'0';
    }
};
operatie a[100][100];
int biti[100];
int totb[100][100];
operatie pre[2][5];

void gethex(int aux[10], char x)
{
    int val = 0;
    if (x >= 'A') val = x-'A'+10;
    else val = x -'0';
    for (int i = 3; i >= 0; i--)
        aux[i] = (val >> (3-i)) & 1;
}

void build(char *p, int b[100])
{
    int c[100], ind = 0, nq = 0;
    for (p; *p; ++p) {
        int aux[10];
        gethex(aux, *p);
        for (int i = 0; i < 4; i++)
            c[++ind] = aux[i];
    }
    int cat = nrb;
    for (ind; ind >= 1 && cat; ind--)
        b[cat--] = c[ind];
    for (cat; cat; cat--)
        b[cat] = 0;
//    for (int i = 1; i <= nrb; i++)
//        printf("%d", b[i]);
//    printf("\n");
}

void read()
{
    pre[1][0] = operatie('A', '1');
    pre[1][1] = operatie('R', '1');
    pre[1][2] = operatie('X', '1');
    pre[0][0] = operatie('A', '0');
    pre[0][1] = operatie('R', '0');
    pre[0][2] = operatie('X', '0');
    char *p = strtok(s, ", ");
    sscanf(p, "%d", &nrb);
    p = strtok(NULL, ", ");
    build(p, biti);
    int i = 2, j = 1;
    for (p = strtok(NULL, ", "); p; p = strtok(NULL, ", "))
    {
        a[i][j] = operatie(p[0], p[1]);
        totb[i][j] = a[i][j].val;
        j++;
        if (j == nrb-i+2) {
            j = 1;
            i++;
        }
    }
    for (int i = 1; i <= nrb; i++)
        totb[1][i] = biti[i];
}

int eval(operatie p, int x, int y)
{
    if (p.op == 'A')
        return x & y;
    else if (p.op == 'R')
        return x | y;
    else if (p.op == 'X')
        return x ^ y;
    else {
        cerr << "ERROR";
        return 1;
    }
}

void solve()
{
    int ok = 0;
    int nivel = nrb;
    for (int i = 2; i <= nivel; i++) {
        for (int j = 1; j <= nrb-i+1; j++) {
            int rez = eval(a[i][j], totb[i-1][j], totb[i-1][j+1]);
            if (rez == totb[i][j]) continue;
            else {
                ok = 1;
                int fix = totb[i][j];
                int gasit = 0;
                for (int k = 0; k < 3 && !gasit; k++) {
                    int trial = eval(pre[fix][k], totb[i-1][j], totb[i-1][j+1]);
                    if (trial == fix) {
                        printf("%d, %d, %s\n", i, j, pre[fix][k].nume);
                        gasit = 1;
                    }
                }
                if (!gasit) {
                    printf("%d, %d, NONE\n", i, j);
                    return;
                }
            }
        }
    }
    if (!ok)
        printf("TRUE\n");
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
        printf("\n");
    }

    return 0;
}
