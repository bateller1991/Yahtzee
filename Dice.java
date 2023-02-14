
public class Dice {

    int[] sides;
    int random, id, rollValue;

    public Dice(int newId){
        fillSides();
        random = (int)Math.random();
        random = (int)Math.random()*random;
        this.id = newId;
        this.rollValue = 0;
    }

    private void fillSides(){
        this.sides = new int[6];
        for(int i = 0; i < this.sides.length; i++){
            this.sides[i] = (i+1);
        }
    }


    private void getIndex(){
        random = (int)(this.sides.length*Math.random());
    }


    public void rollDice(){
        getIndex();
        this.rollValue = this.sides[random];
    }

    public void printDice(){
        switch(this.rollValue){
            case(1):
                System.out.println(" __________");
                System.out.println("|          |");
                System.out.println("|    **    |");
                System.out.println("|          |");
                System.out.println("|__________|");
                break;
            case(2):
                System.out.println(" __________");
                System.out.println("|          |");
                System.out.println("|   **     |");
                System.out.println("|      **  |");
                System.out.println("|__________|");
                break;
            case(3):
                System.out.println(" __________");
                System.out.println("| **       |");
                System.out.println("|    **    |");
                System.out.println("|       ** |");
                System.out.println("|__________|");
                break;
            case(4):
                System.out.println(" __________");
                System.out.println("|  **   ** |");
                System.out.println("|          |");
                System.out.println("|  **   ** |");
                System.out.println("|__________|");
                break;
            case(5):
                System.out.println(" __________");
                System.out.println("| **   **  |");
                System.out.println("|    **    |");
                System.out.println("| **   **  |");
                System.out.println("|__________|");
                break;
            case(6):
                System.out.println(" __________");
                System.out.println("| **    ** |");
                System.out.println("| **    ** |");
                System.out.println("| **    ** |");
                System.out.println("|__________|");
                break;
        }
    }


    public static void main(String[] args){
        Dice d = new Dice(1);
        d.rollDice();
        d.printDice();
    }
}
