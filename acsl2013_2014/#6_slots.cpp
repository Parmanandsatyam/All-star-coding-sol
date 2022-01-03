#include <cstdio>
#include <vector>
#include <cstring>
#include <algorithm>
#define add push_back
using namespace std;

struct point
{
    int lin, col;

    point(int lin = 0, int col = 0): lin(lin), col(col)
    {

    }
    point operator+(point p)
    {
        return point(lin + p.lin, col + p.col);
    }
};

vector<point> getPointsFromPattern(point p, int pattern)
{
    vector<point> v;
    if (pattern == 1)
    {
        v.add(p);
        v.add(p + point(0, 1));
        v.add(p + point(1, 2));
        v.add(p + point(2, 3));
        v.add(p + point(2, 4));
    }
    if (pattern == 2)
    {
        v.add(p);
        v.add(p + point(-1, 1));
        v.add(p + point(-1, 2));
        v.add(p + point(-1, 3));
        v.add(p + point(0, 4));
    }
    if (pattern == 3)
    {
        v.add(p);
        v.add(p + point(1, 1));
        v.add(p + point(2, 2));
        v.add(p + point(1, 3));
        v.add(p + point(0, 4));
    }
    if (pattern == 4)
    {
        v.add(p);
        v.add(p + point(-1, 1));
        v.add(p + point(-2, 2));
        v.add(p + point(-1, 3));
        v.add(p + point(0, 4));
    }
    if (pattern == 5)
    {
        v.add(p);
        v.add(p + point(1, 1));
        v.add(p + point(1, 2));
        v.add(p + point(1, 3));
        v.add(p + point(0, 4));
    }
    if (pattern == 6)
    {
        v.add(p);
        v.add(p + point(0, 1));
        v.add(p + point(-1, 2));
        v.add(p + point(-2, 3));
        v.add(p + point(-2, 4));
    }
    return v;
}


bool matrix[10][10];
int row[10];
char a[1000];
int bet;

int getMultiplier(int pattern)
{
    vector<point> v = getPointsFromPattern(point(row[pattern], 1), pattern);
    int i, t = 0, maxim = 0;

    for (i = 0; i < v.size(); i++)
    {
        if (matrix[v[i].lin][v[i].col] == 1)
            t++;
        else
        {
            maxim = max(maxim, t);
            t = 0;
        }
    }
    maxim = max(maxim, t);

    if (maxim < 3)
        return 0;
    if (maxim == 3)
        return 2;
    if (maxim == 4)
        return 3;
    if (maxim == 5)
        return 4;
}

void formMatrix(char * p)
{
    vector<bool> v;

    int i, j, k, x;
    for (i = 0; p[i]; i++)
    {
        if (p[i] >= 'A' && p[i] <= 'Z')
            x = p[i]-'A'+10;
        else
            x = p[i]-'0';
        v.add(x & 8);
        v.add(x & 4);
        v.add(x & 2);
        v.add(x & 1);
    }
    k = 0;

    for (i = 1; i <= 5; i++)
        for (j = 1; j <= 5; j++)
            matrix[i][j] = v[k++];
}

void debug()
{
    int i, j;

    for (i = 1; i <= 5; i++)
    {
        for (j = 1; j <= 5; j++)
            printf("%d ", matrix[i][j]);
        printf("\n");
    }
}

int main()
{
    freopen("6.in", "r", stdin);
    int i, j, x;
    char * p;

    while (!feof(stdin))
    {
        gets(a);
        scanf("\n");

        for (i = 0; i < 10; i++)
        {
            row[i] = 0;
            for (j = 0; j < 10; j++)
                matrix[i][j] = 0;
        }

        p = strtok(a, " ,");
        for (i = 1; i <= 6; i++)
            row[i] = p[i-1]-'0';

        p = strtok(NULL, " ,");
        sscanf(p, "%d", &bet);

        p = strtok(NULL, " ,");
        formMatrix(p);

        //debug();
        for (i = 1; i <= 5; i++)
        {
            gets(a);
            scanf("\n");

            sscanf(a, "%d", &x);
            printf("%d\n", getMultiplier(x)*bet);
        }
    }

}

