package ValueObjects;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    Cell [][]cells ;

    Board(int boardSize, int numberOfSnakes,int numberOfLadders){
        System.out.println("initializing the board");
        initializeCells(boardSize);
        System.out.println("adding snakes and ladders");
        addSnakesLadders(cells, numberOfSnakes, numberOfLadders);
        System.out.println("added snakes and ladders");
    }

    private void addSnakesLadders(Cell[][] cells, int numberOfSnakes, int numberOfLadders) {
        int snakeHead,snakeTail,ladderstart,ladderend;

        while(numberOfSnakes>0){
            snakeHead = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1 );
            snakeTail = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1 );

            if(snakeHead<snakeTail)
                continue;

            Jump snakeObj = new Jump();
            snakeObj.setStart(snakeHead);
            snakeObj.setEnd(snakeTail);

            Cell cell = getCell(snakeHead);
            cell.setJump(snakeObj);
            
            numberOfSnakes--;

        }

        while(numberOfLadders>0){
            ladderstart = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1 );
            ladderend = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1 );

            if(ladderend<ladderstart)
                continue;

            Jump ladderObj = new Jump();
            ladderObj.setStart(ladderstart);
            ladderObj.setEnd(ladderend);

            Cell cell = getCell(ladderstart);
            cell.setJump(ladderObj);
            numberOfLadders--;
        }
    }

    private void initializeCells(int boardSize) {

        cells = new Cell[boardSize][boardSize];

        for(int i =0;i<boardSize;i++)
            for(int j = 0;j<boardSize;j++){
                Cell cell = new Cell();
                cells[i][j]=cell;
            }
    }

    Cell getCell(int playerPosition) {
        int boardRow = playerPosition / cells.length;
        int boardColumn = (playerPosition % cells.length);
        return cells[boardRow][boardColumn];
    }

}
