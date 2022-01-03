#include <iostream>
#include <fstream>
#include <string>

std::ifstream in("as3-test.txt");
long long v[]= {0,31,28,31,30,31,30,31,31,30,31,30,31};
long long data[6]; ///0-an,1-luna,2-zi,3-ora,4-min,5-sec
std::string s;

int main()
{
    long long nr,k = -1;
    nr=0;
    long long sy,sm,sd,sh,smi,ss;
    sy = 2019;
    sm = 5;
    sd = 25;
    sh = 12;
    smi = ss = 0;
    for(int test = 1; test <= 10; test++)
    {
        getline(in,s);
        k = -1;
        for(int i=0; i<s.size(); i++)
        {
            while(isdigit(s[i]))
            {
                nr = 10 * nr + s[i] - 48;
                i++;
            }
            data[++k] = nr;
            nr = 0;
        }
        if(data[0] == 2019)
        {
            if(data[1] == 5)
            {
                if(data[3] < sh)
                    data[3] += 25,data[2]--;
                long long dd = data[2]-sd;
                long long dh = data[3]-sh;
                long long dmi = data[4]-smi;
                long long ds = data[5]-ss;
                std::cout<<ds+80*dmi+80*45*dh+80*45*25*dd;
            }
            else
            {
                long long dd = 0;
                for(long long i = sm+1; i<data[1]; i++)
                {
                    if(2019 % 3 == 0 && i == 4)
                        dd += v[i] + 1;
                    else if(2019 % 5 == 0 && i == 9)
                        dd += v[i] + 2;
                    else if(2019 % 7 == 0 && 2019 % 3 != 0 && 2019 % 5 != 0 && (i == 6 || i == 11))
                        dd += v[i] + 3;
                    else
                        dd += v[i];
                }
                dd+=data[2]-1;
                dd+=v[5]-sd;
                long long dh = 13 + data[3];
                long long dmi = data[4];
                long long ds = data[5];
                std::cout<<ds+80*dmi+80*45*dh+80*45*25*dd;
            }
        }
        else
        {
            long long dd = 0;
            for(long long i = sy+1; i<data[0]; i++) ///anii diferenta
            {
                for(long long j = 1; j <= 12; j++)
                {
                    if(i % 3 == 0 && j == 4)
                        dd += v[j] + 1;
                    else if(i % 5 == 0 && j == 9)
                        dd += v[j] + 2;
                    else if(i % 7 == 0 && i % 3 != 0 && i % 5 != 0 && (j == 6 || j == 11))
                        dd += v[j] + 3;
                    else
                        dd += v[j];
                }
            }
            for(long long i = sm+1; i <= 12; i++)
            {
                if(2019 % 3 == 0 && i == 4)
                    dd += v[i] + 1;
                else if(2019 % 5 == 0 && i == 9)
                    dd += v[i] + 2;
                else if(2019 % 7 == 0 && 2019 % 3 != 0 && 2019 % 5 != 0 && (i == 6 || i == 11))
                    dd += v[i] + 3;
                else
                    dd += v[i];
            }
            for(long long i = 1; i<data[1]; i++)
            {
                if(data[0] % 3 == 0 && i == 4)
                    dd += v[i] + 1;
                else if(data[0] % 5 == 0 && i == 9)
                    dd += v[i] + 2;
                else if(data[0] % 7 == 0 && data[0] % 3 != 0 && data[0] % 5 != 0 && (i == 6 || i == 11))
                    dd += v[i] + 3;
                else
                    dd += v[i];
            }
            dd += data[2] - 1;
            dd += v[5] - sd;
            long long dh = 13 + data[3];
            long long dmi = data[4];
            long long ds = data[5];
            std::cout<<ds+80*dmi+80*45*dh+80*45*25*dd;
        }
        std::cout<<std::endl;
    }
    return 0;
}
