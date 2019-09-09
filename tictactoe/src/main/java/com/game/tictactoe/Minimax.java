
package com.game.tictactoe;



import javax.persistence.*;


/**
 * The persistent class for the minimax database table.
 *
 */
@Entity
@NamedQuery(name="Minimax.findAll", query="SELECT m FROM Minimax m")
public class Minimax {

    private static final long serialVersionUID = 1L;

    @Column
    private String a;

    @Column
    private String b;

    @Column
    private String c;

    @Column
    private String d;

    @Column
    private String e;

    @Column
    private String f;

    @Column
    private String g;

    @Column
    private String h;

    @Column
    private String i;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    private int moveCol;

    @Column
    private int moveRow;

    @Column
    private int score;


    public Minimax() {

    }

    public Minimax(Board board, Location loc, int score) {
        a = ""+board.get(0, 0);
        b = ""+board.get(0, 1);
        c = ""+board.get(0, 2);
        d = ""+board.get(1, 0);
        e = ""+board.get(1, 1);
        f = ""+board.get(1, 2);
        g = ""+board.get(2, 0);
        h = ""+board.get(2, 1);
        i = ""+board.get(2, 2);
        moveCol = loc.col;
        moveRow = loc.row;
        this.score = score;
    }




}

//
//import org.springframework.data.annotation.Id;
//
//public class Minimax {
//
//
//
//
//        private String a, b, c, d, e, f, g, h, i;
//
//        @Id
//        public long id;
//
//        public int moveCol, moveRow, score;
//
//        public Minimax() {
//
//        }
//
//        public Minimax(Board board, Location loc, int score) {
//            a = ""+board.get(0, 0);
//            b = ""+board.get(0, 1);
//            c = ""+board.get(0, 2);
//            d = ""+board.get(1, 0);
//            e = ""+board.get(1, 1);
//            f = ""+board.get(1, 2);
//            g = ""+board.get(2, 0);
//            h = ""+board.get(2, 1);
//            i = ""+board.get(2, 2);
//            moveCol = loc.col;
//            moveRow = loc.row;
//            this.score = score;
//        }
//
        public Board getBoard() {
            Board board = new Board();
            board.set(0, 0, a.charAt(0));
            board.set(0, 1, b.charAt(0));
            board.set(0, 2, c.charAt(0));
            board.set(1, 0, d.charAt(0));
            board.set(1, 1, e.charAt(0));
            board.set(1, 2, f.charAt(0));
            board.set(2, 0, g.charAt(0));
            board.set(2, 1, h.charAt(0));
            board.set(2, 2, i.charAt(0));
            return board;
        }
//
//        public void setBoard(Board board) {
//            a = ""+board.get(0, 0);
//            b = ""+board.get(0, 1);
//            c = ""+board.get(0, 2);
//            d = ""+board.get(1, 0);
//            e = ""+board.get(1, 1);
//            f = ""+board.get(1, 2);
//            g = ""+board.get(2, 0);
//            h = ""+board.get(2, 1);
//            i = ""+board.get(2, 2);
//        }
//
//        public Location getLocation() {
//            return new Location(moveRow, moveCol);
//        }
//
//        public void setLocation(Location loc) {
//            moveRow = loc.row;
//            moveCol = loc.col;
//        }
//
//        public int getScore() {
//            return score;
//        }
//
//        public void setScore(int score) {
//            this.score = score;
//        }
//
//        public Long getId() {
//            return this.id;
//        }
//
//
//}
