#include <cstdio>
#include <cstring>
#include <string>

using namespace std;

char input[1000];


struct huge
{
   int st[100], dr[100];

   void stergere()
   {
      for (int i = 0; i < 100; i++)
         st[i] = dr[i] = 0;
   }

   huge()
   {
      stergere();
   }

   huge(const huge & h)
   {
      for (int i = 0; i < 100; i++)
      {
         st[i] = h.st[i];
         dr[i] = h.dr[i];
      }
   }

   huge & operator=(const huge & h)
   {
      for (int i = 0; i < 100; i++)
      {
         st[i] = h.st[i];
         dr[i] = h.dr[i];
      }
      return (*this);
   }

   void afisare()
   {
      int i;

      if (st[0] > 0)
         for (i = st[0]; i >= 1; i--)
         {
            if (st[i] >= 0 && st[i] <= 9) printf("%d", st[i]);
            else printf("%c", st[i]-10+'A');
         }
      else
         printf("0");

      printf(".");

      if (dr[0] > 0)
         for (i = 1; i <= dr[0]; i++)
         {
            if (dr[i] >= 0 && dr[i] <= 9) printf("%d", dr[i]);
            else printf("%c", dr[i]-10+'A');
         }
      else
         printf("0");
   }
};

huge citire(string s, int baza)
{
   int i;
   int dp = s.size();
   huge h;

   for (i = 0; i < s.size(); i++)
   {
      if (s[i] == '.')
      {
         dp = i;
         break;
      }
   }

   for (i = dp-1; i >= 0; i--)
   {
      int x;
      if (s[i] >= '0' && s[i] <= '9')
         x = s[i] - '0';
      else
         x = s[i] - 'A' + 10;

      h.st[++h.st[0]] = x;
   }

   for (i = dp+1; i < s.size(); i++)
   {
      int x;
      if (s[i] >= '0' && s[i] <= '9')
         x = s[i] - '0';
      else
         x = s[i] - 'A' + 10;

      h.dr[++h.dr[0]] = x;
   }

   return h;
}

huge rotunjire(huge h, int baza, int roundingPlace)
{
   huge a = h;
   bool addStuff = false;

   int i;

   if (a.dr[roundingPlace+1] * 2 >= baza)
      addStuff = true;

   while (a.dr[0] > roundingPlace)
   {
      a.dr[a.dr[0]] = 0;
      a.dr[0]--;
   }

   if (addStuff)
   {
      int t = 1;
      for (i = a.dr[0]; i >= 1; i--)
      {
         t += a.dr[i];
         a.dr[i] = t%baza;
         t /= baza;
      }

      for (i = 1; i <= a.st[0]; i++)
      {
         t += a.st[i];
         a.st[i] = t%baza;
         t /= baza;
      }

      if (t)
         a.st[++a.st[0]] = t;
   }
   return a;
}

int main()
{

   string number;
   int base, roundingPlace;

   freopen("7.in", "r", stdin);
   freopen("7.out", "w", stdout);

   char * p;

   while (!feof(stdin))
   {
      gets(input);
      scanf("\n");

      p = strtok(input, " ,");
      number = string(p);

      p = strtok(NULL, " ,");
      sscanf(p, "%d", &base);

      p = strtok(NULL, " ,");
      sscanf(p, "%d", &roundingPlace);

      rotunjire(citire(number, base), base, roundingPlace).afisare();
      printf("\n");
   }
   return 0;
}
