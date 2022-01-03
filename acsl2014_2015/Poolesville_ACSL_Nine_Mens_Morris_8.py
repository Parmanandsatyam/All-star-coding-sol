
board = [["-" for x in range(0, 7)] for y in range(0, 7)];

def trans_python_to_output(row, col):
	ret = "";
	alpha = "abcdefg";
	nums = "7654321";
	ret = alpha[col] + nums[6-row];
	return ret;

def trans_output_to_python(marker):
	alpha = "abcdefg";
	nums = "7654321";
	row = nums.index(marker[1]);
	col = alpha.index(marker[0]);
	return [row, col];

def copy_board():
	new_board = [];
	new_board = [[x for x in y] for y in board];
	return new_board;

def set_invalids(l):
	trans = trans_output_to_python;
	for i in l:
		board[trans(i)[0]][trans(i)[1]] = "X";

def set_piece(piece, val, boardname):
	trans = trans_output_to_python;
	boardname[trans(piece)[0]][trans(piece)[1]] = val;

def get_piece(piece, boardname):
	trans = trans_output_to_python;
	return boardname[trans(piece)[0]][trans(piece)[1]];

def has_amount(p1, p2, p3, piece_team, amount, boardname):
	g = get_piece;
	s = g(p1, boardname) + g(p2, boardname) + g(p3, boardname);
#	print s;
	return s.count(piece_team) == amount;
	
def is_block_mill(trans, piece_val, board_name): # piece val is the other team's piece!
	if trans == "a7":
		return has_amount("a7", "a4", "a1", piece_val, 2, board_name) or \
		       has_amount("a7", "d7", "g7", piece_val, 2, board_name);
	if trans == "a4":
		return has_amount("a7", "a4", "a1", piece_val, 2, board_name) or \
		       has_amount("a4", "b4", "c4", piece_val, 2, board_name);
	if trans == "a1":
		return has_amount("a7", "a4", "a1", piece_val, 2, board_name) or \
		       has_amount("a1", "d1", "g1", piece_val, 2, board_name);

	if trans == "b6":
		return has_amount("b6", "b4", "b2", piece_val, 2, board_name) or \
		       has_amount("b6", "d6", "f6", piece_val, 2, board_name);
	if trans == "b4":
		return has_amount("b6", "b4", "b2", piece_val, 2, board_name) or \
		       has_amount("a4", "b4", "c4", piece_val, 2, board_name);
	if trans == "b2":
		return has_amount("b6", "b4", "b2", piece_val, 2, board_name) or \
		       has_amount("b2", "d2", "f2", piece_val, 2, board_name);

	if trans == "c5":
		return has_amount("c5", "c4", "c3", piece_val, 2, board_name) or \
		       has_amount("c5", "d5", "e5", piece_val, 2, board_name);
	if trans == "c4":
		return has_amount("c5", "c4", "c3", piece_val, 2, board_name) or \
		       has_amount("c4", "b4", "a4", piece_val, 2, board_name);
	if trans == "c3":
		return has_amount("c5", "c4", "c3", piece_val, 2, board_name) or \
		       has_amount("c3", "d3", "e3", piece_val, 2, board_name);

	if trans == "d7":
		return has_amount("d7", "d6", "d5", piece_val, 2, board_name) or \
		       has_amount("a7", "d7", "g7", piece_val, 2, board_name);
	if trans == "d6":
		return has_amount("d7", "d6", "d5", piece_val, 2, board_name) or \
		       has_amount("b6", "d6", "f6", piece_val, 2, board_name);
	if trans == "d5":
		return has_amount("d7", "d6", "d5", piece_val, 2, board_name) or \
		       has_amount("c5", "d5", "e5", piece_val, 2, board_name);

	if trans == "d3":
		return has_amount("d3", "d2", "d1", piece_val, 2, board_name) or \
		       has_amount("c3", "d3", "e3", piece_val, 2, board_name);
	if trans == "d2":
		return has_amount("d3", "d2", "d1", piece_val, 2, board_name) or \
		       has_amount("b2", "d2", "f2", piece_val, 2, board_name);
	if trans == "d1":
		return has_amount("d3", "d2", "d1", piece_val, 2, board_name) or \
		       has_amount("a1", "d1", "g1", piece_val, 2, board_name);

	if trans == "e5":
		return has_amount("e5", "e4", "e3", piece_val, 2, board_name) or \
		       has_amount("c5", "d5", "e5", piece_val, 2, board_name);
	if trans == "e4":
		return has_amount("e5", "e4", "e3", piece_val, 2, board_name) or \
		       has_amount("e4", "f4", "g4", piece_val, 2, board_name);
	if trans == "e3":
		return has_amount("e5", "e4", "e3", piece_val, 2, board_name) or \
		       has_amount("c3", "d3", "e3", piece_val, 2, board_name);
			   
	if trans == "f6":
		return has_amount("f6", "f4", "f2", piece_val, 2, board_name) or \
		       has_amount("b6", "d6", "f6", piece_val, 2, board_name);
	if trans == "f4":
		return has_amount("f6", "f4", "f2", piece_val, 2, board_name) or \
		       has_amount("e4", "f4", "g4", piece_val, 2, board_name);
	if trans == "f2":
		return has_amount("f6", "f4", "f2", piece_val, 2, board_name) or \
		       has_amount("b2", "d2", "f2", piece_val, 2, board_name);

	if trans == "g7":
		return has_amount("g7", "g4", "g1", piece_val, 2, board_name) or \
		       has_amount("a7", "d7", "g7", piece_val, 2, board_name);
	if trans == "g4":
		return has_amount("g7", "g4", "g1", piece_val, 2, board_name) or \
		       has_amount("e4", "f4", "g4", piece_val, 2, board_name);
	if trans == "g1":
		return has_amount("g7", "g4", "g1", piece_val, 2, board_name) or \
		       has_amount("a1", "d1", "g1", piece_val, 2, board_name);

	return "IMPOSSIBLE";

def is_in_mill(trans, piece_val, board_name):
	if trans == "a7":
		return has_amount("a7", "a4", "a1", piece_val, 3, board_name) or \
		       has_amount("a7", "d7", "g7", piece_val, 3, board_name);
	if trans == "a4":
		return has_amount("a7", "a4", "a1", piece_val, 3, board_name) or \
		       has_amount("a4", "b4", "c4", piece_val, 3, board_name);
	if trans == "a1":
		return has_amount("a7", "a4", "a1", piece_val, 3, board_name) or \
		       has_amount("a1", "d1", "g1", piece_val, 3, board_name);

	if trans == "b6":
		return has_amount("b6", "b4", "b2", piece_val, 3, board_name) or \
		       has_amount("b6", "d6", "f6", piece_val, 3, board_name);
	if trans == "b4":
		return has_amount("b6", "b4", "b2", piece_val, 3, board_name) or \
		       has_amount("a4", "b4", "c4", piece_val, 3, board_name);
	if trans == "b2":
		return has_amount("b6", "b4", "b2", piece_val, 3, board_name) or \
		       has_amount("b2", "d2", "f2", piece_val, 3, board_name);

	if trans == "c5":
		return has_amount("c5", "c4", "c3", piece_val, 3, board_name) or \
		       has_amount("c5", "d5", "e5", piece_val, 3, board_name);
	if trans == "c4":
		return has_amount("c5", "c4", "c3", piece_val, 3, board_name) or \
		       has_amount("c4", "b4", "a4", piece_val, 3, board_name);
	if trans == "c3":
		return has_amount("c5", "c4", "c3", piece_val, 3, board_name) or \
		       has_amount("c3", "d3", "e3", piece_val, 3, board_name);

	if trans == "d7":
		return has_amount("d7", "d6", "d5", piece_val, 3, board_name) or \
		       has_amount("a7", "d7", "g7", piece_val, 3, board_name);
	if trans == "d6":
		return has_amount("d7", "d6", "d5", piece_val, 3, board_name) or \
		       has_amount("b6", "d6", "f6", piece_val, 3, board_name);
	if trans == "d5":
		return has_amount("d7", "d6", "d5", piece_val, 3, board_name) or \
		       has_amount("c5", "d5", "e5", piece_val, 3, board_name);

	if trans == "d3":
		return has_amount("d3", "d2", "d1", piece_val, 3, board_name) or \
		       has_amount("c3", "d3", "e3", piece_val, 3, board_name);
	if trans == "d2":
		return has_amount("d3", "d2", "d1", piece_val, 3, board_name) or \
		       has_amount("b2", "d2", "f2", piece_val, 3, board_name);
	if trans == "d1":
		return has_amount("d3", "d2", "d1", piece_val, 3, board_name) or \
		       has_amount("a1", "d1", "g1", piece_val, 3, board_name);

	if trans == "e5":
		return has_amount("e5", "e4", "e3", piece_val, 3, board_name) or \
		       has_amount("c5", "d5", "e5", piece_val, 3, board_name);
	if trans == "e4":
		return has_amount("e5", "e4", "e3", piece_val, 3, board_name) or \
		       has_amount("e4", "f4", "g4", piece_val, 3, board_name);
	if trans == "e3":
		return has_amount("e5", "e4", "e3", piece_val, 3, board_name) or \
		       has_amount("c3", "d3", "e3", piece_val, 3, board_name);
			   
	if trans == "f6":
		return has_amount("f6", "f4", "f2", piece_val, 3, board_name) or \
		       has_amount("b6", "d6", "f6", piece_val, 3, board_name);
	if trans == "f4":
		return has_amount("f6", "f4", "f2", piece_val, 3, board_name) or \
		       has_amount("e4", "f4", "g4", piece_val, 3, board_name);
	if trans == "f2":
		return has_amount("f6", "f4", "f2", piece_val, 3, board_name) or \
		       has_amount("b2", "d2", "f2", piece_val, 3, board_name);

	if trans == "g7":
		return has_amount("g7", "g4", "g1", piece_val, 3, board_name) or \
		       has_amount("a7", "d7", "g7", piece_val, 3, board_name);
	if trans == "g4":
		return has_amount("g7", "g4", "g1", piece_val, 3, board_name) or \
		       has_amount("e4", "f4", "g4", piece_val, 3, board_name);
	if trans == "g1":
		return has_amount("g7", "g4", "g1", piece_val, 3, board_name) or \
		       has_amount("a1", "d1", "g1", piece_val, 3, board_name);

	return "IMPOSSIBLE";

def get_other_piece(piece_sym):
	if piece_sym == "B":
		return "W";
	else:
		return "B";

def find_make_mill(piece):
	board_cpy = copy_board();
	piece_val = get_piece(piece, board_cpy);
	row_org, col_org = trans_output_to_python(piece);

	for row in range(0, 7):
		board_cpy[row_org][col_org] = "-";
		for col in range(0, 7):
			trans = trans_python_to_output(row, col);
			if (6-row) == row_org and col == col_org:
				continue;
			if get_piece(trans, board_cpy) != "-":
				continue;

#			print trans, row, col, piece_val, 
			board_cpy[6 - row][col] = piece_val;
#			print board_cpy[row][col];
			if is_in_mill(trans, piece_val, board_cpy):
				return trans;
			
			board_cpy[6 - row][col] = "-";
	return "NONE";
	
def find_block_mill(piece):
	board_cpy = copy_board();
	piece_val = get_piece(piece, board_cpy);
	row_org, col_org = trans_output_to_python(piece);

	for row in range(0, 7):
		board_cpy[row_org][col_org] = "-";
		for col in range(0, 7):
			trans = trans_python_to_output(row, col);
			if (6-row) == row_org and col == col_org:
				continue;
			if get_piece(trans, board_cpy) != "-":
				continue;

			board_cpy[6- row][col] = piece_val;
			if is_block_mill(trans, get_other_piece(piece_val), board_cpy):
				return trans;
			
			board_cpy[6 - row][col] = "-";
	return "NONE";

def find_empty_space(piece):
	board_cpy = copy_board();
	piece_val = get_piece(piece, board_cpy);
	row_org, col_org = trans_output_to_python(piece);

	for row in range(0, 7):
		board_cpy[row_org][col_org] = "-";
		for col in range(0, 7):
			trans = trans_python_to_output(row, col);
#			print trans;
			if (6-row) == row_org and col == col_org:
				continue;
			if get_piece(trans, board_cpy) != "-":
				continue;
			return trans;

	return "NONE";

# board setup
invalids = ["a6", "a5", "a3", "a2", "b7", "b5", "b3", "b1", "c7", "c6", "c2", "c1",
            "d4", "e7", "e6", "e2", "e1", "f7", "f5", "f3", "f1", "g6", "g5", "g3", "g2"];
set_invalids(invalids);

#for i in range(0 ,7):
#	for i2 in range(0, 7):
#		a =  trans_python_to_output(i, i2);
#		print a;
#		print trans_output_to_python(a);


#quit();

for i in range(1, 8):
	usr = raw_input("%d. " % i);
	output = "";
	if i == 1:
		for pos in usr.split(", ")[1:]:
			set_piece(pos, "B", board);
	elif i == 2:
		for pos in usr.split(", ")[1:]:
			set_piece(pos, "W", board);
	else:
		output = find_make_mill(usr);
		if output == "NONE":
			output = find_block_mill(usr);
		if output == "NONE":
			output = find_empty_space(usr);

		print "%d. %s" % (i - 2, output);
		
board = [["-" for x in range(0, 7)] for y in range(0, 7)];
invalids = ["a6", "a5", "a3", "a2", "b7", "b5", "b3", "b1", "c7", "c6", "c2", "c1",
            "d4", "e7", "e6", "e2", "e1", "f7", "f5", "f3", "f1", "g6", "g5", "g3", "g2"];
set_invalids(invalids);

for i in range(8, 15):
	usr = raw_input("%d. " % i);
	output = "";
	if i == 8:
		for pos in usr.split(", ")[1:]:
			set_piece(pos, "B", board);
	elif i == 9:
		for pos in usr.split(", ")[1:]:
			set_piece(pos, "W", board);
	else:
		output = find_make_mill(usr);
		if output == "NONE":
			output = find_block_mill(usr);
		if output == "NONE":
			output = find_empty_space(usr);

		print "%d. %s" % (i - 4, output);
