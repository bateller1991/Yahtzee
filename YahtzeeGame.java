import java.util.InputMismatchException;
import java.util.Scanner;

public class YahtzeeGame {
    private int numUsers;
    private User[] u;
    private int currUser;
    private int currTurn;
    private int MAX_TURNS;
    private String[] gameObjectives;
    private int MAX_USERS = 4;
    String[] names;



    public YahtzeeGame(){
        System.out.println("//////////////////////////////////////////\n\n\n\tYAHTZEE GAME!\n\n\n\n//////////////////////////////////////////");
        MAX_TURNS = 13;
        gameObjectives = new String[MAX_TURNS+1];
        gameObjectives = fillGameObjectives(gameObjectives);
        currTurn = 1;
        currUser = 1;
        getNumUsers();
        u = new User[(numUsers+1)];
        names = new String[numUsers+1];
        getNames();
        initiateUsers();
        //Everything Working until here.
        runGame();
        printAllScoreCards();
    }

    public void runGame(){
        while(currTurn < MAX_TURNS) {
            if (currUser > numUsers) {
                currUser = 1;
                currTurn++;
            }
            u[currUser++].runTurn(currTurn);
        }
        System.out.println("\n\n/////////////////////////////////////////////////////////////////////////\n\nGame Over.\n\n\n\tResults:\n\n");

    }

    private void printAllScoreCards(){
        for(int i = 1; i < u.length; i++){
            u[i].sc.printScoreCard();
            System.out.println("\nTotal Score: " + u[i].sc.getScore() + " pts.");
        }
    }

    private void getNames(){
        Scanner stringInput = new Scanner (System.in);
        for(int i = 1; i < u.length; i++){
            System.out.print("\nEnter a Player's Name: ");
            names[i] = stringInput.nextLine();
        }
    }

    private void initiateUsers(){
        Scanner stringInput = new Scanner (System.in);
        for(int i = 1; i < u.length; i++){
            u[i] = new User(gameObjectives, names[i]);
            u[i].initializeScoreCard(gameObjectives);
        }
    }

    private void getNumUsers(){
        try {
            do {
                numUsers = getIntAnswer("\n\n\nHOW MANY Players there are: ");
            } while (numUsers <= 0 || numUsers > MAX_USERS);
        } catch(InputMismatchException e){
            getNumUsers();
        }
    }

    private int getIntAnswer(String string){
        Scanner input = new Scanner(System.in);
        System.out.print(string);
        int n = input.nextInt();
        return n;
    }

    private String[] fillGameObjectives(String[] go){
        //could upload txt file.
        go[0] = "\n\n\tScore Card:";
        go[1] = "One's.";
        go[2] = "Two's.";
        go[3] = "Three's.";
        go[4] = "Four's.";
        go[5] = "Fives.";
        go[6] = "Sixes.";
        go[7] = "3 of a Kind.";
        go[8] = "4 of a Kind.";
        go[9] = "Full House.";
        go[10] = "Small Straight.";
        go[11] = "Large Straight.";
        go[12] = "Chance.";
        go[13] = "Yahtzee.";
        return go;
    }

    public static void main(String[] args){
        YahtzeeGame newGame = new YahtzeeGame();
    }


}


