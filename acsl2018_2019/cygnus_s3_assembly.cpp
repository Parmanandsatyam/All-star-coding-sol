#include <cstdio>
#include <iostream>
#include <sstream>
#include <vector>
#include <string>
#include <map>

using namespace std;

const int MOD = 1000000;

struct instruction {
    string label;
    string op;
    string value;

    instruction() {
        label = op = value = "";
    }

    instruction(string _label, string _op, string _value) {
        label = _label;
        op = _op;
        value = _value;
    }

};

int checkString(string s) {
    if(s == "LOAD")
        return 1;
    else if(s == "STORE")
        return 2;
    else if(s == "ADD")
        return 3;
    else if(s == "SUB")
        return 4;
    else if(s == "MULT")
        return 5;
    else if(s == "DIV")
        return 6;
    else if(s == "BG")
        return 7;
    else if(s == "BE")
        return 8;
    else if(s == "BL")
        return 9;
    else if(s == "BU")
        return 10;
    else if(s == "READ")
        return 11;
    else if(s == "PRINT")
        return 12;
    else if(s == "DC")
        return 13;
    else if(s == "EXIT")
        return 14;
    return 0;
}

int getInt(string x) {
    stringstream ss(x);
    int a;
    ss >> a;
    return a;
}

vector <instruction> ins;
map <string, int> labels;
map <string, int> memory;
vector <int> reading;
int PC, ACC, readloc;

int main() {
    freopen("as7-test.txt", "r", stdin);
    for(int T = 1; T <= 10; T++) {
        string line;
        getline(cin, line);
        stringstream rr(line);
        while(!rr.eof()) {
            int x;
            rr >> x;
            reading.push_back(x);
        }
        getline(cin, line);
        while(!line.empty()) {
            stringstream ll(line);
            string x, label = "", op = "", value = "";
            ll >> x;
            if(!checkString(x)) {
                label = x;
                labels[x] = ins.size();
                ll >> x;
            }
            op = x;
            if(checkString(x) !=  14)
                ll >> value;
            ins.push_back(instruction(label, op, value));
            getline(cin, line);
        }
        PC = ACC = readloc = 0;
        int val;
        while(ins[PC].op != "END") {
            switch(checkString(ins[PC].op)) {
            case 1:
                if(ins[PC].value[0] == '=')
                    val = getInt(ins[PC].value.substr(1, string::npos));
                else
                    val = memory[ins[PC].value];
                ACC = val;
                PC++;
                break;
            case 2:
                memory[ins[PC].value] = ACC;
                PC++;
                break;
            case 3:
                if(ins[PC].value[0] == '=')
                    val = getInt(ins[PC].value.substr(1, string::npos));
                else
                    val = memory[ins[PC].value];
                ACC = (ACC + val) % MOD;
                PC++;
                break;
            case 4:
                if(ins[PC].value[0] == '=')
                    val = getInt(ins[PC].value.substr(1, string::npos));
                else
                    val = memory[ins[PC].value];
                ACC = (ACC - val) % MOD;
                PC++;
                break;
            case 5:
                if(ins[PC].value[0] == '=')
                    val = getInt(ins[PC].value.substr(1, string::npos));
                else
                    val = memory[ins[PC].value];
                ACC = (ACC * val) % MOD;
                PC++;
                break;
            case 6:
                if(ins[PC].value[0] == '=')
                    val = getInt(ins[PC].value.substr(1, string::npos));
                else
                    val = memory[ins[PC].value];
                ACC = (ACC / val) % MOD;
                PC++;
                break;
            case 7:
                if(ACC > 0)
                    PC = labels[ins[PC].value];
                else
                    PC++;
                break;
            case 8:
                if(ACC == 0)
                    PC = labels[ins[PC].value];
                else
                    PC++;
                break;
            case 9:
                if(ACC < 0)
                    PC = labels[ins[PC].value];
                else
                    PC++;
                break;
            case 10:
                PC = labels[ins[PC].value];
                break;
            case 11: /// DISCUSS
                memory[ins[PC].value] = reading[readloc];
                readloc++;
                PC++;
                break;
            case 12:
                //cout << memory[ins[PC].value] << '\n';
                PC++;
                break;
            case 13:
                memory[ins[PC].label] = getInt(ins[PC].value);
                PC++;
                break;
            case 14:
                break;
            }
        }
        cout << T << ". " << ACC << '\n';
        ins.clear();
        labels.clear();
        memory.clear();
        reading.clear();
    }
}
