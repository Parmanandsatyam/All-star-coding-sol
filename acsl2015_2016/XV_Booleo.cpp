#include <iostream>
#include <cstdio>
#include <cstring>

using namespace std;

int niz[10000][10000];

int main()
{
    int t = 5;
    while (t--) {
        memset(niz, 0, sizeof niz);

        int n;
        scanf("%d", &n);


        int a;
        for (int i = 1; i <= n; i++)
        {
            scanf("%d", &a);
            niz[1][i] = a;
        }

        string out = "";
        int outx = -1, outy = -1, err = 0;

        for (int i = 2; i <= n; i++)
        {
            for (int j = 1; j <= (n - i + 1); j++)
            {
                string s;
                cin >> s;
                char c = s[0];
                a = s[1] - '0';
                niz[i][j] = a;
                if (err == 1) continue;
                if (c == 'A')
                {
                    if ( (niz[i - 1][j] & niz[i - 1][j + 1]) != niz[i][j])
                    {
                        err = 1;
                        out = "AND";
                        outx = i;
                        outy = j;
                    }
                } else if (c == 'X')
                {
                    if ( (niz[i - 1][j] ^ niz[i - 1][j + 1]) != niz[i][j])
                    {
                        err = 1;
                        out = "XOR";
                        outx = i;
                        outy = j;
                    }
                } else if (c == 'R')
                {
                    if ( (niz[i - 1][j] | niz[i - 1][j + 1]) != niz[i][j])
                    {
                        err = 1;
                        out = "OR";
                        outx = i;
                        outy = j;
                    }
                }
            }
        }
        if (err == 0) cout << "TRUE" << endl;
        else  cout << out << ", " << outx << ", " << outy << endl;
    }
    return 0;
}
