#include <iostream>
#include <cstdio>
#include <cstring>

using namespace std;

long long pow[40];

void create_pow()
{
    pow[0] = 1;
    for (int i = 1; i <= 32; i++)
    {
        pow[i] = pow[i - 1] * 2;
    }
}

int main()
{
     // << >>
    create_pow();

    int t = 10;
    while (t--)
    {
        long long a, b;
        cin >> a >> b;
        if (a < b) swap(a, b);

        if (a == 0) {cout << 0 << endl; continue;}

        int bio = 0;
        for (int i = 32; i >= 0; i--)
        {
            if (pow[i] <= a)
            {
                a -= pow[i];
                if (bio) cout << ", ";
                cout << pow[i] * b;
                bio = 1;
//                Zcout << endl << pow[i] << endl;
            }
        }
        cout << endl;
    }
    return 0;
}
