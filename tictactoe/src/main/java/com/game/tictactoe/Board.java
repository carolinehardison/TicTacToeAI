package com.game.tictactoe;


public class Board{

    protected char[][] board;

    public Board() {
        char[][] board = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
        this.board = board;
    }

    public Board(char[][] board) {
        this.board = board;
    }

    public char get(int row, int col) {
        return board[row][col];
    }

    public void set(int row, int col, char val) {
        board[row][col] = val;
    }

    public void set(Location l, char val){
        board[l.row][l.col] = val;
    }

    public void reset() {
        char[][] board = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
        this.board = board;
    }

    public char gameResult() {
        for(int i = 0; i<3; i++) {
            if(board[i][0]!=' ' && board[i][1]!=' ' && board[i][2]!=' ') {
                if(board[i][0]==(board[i][1]) && board[i][0]==(board[i][2])) {
                    //System.out.println(board[i][0]+ "won: 80");
                    return board[i][0];
                }
            }
            if(board[0][i]!=' ' && board[1][i]!=' ' && board[2][i]!=' ') {
                if(board[0][i]==(board[1][i]) && board[0][i]==(board[2][i])) {
                    //System.out.println(board[0][i]+ "won: 86");
                    return board[0][i];
                }
            }

        }
        if(board[0][0]!=' ' && board[1][1]!=' ' && board[2][2]!=' ') {
            if(board[0][0]==(board[1][1]) && board[0][0]==(board[2][2]) && board[0][0]!=' ') {
                //System.out.println(board[0][0]+ "won: 94");
                return board[0][0];
            }
        }
        if(board[0][2]!=' ' && board[1][1]!=' ' && board[2][0]!=' ') {
            if(board[0][2]==(board[1][1]) && board[0][2]==(board[2][0]) && board[0][2]!=' ') {
                //System.out.println(board[0][2]+ "won :100");
                return board[0][2];
            }
        }
        if(this.full())
            return 'T';
        return 'N';
    }

    protected int getScore(){
        char s = gameResult();
        if(s=='O')
            return -1;
        else if(s=='X')
            return 1;
        else
            return 0;
    }

    public boolean full() {
        for(int r =0;r<3;r++)
            for(int c = 0;c<3;c++)
                if(board[r][c]==' ')
                    return false;
        return true;
    }

    public int moves() {
        int count = 0;
        for(int r =0;r<3;r++)
            for(int c = 0;c<3;c++)
                if(board[r][c]!=' ')
                    count++;
        return count;
    }


    public String toString() {
        String x = "";
        x += "+———+———+———+\n";
        x += "| "+board[0][0]+" | "+board[0][1]+" | "+board[0][2]+" |\n";
        x += "+———+———+———+\n";
        x += "| "+board[1][0]+" | "+board[1][1]+" | "+board[1][2]+" |\n";
        x += "+———+———+———+\n";
        x += "| "+board[2][0]+" | "+board[2][1]+" | "+board[2][2]+" |\n";
        x += "+———+———+———+\n";
        return x;
    }

    public void print() {
        System.out.println(this.toString());
    }


}
