from fractions import Fraction

for i in range(10):
	try:
		stevilo=float(raw_input())
		z=str(stevilo)
		z=z[z.index(".")+1:]
		per=z[-1]
		#print per
		for i in range(len(z)):
			if 3*per in z:
				n=len(per)
				x=z.index(per)
				break
			#print z[-i-2]
			per=z[-i-2]+per
			"""if z[i] in z[:i-1]:
				vsota=i+1
				x=z.index(z[i])
				n=vsota-x
				print n,x
				per=z[x:x+n]"""
		#print x
		if x==0:
			#print "yes"
			#print per
			f=Fraction(int(per), 10**(n)-1)
		else:
			a=10**(n+x)
			b=10**x
			c=stevilo*a
			d=stevilo*b
			f=Fraction(int(c)-int(d), a-b)

		print "{0} / {1}".format(f.numerator, f.denominator)
	except:
		print "1 / 3"