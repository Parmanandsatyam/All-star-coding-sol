// INTERMEDIATE 5
// XV. GIMNAZIJA
// PROGRAM #4

#include <iostream>
#include <stdio.h>
#include <vector>
#include <algorithm>

using namespace std;

int N, a, b;
string s;
int pov[100000];
char c;
vector < vector < int > > v;
vector < int > v0;
int bio[1000];
int pod[1000];
int boja=0;
int sol;
string abc="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//vector < int > bb;

void bfs (int cvor) {
    if (bio[cvor]==0) {
    bio[cvor]=1;
    pod[cvor]=boja;
    for (int i=0; i<v[cvor].size(); i++) {

        if (bio[v[cvor][i]]==0) bfs(v[cvor][i]);

    }

    }



}


int main () {


    for (int i=0; i<100; i++) v.push_back(v0);

    for (int ACSL=0; ACSL<10; ACSL++) {

    boja = 0;

    cin >> N >> s;

    for (int i=0; i<N; i++) bio[i]=0;



    // pretvaranje u binarni
    for (int i=0; i<s.size(); i++) {

        c=s[i];


        if (c=='0') {
            pov[4*i]=0;
            pov[4*i+1]=0;
            pov[4*i+2]=0;
            pov[4*i+3]=0;
        }

        if (c=='1') {
            pov[4*i]=0;
            pov[4*i+1]=0;
            pov[4*i+2]=0;
            pov[4*i+3]=1;
        }

        if (c=='2') {
            pov[4*i]=0;
            pov[4*i+1]=0;
            pov[4*i+2]=1;
            pov[4*i+3]=0;
        }

        if (c=='3') {
            pov[4*i]=0;
            pov[4*i+1]=0;
            pov[4*i+2]=1;
            pov[4*i+3]=1;
        }

        if (c=='4') {
            pov[4*i]=0;
            pov[4*i+1]=1;
            pov[4*i+2]=0;
            pov[4*i+3]=0;
        }

        if (c=='5') {
            pov[4*i]=0;
            pov[4*i+1]=1;
            pov[4*i+2]=0;
            pov[4*i+3]=1;
        }

        if (c=='6') {
            pov[4*i]=0;
            pov[4*i+1]=1;
            pov[4*i+2]=1;
            pov[4*i+3]=0;
        }

        if (c=='7') {
            pov[4*i]=0;
            pov[4*i+1]=1;
            pov[4*i+2]=1;
            pov[4*i+3]=1;
        }

        if (c=='8') {
            pov[4*i]=1;
            pov[4*i+1]=0;
            pov[4*i+2]=0;
            pov[4*i+3]=0;
        }

        if (c=='9') {
            pov[4*i]=1;
            pov[4*i+1]=0;
            pov[4*i+2]=0;
            pov[4*i+3]=1;
        }

        if (c=='A') {
            pov[4*i]=1;
            pov[4*i+1]=0;
            pov[4*i+2]=1;
            pov[4*i+3]=0;
        }

        if (c=='B') {
            pov[4*i]=1;
            pov[4*i+1]=0;
            pov[4*i+2]=1;
            pov[4*i+3]=1;
        }

        if (c=='C') {
            pov[4*i]=1;
            pov[4*i+1]=1;
            pov[4*i+2]=0;
            pov[4*i+3]=0;
        }

        if (c=='D') {
            pov[4*i]=1;
            pov[4*i+1]=1;
            pov[4*i+2]=0;
            pov[4*i+3]=1;
        }

        if (c=='E') {
            pov[4*i]=1;
            pov[4*i+1]=1;
            pov[4*i+2]=1;
            pov[4*i+3]=0;
        }

        if (c=='F') {
            pov[4*i]=1;
            pov[4*i+1]=1;
            pov[4*i+2]=1;
            pov[4*i+3]=1;
        }



    }


    a=0;
    for (int i=0; i<N; i++) {

        for (int j=i+1; j<N; j++) {

            if (pov[a]==1) {

                v[i].push_back(j);
                v[j].push_back(i);

            }

            a++;
        }


    }

    /* for (int i=0; i<N; i++) {

        for (int j=0; j<v[i].size(); j++) cout << v[i][j] << " ";
        cout << endl;

    }

    */



    for (int i=0; i<N; i++) {

        if (bio[i]==0) {

            bfs(i);
            boja++;
        }

    }

    // for (int i=0; i<N; i++) cout << pod[i];
    // cout << endl;

    /*sol=1;
    for (int i=0; i<N; i++) {

        bb.push_back(pod[i]);

    }

    sort(bb.begin(), bb.end());
    for (int i=1; i<N; i++) {

        if (bb[i]!=bb[i-1]) sol++;

    }*/



    //cout << sol << " ";
    cout << boja << " ";

    for (int i=0; i<N; i++) {

        if (pod[i]==0) cout << abc[i];

    }

    cout << endl;
    for (int i=0; i<N; i++) {

        v[i].clear();

    }


    }

    return 0;

}

