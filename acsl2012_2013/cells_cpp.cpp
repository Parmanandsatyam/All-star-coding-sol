#include <cstdio>
#include <cstring>
#include <string>
#include <algorithm>
using namespace std;

string x, y, z;

string DIV(string a){
    string left = string(a.begin(), a.begin()+4);
    string right = string(a.begin()+4, a.end());

    return left + left + " " + right + right;
}

string ADD(string a, int x){
    string left = string(a.begin(), a.begin()+x);
    string right = string(a.begin()+x, a.begin()+x+8-2*x);

    return left + left + right;
}

string sorts(string a){
    int v[100];
    memset(v, 0, sizeof(v));
    for (int i = 0; i < a.size(); i++)
        v[i] = a[i];
    sort(v, v + a.size());
    for (int i = 0; i < a.size(); i++)
        a[i] = v[i];
    return a;
}

string SUB(string a, int x){

    string left = string(a.begin()+x, a.end());
    string right = string(a.end()-x, a.end());

    right = sorts(right);

    return left + right;
}

string UNI(string a){
    char aux[100];
    strcpy(aux, a.data());
    string left = string(aux, strchr(aux, ' '));
    string right = string(strchr(aux, ' ')+1);

    string stanga = sorts(string(left.begin()+4, left.end()));
    string dreapta = sorts(string(right.begin(), right.begin()+4));

    return stanga + dreapta;
}

string INT(string a){
    char aux[100];
    strcpy(aux, a.data());
    string left = string(aux, strchr(aux, ' '));
    string right = string(strchr(aux, ' ')+1);

    string stanga = string(left.begin(), left.begin()+2) + string(left.end()-2, left.end());
    string dreapta = string(right.begin(), right.begin()+2) + string(right.end()-2, right.end());

    return sorts(stanga) + sorts(dreapta);
}

string ALI(string a){
    string s = "";
    a = sorts(a);
    for (int i = a.size() - 1; i >= 0; i--)
        s += string(1, a[i]);

    return s;
}

char line[1000];
char * p;
string prefix(){
    string a = string(p);

    if (a == "X")
        return x;
    if (a == "Y")
        return y;
    if (a == "Z")
        return z;

    p = strtok(NULL, " ");
    if (a == "DIV")
        return DIV(prefix());
    if (a == "ALI")
        return ALI(prefix());

    if (a == "ADD0")
        return ADD(prefix(), 0);
    if (a == "ADD1")
        return ADD(prefix(), 1);
    if (a == "ADD2")
        return ADD(prefix(), 2);
    if (a == "ADD3")
        return ADD(prefix(), 3);
    if (a == "ADD4")
        return ADD(prefix(), 4);

    if (a == "SUB0")
        return SUB(prefix(), 0);
    if (a == "SUB1")
        return SUB(prefix(), 1);
    if (a == "SUB2")
        return SUB(prefix(), 2);
    if (a == "SUB3")
        return SUB(prefix(), 3);
    if (a == "SUB4")
        return SUB(prefix(), 4);

    if (a == "UNI"){
        string n = prefix();
        p = strtok(NULL, " ");
        string m = prefix();
        return UNI(n + " " + m);
    }
    if (a == "INT"){
        string n = prefix();
        p = strtok(NULL, " ");
        string m = prefix();
        return INT(n + " " + m);
    }

    return "";
}
int main(){
    freopen("cells.in","r",stdin);
    char a[100], b[100], c[100];

    gets(a);
    scanf("\n");
    gets(b);
    scanf("\n");
    gets(c);
    scanf("\n");

    x = string(a);
    y = string(b);
    z = string(c);

    while (!feof(stdin)){
        gets(line);
        scanf("\n");

        p = strtok(line, " ");
        string s = prefix();
        for (int i = 0; i < s.size(); i++)
            if (s[i] == ' ')
                printf(" and ");
            else
                printf("%c", s[i]);

        printf("\n");
    }
    return 0;
}
