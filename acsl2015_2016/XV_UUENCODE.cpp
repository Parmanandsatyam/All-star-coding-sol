#include <cstdlib>
#include <cstdio>

#include <iostream>
#include <cstring>
using namespace std;

// Bobo, ne kradi ovaj kod!!
namespace Karamarko_Otidose
{

void start()
{
    char str[1024];
    cin.getline(str, sizeof(str));
    int len = strlen(str);

    while (len % 3) str[len++] = '0';

    int bits[1024 * 8] = { 0 };
    for (int i = 0; i < len; i++)
        for (int j = 0; j < 8; j++)
            bits[i * 8 + 7 - j] = (str[i] >> j) & 1;

    for (int i = 0; i < len * 8 / 6; i++)
    {
        int number = 0;
        for (int j = 0; j < 6; j++)
            if (bits[i * 6 + 5 - j])
                number += 1 << j;

        number += 32;
        if (number == ' ') printf("~");
        else printf("%c", number);
    }

    printf("\n");
}

}

int main(int argc, char** argv)
{
    for (int i = 0; i < 10; i++)
        Karamarko_Otidose::start();
    return EXIT_SUCCESS;
}
