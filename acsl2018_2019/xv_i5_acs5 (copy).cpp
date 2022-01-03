#include <cstdio>
#include <cstring>
#include <iostream>
#include <vector>

#define PB push_back

using namespace std;

typedef vector < string > vs;

vs nar = {"DC", "BE", "BG", "BL", "BU", "MULT", "ADD", "SUB", "DIV", "LOAD", "STORE", "READ", "END", "PRINT"};
vs sve;
vector < vs > prog;
string pr[300][3];
int k;

bool jebovampas(string x, vs &y){
    for(string yy : y) if(x == yy) return 1;
    return 0;
}

string upper(string x){
    for(int i = 0;i < x.size();i++)
        if('a' <= x[i] && x[i] <= 'z')
            x[i] = x[i] + 'A' - 'a';
    return x;
}

void input(){
    string cur;
    for(char c = getchar();;c = getchar()){
        if(c == ' ' || c == '\t' || c == '\n'){
            if(jebovampas(upper(cur), nar))
                cur = upper(cur);
            if(cur != "") sve.PB(cur);// cout << "CUR: " << cur << " " << (int)cur.size() << endl;
            if(cur == "END") break;
            cur = "";
        }
        else{
            cur.PB(c);
        }
    }
}

void solve(){
    scanf("%d", &k);
    input();
    vs tren;
    for(int i = 0;i < sve.size();i++){
        string cur = sve[i];
        tren.PB(cur);
        if(jebovampas(cur, nar)){
            //cout << "CUR = " << cur << endl;
            if(cur != "END")
                tren.PB(sve[++i]);
            prog.PB(tren);
            tren.clear();
        }
    }
    int vel1 = 0, vel2 = 0;
    for(int i = 0;i < prog.size();i++){
        if(prog[i].size() == 3){
            pr[i][0] = prog[i][0];
            pr[i][1] = prog[i][1];
            pr[i][2] = prog[i][2];
        }
        else if(prog[i].size() == 2 && prog[i].back() == "END"){
            pr[i][0] = prog[i][0];
            pr[i][1] = prog[i][1];
        }
        else if(prog[i].size() == 2){
            pr[i][1] = prog[i][0];
            pr[i][2] = prog[i][1];
        }
        else{
            pr[i][1] = prog[i][0];
        }
        vel1 = max(vel1, (int)pr[i][0].size());
        vel2 = max(vel2, (int)pr[i][1].size());
    }
    cout << pr[k - 1][0] << vel1 + 1 - pr[k - 1][0].size() << pr[k - 1][1];
    if(pr[k - 1][2].size() != 0) cout << vel2 + 1 - pr[k - 1][1].size() << pr[k - 1][2];
    cout << endl;
    //cout << "PROGRAM\n";
    //printf("VELICINA %d\n", (int)prog.size());
    //for(int x = 0; x < prog.size(); x++)
    //    cout << pr[x][0] << vel1 + 1 - pr[x][0].size() << pr[x][1] << vel2 + 1 - pr[x][1].size() << pr[x][2] << endl;

}

int main(){
    int T = 10;
    //freopen("as5-test.txt", "r", stdin);
    for(;T--;) {
        sve.clear(); prog.clear();
        for(int i = 0;i < 100;i++)
            pr[i][0] = "", pr[i][1] = "", pr[i][2] = "";
        solve();
    }
    return 0;
}
