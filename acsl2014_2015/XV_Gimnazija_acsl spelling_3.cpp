#include <iostream>
using namespace std;

//      ***         XV. gimnazija Zagreb, Junior 5's         ***

bool vowel ( char c ) {
    if( c == 'a' or c == 'e' or c == 'i' or c == 'o' or c == 'u' ) return true;
    return false;
}

int pet;

int main() {

pet = 5;
while( pet-- ) {
    string s;
    cin >> s;
    if( s == "f" )
        cout << "ves";
    else if( s.size() == 1 ) {
        cout << s;
        if( s == "s" or s == "x" or s == "z" ) cout << "es";
        else cout << "s";
    }
    else {
        cout << s.substr( 0, s.size() - 2 );
        s = s.substr( s.size() - 2);
        if( s == "ch" or s == "sh" or s[1] == 's' or s[1] == 'x' or s[1] == 'z' )
            cout << s << "es" << endl;
        else if( vowel( s[0] ) and s[1] == 'y' )
            cout << s << "s"  << endl;
        else if( !vowel( s[0] ) and s[1] == 'y' )
            cout << s[0] << "ies";
        else if( s == "fe" )
            cout << "ves";
        else if( s[1] == 'f' )
            cout << s[0] << "ves";
        else if( vowel( s[0] ) and s[1] == 'o' )
            cout << s << "s";
        else if( !vowel( s[0] ) and s[1] == 'o' )
            cout << s << "es";
        else
            cout << s << "s";
    }
    cout << endl;
}

pet = 5;
while( pet-- ) {
    string s, suff;
    cin >> s >> suff;

    char l = s[ s.size()-1 ];
    char l1 = '2';
    if( s.size() >= 2 ) l1 = s[ s.size()-2 ];
    char l2 = '3';
    if( s.size() >= 3 ) l2 = s[ s.size()-3 ];
    if( l == 'e' ) {
        if( vowel( suff[0] ) )
            cout << s.substr( 0, s.size()-1 );
        else cout << s;
    }
    else if( l == 'y' ) {
        if( suff[0] == 'i' )
            cout << s;
        else cout << s.substr( 0, s.size()-1 ) << 'i';
    }
    else if( s.size() >= 2 and vowel( l1 ) and !vowel( l ) and vowel( suff[0] ) ) {
        if( s.size() >= 3 and vowel( l2 ) )
            cout << s;
        else
            cout << s << l;
    }
    else
        cout << s;
    cout << suff << endl;
}
    return 0;
}
