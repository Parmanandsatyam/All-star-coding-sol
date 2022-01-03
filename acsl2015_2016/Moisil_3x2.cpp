#include <iostream>
#include <cstdio>
#include <cstring>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

char s[1000];

struct coord
{
    int x, y;
    coord(int x , int y) : x(x), y(y) { }
    coord(int nr = 0)
    {
        x = (nr-1)/8 + 1;
        y = (nr-1)%8 + 1;
    }
};

bool operator<(coord a, coord b)
{
    if (a.x != b.x) return a.x < b.x;
    return a.y < b.y;
}

coord albe[100], negre[100];
int a[100][100]; /// 0-> nimic 1->alb 2->negru
int catea, caten;

void read()
{
    char *p = strtok(s, ", ");
    sscanf(p, "%d", &catea);
    p = strtok(NULL, ", ");
    for (int i = 1; p && i <= catea; i++, p = strtok(NULL, ", "))
    {
        int nr;
        sscanf(p, "%d", &nr);
        albe[i] = coord(nr);
        a[albe[i].x][albe[i].y] = 1;
    }
    sscanf(p, "%d", &caten);
    p = strtok(NULL, ", ");
    for (int i = 1; p && i <= caten; i++, p = strtok(NULL, ", "))
    {
        int nr;
        sscanf(p, "%d", &nr);
        negre[i] = coord(nr);
        a[negre[i].x][negre[i].y] = 2;
    }
}

int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, 1, 0, -1};

int bx[8] = {-3, -2, 2, 3, 3, 2, -2, -3};
int by[8] = {2, 3, 3, 2, -2, -3, -3, -2};

int verdix[10][10] = {{-1, -2, -3, -3, -3}, {0, 0, 0, -1, -2}, {0, 0, 0, 1, 2}, {1, 2, 3, 3, 3}, {1, 2, 3, 3, 3}, {0, 0, 0, 1, 2}, {0, 0, 0, -1, -2}, {-1, -2, -3, -3, -3}};
int verdiy[10][10] = {{0, 0, 0, 1, 2}, {1, 2, 3, 3, 3}, {1, 2, 3, 3, 3}, {0, 0, 0, 1, 2}, {0, 0, 0, -1, -2}, {-1, -2, -3, -3, -3}, {-1, -2, -3, -3, -3}, {0, 0, 0, -1, -2}};

vector<coord> sol;
vector<vector<coord> > finale;
map<vector<coord>, int> este;
int best = 0;


void save()
{
    sort(sol.begin(), sol.end());
    if (sol.size() > best)
    {
        finale.clear();
        este.clear();
        best = sol.size();
        finale.push_back(sol);
        este[sol] = 1;
    }
    else if (sol.size() == best && !este[sol]) {
        finale.push_back(sol);
        este[sol] = 1;
    }
}


void bt(int k, int x, int y, int first)
{
    vector<coord> sterse;
    if (!first)
    {
        for (int d = 0; d < 4; d++)
        {
            int nx = x + dx[d], ny = y + dy[d];
            int inix = nx, iniy = ny;
            if (a[nx][ny] != 1) continue;

            if (a[nx][ny] == 1) {
                while (a[nx][ny] == 1) nx += dx[d], ny += dy[d];
                if (a[nx][ny] == 2 || nx < 1 || ny < 1 || nx > 8 || ny > 8)
                {
                    for (; inix != nx || iniy != ny; inix += dx[d], iniy += dy[d]) {
                        sterse.push_back(coord(inix, iniy));
                        sol.push_back(coord(inix, iniy));
                        a[inix][iniy] = 0;
                    }
                }
            }
        }
        if (sterse.empty()) {
            save();
            return;
        }
    }
    for (int d = 0; d < 8; d++)
    {
        //if (d > 6)
            //cerr << "pls";
        int nx = x + bx[d], ny = y + by[d];
        if (nx < 1 || nx > 8 || ny < 1 || ny > 8 || a[nx][ny] != 0) continue;
        int bun = 1;
        for (int k = 0; k < 5 && bun; k++)
            if (a[x + verdix[d][k]][y + verdiy[d][k]] == 1)
                bun = 0;
        if (!bun) continue;
        bt(k+1, nx, ny, 0);
    }

    for (int i = 0; i < sterse.size(); i++) {
        a[sterse[i].x][sterse[i].y] = 1;
        sol.pop_back();
    }
}

int getNr(coord c)
{
    return (c.x-1)*8 + c.y;
}

void solve()
{
    for (int i = 1; i <= caten; i++)
    {
        bt(1, negre[i].x, negre[i].y, 1);
    }
    if (best == 0)
    {
        printf("NONE\n");
        return;
    }
    for (int i = 0; i < finale.size(); i ++) {
        for (int j = 0; j < finale[i].size(); j++)
            printf("%d ", getNr(finale[i][j]));
        printf("\n");
    }
}

int main()
{
    freopen("data.in", "r", stdin);

    while (!feof(stdin))
    {
        memset(s, 0, sizeof s);
        memset(a, 0, sizeof a);
        best = 0;
        finale.clear();
        sol.clear();
        gets(s);
        if (!*s)
            break;
        read();
        solve();
    }

    return 0;
}
