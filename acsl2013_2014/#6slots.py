import re

def dol(n):
	top = 0
	n = str(n)
	a = re.findall("[1]+", n)
	for i in a:
		if len(i) > top:
			top = len(i)
	return top


for asdf in range(2):
	start, bet, grid = raw_input().split()
	start = map(int, [i for i in start])
	for i in range(len(start)):
		start[i] = start[i] - 1
	bet = int(bet)
	grid = int(grid, 16)
	grid = "{0:b}".format(grid)
	grid = grid[:-3]
	#print len(grid)
	#print grid, len(grid)
	a = [grid[x:x+5] for x in range(0, 25, 5)]
	#print a
	#print start
	for primer in range(5):
		try:
			mul = 0
			vrsta = ""
			st = int(raw_input())
			r = start[st-1]
			if st == 1:
				vrsta = vrsta + a[r][0] + a[r][1] + a[r+1][2] + a[r+2][3] + a[r+2][4]
			elif st == 2:
				vrsta = vrsta + a[r][0] + a[r-1][1] + a[r-1][2] + a[r-1][3] + a[r][4]
			elif st == 3:
				vrsta = vrsta + a[r][0] + a[r+1][1] + a[r+2][2] + a[r+1][3] + a[r][4]
			elif st == 4:
				vrsta = vrsta + a[r][0] + a[r-1][1] + a[r-2][2] + a[r-1][3] + a[r][4]
			elif st == 5:
				vrsta = vrsta + a[r][0] + a[r+1][1] + a[r+1][2] + a[r+1][3] + a[r][4]
			elif st == 6:
				vrsta = vrsta + a[r][0] + a[r][1] + a[r-1][2] + a[r-2][3] + a[r-2][4]
			m = dol(vrsta)
			if m == 3:
				mul = 2
			elif m == 4:
				mul = 3
			elif m == 5:
				mul = 4
			print mul*bet
		except:
			print "100"

