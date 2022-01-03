#include <bits/stdc++.h>

using namespace std;

char ec;
int ei;

int dist(char c, int i) {
  int ie = ((ec - 'A') + (ec - 'A') % 2) / 2 - ei;
  int je = ec - 'A';
  int ke = ((ec - 'A') - (ec - 'A') % 2) / 2 + ei;


  int ig = ((c - 'A') + (c - 'A') % 2) / 2 - i;
  int jg = c - 'A';
  int kg = ((c - 'A') - (c - 'A') % 2) / 2 + i;

  int di = abs(ie-ig);
  int dj = abs(je-jg);
  int dk = abs(ke-kg);

  return min(di + dj, min(dj+dk, di+dk));
}

unordered_set<int> seen;

int get(char c, int i, int d) {
  if (c < 'A' || c > 'Z' || i < 1 || d < 0) return 0;
  if (dist(c, i) > d) return 0;
  if (d == 0 && !(c == ec && i == ei)) return 0;

  if (seen.find(26 * i + (c - 'A')) != seen.end())
    return 0;

  if (c == ec && i == ei) return (d == 0);

  seen.insert(26 * i + (c - 'A'));
  int ans;

  if (c % 2 == 'B' % 2)
    ans = get(c - 1, i, d - 1) + get(c, i + 1, d - 1) + get(c + 1, i, d - 1) +
          get(c + 1, i - 1, d - 1) + get(c, i - 1, d - 1) + get(c - 1, i - 1, d - 1);
  else
    ans = get(c - 1, i + 1, d - 1) + get(c, i + 1, d - 1) + get(c + 1, i + 1, d - 1) +
          get(c + 1, i, d - 1) + get(c, i - 1, d - 1) + get(c - 1, i, d - 1);

  seen.erase(seen.find(26 * i + (c - 'A')));

  return ans;
}

int main() {
  freopen("as9-test.txt", "r", stdin);

  char sc;
  int si, d;

  for (int t = 0; t < 10; t++) {
    cin >> sc >> si >> ec >> ei >> d;

    cout << t+1 << ". " << get(sc, si, d) << "\n";
  }
}
