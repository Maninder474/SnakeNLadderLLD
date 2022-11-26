package ValueObjects;

import java.util.Deque;
import java.util.LinkedList;

public class Game {
    Board board;
    Dice dice;
    Player winner;
    Deque<Player> playersList = new LinkedList<>();

    public Game(){
        InitialGame();
    }

    private void InitialGame() {
        System.out.println("initializing the Game");
        board = new Board(10, 4, 5);
        dice = new Dice(1);
        winner = null;
        System.out.println("Adding Players into the game");
        addPlayers();
    }

    private void addPlayers() {
        Player player1 = new Player("P1",0);
        Player player2 = new Player("P2",0);
        playersList.add(player1);
        playersList.add(player2);
    }

    public void startGame(){
        while(winner==null){
            //check whose turn now
            Player playerTurn = findPlayerTurn();
            System.out.println("player turn is:" + playerTurn.getId() + " current position is: " + playerTurn.getCurrentPosition());

            //roll the dice
            int diceNumbers = dice.rollDice();

            //get the new position
            int playerNewPosition = playerTurn.getCurrentPosition() + diceNumbers;
            playerNewPosition = jumpCheck(playerNewPosition);
            playerTurn.setCurrentPosition( playerNewPosition);

            System.out.println("player turn is:" + playerTurn.getId() + " new Position is: " + playerNewPosition);
            //check for winning condition
            if(playerNewPosition >= board.cells.length * board.cells.length-1){

                winner = playerTurn;
            }
        }
        System.out.println(winner.getId() +"is the Winner!!");
    }

    private int jumpCheck(int playerNewPosition) {
        if(playerNewPosition > board.cells.length * board.cells.length-1 ){
            return playerNewPosition;
        }

        Cell cell = board.getCell(playerNewPosition);
        if(cell.jump != null && cell.jump.start == playerNewPosition) {
            String jumpBy = (cell.jump.start < cell.jump.end)? "ladder" : "snake";
            System.out.println("jump done by: " + jumpBy);
            return cell.jump.end;
        }
        return playerNewPosition;

    }

    private Player findPlayerTurn() {
        Player player = playersList.removeFirst();
        playersList.addLast(player);
        return player;
    }
}
