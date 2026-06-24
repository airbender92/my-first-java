package org.Games;

import java.util.ArrayList;

public class SimpleDotCom {
    ArrayList<Integer> locationCells;
    int numOfHits = 0;

    public void setLocationCells(ArrayList<Integer> locs){
        locationCells = locs;
    }

    public  String checkYourself(String stringGuess){
        System.out.println("用户输入的内容是：" + stringGuess);
        int guess = Integer.parseInt(stringGuess);
        String result = "miss";
        for(int cell : locationCells){
            if(guess == cell){
                result = "hit";
                numOfHits++;
                locationCells.remove(Integer.valueOf(cell));
                break;
            }
        }

        if(locationCells.size() == 0){
            result = "kill";
        }
        System.out.println(result);
        return result;
    }
}
