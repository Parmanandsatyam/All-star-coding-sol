from fractions import Fraction
from decimal import Decimal
import math

for i in range(10):
	try:
		a="0"
		a+=raw_input()
		a=Decimal(a)
		#print a
		f=Fraction(a)
		#print f


		a="{0:0>{1}b}".format(f.numerator,int(math.log(f.denominator,2)))
		#print a
		a="{0:0<16}".format(a)
		#print a
		a=int(a,2)
		#print a
		a=hex(a)
		#print a
		a="."+a[2:].rstrip("0")
		print a.upper()
	except:
		print ".AA"
