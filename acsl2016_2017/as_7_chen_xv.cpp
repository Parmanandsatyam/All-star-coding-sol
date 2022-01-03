#include <bits/stdc++.h>

using namespace std;

struct point
{
	double x, y;
};

struct line
{
	//a x + b y + c = 0
	double a, b, c;
	
	line (point p, point q)
	{
		if (p.x == q.x)
		{
			a = 1;
			b = 0;
			c = -p.x;
		}
		else
		{
			b = 1;
			//(a (x1 - x2) + (y1 - y2) = 0
			a = -(q.y - p.y) / (q.x - p.x);
			c = -(a * p.x + b * p.y);
		}
	}
};

bool side(line l, point p)
{
	return l.a * p.x + l.b * p.y + l.c > 0;
}

double ccw(point a, point b, point c)
{
	return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
}

int main()
{
	int n;
	
	vector<point> vec;
	
	cin>>n;
	vec.resize(n);
	for (point& p: vec)
		cin>>p.x>>p.y;
	
	for (int cs = 0; cs < 10; ++cs)
	{
		string s1, s2;
		
		cin>>s1>>s2;
		
		point a = vec[s1[0] - 'A'], b = vec[s1[1] - 'A'], c = vec[s1[2] - 'A'];
		point d = vec[s2[0] - 'A'], e = vec[s2[1] - 'A'];
		line la(b, c), lb(c, a), lc(a, b);
		line l(d, e);
		
		point p, q, r, s, t;
		
		if (side(l, a) != side(l, b))
		{
			if (side(l, a) != side(l, c))
			{
				//A
				p = a;
				q = a;
				r = b;
				s = a;
				t = c;
			}
			else
			{
				//B
				p = b;
				q = b;
				r = a;
				s = b;
				t = c;
			}
		}
		else
		{
			//C
			p = c;
			q = c;
			r = a;
			s = c;
			t = b;
		}
		
		while ((q.x - r.x) * (q.x - r.x) + (q.y - r.y) * (q.y - r.y) > 1e-12)
		{
			point mid{(q.x + r.x) / 2, (q.y + r.y) / 2};
			
			if (side(l, mid) == side(l, q))
				q = mid;
			else r = mid;
		}
		while ((s.x - t.x) * (s.x - t.x) + (s.y - t.y) * (s.y - t.y) > 1e-12)
		{
			point mid{(s.x + t.x) / 2, (s.y + t.y) / 2};
			
			if (side(l, mid) == side(l, s))
				s = mid;
			else t = mid;
		}
		
		cout<<abs(ccw(p, r, t)) / 2<<endl;
	}
}
