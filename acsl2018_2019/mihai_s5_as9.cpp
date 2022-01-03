// By Stefan Radu

#include <algorithm>
#include <fstream>
#include <iomanip>
#include <cassert>
#include <vector>
#include <string>
#include <cctype>
#include <queue>
#include <deque>
#include <cmath>
#include <stack>
#include <map>
#include <set>

using namespace std;

#define sz(x) (int)(x).size()

typedef pair < int, int > pii;
typedef long long ll;
typedef long double ld;
typedef unsigned int ui;
typedef unsigned long long ull;

fstream cin ("as9-test.txt");
ofstream cout("output");

struct Piece {

  Piece(string s = "") {

    if (sz(s) == 0) return;

    p0.resize(4); for (auto &x : p0) x.resize(4);
    p1.resize(4); for (auto &x : p1) x.resize(4);
    p2.resize(4); for (auto &x : p2) x.resize(4);
    p3.resize(4); for (auto &x : p3) x.resize(4);

    for (int i = 0; i < 3; ++ i) {

      int k = 0;
      if (isalpha(s[i])) k = s[i] - 'A' + 10;
      else k = s[i] - '0';

      p0[i][0] = (k & (1 << 3)) != 0;
      p0[i][1] = (k & (1 << 2)) != 0;
      p0[i][2] = (k & (1 << 1)) != 0;
      p0[i][3] = (k & (1 << 0)) != 0;
    }

    for (int i = 3; i >= 0; -- i) {
      for (int j = 0; j < 4; ++ j) {
        p1[j][3 - i] = p0[i][j];
      }
    }

    for (int i = 3; i >= 0; -- i) {
      for (int j = 0; j < 4; ++ j) {
        p2[j][3 - i] = p1[i][j];
      }
    }

    for (int i = 3; i >= 0; -- i) {
      for (int j = 0; j < 4; ++ j) {
        p3[j][3 - i] = p2[i][j];
      }
    }
  }

  vector < vector < int > > p0;
  vector < vector < int > > p1;
  vector < vector < int > > p2;
  vector < vector < int > > p3;

  void operator = (Piece other) {
    this -> p0 = other.p0;
    this -> p1 = other.p1;
    this -> p2 = other.p2;
    this -> p3 = other.p3;
  }
};

void init(int n, vector < vector < int > > &mat) {

  for (int i = 0; i <= 5; ++ i) {
    for (int j = 0; j <= n + 11; ++ j) {
      mat[i][j] = 1;
    }
  }
  
  for (int i = 0; i <= n + 11; ++ i) {
    for (int j = 0; j <= 5; ++ j) {
      mat[i][j] = 1;
    }
  }

  for (int i = 6 + n; i <= n + 11; ++ i) {
    for (int j = 0; j <= n + 11; ++ j) {
      mat[i][j] = 1;
    }
  }

  for (int i = 0; i <= n + 10; ++ i) {
    for (int j = 6 + n; j <= n + 11; ++ j) {
      mat[i][j] = 1;
    }
  }

  for (int i = 6; i <= n + 5; ++ i) {
    for (int j = 6; j <= n + 5; j += 4) {

      char c;
      cin >> c;

      int k = 0;
      if (isalpha(c)) k = c - 'A' + 10;
      else k = c - '0';

      mat[i][j] = (k & (1 << 3)) != 0;
      mat[i][j + 1] = (k & (1 << 2)) != 0;
      mat[i][j + 2] = (k & (1 << 1)) != 0;
      mat[i][j + 3] = (k & (1 << 0)) != 0;
    }
  }
}

int main() {

#ifdef STEF
  freopen("input", "r", stdin);
  freopen("output", "w", stdout);
#endif

  ios::sync_with_stdio(false);
  cin.tie(0);cout.tie(0);

  int t = 10;
  while (t --) {

    int n; cin >> n;
    int p; cin >> p;

    vector < vector < int > > mat(n + 12, vector < int > (n + 12));

    vector < Piece > pieces(p + 1);

    for (int i = 1; i <= p; ++ i) {
      string s; cin >> s;
      pieces[i] = Piece(s);
    }

    init(n, mat);

    for (int i = 6; i <= n + 5; ++ i) {
      for (int j = 6; j <= n + 5; ++ j) {
        cerr << mat[i][j] << ' ';
      }
      cerr << '\n';
    }


    ll sol = 0;

    while (true) {

      int cnt = 0;

      for (int pi = 1; pi <= p; ++ pi) {

        bool found = false;
        for (int i = n + 8; i >= 0; -- i) {

          if (found) break;

          for (int j = 0; j <= n + 8; ++ j) {

            bool ok = true;
            for (int a = 0; a < 4; ++ a) {
              for (int b = 0; b < 4; ++ b) {
                if (mat[i + a][j + b] == pieces[pi].p0[a][b] and mat[i + a][j + b] == 1) {
                  ok = false;
                  break;
                }
              }
            }

            if (ok) {
              sol += 0;

              for (int a = 0; a < 4; ++ a) {
                for (int b = 0; b < 4; ++ b) {
                  mat[i + a][j + b] += pieces[pi].p0[a][b];
                }
              }

              found = true;
              break;
            }
          }
        }

        if (found) {
          ++ cnt;
          continue;
        }

        for (int i = n + 8; i >= 0; -- i) {

          if (found) break;

          for (int j = 0; j <= n + 8; ++ j) {

            bool ok = true;
            for (int a = 0; a < 4; ++ a) {
              for (int b = 0; b < 4; ++ b) {
                if (mat[i + a][j + b] == pieces[pi].p1[a][b] and mat[i + a][j + b] == 1) {
                  ok = false;
                  break;
                }
              }
            }

            if (ok) {
              sol += 90;

              for (int a = 0; a < 4; ++ a) {
                for (int b = 0; b < 4; ++ b) {
                  mat[i + a][j + b] += pieces[pi].p1[a][b];
                }
              }

              found = true;
              break;
            }
          }
        }

        if (found) {
          ++ cnt;
          continue;
        }

        for (int i = n + 8; i >= 0; -- i) {

          if (found) break;

          for (int j = 0; j <= n + 8; ++ j) {

            bool ok = true;
            for (int a = 0; a < 4; ++ a) {
              for (int b = 0; b < 4; ++ b) {
                if (mat[i + a][j + b] == pieces[pi].p2[a][b] and mat[i + a][j + b] == 1) {
                  ok = false;
                  break;
                }
              }
            }

            if (ok) {
              sol += 180;

              for (int a = 0; a < 4; ++ a) {
                for (int b = 0; b < 4; ++ b) {
                  mat[i + a][j + b] += pieces[pi].p2[a][b];
                }
              }

              found = true;
              break;
            }
          }
        }

        if (found) {
          ++ cnt;
          continue;
        }

        for (int i = n + 8; i >= 0; -- i) {

          if (found) break;

          for (int j = 0; j <= n + 8; ++ j) {

            bool ok = true;
            for (int a = 0; a < 4; ++ a) {
              for (int b = 0; b < 4; ++ b) {
                if (mat[i + a][j + b] == pieces[pi].p3[a][b] and mat[i + a][j + b] == 1) {
                  ok = false;
                  break;
                }
              }
            }

            if (ok) {
              sol += 270;

              for (int a = 0; a < 4; ++ a) {
                for (int b = 0; b < 4; ++ b) {
                  mat[i + a][j + b] += pieces[pi].p3[a][b];
                }
              }

              found = true;
              break;
            }
          }
        }

        if (found) {
          ++ cnt;
          continue;
        }
      }

      if (cnt == 0) break;
    }

    cerr << sol << '\n';
  }
}
