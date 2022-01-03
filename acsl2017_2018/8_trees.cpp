#include <bits/stdc++.h>
#include <deque>

using namespace std;

typedef pair<int,char> ic;

struct node {
	node(string s, int freq) : s(s), freq(freq) { }
	node *p = 0; // parent
	int dir = -1; // 0 = left, 1 = right
	int freq;
	string s;
};

int main() {
	ifstream fin ("as8-test.txt");

	for (int i = 0; i < 10; i++) {
		string s;
		char c;
		fin >> s >> c;
		//cout << s << " " << c << "\n";
		vector<ic> freq;
		for (int i = 0; i < 26; i++) {
			freq.push_back(ic(0,'A' + i));
		}

		for (int j = 0; j < s.length(); j++) {
			if (s[j] < 'A' || s[j] > 'Z') {

			} else {
				freq[s[j] - 'A'].first++;
			}
		}

		sort(freq.begin(), freq.end());

		/* for (int i = 0; i < freq.size(); i++) {
			cout << freq[i].first << " " << freq[i].second << "\n";
		} */

		vector<node*> vn (26);
		deque<node*> qn;


		for (int j = 0; j < 26; j++) {
			if (freq[j].first > 0) {
				node* n0 = new node(string(1,freq[j].second), freq[j].first);
				vn[freq[j].second - 'A'] = n0;
				qn.push_back(n0);

				//cout << vn[freq[j].second - 'A']->s << vn[freq[j].second - 'A']->freq << "\n";
			}
		}

		while (qn.size() > 1) {
			node* l = qn.front();
			qn.pop_front();
			node* r = qn.front();
			qn.pop_front();
			string ns = l->s + r->s;
			sort(ns.begin(), ns.end());
			node* p = new node(ns, l->freq + r->freq);

			//cout << p->s << p->freq << "\n";

			l->dir = 0;
			r->dir = 1;
			l->p = p;
			r->p = p;

			bool inserted = false;
			for (int j = 0; j < qn.size() && !inserted; j++) {
				if (p->freq == qn[j]->freq && p->s < qn[j]->s) { qn.insert(qn.begin()+j,p); inserted = true;}
				if (p->freq < qn[j]->freq) { qn.insert(qn.begin()+j,p); inserted = true; }
			}
			if (!inserted) { qn.push_back(p); inserted = true; }
		}

		string out = "";
		if (vn[c - 'A'] == 0) { out = "A/N"; }
			else {
			node* cur = vn[c - 'A'];
			while (cur->p != 0) {
				out += string(1,cur->dir + '0');
				cur = cur->p;
			}
		}
		reverse(out.begin(), out.end());
		cout << i+1 << ". ";
		cout << out;
		cout << "\n";
	}

}
