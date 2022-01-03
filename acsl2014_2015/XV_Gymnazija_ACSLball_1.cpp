#include <iostream>

using namespace std;

struct dude
{
	string name;
	int one, two, three;
} arr[8];

int main()
{
	int i;
	
	for (i = 0; i < 8; ++i) cin>>arr[i].name>>arr[i].one>>arr[i].two>>arr[i].three;
	
	{
		int res = 0;
		for (i = 0; i < 8; ++i) res += arr[i].three;
		cout<<res<<endl;
	}
	{
		string res;
		int num = -1;
		
		for (i = 0; i < 8; ++i) if (arr[i].one + arr[i].two + arr[i].three > num)
			res = arr[i].name, num = arr[i].one + arr[i].two + arr[i].three;
		
		cout<<res<<endl;
	}
	{
		string res;
		int num = -1;
		
		for (i = 0; i < 8; ++i) if (arr[i].one + 2 * arr[i].two + 3 * arr[i].three > num)
			res = arr[i].name, num = arr[i].one + 2 * arr[i].two + 3 * arr[i].three;
			
		cout<<res<<endl;
	}
	
	int a = 0, b = 0;
	
	for (i = 0; i < 4; ++i) a += arr[i].one + 2 * arr[i].two + 3 * arr[i].three;
	for (i = 4; i < 8; ++i) b += arr[i].one + 2 * arr[i].two + 3 * arr[i].three;
	
	if (a < b)
	{
		cout<<b<<endl;
		
		string firName, secName;
		int fir = 0, sec = 0;
		
		for (i = 0; i < 4; ++i)
			if (arr[i].one + 2 * arr[i].two + 3 * arr[i].three > fir)
				sec = fir,
				secName = firName,
				fir = arr[i].one + 2 * arr[i].two + 3 * arr[i].three,
				firName = arr[i].name;
			else if (arr[i].one + 2 * arr[i].two + 3 * arr[i].three > sec && arr[i].one + 2 * arr[i].two + 3 * arr[i].three < fir)
				sec = arr[i].one + 2 * arr[i].two + 3 * arr[i].three,
				secName = arr[i].name;
		
		cout<<secName<<endl;
	}
	else
	{
		cout<<a<<endl;
		
		string firName, secName;
		int fir = 0, sec = 0;
		
		for (i = 4; i < 8; ++i)
			if (arr[i].one + 2 * arr[i].two + 3 * arr[i].three > fir)
				sec = fir,
				secName = firName,
				fir = arr[i].one + 2 * arr[i].two + 3 * arr[i].three,
				firName = arr[i].name;
			else if (arr[i].one + 2 * arr[i].two + 3 * arr[i].three > sec)
				sec = arr[i].one + 2 * arr[i].two + 3 * arr[i].three,
				secName = arr[i].name;
		
		cout<<secName<<endl;
	}
}

