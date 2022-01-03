#include <iostream>
#include <fstream>
#define ll long long

using namespace std;

ifstream fin("as4-test.txt");

const int MAXS = 1e2;

char expression[MAXS], input[MAXS], operators[] = {' ', '+', '-', '*'};
bool ok, used[4];
int n, sol;

int findSign(int left, int right, int p) {
    if (p == 1) {
        for (int i = right; i >= left; --i)
            if (expression[i] == '+' || expression[i] == '-')
                return i;
    }
    else if (p == 2) {
        for (int i = right; i >= left; --i)
            if (expression[i] == '*')
                return i;
    }
    return -1;
}

ll getNumber(int left, int right) {
    ll number = 0;
    if (expression[left] == '0')
        ok = false;
    for (int i = left; i <= right; ++i)
        if (isdigit(expression[i])) {
            number = number * 10 + expression[i] - '0';
        }
    return number;
}

ll calculate(int left, int right) {
    ll sign = findSign(left, right, 1);
    if (sign != -1) {
        ll res1, res2;
        res1 = calculate(left, sign - 1);
        res2 = calculate(sign + 1, right);
        if (expression[sign] == '+')
            return res1 + res2;
        return res1 - res2;
    }
    sign = findSign(left, right, 2);
    if (sign != -1) {
        ll res1, res2;
        res1 = calculate(left, sign - 1);
        res2 = calculate(sign + 1, right);
        return res1 * res2;
    }
    return getNumber(left, right);
}

void read() {
    sol = 0;
    n = 0;
    ok = true;
    fin.getline(input, MAXS);
    for (int i = 0; input[i]; ++i) {
        expression[2 * i] = input[i];
        expression[2 * i - 1] = ' ';
        n = 2 * i;
    }
}
/// >= SAU > ?
ll verify() {
    ll res = calculate(0, n);
    if (ok == false) {
        ok = true;
        return -1;
    }
    if (res >= 0) {
        ///cout << res << "\n";
        return res;
    }
    return -1;
}

void bt(int k) {
    if (k >= n) {
        if (verify() != -1) {
            ///cout << expression << "\n";
            ++sol;
        }
        return;
    }
    for (int i = 0; i < 4; ++i) {
        expression[k] = operators[i];
        if (i == 0 || used[i] == 0) {
            used[i] = true;
            bt(k + 2);
            used[i] = false;
        }
    }
}

int main() {
    for (int k = 0; k < 10; ++k) {
        read();
        bt(1);
        cout << sol - 1 << '\n';
    }
    return 0;
}
