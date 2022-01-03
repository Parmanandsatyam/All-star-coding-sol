#include <cstdio>
#include <algorithm>
#include <map>
#include <string>
#include <iostream>

using namespace std;

const int N = 24;
int DEB = 0;

string mlin[16][3] = {"1a", "4a", "7a",
                      "2b", "4b", "6b",
                      "3c", "4c", "5c",
                      "1d", "2d", "3d",
                      "5d", "6d", "7d",
                      "3e", "4e", "5e",
                      "2f", "4f", "6f",
                      "1g", "4g", "7g",

                      "1a", "1d", "1g",
                      "2b", "2d", "2f",
                      "3c", "3d", "3e",
                      "4a", "4b", "4c",
                      "4e", "4f", "4g",
                      "5c", "5d", "5e",
                      "6b", "6d", "6f",
                      "7a", "7d", "7g"};

map <string, int> M;
string poz[N] = { "1a", "4a", "7a", "2b", "4b", "6b", "3c", "4c", "5c", "1d", "2d", "3d", "5d", "6d", "7d",
                   "3e", "4e", "5e", "2f", "4f", "6f", "1g", "4g", "7g" };

int Mlin(string str)
{
    int ind = M[str];

    for (int i=0; i<16; i++) {
        int br=0, ima=0;
        for (int j=0; j<3; j++) {
            br += (M[mlin[i][j]] == ind);
            ima |= (mlin[i][j] == str);
        }
        if (ima && br == 3)
            return 1;
    }

    return 0;
}

int main()
{
    for (int tt=0; tt<2; tt++) {
        M.clear();
        for (int i=1; i<=2; i++) {
            int tmp;
            scanf("%d", &tmp);
            for (; tmp; tmp--) {
                char sss[10];
                scanf(" %s", sss);
                string s = (string) sss;
                swap(s[0], s[1]);
                M[s] = i;
            }
        }

        for (int j=0; j<5; j++) {
            string s;
            cin >> s;
            swap(s[0], s[1]);

            string rje = "99";
            int ind = M[s];
            M[s] = 0;

            for (int i=0; i<N; i++) {
                if (poz[i] != s && !M[poz[i]]) {
                    M[poz[i]] = ind;
                    if (Mlin(poz[i]))
                        rje = min(rje, poz[i]);
                    M[poz[i]] = 0;
                }
            }
            if (rje != "99") {
                printf("%c%c\n", rje[1], rje[0]);
                M[s] = ind;
                continue;
            }

            for (int i=0; i<N; i++) {
                if (poz[i] != s && !M[poz[i]]) {
                    M[poz[i]] = 3 - ind;
                    if (Mlin(poz[i]))
                        rje = min(rje, poz[i]);
                    M[poz[i]] = 0;
                }
            }

            if (rje != "99") {
                printf("%c%c\n", rje[1], rje[0]);
                M[s] = ind;
                continue;
            }

            for (int i=0; i<N; i++)
                if (poz[i] != s && !M[poz[i]])
                    rje = min(rje, poz[i]);

            printf("%c%c\n", rje[1], rje[0]);
            M[s] = ind;
        }
    }

    return 0;
}
