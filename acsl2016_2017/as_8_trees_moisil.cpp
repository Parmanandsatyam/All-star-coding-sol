/// NCSC GR MOISIL SR3
#include <iostream>
#include <cstdio>
#include <cstring>

using namespace std;

char s[10000];
char tmp[10000];

void read()
{
	memset(tmp, 0, sizeof tmp);
	for (int i = 0; s[i]; i++)
		if (s[i] >= 'a' && s[i] <= 'z' || s[i] >= 'A' && s[i] <= 'Z')
			tmp[strlen(tmp)] = s[i];
	strcpy(s, tmp);
}

struct Node
{
	char inf;
	Node *st, *dr;
	Node(char _inf) {
		inf = _inf;
		st = dr = nullptr;
	}
};
Node *root;

void add(Node* &crt, char x)
{
	if (crt == nullptr)
		crt = new Node(x);
	else if (x > crt->inf)
		add(crt->dr, x);
	else
		add(crt->st, x);
}

int depth(Node* &crt, int d = 0)
{
	if (crt == nullptr)
		return 0;
	return max(max(d, depth(crt->st, d+1)), depth(crt->dr, d+1));
}

int leaf(Node* &crt)
{
	if (crt == nullptr)
		return 0;
	if (crt->st == nullptr && crt->dr == nullptr)
		return 1;
	return leaf(crt->st) + leaf(crt->dr);
}

int onechild(Node* &crt)
{
	if (crt == nullptr)
		return 0;
	int c = 0;
	if ((crt->st == nullptr) ^ (crt->dr == nullptr))
		c++;
	return c + onechild(crt->st) + onechild(crt->dr);
}

int ipl(Node* &crt, int d = 0)
{
	if (crt == nullptr)
		return 0;
	return d + ipl(crt->st, d+1) + ipl(crt->dr, d+1);
}

int epl(Node* &crt, int d = 0)
{
	if (crt == nullptr)
		return d;
	return epl(crt->st, d+1) + epl(crt->dr, d+1);
}

void solve()
{
	root = nullptr;
	for (int i = 0; s[i]; i++)
		add(root, s[i]);
	printf("%d\n", depth(root));
	printf("%d\n", leaf(root));
	printf("%d\n", onechild(root));
	printf("%d\n", ipl(root));
	printf("%d\n", epl(root));
}

int main()
{
	freopen("../8.in", "r", stdin);
	//freopen(".out", "w", stdout);
	
	while (!feof(stdin)) {
		memset(s, 0, sizeof s);
		fgets(s, 1000, stdin);
		if (!*s) break;
		if (s[strlen(s)-1] == '\n')
			s[strlen(s)-1] = 0;
		if (!*s) break;
		read();
		solve();
	}
	
	return 0;
}
