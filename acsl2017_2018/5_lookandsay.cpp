#include <bits/stdc++.h>
using namespace std;
string next_num(string prev) {
	string ans;
	int count = 1;
	char curChar = prev[0];
	for(int i = 1; i < prev.size(); i++) {
		if(prev[i] != curChar) {
			ans = ans + to_string(count) + curChar;
			count = 1;
			curChar = prev[i];
		}
		else {
			count++;
		}
	}
	ans = ans + to_string(count) + curChar;
	return(ans);
}
int main() {
	ifstream fin("as5-test.txt");
	for(int t_num = 0; t_num < 10; t_num++) {
		int m, n, p; fin >> m >> n >> p;
		string str = "1";
		for(int i = 0; i < m - 1; i++) {
			str = next_num(str);
		}
		if(n + p - 1 > str.size()) {
			cout << t_num + 1 << ". oops" << endl;
		}
		else {
			cout << t_num + 1 << ". " << str.substr(n - 1, p + 1) << endl;
		}
	}
}