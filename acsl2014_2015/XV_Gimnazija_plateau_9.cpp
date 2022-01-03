#include <cstdio>
#include <cstring>
#include <algorithm>
#include <vector>

//XV GIMNAZIJA, SENIOR 5

using namespace std;

const int N = 5;

vector <int> Best;
int poc[N][N], p[N][N], ost[N][N];
int pr[4] = {1, 0, -1, 0}, ps[4] = {0, 1, 0, -1};

int Vani(int r, int s)
{
    return r < 0 || s < 0 || r >= N || s >= N;
}

void Ispis(int polje[N][N])
{
    for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++)
            printf("%d ", polje[i][j]);
        printf("\n");
    }

    printf("\n\n");
}

vector <int> Eval()
{
    vector <int> R = vector <int> (3, 0);

    for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
            if (p[i][j] < 0) {
                int br = -p[i][j];
                if (ost[i][j] > br)
                    return vector<int> (3, 0);
                else if (ost[i][j] == br)
                    R[0] += br;
                else if (ost[i][j] < br && ost[i][j])
                    R[1] += br;
                R[2] = max(R[2], ost[i][j]);
            }
            else {
                R[2] = max(R[2], ost[i][j] + p[i][j]);
            }
        }
    }

    return R;
}

void Rek(int r, int s, int vis, int sm, int jos) //cap, pin, maxh
{
    int nr = r + pr[sm];
    int ns = s + ps[sm];

    if (!vis) {
        Best = max(Best, Eval());
        return;
    }

    if (!jos || Vani(nr, ns)) {
        ost[r][s] += vis;
        Best = max(Best, Eval());
        ost[r][s] -= vis;
        return;
    }

    for (int i=0; i<=vis; i++) {
        ost[r][s] = i;
        Rek(nr, ns, vis - i, sm, jos - 1);
        ost[r][s] = 0;
    }
    if (p[r][s] > 0) {
        int tmp = p[r][s];
        for (int i=0; i<=p[r][s]; i++) {
            p[r][s]--;
            Rek(nr, ns, vis + i, sm, jos - 1);
        }
        p[r][s] = tmp;
    }
}

void Rijesi(int red, int stup)
{
    for (int i=0; i<4; i++) {
        memset(ost, 0, sizeof ost);
        memcpy(p, poc, sizeof poc);
        p[red][stup] = 0;
        Rek(red, stup, poc[red][stup], i, poc[red][stup]);
    }
}

int Int(char s[10])
{
    int l = (int) strlen(s);
    int ret = 0;
    for (int i=0; i<l-1; i++)
        ret = ret * 10 + s[i] - '0';

    if (s[l-1] == 'W')
        ret = -ret;
    return ret;
}

int main()
{
    for (int tt=0; tt<10; tt++) {
        memset(poc, 0, sizeof poc);
        Best = vector<int> (3, 0);

        for (;;) {
            int poz;
            char s[10];
            scanf(" %d", &poz); poz--;
            if (poz < 0)
                break;
            scanf(" %s", s);
            int br = Int(s);
            int red = poz / N;
            int stup = poz % N;

            poc[red][stup] = br;
        }

        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++)
                if (poc[i][j] > 0)
                    Rijesi(i, j);

        for (int i=0; i<3; i++)
            printf("%d ", Best[i]);
        printf("\n");
    }

    return 0;
}
