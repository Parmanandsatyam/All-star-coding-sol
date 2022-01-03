#include <cstdio>
#include <cstring>
#include <vector>
#define add push_back
using namespace std;

int uLin[100][100];
int uCol[100][100];

int v[100];
char a[1000];
vector<pair<vector<int>, int> > regions;
int n;
bool foundSolution;

void formRegions(char * a)
{
    char * p;
    int m;
    int i, j;
    int x;
    vector<int> aux;

    for (i = 0; i < 100; i++)
        v[i] = 0;
    for (i = 0; i < 100; i++)
        for (j = 0; j < 100; j++)
            uLin[i][j] = uCol[i][j] = 0;

    p = strtok(a, " ,");
    sscanf(p, "%d", &m);

    regions = vector<pair<vector<int>, int> >();
    for (i = 0; i < m; i++)
    {
        aux = vector<int>();
        regions.add(make_pair(aux, 0));
        regions[i].first = vector<int>();
        while (p)
        {
            p = strtok(NULL, " ,");
            if (!strchr(p, '+') && !strchr(p, '#'))
            {
                x = 0;
                while (p[0] >= '0' && p[0] <= '9')
                {
                    x = x * 10 + p[0] - '0';
                    p++;
                }
                regions[i].first.add(x);
            }
            else
            {
                x = 0;
                while (p[0] >= '0' && p[0] <= '9')
                {
                    x = x * 10 + p[0] - '0';
                    p++;
                }
                regions[i].second = x;
                break;
            }
        }
    }

}

bool isOK(int k)
{
    int i, j, s, x;

    for (i = 0; i < regions.size(); i++)
    {
        s = 0;
        for (j = 0; j < regions[i].first.size(); j++)
        {
            if (regions[i].first[j] > k)
            {
                s = regions[i].second;
                break;
            }
            s += v[regions[i].first[j]];
        }
        if (s != regions[i].second)
            return 0;
    }
    return 1;
}

int getLin(int x)
{
    return (x-1)/n+1;
}

int getCol(int x)
{
    return (x-1)%n+1;
}

void formSolution(int k)
{
    if (k == n * n + 1)
    {
        if (isOK(k))
        {
            isOK(k);
            foundSolution = 1;
        }
        return;
    }
    for (v[k] = 1; v[k] <= n && !foundSolution; v[k]++)
        if (!uLin[getLin(k)][v[k]] && !uCol[getCol(k)][v[k]] /*&& isOK(k)*/)
        {
            uLin[getLin(k)][v[k]] = uCol[getCol(k)][v[k]] = 1;
            formSolution(k+1);
            if (foundSolution)
                return;
            uLin[getLin(k)][v[k]] = uCol[getCol(k)][v[k]] = 0;
        }
}

void printOutput(char * a)
{
    char * p;
    int x;

    p = strtok(a, " ,");
    while (p)
    {
        sscanf(p, "%d", &x);
        printf("%d\n", v[x]);

        p = strtok(NULL, " ,");
    }
}

int main()
{
    freopen("7.in", "r", stdin);

    for (n = 3; n <= 4; n++)
    {
        gets(a);
        scanf("\n");

        formRegions(a);
        foundSolution = 0;
        formSolution(1);

        gets(a);
        scanf("\n");
        printOutput(a);
    }


    return 0;
}
