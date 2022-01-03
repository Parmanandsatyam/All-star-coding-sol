#include <cctype>
#include <cstdio>
#include <cstring>

using namespace std;

const int TC = 10, MAXL = 100;
const char PAD[10] = "rwxrwxrwx";
int oct, idx;
char args[MAXL + 1], sol[10], op;
bool per[3][3], c[3], p[3];

int main() {
	for(int t = 0; t < TC; t++) {
		scanf("%o%s", &oct, args);
		for(int i = 2; i >= 0; i--)
			for(int j = 2; j >= 0; j--) {
				per[i][j] = (oct & 1);
				oct >>= 1;
			}
		for(int k = 0; args[k] != '\0';) {
			memset(c, false, sizeof c);
			memset(p, false, sizeof p);
			for(; isalpha(args[k]); k++)
				switch(args[k]) {
					case 'a':
						c[0] = c[1] = c[2] = true;
						break;
					case 'u':
						c[0] = true;
						break;
					case 'g':
						c[1] = true;
						break;
					case 'o':
						c[2] = true;
						break;
				}
			op = args[k++];
			for(; isalpha(args[k]); k++)
				switch(args[k]) {
					case 'r':
						p[0] = true;
						break;
					case 'w':
						p[1] = true;
						break;
					case 'x':
						p[2] = true;
						break;
				}
			switch(op) {
				case '+':
					for(int i = 0; i < 3; i++)
						if(c[i])
							for(int j = 0; j < 3; j++)
								if(p[j])
									per[i][j] = true;
					break;
				case '-':
					for(int i = 0; i < 3; i++)
						if(c[i])
							for(int j = 0; j < 3; j++)
								if(p[j])
									per[i][j] = false;
					break;
				case '=':
					for(int i = 0; i < 3; i++)
						if(c[i])
							for(int j = 0; j < 3; j++)
								per[i][j] = p[j];
					break;
			}
			while(args[k] == ',')
				k++;
		}
		memcpy(sol, PAD, sizeof sol);
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				if(!per[i][j])
					sol[3 * i + j] = '-';
		printf("%s\n", sol);
	}
	return 0;
}
