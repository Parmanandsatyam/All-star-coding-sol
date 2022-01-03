#include <cstdio>
#include <map>
#include <cstring>

using namespace std;

map<char,int> ctobin;
map<int,char> bintoc;
int key1[15] = {24,19,14,18,16,22,11,5,12,26,30,9,7,6};
int key2[5] = {24,24,19,19};
int pz1,pz2;
char word[1000];

void prepare()
{
    ctobin['A'] = 24;
    ctobin['B'] = 19;
    ctobin['C'] = 14;
    ctobin['D'] = 18;
    ctobin['E'] = 16;
    ctobin['F'] = 22;
    ctobin['G'] = 11;
    ctobin['H'] = 5;
    ctobin['I'] = 12;
    ctobin['J'] = 26;
    ctobin['K'] = 30;
    ctobin['L'] = 9;
    ctobin['M'] = 7;
    ctobin['N'] = 6;
    ctobin['O'] = 3;
    ctobin['P'] = 13;
    ctobin['Q'] = 29;
    ctobin['R'] = 10;
    ctobin['S'] = 20;
    ctobin['T'] = 1;
    ctobin['U'] = 28;
    ctobin['V'] = 15;
    ctobin['W'] = 25;
    ctobin['X'] = 23;
    ctobin['Y'] = 21;
    ctobin['Z'] = 17;
    ctobin['+'] = 27;
    ctobin['/'] = 0;
    ctobin['9'] = 4;
    ctobin['8'] = 31;
    ctobin['4'] = 8;
    ctobin['3'] = 2;



    bintoc[24] = 'A';
    bintoc[19] = 'B';
    bintoc[14] = 'C';
    bintoc[18] = 'D';
    bintoc[16] = 'E';
    bintoc[22] = 'F';
    bintoc[11] = 'G';
    bintoc[5] = 'H';
    bintoc[12] = 'I';
    bintoc[26] = 'J';
    bintoc[30] = 'K';
    bintoc[9] = 'L';
    bintoc[7] = 'M';
    bintoc[6] = 'N';
    bintoc[3] = 'O';
    bintoc[13] = 'P';
    bintoc[29] = 'Q';
    bintoc[10] = 'R';
    bintoc[20] = 'S';
    bintoc[1] = 'T';
    bintoc[28] = 'U';
    bintoc[15] = 'V';
    bintoc[25] = 'W';
    bintoc[23] = 'X';
    bintoc[21] = 'Y';
    bintoc[17] = 'Z';
    bintoc[27] = '+';
    bintoc[0] = '/';
    bintoc[4] = '9';
    bintoc[31] = '8';
    bintoc[8] = '4';
    bintoc[2] = '3';
}


void solve()
{
    scanf("%d%d%s",&pz1,&pz2,word);
    pz1--;
    pz2--;
    int ans;
    for(int i = 0; i < strlen(word); ++i)
    {
        ans = ctobin[word[i]];
        ans ^= key1[pz1];
        ans ^= key2[pz2];
        printf("%c",bintoc[ans]);
        pz1++;
        pz2++;
        pz1 %= 14;
        pz2 %= 4;
    }
    printf("\n");
}


int main()
{
    freopen("lorenz.in","r",stdin);
    prepare();
    for(int i = 1; i <= 10; ++i)
    {
        memset(word,0,sizeof(word));
        solve();
    }
    return 0;
}
