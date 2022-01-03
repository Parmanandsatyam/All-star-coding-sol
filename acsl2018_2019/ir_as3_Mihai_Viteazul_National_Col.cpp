#include <iostream>
#include <fstream>

using namespace std;

// global config data
ifstream fin ("as3-test.txt");
const int TESTS = 10;

class Time
{
    // input data
    int year, month, day, hour, minute, second;

    // helper data
    int yd = 365;
    unsigned long long initial = 13183200; // 25 May 2019 12:00:00 since 01 Jan 2019 00:00:00

    // result
    unsigned long long result = 0;

private:
    // - parse
    void parseDate()
    {
        for(int i = 2019; i < year; i++)
        {
            result += yd + leapYear(4, i) + leapYear(6, i) + leapYear(9, i) + leapYear(11, i);
        }

        for(int i = 1; i < month; i++)
        {
            result += monthDays(i, year);
        }

        result += day;
    }

    void parseTime()
    {
        result = result * 25 * 45 * 80 + hour * 45 * 80 + minute * 80 + second;
    }

    // - month to day
    int monthDays(int m, int y)
    {
        int monthDays[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int res = monthDays[m - 1] + leapYear(m, y);
        return res;
    }

    int leapYear(int m, int y)
    {
        switch(m)
        {
        case 4:
            if(y % 3 == 0)
                return 1;
            break;
        case 9:
            if(y % 5 == 0)
                return 2;
            break;
        case 6:
        case 11:
            if(y % 7 == 0 && y % 5 != 0 && y % 3 != 0)
                return 3;
            break;
        }
        return 0;
    }

    // - i/o operations
    void read()
    {
        char sign;
        fin >> year >> sign
            >> month >> sign
            >> day
            >> hour >> sign
            >> minute >> sign
            >> second;
    }

    void print()
    {
        cout << result - initial << '\n';
    }

public:
    void solve()
    {
        read();
        parseDate();
        parseTime();
        print();
    }
};

int main()
{
    for(int i = 0; i < TESTS; i++)
    {
        Time t;
        t.solve();
    }

    return 0;
}

