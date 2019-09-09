package com.game.tictactoe.src;


import com.game.tictactoe.Board;
import com.game.tictactoe.Location;
import com.game.tictactoe.Minimax;
import com.game.tictactoe.Service.MinimaxService;

import java.util.ArrayList;
import java.util.List;

public class Computer {

    int difficulty;
    private MinimaxService ms;

    public Computer() {
        difficulty = 0;
    }

    public Computer(int difficulty) {
        this.difficulty = difficulty;
    }

    public Computer(int difficulty, MinimaxService ms){
        this.difficulty = difficulty;
        this.ms = ms;
    }

    public Board move(Board b) {

        char letter = 'O';
        if(b.moves()%2==0) {
            letter = 'X';

        }
        if(difficulty == 0) {
            double r = Math.random() * 3;
            double c = Math.random() * 3;
            if(b.get((int) r, (int) c)==' ') {
                b.set((int) r, (int) c, letter);
                return b;
            }
            return move(b);
        } else if(difficulty == 1) {
            double random = Math.random() * 4;
            boolean favorableOdds = ((int) random)==0;
            if(favorableOdds) {
                if(offense(b).size()>0) {
                    double r = Math.random() * offense(b).size();
                    Location l = offense(b).get((int)r);
                    b.set(l.row, l.col, letter);
                }else if(defend(b).size()>0) {
                    double r = Math.random() * defend(b).size();
                    Location l = defend(b).get((int)r);
                    b.set(l.row, l.col, letter);
                }
                return b;
            }
            while(true) {
                double r = Math.random() * 3;
                double c = Math.random() * 3;
                if(b.get((int) r, (int) c)==' ') {
                    b.set((int) r, (int) c, letter);
                    return b;
                }
            }
        } else if(difficulty == 2) {
            if(offense(b).size()>0) {
                double r = Math.random() * offense(b).size();
                Location l = offense(b).get((int)r);
                b.set(l.row, l.col, letter);
            }else if(defend(b).size()>0) {
                double r = Math.random() * defend(b).size();
                Location l = defend(b).get((int)r);
                b.set(l.row, l.col, letter);
            }else {
                double r = Math.random() * 3;
                double c = Math.random() * 3;
                if(b.get((int) r, (int) c)==' ') {
                    b.set((int) r, (int) c, letter);
                    return b;
                }
                return move(b);
            }
            return b;
        }else {
            if(MinimaxService.getMove(b).valid) {
                Location l = MinimaxService.getMove(b);
                b.set(l.row, l.col, letter);
                return b;
            }else {
                minimax(b);
                return move(b);
            }
        }

    }

    public int minimax(Board board) {
        boolean winForX =board.moves()%2==0;
        if(MinimaxService.getMove(board).valid) {
            if(winForX) {
                int high = MinimaxService.getHighScore(board);
                if(high!=2) {
                    return high;
                }
            }else {
                int low = MinimaxService.getLowScore(board);
                if(low!=2) {
                    return low;
                }
            }
        }else if(board.gameResult()!='N') {
            if(board.gameResult()=='X')
                return 1;
            else if(board.gameResult()=='O')
                return -1;
            return 0;
        }
        if(winForX){
            int b = -1;
            List<Location> best = new ArrayList<>();
            for(int r = 0; r<3;r++){
                for(int c = 0; c<3; c++){
                    if(board.get(r,c)==' '){
                        board.set(r,c,'X');
                        int res = minimax(board);
                        if(res>b){
                            best = new ArrayList<>();
                            best.add(new Location(r,c));
                            b=res;
                        } else if(res == b){
                            best.add(new Location(r,c));
                        }
                        board.set(r,c, ' ');
                    }
                }

            }
            for(Location bestMove: best){
                MinimaxService.createGame(board, bestMove,b); //adding board in all 4 rotations
            }
            return b;
        }else{
            int b = 1;
            List<Location> best = new ArrayList<>();
            for(int r = 0; r<3;r++){
                for(int c = 0; c<3; c++){
                    if(board.get(r,c)==' '){
                        board.set(r,c,'O');
                        int res = minimax(board);
                        if(res<b){
                            best = new ArrayList<>();
                            best.add(new Location(r,c));
                            b=res;
                        } else if(res == b){
                            best.add(new Location(r,c));
                        }
                        board.set(r,c,' ');
                    }
                }
            }
            for(Location bestMove: best){
                MinimaxService.createGame(board, bestMove, b); //adding board in all 4 rotations
            }
            return b;
        }





    }

    public List<Location> defend(Board b) {
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List<Location> toReturn = new ArrayList();
        char opponent = 'O';
        if(b.moves()%2==1)
            opponent = 'X';
        for(int i = 0; i<3; i++) {
            if(b.get(i, 0)==opponent && b.get(i, 1)== opponent && b.get(i, 2)==' ') {
                toReturn.add(new Location(i, 2));
            } else if(b.get(i, 0)==opponent && b.get(i, 1)== ' ' && b.get(i, 2)==opponent) {
                toReturn.add(new Location(i, 1));
            } else if(b.get(i, 0)==' ' && b.get(i, 1)== opponent && b.get(i, 2)==opponent) {
                toReturn.add(new Location(i, 0));
            } else if(b.get(0, i)==opponent && b.get(1, i)== opponent && b.get(2, i)==' ') {
                toReturn.add(new Location(2, i));
            } else if(b.get(0, i)==opponent && b.get(1, i)== ' ' && b.get(2, i)==opponent) {
                toReturn.add(new Location(1, i));
            } else if(b.get(0, i)==' ' && b.get(1, i) == opponent && b.get(2, i)==opponent) {
                toReturn.add(new Location(0, i));
            }
        }
        if(b.get(0, 0)==opponent && b.get(1, 1)==opponent && b.get(2, 2) == ' ') {
            toReturn.add(new Location(2,2));
        } else if(b.get(0, 0)==opponent && b.get(1, 1)==' ' && b.get(2, 2) == opponent) {
            toReturn.add(new Location(1,1));
        } else if(b.get(0, 0)==' ' && b.get(1, 1)==opponent && b.get(2, 2) == opponent) {
            toReturn.add(new Location(0,0));
        } else if(b.get(2, 0)==opponent && b.get(1, 1)==opponent && b.get(0, 2) == ' ') {
            toReturn.add(new Location(0,2));
        } else if(b.get(2, 0)==opponent && b.get(1, 1)==' ' && b.get(0, 2) == opponent) {
            toReturn.add(new Location(1,1));
        } else if(b.get(2, 0)==' ' && b.get(1, 1)==opponent && b.get(0, 2) == opponent) {
            toReturn.add(new Location(2,0));
        }
        return toReturn;
    }

    public List<Location> offense(Board b) {
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List<Location> toReturn = new ArrayList();
        char letter = 'O';
        if(b.moves()%2==0)
            letter = 'X';
        for(int i = 0; i<3; i++) {
            if(b.get(i, 0)==letter && b.get(i, 1)== letter && b.get(i, 2)==' ') {
                toReturn.add( new Location(i, 2));
            } else if(b.get(i, 0)==letter && b.get(i, 1)== ' ' && b.get(i, 2)==letter) {
                toReturn.add( new Location(i, 1));
            } else if(b.get(i, 0)==' ' && b.get(i, 1)== letter && b.get(i, 2)==letter) {
                toReturn.add( new Location(i, 0));
            } else if(b.get(0, i)==letter && b.get(1, i)== letter && b.get(2, i)==' ') {
                toReturn.add( new Location(2, i));
            } else if(b.get(0, i)==letter && b.get(1, i)== ' ' && b.get(2, i)==letter) {
                toReturn.add( new Location(1, i));
            } else if(b.get(0, i)==' ' && b.get(1, i) == letter && b.get(2, i)==letter) {
                toReturn.add( new Location(0, i));
            }
        }
        if(b.get(0, 0)==letter && b.get(1, 1)==letter && b.get(2, 2) == ' ') {
            toReturn.add( new Location(2,2));
        } else if(b.get(0, 0)==letter && b.get(1, 1)==' ' && b.get(2, 2) == letter) {
            toReturn.add( new Location(1,1));
        } else if(b.get(0, 0)==' ' && b.get(1, 1)==letter && b.get(2, 2) == letter) {
            toReturn.add( new Location(0,0));
        } else if(b.get(2, 0)==letter && b.get(1, 1)==letter && b.get(0, 2) == ' ') {
            toReturn.add( new Location(0,2));
        } else if(b.get(2, 0)==letter && b.get(1, 1)==' ' && b.get(0, 2) == letter) {
            toReturn.add( new Location(1,1));
        } else if(b.get(2, 0)==' ' && b.get(1, 1)==letter && b.get(0, 2) == letter) {
            toReturn.add( new Location(2,0));
        }
        return toReturn;
    }


}
