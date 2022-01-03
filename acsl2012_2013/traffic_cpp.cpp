#include <fstream>
using namespace std;
ifstream fin("traffic.in");
ofstream fout("traffic.out");
int conv(int a, int b){
	a--;
	b--;
	return(b*6+a);
}

int main(){
	double grid[36][36];
	for(int a=0;a<36;a++){
		for(int b=0;b<36;b++){
			grid[a][b] = 10000;
		}}
	//Up
	for (int x=0;x<30;x=x+2){
		grid[x][x+6] = 1;
	}
	//Down
	for (int x=35;x>6;x=x-2){
		grid[x][x-6] = 1;
	}
	//Right
	for (int x=0;x<5;x++){
		grid[x][x+1] = 1;
	}
	for (int x=12;x<17;x++){
		grid[x][x+1] = 1;
	}
	for (int x=24;x<29;x++){
		grid[x][x+1] = 1;
	}
	//Left
	for (int x=11;x>6;x--){
		grid[x][x-1] = 1;
	}
	for (int x=23;x>18;x--){
		grid[x][x-1] = 1;
	}
	for (int x=35;x>30;x--){
		grid[x][x-1] = 1;
	}
	for(int k=0;k<36;k++){
		for(int i=0;i<36;i++){
			for(int j=0;j<36;j++){
				grid[i][j]=min(grid[i][j],grid[i][k]+grid[k][j]);
			}
		}
	}
	for(int x=1;x<6;x++){
		fout<<x<<". ";
		int x1,y1,x2,y2;
		fin>>x1>>y1>>x2>>y2;
		fout<<grid[conv(x1,y1)][conv(x2,y2)]<<endl;
	}

	///////6-10
	for(int a=0;a<36;a++){
		for(int b=0;b<36;b++){
			grid[a][b] = 10000;
		}}
	//Up
	for (int x=0;x<30;x=x+2){
		grid[x][x+6] = 1;
	}
	//Down
	for (int x=35;x>6;x=x-2){
		grid[x][x-6] = 1;
	}
	//Right
	for (int x=0;x<5;x++){
		grid[x][x+1] = 1;
	}
	for (int x=12;x<17;x++){
		grid[x][x+1] = 1;
	}
	for (int x=24;x<29;x++){
		grid[x][x+1] = 1;
	}
	//Left
	for (int x=11;x>6;x--){
		grid[x][x-1] = 1;
	}
	for (int x=23;x>18;x--){
		grid[x][x-1] = 1;
	}
	for (int x=35;x>30;x--){
		grid[x][x-1] = 1;
	}
	//Diagonals
	grid[30][25] = 1.4;
	grid[25][30] = 1.4;
	grid[25][20] = 1.4;
	grid[20][25] = 1.4;
	grid[20][15] = 1.4;
	grid[15][20] = 1.4;
	grid[20][14] = 1.4;
	grid[20][21] = 1.4;
	grid[21][14] = 1.4;
	grid[14][21] = 1.4;
	grid[15][21] = 1.4;
	grid[15][14] = 1.4;
	grid[14][7] = 1.4;
	grid[7][14] = 1.4;
	grid[6][13] = 1.4;
	grid[13][6] = 1.4;
	grid[13][20] = 1.4;
	grid[20][13] = 1.4;
	grid[15][10] = 1.4;
	grid[10][15] = 1.4;
	grid[21][28] = 1.4;
	grid[28][21] = 1.4;
	for(int k=0;k<36;k++){
		for(int i=0;i<36;i++){
			for(int j=0;j<36;j++){
				grid[i][j]=min(grid[i][j],grid[i][k]+grid[k][j]);
			}
		}
	}
	for(int x=6;x<11;x++){
		fout<<x<<". ";
		int x1,y1,x2,y2;
		fin>>x1>>y1>>x2>>y2;
		fout<<grid[conv(x1,y1)][conv(x2,y2)]<<endl;
	}
}
