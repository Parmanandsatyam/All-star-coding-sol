#include <bits/stdc++.h>

using namespace std;

int area(string a)
{
	if (a.size() == 2)
		return (a[0] - '0') * (a[1] - '0');
	
	return a[0] - '0' + (a[1] - '1') * (a[2] - '0');
}

bool cmp(string a, string b)
{
	return make_pair(area(a), a[0]) < make_pair(area(b), b[0]);
}

bool bio[100][100];

int main()
{
	string arr[10];
	vector<string> arr2;
	
	for (int i = 0; i < 10; ++i)
		cin>>arr[i];
	
	sort(arr, arr + 10, cmp);
	for (string s: arr) if (s.size() == 2)
		arr2.push_back(s);
	
//	for (string s: arr)
//		cout<<s<<' ';
//	cout<<endl;
	
	for (int cs = 0; cs < 5; ++cs)
	{
		int h, w;
		
		cin>>w>>h;
		
		for (int bit = (1<<arr2.size()) - 1; bit > 0; --bit) if (__builtin_popcount(bit) > 1)
		{
//			cout<<endl;
//			for (int k = 0; k < arr2.size(); ++k) if (bit & 1<<k)
//				cout<<arr2[k]<<' ';
//			cout<<endl<<endl;
			memset(bio, false, sizeof bio);
			for (int k = arr2.size() - 1; k >= 0; --k) if (bit & 1<<k)
			{
				int p = -1, q = -1;
				for (int i = 0; i <= h - (arr2[k][1] - '0'); ++i) for (int j = 0; j <= w - (arr2[k][0] - '0'); ++j)
				{
					if (!bio[i][j])
					{
						if (arr2[k].size() == 3)
						{
							for (int ii = i; ii < i + (arr2[k][1] - '0'); ++i) for (int jj = j; jj < j + (arr2[k][2] - '0'); ++jj)
								if (bio[ii][jj])
									goto conta;
							for (int jj = j + (arr2[k][2] - '0'); jj < j + (arr2[k][0] - '0'); ++jj)
								if (bio[i][jj])
									goto conta;
						}
						else
						{
							for (int ii = i; ii < i + (arr2[k][1] - '0'); ++ii) for (int jj = j; jj < j + (arr2[k][0] - '0'); ++jj)
								if (bio[ii][jj])
									goto conta;
						}
						p = i;
						q = j;
						goto out1;
					}
					conta:;
				}
				out1:
				if (p == -1)
					goto cont1;
				
//				cout<<p<<' '<<q<<endl;
				
				if (arr2[k].size() == 3)
				{
					for (int i = p; i < p + (arr2[k][1] - '0'); ++i) for (int j = q; j < q + (arr2[k][2] - '0'); ++j)
						bio[i][j] = true;
					for (int j = q + (arr2[k][2] - '0'); j < q + (arr2[k][0] - '0'); ++j)
						bio[p][j] = true;
				}
				else
				{
					for (int i = p; i < p + (arr2[k][1] - '0'); ++i) for (int j = q; j < q + (arr2[k][0] - '0'); ++j)
						bio[i][j] = true;
				}
				
//				for (int i = 0; i < h; ++i)
//				{
//					for (int j = 0; j < w; ++j)
//						cout<<bio[i][j]<<' ';
//					cout<<endl;
//				}
			}
			
			for (int i = 0; i < h; ++i) for (int j = 0; j < w; ++j)
				if (!bio[i][j])
					goto cont1;
			
			for (int k = arr2.size() - 1; k >= 0; --k) if (bit & 1<<k)
				cout<<arr2[k]<<' ';
			cout<<endl;
			
			goto cont2;
			
			cont1:;
		}
		
		cout<<"NONE"<<endl;
		
		cont2:;
	}
	for (int cs = 5; cs < 10; ++cs)
	{
		int h, w;
		
		cin>>w>>h;
		
		for (int bit = (1<<10) - 1; bit > 0; --bit) if (__builtin_popcount(bit) > 1) if (__builtin_popcount(bit) > 1)
		{
			memset(bio, false, sizeof bio);
			for (int k = 9; k >= 0; --k) if (bit & 1<<k)
			{
				int p = -1, q = -1;
				for (int i = 0; i <= h - (arr[k][1] - '0'); ++i) for (int j = 0; j <= w - (arr[k][0] - '0'); ++j)
				{
					if (!bio[i][j])
					{
						if (arr[k].size() == 3)
						{
							for (int ii = i; ii < i + (arr[k][1] - '0'); ++ii) for (int jj = j; jj < j + (arr[k][2] - '0'); ++jj)
								if (bio[ii][jj])
									goto contb;
							for (int jj = j + (arr[k][2] - '0'); jj < j + (arr[k][0] - '0'); ++jj)
								if (bio[i][jj])
									goto contb;
						}
						else
						{
							for (int ii = i; ii < i + (arr[k][1] - '0'); ++ii) for (int jj = j; jj < j + (arr[k][0] - '0'); ++jj)
								if (bio[ii][jj])
									goto contb;
						}
						
						p = i;
						q = j;
						goto out2;
					}
					contb:;
				}
				out2:
				if (p == -1)
					goto cont3;
				
				
				if (arr[k].size() == 3)
				{
					for (int i = p; i < p + (arr[k][1] - '0'); ++i) for (int j = q; j < q + (arr[k][2] - '0'); ++j)
						bio[i][j] = true;
					for (int j = q + (arr[k][2] - '0'); j < q + (arr[k][0] - '0'); ++j)
						bio[p][j] = true;
				}
				else
				{
					for (int i = p; i < p + (arr[k][1] - '0'); ++i) for (int j = q; j < q + (arr[k][0] - '0'); ++j)
						bio[i][j] = true;
				}
				
//				for (int i = 0; i < h; ++i)
//				{
//					for (int j = 0; j < w; ++j)
//						cout<<bio[i][j]<<' ';
//					cout<<endl;
//				}
			}
			
			for (int i = 0; i < h; ++i) for (int j = 0; j < w; ++j)
				if (!bio[i][j])
					goto cont3;
			
			for (int k = 9; k >= 0; --k) if (bit & 1<<k)
				cout<<arr[k]<<' ';
			cout<<endl;
			
			goto cont4;
			
			cont3:;
		}
		
		cout<<"NONE"<<endl;
		
		cont4:;
	}
}
