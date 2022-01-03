#include <iostream>
#include <cstdio>
#include <algorithm>
#include <cstring>

using namespace std;

char s[200], a[100], b[100], c[100], mix[100], abc[100];
int n, per;

typedef int huge[100];

void removeExtraZeroes(huge & a)
{
    while (a[0] > 0 && a[a[0]] == 0)
        a[0]--;
}

void attribute(huge & h, char * a)
{
    h[0] = 0;
    int i;

    for (i = strlen(a)-1; i >= 0; i--)
    {
        if (a[i] >= 'A' && a[i] <= 'Z')
            h[++h[0]] = a[i]-'A'+10;
        else if (a[i] >= '0' && a[i] <= '9')
            h[++h[0]] = a[i]-'0';
    }
    removeExtraZeroes(h);
}

void equalize(huge & a, huge & b)
{
    int i;
    removeExtraZeroes(b);
    for (i = 0; i <= b[0]; i++)
        a[i] = b[i];
}

void print(huge & h)
{
    int i;
    bool ok = 0;
    removeExtraZeroes(h);
    for (i = h[0]; i >= 1; i--)
    {
        ok = 1;
        if (h[i] > 10)
            printf("%c", h[i]-10+'A');
        else
            printf("%d", h[i]);
    }
    if (!ok)
        printf("0");
}



int cmp(huge & a, huge & b)
{
    removeExtraZeroes(a);
    removeExtraZeroes(b);
    int i;
    if (a[0] < b[0])
        return -1;
    if (a[0] > b[0])
        return 1;
    for (i = a[0]; i >= 1; i--)
        if (a[i] < b[i])
            return -1;
        else if (a[i] > b[i])
            return 1;
    return 0;
}


void add(huge & a, huge & b, huge & c, int base)
{
    removeExtraZeroes(a);
    removeExtraZeroes(b);
    c[0] = 0;
    int i, t = 0;

    for (i = 1; i <= max(a[0], b[0]); i++)
    {
        c[c[0]=i] = 0;
        if (i <= a[0])
            c[i] += a[i];
        if (i <= b[0])
            c[i] += b[i];

        c[i] += t;
        t = c[i]/base;
        c[i] %= base;
    }
    if (t)
        c[++c[0]] = t;
}

void sub(huge & a, huge & b, huge & c, int base)
{
    removeExtraZeroes(a);
    removeExtraZeroes(b);

    int i, t = 0;
    for (i = 1; i <= a[0]; i++)
    {
        c[c[0]=i] = a[i];
        if (i <= b[0])
            c[i] -= b[i];
        c[i] -= t;
        if (c[i] < 0)
        {
            c[i] += base;
            t = 1;
        }
        else
            t = 0;
    }

    removeExtraZeroes(c);
}

void divide(huge & a, huge & b, huge & q, huge & r, int base)
{
    removeExtraZeroes(a);
    removeExtraZeroes(b);

    huge aux;
    huge one;
    attribute(one, "1");
    attribute(q, "0");
    equalize(r, a);

    while (cmp(r, b) >= 0)
    {
        sub(r, b, aux, base);
        equalize(r, aux);

        add(q, one, aux, base);
        equalize(q, aux);
    }

    removeExtraZeroes(q);
    removeExtraZeroes(r);
}


void mult(huge & a, huge & b, huge & c, int base)
{
    removeExtraZeroes(a);
    removeExtraZeroes(b);
    huge x, one, s;
    attribute(x, "0");
    attribute(one, "1");
    attribute(c, "0");
    attribute(s, "0");

    while (cmp(x, b) < 0)
    {
        add(c, a, s, base);
        equalize(c, s);

        add(x, one, s, base);
        equalize(x, s);
    }
}


void Simplify(huge & x, huge & y)
{
    removeExtraZeroes(x);
    removeExtraZeroes(y);
    huge minim, aux;
    huge one, zero;
    huge q, r;
    attribute(one, "1");
    attribute(zero, "0");

    if (cmp(x, y) <= 0)
        equalize(minim, x);
    else
        equalize(minim, y);

    while (cmp(minim, one) >= 1)
    {
        divide(x, minim, q, r, 10);
        if (cmp(r, zero) == 0)
        {
            divide(y, minim, q, r, 10);
            if (cmp(r, zero) == 0)
                break;
        }
        sub(minim, one, aux, 10);
        equalize(minim, aux);
    }

    divide(x, minim, q, r, 10);
    equalize(x, q);

    divide(y, minim, q, r, 10);
    equalize(y, q);
    /*for(int i=2; i<=min(x, y); i++)
        while(x%i==0 && y%i==0)
        {
            x/=i;
            y/=i;
        }*/
}

long long GetNumber(char alfa[100])
{
    long long rez=0;
    for(int i=0; i<strlen(alfa); i++)
        rez=rez*10 + alfa[i]-'0';
    return rez;
}

void Simpla()
{
    /*long long x;
    long long y=0;
    x = GetNumber(s+n-per);
    for(int i=0; i<per; i++)
        y = y*10+9;
    Simplify(x, y);
    printf("%lld / %lld\n", x, y);
    */
    huge x, y, aux, ten, nine;
    attribute(y, "0");
    attribute(x, s+n-per);
    attribute(ten, "10");
    attribute(nine, "9");

    for(int i=0; i < per; i++)
    {
        mult(y, ten, aux, 10);
        add(aux, nine, y, 10);
    }
    Simplify(x, y);

    print(x); printf(" / "); print(y); printf("\n");
}

void Mixta()
{
    /*long long x;
    long long y=0;
    memset(abc, 0, sizeof(abc));
    strncpy(abc, s, strlen(mix)+per);
    x = GetNumber(abc) - GetNumber(mix);
    for(int i=0; i<per; i++)
        y = y*10+9;
    for(int i=0; i<strlen(mix); i++)
        y*=10;
    Simplify(x, y);
    printf("%lld / %lld\n", x, y);
    */
    memset(abc, 0, sizeof(abc));
    strncpy(abc, s, strlen(mix)+per);
    huge x, y, aux, ten, nine;
    attribute(y, "0");
    huge a, b;

    attribute(a, abc);
    attribute(b, mix);

    sub(a, b, x, 10);

    attribute(ten, "10");
    attribute(nine, "9");

    for(int i=0; i < per; i++)
    {
        mult(y, ten, aux, 10);
        add(aux, nine, y, 10);
    }
    for(int i=0; i<strlen(mix); i++)
    {
        mult(y, ten, aux, 10);
        equalize(y, aux);
    }
    Simplify(x, y);

    print(x); printf(" / "); print(y); printf("\n");
}

void solve(int ind)
{
    memset(mix, 0, sizeof(mix));
    if(ind==0)
        Simpla();
    else
    {
        strncpy(mix, s, ind);
        Mixta();
    }
}

void formare()
{
    int p;

    strcpy(s, s+2);
    n = strlen(s);
    for(int i=1; i<=n; i++)
    {
        memset(a, 0, sizeof(a));
        memset(b, 0, sizeof(b));
        memset(c, 0, sizeof(c));
        strcpy(a, s+n-i);
        strncpy(b, s+n-2*i, i);
        strncpy(c, s+n-3*i, i);
        if(!strcmp(a, b) && !strcmp(b, c))
        {
            per = i;
            solve(n-3*i);
            return;
        }
    }
}

int main()
{
    freopen("repeat2.in", "r", stdin);


    while(!feof(stdin))
    {
        gets(s);
        scanf("\n");
        formare();
        memset(s, 0, sizeof(s));
    }
    return 0;
}
