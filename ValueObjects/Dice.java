package ValueObjects;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    int diceUsed;
    int min = 1;
    int max = 6;

    Dice(int diceUsed){
        this.diceUsed = diceUsed;
    }

    public int rollDice(){
        int totalCount=0;
        int diceRolled = 0;
        while(diceRolled<diceUsed){
            totalCount += ThreadLocalRandom.current().nextInt(min,max+1);
            diceRolled++;
        }
        return totalCount;
    }
}
