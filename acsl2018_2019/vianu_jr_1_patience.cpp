#include <bits/stdc++.h>

using namespace std;

struct Pile {
    int sum;
    int sz;
    int last_nr;
    int last_type;
};

const int MAX_N = 10000;

Pile a[1 + MAX_N];

int m;


bool bigger (int a, int b) {
    if (a > b)
        return true;
    if (a == 1 && b == 13)
        return true;
    return false;
}

int find_pile (int nr, int type) {
    for (int i = 1; i <= m; i++)
        if (a[i].last_type != type && bigger (a[i].last_nr, nr))
            return i;
    return -1;
}

int get_nr (char ch) {
    if (isdigit (ch))
        return ch - '0';
    if (ch == 'A')
        return 1;
    if (ch == 'T')
        return 10;
    if (ch == 'J')
        return 11;
    if (ch == 'Q')
        return 12;
    if (ch == 'K')
        return 13;
}
ifstream in ("as1-test.txt");

int main() {
    int tests = 10;
    while (tests--) {
        string s;
        getline (in, s);
        m = 0;
        memset (a, 0, sizeof (a));
        for (int i = 0; i < s.size (); i += 3) {
            if (!isdigit (s[i]) && !isalpha (s[i]))
                continue;
            int nr = get_nr (s[i]);
            int type = s[i + 1] - 'A';
            int poz = find_pile (nr, type);
            if (poz == -1) {
                m++;
                poz = m;
                a[poz].sum = 0;
                a[poz].sz = 0;
            }
            a[poz].sum += nr;
            a[poz].sz++;
            a[poz].last_nr = nr;
            a[poz].last_type = type;
        }
        int best_sum = a[1].sum;
        int mx = a[1].sz;
        for (int i = 2; i <= m; i++) {
            if (mx < a[i].sz) {
                best_sum = a[i].sum;
                mx = a[i].sz;
            }
            else if (mx == a[i].sz) {
                if (best_sum > a[i].sum)
                    best_sum = a[i].sum;
            }
        }
        cout << best_sum << endl;
    }
    return 0;
}
