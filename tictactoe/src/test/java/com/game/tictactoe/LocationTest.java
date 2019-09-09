package com.game.tictactoe;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LocationTest {
    @Test
    public void createListofUnusedSpots(){
        Location a = new Location(0,0);
        Location b = new Location(0,1);
        Location c = new Location(0,2);
        List<Location> o = Location.Available(a,b,c);
        List<Location> loc = new ArrayList<>();
        loc.add(new Location(1,0));
        loc.add(new Location(1,1));
        loc.add(new Location(1,2));
        loc.add(new Location(2,0));
        loc.add(new Location(2,1));
        loc.add(new Location(2,2));
        assertThat(loc.equals(o));
    }
}
