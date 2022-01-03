#!/usr/bin/python

import os
import subprocess

def get_perms():
	return subprocess.check_output(["ls", "-l", ".file"])[1:10]

for i in range(1, 11):
	try:
		usr = raw_input('%d. ' % i)
		try:
			subprocess.call(["rm", ".file"])
		except:
			pass
		subprocess.call(["touch", ".file"])
		perms = usr.split(', ')[0]
		cmd = usr.split(', ')[1]

		subprocess.call(["chmod", perms, ".file"])
		subprocess.call(["chmod", cmd, ".file"])

		print '%d. %s' % (i, get_perms())
	except:
		print '%d. rw-rw-rw' % i
