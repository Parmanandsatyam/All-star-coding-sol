#include <iostream>
#include <fstream>

using namespace std;

ifstream f("tree.in");

int k, n;
struct punct{
    int x, y;
}punct_citit;

struct arbore{
    int valoare;
    punct locatie;
    arbore *cadran1, *cadran2, *cadran3, *cadran4;
    arbore(int a, punct p)
    {
        valoare=a;
        locatie.x=p.x;
        locatie.y=p.y;
        cadran1=cadran2=cadran3=cadran4=0;
    }
}*root;

bool cadran1(punct p_fix, punct p_nou)
{
    if (p_nou.x>p_fix.x && p_nou.y>p_fix.y)
        return true;
    return false;
}

bool cadran2(punct p_fix, punct p_nou)
{
    if (p_nou.x<=p_fix.x && p_nou.y>p_fix.y)
        return true;
    return false;
}

bool cadran3(punct p_fix, punct p_nou)
{
    if (p_nou.x<=p_fix.x && p_nou.y<=p_fix.y)
        return true;
    return false;
}

bool cadran4(punct p_fix, punct p_nou)
{
    if (p_nou.x>p_fix.x && p_nou.y<=p_fix.y)
        return true;
    return false;
}

void adaugare_arbore(punct punct_adaugat, int ind, arbore *nod)
{
    if (cadran1(nod->locatie,punct_adaugat))
    {
        if (nod->cadran1)
            adaugare_arbore(punct_adaugat, ind, nod->cadran1);
        else
        {
            nod->cadran1=new arbore(ind, punct_adaugat);
        }
    }
    else if (cadran2(nod->locatie, punct_adaugat))
    {
        if (nod->cadran2)
            adaugare_arbore(punct_adaugat, ind, nod->cadran2);
        else
            nod->cadran2=new arbore(ind, punct_adaugat);
    }
    else if (cadran3(nod->locatie, punct_adaugat))
    {
        if (nod->cadran3)
            adaugare_arbore(punct_adaugat, ind, nod->cadran3);
        else
            nod->cadran3=new arbore(ind, punct_adaugat);
    }
    else if (cadran4(nod->locatie, punct_adaugat))
    {
        if (nod->cadran4)
            adaugare_arbore(punct_adaugat, ind, nod->cadran4);
        else
            nod->cadran4=new arbore(ind, punct_adaugat);
    }
}

void afisare(arbore *node)
{
    if (node->valoare==k)
    {
        if (node->cadran1)
            cout << node->cadran1->valoare;
        else
            cout << 0;
        if (node->cadran2)
            cout << node->cadran2->valoare;
        else
            cout << 0;
        if (node->cadran3)
            cout << node->cadran3->valoare;
        else
            cout << 0;
        if (node->cadran4)
            cout << node->cadran4->valoare;
        else
            cout << 0;
    }
    else
    {
        if (node->cadran1)
            afisare(node->cadran1);
        if (node->cadran2)
            afisare(node->cadran2);
        if (node->cadran3)
            afisare(node->cadran3);
        if (node->cadran4)
            afisare(node->cadran4);
    }
}

void stergere(arbore *&node)
{
    if (node->cadran1)
        stergere(node->cadran1);
    if (node->cadran2)
        stergere(node->cadran2);
    if (node->cadran3)
        stergere(node->cadran3);
    if (node->cadran4)
        stergere(node->cadran4);
    node=0;
    delete node;
}

int main() {
    for (int acsl=1; acsl<=10; ++acsl)
    {
        f >> k >> n;
        f >> punct_citit.x >> punct_citit.y;
        root=new arbore(1, punct_citit);
        for (int i=2; i<=n; ++i)
        {
            f >> punct_citit.x >> punct_citit.y;
            adaugare_arbore(punct_citit, i, root);
        }
        afisare(root);
        stergere(root);
        cout << '\n';
    }
    return 0;
}