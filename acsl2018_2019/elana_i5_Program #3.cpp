#include <iostream>
#include <fstream>

using namespace std;

ifstream f("pr.in");

int an, luna, zi, ora, minut, sec;
int an_initial = 2019;
int luna_initial = 5;
int zi_initial = 25;
int ora_initial = 12;
int minut_initial = 0;
int sec_initial = 0;

int calendar_initial[14] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
int calendar[14];

int zile_an = 365;

void citire()
{
    char c;
    f >> an;
    f >> c;
    f >> luna;
    f >> c;
    f >> zi;
    f >> ora;
    f >> c;
    f >> minut;
    f >> c;
    f >> sec;
}

void adaos(int an)
{
    if(an % 3 == 0) calendar[4] += 1;
    if(an % 5 == 0) calendar[9] += 2;
    if(an % 3 != 0 && an % 5 != 0 && an % 7 == 0) calendar[6] += 3, calendar[11] += 3;
}

unsigned long long secunde;

int main()
{
   for(int e = 1; e <= 10; ++e)
   {
       secunde = 0;
        for(int i = 1; i <= 12; ++i) calendar[i] = calendar_initial[i];
        citire();
        adaos(an);
        //for(int i = 1; i <= 12; ++i) cout << calendar[i] << " ";
        for(int i = an_initial; i <= an; ++i)
        {
            zile_an = 365;
            if(i % 3 == 0) zile_an += 1;
            if(i % 5 == 0) zile_an += 2;
            if(i % 3 != 0 && i % 5 != 0 && i % 7 == 0) zile_an += 6;
            //cout << i << " - " << zile_an << '\n';
            secunde += zile_an;
        }
        secunde -= 121 + 25;
        for(int i = luna + 1; i <= 12; ++i)
        {
            secunde -= calendar[i];
        }
        secunde -= calendar[luna] - zi;
        secunde *= 25;
        secunde -= 12 - ora;
        secunde *= 45;
        secunde += minut;
        secunde *= 80;
        secunde += sec;
        cout << secunde << '\n';
   }
    return 0;
}
