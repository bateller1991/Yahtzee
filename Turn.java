import java.util.Locale;
import java.util.Scanner;

public class Turn {
    Dice[] dice;
    int currRoll, MAX_ROLLS, sumOfDice, turnId, userChoice;
    Roll[] rolls;
    String name;
    boolean[] keep;


    public Turn(int turnId, String name){
        this.name = name;
        this.turnId = turnId;
        currRoll = 1;
        MAX_ROLLS = 3;
        dice = new Dice[6];
        dice = createDice();
        sumUpDice();
        rolls = new Roll[(MAX_ROLLS+1)];
        initiateRolls();
    }

    public ScoreCard runTurn(ScoreCard sc){
        keep = new boolean[dice.length];
        int objIndex = -1;
        boolean completedTurn = false;
        while(currRoll <= MAX_ROLLS) {
            int choice = runTurnMenu();
            switch (choice) {
                case (1):
                    this.dice = rolls[currRoll++].RollDice(keep);
                    rolls[(currRoll - 1)].printDice();
                    if(currRoll > MAX_ROLLS) {
                        sc = finishTurn(sc, this.dice);
                        break;
                    }
                    keep = resetKeep(keep);
                    keep = keepWhichDice(keep);
                    break;

                case (2):
                    sc.printScoreCard();
                    break;
            }
        }
        sc.printScoreCard();
        return sc;
    }

    private ScoreCard finishTurn(ScoreCard sc, Dice[] d) {
        int objIndex = -1;
        boolean completedTurn = false;
        objIndex = whichObjToScore(sc);
        if (objIndex == 12 && sc.gameObjectives[objIndex].isScored == true) {
            System.out.println("\n\nChance has already been scored, sorry...\n\n");
            sc = finishTurn(sc, d);
        }
        completedTurn = sc.completeObjective(objIndex, this.dice);
        if (completedTurn == false) {
            System.out.println("\n\n" + sc.gameObjectives[objIndex].obj + " cannot be used, SORRY.\n\nThe only way to Score " + sc.gameObjectives[objIndex].obj + " is to accept a ZERO.\n\t'y' or 'n', Accept?: ");
            Scanner input = new Scanner(System.in);
            String ans = input.nextLine();
            if (ans.equalsIgnoreCase("y") && sc.gameObjectives[objIndex].isScored == false) {
                sc.gameObjectives[objIndex].obj = sc.gameObjectives[objIndex].obj + " * COMPLETED! *";
                sc.gameObjectives[objIndex].isScored = true;
                sc.gameObjectives[objIndex].score = 0;
                return sc;
            }
            else if (ans.equalsIgnoreCase("y") && sc.gameObjectives[objIndex].isScored == true){
                System.out.println("\n" + sc.gameObjectives[objIndex].obj + " has already been scored. Choose another objective.");
                sc = finishTurn(sc, d);
            }
            else {
                sc = finishTurn(sc, d);
            }
        }
        return sc;
    }

    private int whichObjToScore(ScoreCard sc){
        int i = 0;
        System.out.print("Which Objective would you like to Score with your dice?\n\n");
        for(int j = 1; j < sc.gameObjectives.length; j++){
            System.out.println("(" + j + ") to Score --> " + sc.gameObjectives[j].obj);
        }
        System.out.print("\n\tObjective #: ");
        Scanner input = new Scanner(System.in);
        i = input.nextInt();
        if(i < 1 || i >= sc.gameObjectives.length) {
            i = whichObjToScore(sc);
        }
        return i;
    }

    private boolean[] resetKeep(boolean[] k){
        for(boolean keep: keep){
            keep = false;
        }
        return k;
    }

    private boolean[] keepWhichDice(boolean[] keep){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter which dice NUMBERS to save: ");
        char[] keepers = input.nextLine().toCharArray();
        for(int i = 0; i < keepers.length; i++){
            int index = keepers[i] - 48;
            keep[index] = true;
        }
        return keep;
    }


    public int runTurnMenu(){
        System.out.print("\n\n\n\n\n" + this.name + "'s Turn #(" + this.turnId + "):\n\nRolls Remaining: " + (rolls.length - currRoll) + "\n\n\tMenu Options:\n");
        System.out.print("\n(1) to Roll Dice.\n(2) to print Score Card.\n\n\tYour Choice: ");
        Scanner input = new Scanner(System.in);
        int answer = input.nextInt();
        System.out.println("\n");
        if(answer < 1 || answer > 3)
            runTurnMenu();
        return answer;
    }


    private void initiateRolls(){
        for(int i = 1; i < rolls.length; i++){
            rolls[i] = new Roll(i, dice);
        }
    }

    public void sumUpDice(){
        sumOfDice = 0;
        for(int i = 0; i < dice.length; i++){
            sumOfDice += dice[i].rollValue;
        }
    }

    private Dice[] createDice(){
        for(int i = 0; i < dice.length; i++){
            dice[i] = new Dice((i+1));
        }
        return dice;
    }

}
