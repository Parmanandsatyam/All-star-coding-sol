#include <bits/stdc++.h>

using namespace std;

ifstream f("as10-test.txt");
ofstream g("output");

struct nod {
    int ind;
    int x, y;
    nod* fii[5];
    nod(int _ind = 0, int _x = 0, int _y = 0): ind(_ind), x(_x), y(_y){
        for(int i = 0; i < 4; ++i) {
            fii[i] = 0;
        }
    }
};

int getInd(nod * n) {
    if(!n) return 0;
    return n->ind;
}

nod * baga(nod * n, int ind, int x, int y) {
    if(!n) return new nod(ind, x, y);
    if(x > n->x && y > n->y) n->fii[0] = baga(n->fii[0], ind, x, y);
    if(x <= n->x && y > n->y) n->fii[1] = baga(n->fii[1], ind, x, y);
    if(x <= n->x && y <= n->y) n->fii[2] = baga(n->fii[2], ind, x, y);
    if(x > n->x && y <= n->y) n->fii[3] = baga(n->fii[3], ind, x, y);

    return n;
}


void getSol(nod * n, int ind, int x, int y) {
    if(!n) {
        g << "0000\n";
        return;
    }

    if(ind == n->ind) {
        for(int i = 0; i < 4; ++i) {
            g << getInd(n->fii[i]);
        }

        g << '\n';
        return;
    }

    if(x > n->x && y > n->y) getSol(n->fii[0], ind, x, y);
    if(x <= n->x && y > n->y) getSol(n->fii[1], ind, x, y);
    if(x <= n->x && y <= n->y) getSol(n->fii[2], ind, x, y);
    if(x > n->x && y <= n->y) getSol(n->fii[3], ind, x, y);

    return;
}

void solve() {
    nod * root = 0;
    int k, n;
    f >> k >> n;
    vector< int > x(n + 1);
    vector< int > y(n + 1);

    for(int i = 1; i <= n; ++i) {
        f >> x[i] >> y[i];
        root = baga(root, i, x[i], y[i]);
    }
    getSol(root, k, x[k], y[k]);
}
int main () {
    for(int i = 1; i <= 10; ++i) {
        solve();
    }
    return 0;
}
