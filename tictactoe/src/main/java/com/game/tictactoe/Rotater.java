//package com.game.tictactoe;
//
//
///**
// * takes a given board and returns the same boards
// * rotated 90, 180 and 270 times
// */
//public class Rotater {
//
//    /**
//     *
//     * @param toRotate board
//     * @return all rotations of board state
//     */
//    static Board[] rotate(Board toRotate){
//
//        Board[] toReturn = new Board[3];
//
//        //Boards as positions on the clock
//        char[][] twelve = toRotate.board;
//        char[][] three = new char[3][3];
//        char[][] six = new char[3][3];
//        char[][] nine = new char[3][3];
//
//        for(int r = 0; r<3; r++){
//            for(int c = 0; c<3; c++){
//                char temp = twelve[r][c];
//                three[2-c][r] = temp;
//                six[2-r][2-c] = temp;
//                nine[c][2-r] = temp;
//            }
//        }
//
//        toReturn[0] = new Board(three);
//        toReturn[1] = new Board(six);
//        toReturn[2] = new Board(nine);
//        return toReturn;
//
//    }
//
//}
