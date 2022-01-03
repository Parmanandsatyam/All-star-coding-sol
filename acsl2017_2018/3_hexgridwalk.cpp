#include <iostream>
#include <fstream>
#define DataLines 10

using namespace std;

ifstream fin("as3-test.txt");

struct tina
{
    int nr;
    char letter;
};

void movehex(tina &x, int dir)
{
    if (dir==1)
    {
        x.nr++;
        return;
    }
    if (dir==2)
    {
        if (x.letter!='Z')
        {
            if ((x.letter-'A')%2==0)
                x.nr++;
            x.letter++;
        }
        return;
    }
    if (dir==3)
    {
        if (x.letter!='Z')
        {
            if ((x.letter-'A')%2)
            {
                if (x.nr>1)
                {
                    x.nr--;
                    x.letter++;
                }
            }
            else
                x.letter++;
        }
        return;
    }
    if (dir==4)
    {
        if (x.nr>1)
            x.nr--;
        return;
    }
    if (dir==5)
    {
        if (x.letter!='A')
        {
            if ((x.letter-'A')%2)
            {
                if (x.nr>1)
                {
                    x.nr--;
                    x.letter--;
                }
            }
            else
                x.letter--;
        }
        return;
    }
    if (dir==6)
    {
        if (x.letter!='A')
        {
            if ((x.letter-'A')%2==0)
                x.nr++;
            x.letter--;
        }
        return;
    }
}

int main()
{
    tina input;
    char digits[100];
    for (int k=1; k<=DataLines; k++)
    {
        fin >> input.letter >> input.nr;
        fin.get();
        fin.getline(digits, 100);
        for (int i=0; digits[i]; i++)
            movehex(input, digits[i]-48);
        cout << input.letter << input.nr << "\n";
    }
    return 0;
}
