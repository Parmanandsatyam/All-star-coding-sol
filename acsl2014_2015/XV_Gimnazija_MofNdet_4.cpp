#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

void work()
{
	string mask;
	int bits, num, x, i;
	vector<bool> rep;
	bool found;
	
	cin>>mask>>bits>>num;
		
	found = false;
	
	if (bits > mask.size()) goto end;
	
	rep.resize(mask.size());
	
	for (i = 1; i <= bits; ++i) rep[mask.size() - i] = true;
	
	do
	{
		x = 0;
		
		for (i = 0; i < mask.size(); ++i)
			x += rep[i] * (mask[i] - '0');
		
		if (x == num)
		{
			if (found) cout<<", ";
			for (i = 0; i < mask.size(); ++i) cout<<rep[i];
			found = true;
		}
	} while (next_permutation(rep.begin(), rep.end()));
	
	end:
	if (!found) cout<<"NONE";
	cout<<endl;
}

int main()
{
	for (int t = 0; t < 10; ++t) work();
}
