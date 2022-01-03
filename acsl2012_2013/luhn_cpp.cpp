#include <cstdio>
#include <cstring>

using namespace std;

int a[100];
int checkSum;
int n;


int sumCif(int x){
    if (x == 0){
        return 0;
    }
    return x % 10 + sumCif (x / 10);
}

int luhn(){
    int s = 0;
    int mod = n % 2;

    for (int i = 0; i < n; ++ i){
        if (i % 2 == mod){
            s += sumCif (a[i]);
        }else{
            s += sumCif (a[i] *  2);
        }
    }
    return s * 9 % 10;
}

void citire(){
    char c[100];
    memset (c, 0, sizeof(c));
    gets(c);
    checkSum = c[strlen(c) - 1]  - '0';
    c[strlen(c) - 1] = 0;
    n = strlen (c);
    for (int i = 0; i < n; ++ i){
        a[i] = c[i] - '0';
    }
}

void afiseaza(){
    for (int i = 0; i < n; ++ i){
        printf ("%d", a[i]);
    }
    printf ("%d\n", checkSum);
}

void singleDigit(){
    for (int i = 0; i < n; ++ i){
        a[i] = (a[i] + 1) % 10;
        if (checkSum == luhn()){
            afiseaza();
            return;
        }
        a[i] = (a[i] + 10 - 2) % 10;
        if (checkSum == luhn()){
            afiseaza();
            return;
        }
        a[i] = (a[i] + 10 + 1) % 10;
    }
}

void schimba (int i){
    int t = a[i];
    a[i] = a[i - 1];
    a[i - 1] = t;
}

void transposition(){
    for (int i = 1; i < n; ++ i){
        schimba(i);
        if (checkSum == luhn()){
            afiseaza();
            return;
        }
        schimba(i);
    }
}

void twin(){
    for (int i = 1; i < n; ++ i){
        if (a[i] == a[i - 1]){
            a[i] = (a[i] + 1) % 10;
            a[i - 1] = (a[i - 1] + 1) % 10;
            if (checkSum == luhn()){
                afiseaza();
                return;
            }
            a[i] = (a[i] + 10 - 2) % 10;
            a[i - 1] = (a[i - 1] + 10 - 2) % 10;
            if (checkSum == luhn()){
                afiseaza();
                return;
            }
            a[i] = (a[i] + 10 + 1) % 10;
            a[i - 1] = (a[i - 1] + 10 + 1) % 10;
        }
    }
}

void ialePeToate(){
    for (int i = 0; i < 4; ++ i){
        citire();
        if (luhn() == checkSum){
            printf ("VALID\n");
        }else{
            printf ("%d\n", luhn());
        }
    }
    for (int i = 0; i < 2; ++ i){
        citire();
        singleDigit();
    }
    for (int i = 0; i < 2; ++ i){
        citire();
        transposition();
    }
    for (int i = 0; i < 2; ++ i){
        citire();
        twin();
    }
}


int main()
{
    freopen ("fis.in", "r", stdin);
    ialePeToate();

    return 0;
}
