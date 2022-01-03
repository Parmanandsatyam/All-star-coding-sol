#include <cstdio>
#include <cstring>

using namespace std;

int a[10][10];
char col[10];
char lin[10];
int n;

void citire(){
    scanf ("%s", col);
    scanf ("%s", lin);
    col [strlen(col) - 1] = 0;
    n = strlen (col);
}

int sumaLin(int i){
    int s = 0;
    for (int j = 0; j < n; ++ j){
        s += a[i][j];
    }
    return s;
}

int sumaCol(int j){
    int s = 0;
    for (int i = 0; i < n; ++ i){
        s += a[i][j];
    }
    return s;
}


void verf(){
    for (int i = 0; i < n; ++ i){
        if (col[i] - '0' != sumaLin(i) || lin[i] - '0' != sumaCol(i)){
            return;
        }
    }
    throw (0);
}

void back (int i, int j){
    if (j == n){
        back (i + 1, 0);
        return;
    }
    if (i == n){
        verf();
        return;
    }
    a[i][j] = 0;
    back (i, j + 1);
    a[i][j] = 1;
    back (i, j + 1);
}

int main()
{
    freopen ("fis.in", "r", stdin);
    freopen ("fis.out", "w", stdout);

    for (int w = 0; w < 10; ++ w){
        citire();
        try{
            back (0, 0);
            printf ("NONE\n\n");
        }catch (int x){
            for (int i = 0; i < n; ++ i){
                for (int j = 0; j < n; ++ j){
                    printf ("%d ", a[i][j]);
                }
                printf ("\n");
            }
            printf ("\n");
        }
    }

    return 0;
}
