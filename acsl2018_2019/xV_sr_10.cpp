#include <bits/stdc++.h>
using namespace std;

int T = 10;
int K;
int N;
int a, b;

struct point
{
    int X, Y, bottomLeft, bottomRight, topLeft, topRight;
    point(int _X, int _Y, int _bottomLeft, int _bottomRight, int _topLeft, int _topRight)
    {X = _X; Y = _Y; bottomLeft = _bottomLeft; bottomRight = _bottomRight; topLeft = _topLeft; topRight = _topRight;}
    point(){};
};

vector <point> vp;

bool up_right(int x, int kx, int ky)
   {return (kx > vp[x].X && ky > vp[x].Y);}
bool up_left(int x, int kx, int ky)
    {return (kx <= vp[x].X && ky > vp[x].Y);}
bool down_left(int x, int kx, int ky)
    {return (kx <= vp[x].X && ky <= vp[x].Y);}
bool down_right(int x, int kx, int ky)
    {return (kx > vp[x].X && ky <= vp[x].Y);}

void rek(int x, int kx, int ky, int curr)
{
    if (up_right(x, kx, ky))
    {
        if (vp[x].topRight == 0) vp[x].topRight = curr;
        else rek(vp[x].topRight, kx, ky, curr);
    }
    if (up_left(x, kx, ky))
    {
        if (vp[x].topLeft == 0) vp[x].topLeft = curr;
        else rek(vp[x].topLeft, kx, ky, curr);
    }
    if (down_right(x, kx, ky))
    {
        if (vp[x].bottomRight== 0) vp[x].bottomRight = curr;
        else rek(vp[x].bottomRight, kx, ky, curr);
    }
    if (down_left(x, kx, ky))
    {
        if (vp[x].bottomLeft == 0) vp[x].bottomLeft = curr;
        else rek(vp[x].bottomLeft, kx, ky, curr);
    }
    return;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    point NP(0, 0, 0, 0, 0, 0);
    ifstream in;
    in.open("as10-test.txt");
    while (T--)
    {
	in >> K;
	in >> N;
        //cin >> K;
        //cin >> N;
        vp.clear();
        vp.push_back(NP);

        //cin >> a >> b;
	in >> a;
	in >> b;
        point P0(a, b, 0, 0, 0, 0);
        vp.push_back(P0);

        for (int i = 2; i <= N; i++)
        {
            //cin >> a >> b;
	    in >> a;
	    in >> b;
            point P(a, b, 0, 0, 0, 0);
            vp.push_back(P);
            rek(1, a, b, i);
        }

        cout << vp[K].topRight << vp[K].topLeft << vp[K].bottomLeft << vp[K].bottomRight << endl;
    }
}




