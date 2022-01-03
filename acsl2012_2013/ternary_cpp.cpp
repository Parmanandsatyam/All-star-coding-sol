#include <iostream>
#include <string>
#include <vector>
using namespace std;

std::string toBinary(char octal);

int main()
{
	for(int run=0; run<5; ++run)
	{
		int numOriginal;
		cin >> numOriginal;
		string A="",B="",C="",D="";
		for(int a=0; a<numOriginal; a++)
		{
			string gate;
			string push;

			cin >> gate >> push;
			string val=toBinary(gate[1]);
			bool toPush=false;
			switch(gate[0])
			{
			case 'A':
				toPush=(val=="000");
				break;
			case 'B':
				toPush=(val=="000" or val=="001" or val=="010" or val=="100");
				break;
			case 'C':
				toPush=(val=="111");
				break;
			case 'D':
				toPush=(val!="111");
				break;
			}
			for(unsigned int b=0; b<push.length(); b++)
			{
				switch(push[b])
				{
				case 'A':
					A+=toPush?"1":"0";
					break;
				case 'B':
					B+=toPush?"1":"0";
					break;
				case 'C':
					C+=toPush?"1":"0";
					break;
				case 'D':
					D+=toPush?"1":"0";
					break;
				}
			}
		}

		string final="";

		if((A.length()+B.length()+C.length()+D.length())==3)
		{
			cout << A << B << C << D << endl;
			string f=A+B+C+D, fG;
			cin >> fG;
			switch(fG[0])
			{
			case 'A':
				cout << (f=="000");
				break;
			case 'B':
				cout << (f=="000" or f=="001" or f=="010" or f=="100");
				break;
			case 'C':
				cout << (f=="111");
				break;
			case 'D':
				cout << (f!="111");
				break;
			}
			cout << endl << endl;
			continue;
		}

		if(A.length()==3)
		{
			cout << (A=="000");
			final+=(A=="000")?"1":"0";
		}
		if(B.length()==3)
		{
			cout << (B=="000" or B=="001" or B=="010" or B=="100");
			final+=(B=="000" or B=="001" or B=="010" or B=="100")?"1":"0";
		}
		if(C.length()==3)
		{
			cout << (C=="111");
			final+=(C=="111")?"1":"0";
		}
		if(D.length()==3)
		{
			cout << (D!="111");
			final+=(D!="111")?"1":"0";
		}
		cout << endl;
		string finalG;
		cin >> finalG;
		switch(finalG[0])
		{
		case 'A':
			cout << (final=="000");
			break;
		case 'B':
			cout << (final=="000" or final=="001" or final=="010" or final=="100");
			break;
		case 'C':
			cout << (final=="111");
			break;
		case 'D':
			cout << (final!="111");
			break;
		}
		cout << endl << endl;


	}
	return 0;
}

std::string toBinary(char octal)
{
	switch(octal)
	{
		case '0':
			return "000";
			break;
		case '1':
			return "001";
			break;
		case '2':
			return "010";
			break;
		case '3':
			return "011";
			break;
		case '4':
			return "100";
			break;
		case '5':
			return "101";
			break;
		case '6':
			return "110";
			break;
		case '7':
			return "111";
			break;
		default:
			return "000";
			break;
	}
	return "000";
}
