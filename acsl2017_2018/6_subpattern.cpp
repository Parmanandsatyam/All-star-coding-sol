#include <bits/stdc++.h>

using namespace std;

unordered_map<char, string> b;

string bin(string s) {
  string ret = "";
  for (int i = 0; i < s.size(); i++)
    ret += b[s[i]];
  return ret;
}

int main() {
  b['0'] = "0000";  b['1'] = "0001";  b['2'] = "0010";  b['3'] = "0011";  b['4'] = "0100";  b['5'] = "0101";  b['6'] = "0110";  b['7'] = "0111";  b['8'] = "1000";  b['9'] = "1001";  b['A'] = "1010";  b['B'] = "1011";  b['C'] = "1100";  b['D'] = "1101";  b['E'] = "1110";  b['F'] = "1111";
  freopen("as6-test.txt", "r", stdin);

  for (int t = 0; t < 10; t++) {
    int r, c;
    cin >> r >> c;

    vector<vector<bool>> A(r, vector<bool>(c));
    for (int i = 0; i < r; i++) {
      string s;
      cin >> s;

      s = bin(s).substr(0, c);

      for (int j = 0; j < c; j++)
        A[i][j] = s[j] == '1';
    }

    int mina = r * c, mini = r, minj = c;

    for (int i = 1; i <= r; i++) {
      if (r % i != 0) continue;
      for (int j = 1; j <= c; j++) {
        if (c % j != 0) continue;

        bool allgood = 1;

        for (int a = 0; allgood && a <= r - i; a += i)
          for (int b = 0; allgood && b <= c - j; b += j) {
            bool good = 1;

            for (int x = a; good && x < a + i; x++)
              for (int y = b; good && y < b + j; y++)
                good = good && (A[x][y] == A[x - a][y - b]);

            allgood = allgood && good;
          }

        if (allgood && i * j < mina) {
          mina = i * j;
          mini = i;
          minj = j;
        }
      }
    }

    cout << t+1 << ". " << mini << " " << minj << "\n";
  }
}
