package com.game.tictactoe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class TictactoeApplicationTests {

    @Autowired
    MinimaxRepository minimaxRepo;

    @Test
    public void createSimpleMinimax() {

        Board b = new Board();
        b.set(0,0, 'X');
        b.set(0,1,'O');
        b.set(1,0,'X');
        b.set(1,1,'O');
        Location l = new Location(2,0);
        Minimax min = new Minimax(b, l, 1);
        Minimax saved = minimaxRepo.save(min);
        assertThat(saved.id).isNotNull();


    }

}
