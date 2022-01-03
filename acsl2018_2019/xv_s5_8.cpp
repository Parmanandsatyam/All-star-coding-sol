#include <bits/stdc++.h>
using namespace std;

int T = 10;
int K;
const double eps = 0.00001;
double maxx, maxy;
double pocx, pocy;
double x, y;
double k;

bool kut(double x, double y)
{
    if (abs(x) <= eps && abs(y) <= eps) return 1;
    if (abs(x) <= eps && abs(y - maxy) <= eps) return 1;
    if (abs(x - maxx) <= eps && abs(y) <= eps) return 1;
    if (abs(x - maxx) <= eps && abs(y - maxy) <= eps) return 1;
    return 0;
}

bool valid(double X, double Y)
{
    if (abs(X - x) <= eps && abs(Y - y) <= eps) return 0;
    if (X < -eps) return 0;
    if (X > maxx + eps) return 0;
    if (Y < -eps) return 0;
    if (Y > maxy + eps) return 0;
    return 1;
}

bool change(double xnew, double ynew)
{
    if (!valid(xnew, ynew)) return 0;
    if (kut(xnew, ynew))
        k = 1/k;
    else
        k = -k;
    x = xnew;
    y = ynew;
    return 1;
}

void solve()
{
    double x0, y0, xnew, ynew;
    //lijevo
    x0 = 0;
    xnew = 0;
    ynew = y - k * x;
    if (change(xnew, ynew)) return;
    // desno
    x0 = maxx;
    xnew = maxx;
    ynew = y + k * (x0 - x);
    if (change(xnew, ynew)) return;
    //dolje
    y0 = 0;
    ynew = 0;
    xnew = x - y / k;
    if (change(xnew, ynew)) return;
    //gore
    y0 = maxy;
    ynew = maxy;
    xnew = x + (y0 - y) / k;
    if (change(xnew, ynew)) return;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    ifstream in;
    in.open("as8-test.txt");
    while (T--)
    {
        in >> maxx >> maxy;
        in >> pocx >> pocy;
        in >> x >> y;
        in >> K;
        //cin >> maxx >> maxy;
        //cin >> pocx >> pocy;
        //cin >> x >> y;
        //cin >> K;

        if (x == pocx) {cout << x << endl; continue;}
        if (y == pocy) {cout << y << endl; continue;}

        k = (y - pocy) / (x - pocx);

        if (!kut(x, y))  k = -k;
        else k = 1 / k;

        for (int i = 2; i <= K; i++)
        {
            //cout << x << ' ' << y << endl;
            solve();
        }
        //cout << x << ' ' << y << endl;

        if (kut(x, y))
        {
                if (abs(x) <= eps && abs(y) <= eps)
                    cout << 0 << endl;
            else if (abs(x) <= eps && abs(y - maxy) <= eps)
                cout << maxy << endl;
            else if (abs(x - maxx) <= eps && abs(y) <= eps)
                cout << maxx << endl;
            else
                if (maxx >= maxy) cout << maxx << endl;
                else cout << maxy << endl;
        }
        else
        {
            if (x == 0 || x == maxx) cout << (int)(y + 0.5) << endl;
            else cout << (int)(x + 0.5) << endl;
        }
    }
}





