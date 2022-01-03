#include <bits/stdc++.h>

using namespace std;
typedef long long ll;

unordered_map<char, string> b;

int dec(char c) {
  if ('0' <= c && c <= '9')
    return c - '0';
  return c - 'A' + 10;
}

int dec(string s) {
  int ret = 0;

  for (int i = 0; i < s.size(); i++) {
    ret *= 16;
    ret += dec(s[i]);
  }
  return ret;
}

vector<int> dec(string s, int c) {
  int x = dec(s);
  vector<int> v(c);

  for (int i = c - 1; i >= 0; i--) {
    v[i] = x % 10;
    x /= 10;
  }

  return v;
}

int dx[] = { -1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};

int main() {
  freopen("as7-sample.txt", "r", stdin);

  for (int t = 0; t < 10; t++) {
    int r, c;
    cin >> r >> c;

    vector<vector<int>> f(r, vector<int>(c));
    for (int i = 0; i < r; i++) {
      string s;
      cin >> s;

      vector<int> row = dec(s, c);
      f[i] = row;
    }

//    for (int i = 0; i < r; i++) {
//      for (int j = 0; j < c; j++)
//        cout << f[i][j] << " ";
//      cout << "\n";
//    }
//    cout << "\n\n";


    for (ll i = 0; i < (1ll << (r * c)); i++) {
      bool good = 1;

      for (int b = 0; good && b < r * c; b++) {
        int x = b / c, y = b % c;

        int cnt = 0;
        for (int dir = 0; dir < 4; dir++)
          if (x + dx[dir] >= 0 && x + dx[dir] < r && y + dy[dir] >= 0 && y + dy[dir] < c)
            cnt += ((i >> (c * (x + dx[dir]) + y + dy[dir])) & 1);

        if ((i >> b) & 1)
          good = good && (4 - cnt == f[x][y]);
        else
          good = good && (cnt == f[x][y]);
      }

      if (good) {
        vector<vector<bool>> s(r, vector<bool>(c));
        for (int x = 0; x < r; x++) {
          for (int y = 0; y < c; y++) {
            s[x][y] = i & 1;
//            cout << s[x][y];
            i >>= 1;
          }
//          cout << "\n";
        }
//        cout << "\n";


        int ans = 0;

        for (int x = 0; x < r; x++) {
          for (int y = 0; y < c; y++) {
            if (!s[x][y]) continue;

            int cnt = 0;
            for (int dir = 0; dir < 4; dir++)
              if (x + dx[dir] >= 0 && x + dx[dir] < r && y + dy[dir] >= 0 && y + dy[dir] < c)
                cnt += s[x + dx[dir]][y + dy[dir]];

            ans += 4-cnt;
          }
        }

        cout << t+1 << ". " << ans << "\n";

        goto done;
      }
    }
done:
    ;
  }


}
