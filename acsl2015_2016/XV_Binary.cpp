#include <cstdlib>
#include <cstdio>

// Bobo, ne kradi ovaj kod!!
namespace Karamarko_Otidose
{

int pupa_capa(int a1, int b1, int a2, int b2)
{
    return a1 * b2 - a2 * b1;
}

void start()
{
    int a, b;
    scanf("%d %d", &a, &b);

    int cijeli_dio = a / b;
    a %= b;

    bool first = true;
    for (int i = 31; i >= 0; i--)
    {
        if (cijeli_dio & (1 << i)) { printf("1"); first = false; }
        else if (!first || !i) printf("0");
    }

    printf(".");

    int hardcoded_pupa[6][6] = { { 5, 0, 0, 0, 0, 0 },   // 1 / 2
                                 { 2, 5, 0, 0, 0, 0 },   // 1 / 4
                                 { 1, 2, 5, 0, 0, 0 },   // 1 / 8
                                 { 0, 6, 2, 5, 0, 0 },   // 1 / 16
                                 { 0, 3, 1, 2, 5, 0 },   // 1 / 32
                                 { 0, 1, 5, 6, 2, 5 } }; // 1 / 64

    int plupa[7] = { 0 };

    for (int i = 1; i <= 6; i++)
    {
        if (pupa_capa(a, b, 1, 1 << i) >= 0)
        {
            a = a * (1 << i) - b;
            b *= 1 << i;
            printf("1");

            for (int j = 0; j < 6; j++)
                plupa[j + 1] += hardcoded_pupa[i - 1][j];
        }
        else
        {
            printf("0");
        }
    }

    printf(", ");

    for (int i = 6; i > 0; i--)
    {
        plupa[i - 1] += plupa[i] / 10;
        plupa[i] %= 10;
    }

    printf("%d.", cijeli_dio + plupa[0]);
    for (int i = 1; i <= 6; i++)
        printf("%d", plupa[i]);

    printf("\n");
}

}

int main(int argc, char** argv)
{
    for (int i = 0; i < 5; i++)
        Karamarko_Otidose::start();
    return EXIT_SUCCESS;
}
