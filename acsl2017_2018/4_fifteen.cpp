#include <iostream>
#include <fstream>
#include <cstring>
#define DataLines 10

using namespace std;

ifstream fin("as4-test.txt");

void movepiece(int &i, int &j, char m)
{
    if (m=='A' && i!=1)
    {
        i--;
        return;
    }
    if (m=='B' && i!=4)
    {
        i++;
        return;
    }
    if (m=='R' && j!=4)
    {
        j++;
        return;
    }
    if (m=='L' && j!=1)
    {
        j--;
        return;
    }
}


int main()
{
    int i, j, n;
    char moves[1000];
    for (int k=1; k<=DataLines; k++)
    {
        fin >> n;
        fin.get();
        fin.getline(moves, 1000);
        if (n%4==0)
            i=n/4,j=4;
        else
            i=n/4+1,j=n%4;
        for (int it=0; moves[it]; it++)
            movepiece(i, j, moves[it]);
        if (j==4)
            cout << i*4;
        else
            cout << (i-1)*4+j%4;
        cout << "\n";
    }
    return 0;
}
