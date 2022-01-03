#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

vector<int> houses[4];

vector<int>& get(char c)
{
	return houses[c - 'A'];
}

int main()
{
	int n, m, k, i, x, idx;
	char c;
	
	cin>>n;
	
	for (i = 0; i < n; ++i) cin>>c>>x, get(c).push_back(x);
	
	for (int t = 0; t < 5; ++t)
	{
		cin>>m;
		
		for (i = 0; i < m; ++i) cin>>c>>x, get(c).push_back(x);
		
		for (i = 0; i < 4; ++i) sort(houses[i].begin(), houses[i].end());
		
		cin>>c>>k;
		
		idx = c - 'A';
		
		for (int j = 0; j < 4; ++j) for (i = 0; i < houses[(idx + j) % 4].size(); ++i)
		{
			if (houses[(idx + j) % 4][i] % 2 == 1) --k;
			
			if (!k)
			{
				cout<<char((idx + j) % 4 + 'A')<<houses[(idx + j) % 4][i];
				goto out;
			}
		}
		
		for (int j = 0; j < 4; ++j) for (i = 0; i < houses[(idx + j) % 4].size(); ++i)
		{
			if (houses[(idx + j) % 4][i] % 2 == 0) --k;
			
			if (!k)
			{
				cout<<char((idx + j) % 4 + 'A')<<houses[(idx + j) % 4][i];
				goto out;
			}
		}
		
		continue;
		out:
		cout<<endl;
	}
}

