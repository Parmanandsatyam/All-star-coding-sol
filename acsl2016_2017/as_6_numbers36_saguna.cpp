#include <cstdio>
#include <cstring>
#include <cctype>
#include <algorithm>

using namespace std;

char S[10000];
int k;
int viz[100];
char C3[1000],C4[1000],C5[1000];

void frecventa()
{
    for(int i = 0; i < strlen(S); ++i)
        if(isdigit(S[i]))
            viz[(int)(S[i]-'0')] ++;
        else
            viz[(int)(S[i]-'A'+10)] ++;
}

long long valoare(char c)
{
    if(isdigit(c))
        return (long long)(c - '0');
    return (long long)(c-'A'+10);
}

long long toDec(char s[])
{
    long long v = 0, inm = 1;
    for(int i = strlen(s) - 1; i >= 0; --i)
    {
        v += valoare(s[i]) * inm;
        inm *= 36;
    }
    return v;
}

void cerinta1(char s[])
{
    int len = strlen(s);
    sort(s,s+len);
    printf("%s\n", s);
}

void cerinta2(char s[], int nrzero)
{
    int len = strlen(s);
    sort(s,s+len);
    for(int i = len - 1; i >= 0; --i)
        printf("%c", s[i]);
    while(nrzero--)
        printf("0");
    printf("\n");
}

int sol[1000],pana_la_50;

void BackDesc(int poz)
{
    if(pana_la_50 == 50)
        return;

    if(poz == strlen(S) + 1)
    {
        //if(sol[1])
        //{
            ++pana_la_50;
            if(pana_la_50 == 50)
            {
                for(int i = 1; i <= strlen(S); ++i)
                    if(sol[i] < 10)
                        C3[i-1] = sol[i] + '0';
                    else
                        C3[i-1] = sol[i] + 'A' - 10;
            }
        //}
        return;
    }

    for(int i = 35; i >= 0; --i)
        if(viz[i])
        {
            viz[i] -- ;
            sol[poz] = i;

            BackDesc(poz+1);

            sol[poz] = -1;
            viz[i] ++;
        }
}

int pana_la_k = 0;

void BackCresc(int poz)
{
    if(pana_la_k == k)
        return;

    if(poz == strlen(S) + 1)
    {
        //if(sol[1])
        //{
            ++pana_la_k;
            if(pana_la_k == k)
            {
                for(int i = 1; i <= strlen(S); ++i)
                    if(sol[i] < 10)
                        C4[i-1] = sol[i] + '0';
                    else
                        C4[i-1] = sol[i] + 'A' - 10;
            }
        //}
        return;
    }

    for(int i = 0; i < 36; ++i)
        if(viz[i])
        {
            viz[i] -- ;
            sol[poz] = i;

            BackCresc(poz+1);

            sol[poz] = -1;
            viz[i] ++;
        }
}

void cerinta3()
{
    BackDesc(1);

    int poz = 0;
    while(C3[poz] =='0')
        ++poz;
    printf("%s\n", C3+poz);
}

void cerinta4()
{
    BackCresc(1);


    int poz = 0;
    while(C4[poz] == '0')
        ++poz;
    printf("%s\n", C4+poz);
}

bool trecut_k = 0, ajuns = 0;
long long diferential = 9999999999999999;
char aux[10000];
long long val1,val2,median;

long long absolut(long long val)
{
    return val > 0 ? val : -val;
}

void bckt5(int poz)
{
    if(ajuns == 1)
        return;

    if(poz == strlen(S) + 1)
    {
        //if(sol[1])
        //{
            for(int i = 1; i <= strlen(S); ++i)
                    if(sol[i] < 10)
                        aux[i-1] = sol[i] + '0';
                    else
                        aux[i-1] = sol[i] + 'A' - 10;
            int valaux = toDec(aux);

            if(valaux >= val1)
                trecut_k = 1;

            if(trecut_k && diferential > absolut(median-valaux))
            {
                diferential = absolut(median-valaux);
                for(int i = 1; i <= strlen(S); ++i)
                    C5[i-1] = aux[i-1];
            }

            if(valaux >= val2)
                ajuns = 1;
        //}
        return;
    }

    for(int i = 0; i < 36; ++i)
        if(viz[i])
        {
            viz[i] -- ;
            sol[poz] = i;

            bckt5(poz+1);

            sol[poz] = -1;
            viz[i] ++;
        }
}

void cerinta5()
{
    val1 = toDec(C3);
    val2 = toDec(C4);
    median =  (val1 + val2) / 2;

    if(val1 > val2)
        swap(val1,val2);

    bckt5(1);

    //printf("%lld %lld\n",val1,val2);

    int poz = 0;
    while(C5[poz] == '0')
        ++ poz;
    printf("%s\n", C5 + poz);
}

void reset()
{
    pana_la_50 = 0;
    pana_la_k = 0;
    for(int j = 0; j < 100; ++j)
            sol[j] = -1;
    memset(C3,0,sizeof(C3));
    memset(C4,0,sizeof(C4));
    memset(viz,0,sizeof(viz));
    k = 0;
    memset(S,0,sizeof(S));
    memset(aux,0,sizeof(aux));
    memset(C5,0,sizeof(C5));
    diferential = 9999999999999999;
    trecut_k = ajuns = 0;
}

int main()
{
    freopen("numbers.in", "r", stdin);
    for(int j = 0; j < 100; ++j)
            sol[j] = -1;
    for(int i = 1; i <= 2; ++i) /// sa pui 2 teste
    {
        int nrzero = 0,ln=0;
        char sl[1000];

        scanf("%s %d\n", S, &k);

        frecventa();

        for(int j = 0; j < strlen(S); ++j)
            if(S[j] != '0')
                sl[ln++] = S[j];
            else
                ++ nrzero;
        sl[ln] = 0;

        cerinta1(sl);
        cerinta2(sl,nrzero);
        cerinta3();
        cerinta4();
       // cerinta5();
       printf("\n");
        ///fa reset

        pana_la_50 = 0;
        reset();
    }

    return 0;
}
