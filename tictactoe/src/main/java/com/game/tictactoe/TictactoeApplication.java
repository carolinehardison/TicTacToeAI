package com.game.tictactoe;

import com.game.tictactoe.Service.MinimaxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

//@SpringBootApplication
//public class TictactoeApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(TictactoeApplication.class, args);
//    }
//
//}

@SpringBootApplication

public class TictactoeApplication {

    private static final Logger log = LoggerFactory.getLogger(TictactoeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TictactoeApplication.class);
    }


    @Bean
    public CommandLineRunner demo(MinimaxRepository repository) {
        return (args) -> {
            // save a couple of customers

            MinimaxService ms = new MinimaxService(repository);

            Board b = new Board();


            for (int move1 = 0; move1 < 9; move1++) {

                List<Location> m1s = Location.Available();
                Location m1 = m1s.get(move1);
                b.set(m1, 'X');
                List<Location> move2s = Location.Available(m1);
                for (int move2 = 0; move2 < 8; move2++) {
                    Location m2 = move2s.get(move2);
                    b.set(m2, 'O');
                    List<Location> move3s = Location.Available(m1, m2);
                    for (int move3 = 0; move3 < 7; move3++) {
                        Location m3 = move3s.get(move3);
                        b.set(m3, 'X');
                        //start of m4 logic
                        List<Location> move4s = Location.Available(m1, m2, m3);
                        for (int move4 = 0; move4 < 6; move4++) {
                            Location m4 = move4s.get(move4);
                            b.set(m4, 'O');
                            List<Location> move5s = Location.Available(m1, m2, m3, m4);
                            for (int move5 = 0; move5 < 5; move5++) {
                                Location m5 = move5s.get(move5);
                                b.set(m5, 'X');
                                int score = b.getScore();

                                if (score != 0) {
                                    b.set(m5, ' ');
                                    if (!ms.exists(b, m5, score)) {
                                        repository.save(new Minimax(b, m5, score));
                                        System.out.println("created");
                                    }else{
                                        System.out.println("existed: 5");
                                    }

                                } else {
                                    List<Location> move6s = Location.Available(m1, m2, m3, m4, m5);
                                    for (int move6 = 0; move6 < 4; move6++) {
                                        Location m6 = move6s.get(move6);
                                        b.set(m6, 'O');
                                        score = b.getScore();
                                        if (score != 0) {
                                            b.set(m6, ' ');
                                            if (!ms.exists(b, m6, score)) {
                                                repository.save(new Minimax(b, m6, score));
                                                System.out.println("created");
                                            }else{
                                                System.out.println("existed: 6");
                                            }

                                        } else {
                                            List<Location> move7s = Location.Available(m1, m2, m3, m4, m5, m6);
                                            for (int move7 = 0; move7 < 3; move7++) {
                                                Location m7 = move7s.get(move7);
                                                b.set(m7, 'X');
                                                score = b.getScore();
                                                if (score != 0) {
                                                    b.set(m7, ' ');
                                                    if (!ms.exists(b, m7, score)) {
                                                        repository.save(new Minimax(b, m7, score));
                                                        System.out.println("created");
                                                    }else{
                                                        System.out.println("existed: 7");
                                                    }

                                                } else {
                                                    //8th move logic
                                                    List<Location> move8s = Location.Available(m1,m2,m3,m4,m5,m6,m7);
                                                    for(int move8=0; move8<2; move8++){
                                                        Location m8 = move8s.get(move8);
                                                        b.set(m8,'O');
                                                        score=b.getScore();
                                                        if(score!=0) {
                                                            b.set(m8, ' ');
                                                            if (!ms.exists(b, m8, score)) {
                                                                repository.save(new Minimax(b, m8, score));
                                                                System.out.println("created");
                                                            }else{
                                                                System.out.println("existed: 8");
                                                            }

                                                        }else{
                                                            //LAST MOVE
                                                            List<Location> move9s = Location.Available(m1,m2,m3,m4,m5,m6,m7,m8);
                                                            Location m9 = move9s.get(0);
                                                            b.set(m9, 'X');
                                                            score = b.getScore();
                                                            b.set(m9, ' ');
                                                            if (!ms.exists(b, m9, score)) {
                                                                repository.save(new Minimax(b, m9, score));
                                                                System.out.println("created");
                                                            }else{
                                                                System.out.println("existed: 9");
                                                            }

                                                            //END OF LAST MOVE LOGIC
                                                        }
                                                        b.set(m8,' ');
                                                        //end of 8th move logic
                                                    }
                                                }
                                                b.set(m7, ' ');
                                            }
                                        }
                                        b.set(m6, ' ');
                                    }
                                }
                                b.set(m5, ' ');
                            }
                            b.set(m4, ' ');
                            //end of m4 logic
                        }
                        b.set(m3, ' ');
                        System.out.println("----------"+m1.correspondingLetter+ ": X, "+m2.correspondingLetter+": O, "+m3.correspondingLetter+": X"+"----------");
                    }
                    b.set(m2, ' ');
                }

                b.set(m1, ' ');
            }


        };



    }
}