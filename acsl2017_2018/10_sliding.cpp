#include <bits/stdc++.h>

using namespace std;

int main() {
  freopen("as10-test.txt", "r", stdin);

  for (int t = 0; t < 10; t++) {
    string inp;
    getline(cin, inp);

    while (inp.back() == ' ')
      inp.resize(inp.size() - 1);

    stringstream ss;
    ss << inp;

    int n, e;
    ss >> n >> e;

    vector<vector<int>> mp(n, vector<int>(n, -1));
    int sp = 1;
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        if (n * i + j + 1 != e)
          mp[i][j] = sp++;

    int nx = (e - 1) / n, ny = (e - 1) % n;

    while(!ss.eof()) {
      int x;
      ss >> x;

      for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
          if (mp[i][j] == x) {
            if (i == nx) {
              if (j > ny) {
                for (int c = ny; c < j; c++)
                  mp[i][c] = mp[i][c + 1];
              } else {
                for (int c = ny; c > j; c--)
                  mp[i][c] = mp[i][c - 1];
              }
            } else if (j == ny) {
              if (i > nx) {
                for (int c = nx; c < i; c++)
                  mp[c][j] = mp[c + 1][j];
              } else {
                for (int c = nx; c > i; c--)
                  mp[c][j] = mp[c - 1][j];
              }
            }

            mp[i][j] = -1;
            nx = i;
            ny = j;

            goto done;
          }
done:
      ;

    }

    cout << t+1 << ". " << nx*n + ny + 1 << "\n";


  }
}
