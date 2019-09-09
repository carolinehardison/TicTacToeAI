package com.game.tictactoe.Service;

import com.game.tictactoe.Board;
import com.game.tictactoe.Location;
import com.game.tictactoe.Minimax;
import com.game.tictactoe.MinimaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MinimaxService {
    private final MinimaxRepository repository;

    @Autowired
    public MinimaxService(MinimaxRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public boolean exists(Board b, Location loc, int score) {
        Minimax m = repository.findByAll("" + b.get(0, 0),
                "" + b.get(0, 1),
                "" + b.get(0, 2),
                "" + b.get(1, 0),
                "" + b.get(1, 1),
                "" + b.get(1, 2),
                "" + b.get(2, 0),
                "" + b.get(2, 1),
                "" + b.get(2, 2),
                loc.row, loc.col, score);
        if(m!=null){
            return true;
        }
        return false;
    }

    @Transactional
    public Long[] getBoards(Board b, int score){
        Long[] m = repository.findByBoard("" + b.get(0, 0),
                "" + b.get(0, 1),
                "" + b.get(0, 2),
                "" + b.get(1, 0),
                "" + b.get(1, 1),
                "" + b.get(1, 2),
                "" + b.get(2, 0),
                "" + b.get(2, 1),
                "" + b.get(2, 2), score);
        return m;

    }

    @Transactional
    public Long count(Board b, int score){
        Long m = repository.countBoards("" + b.get(0, 0),
                "" + b.get(0, 1),
                "" + b.get(0, 2),
                "" + b.get(1, 0),
                "" + b.get(1, 1),
                "" + b.get(1, 2),
                "" + b.get(2, 0),
                "" + b.get(2, 1),
                "" + b.get(2, 2), score);
        return m;
    }

    @Transactional
    public Board bestForX(Board b){
        //need a query that counts
        if(count(b,1)>0){
            Long[] b = getBoards(b, 1);
            int ind = (int) (Math.random() * b.length);
            try {
                Optional<Minimax> m = repository.findById(b[ind]);
                return m.getBoard();
            }catch (Exception e){

            }
        }else if(count(b, 0)>0){
            Long[] b = getBoards(b, 0);
            int ind = (int) (Math.random() * b.length);
            try {
                Minimax m = repository.findById(b[ind]);
                return m.getBoard();
            }catch (Exception e){

            }

        }else {
            Long[] b = getBoards(b, -1);
            int ind = (int) (Math.random() * b.length);
            try {
                Minimax m = repository.findById(b[ind]);
                return m.getBoard();
            }catch (Exception e){

            }
        }
        return null;
    }


}
