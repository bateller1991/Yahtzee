public class Roll {
    int rollId, sumOfDice;
    Dice[] dice;


    public Roll(int newID, Dice[] newDice){
        this.rollId = newID;
        this.dice = newDice;
        this.sumOfDice = 0;
    }

    public Dice[] RollDice(boolean[] keep){
        for(int i = 1; i < dice.length; i++) {
            if(!(keep[i]))
                this.dice[i].rollDice();
        }
        return this.dice;
    }

    public void sumUpDice(){
        sumOfDice = 0;
        for(int i = 1; i < dice.length; i++){
            sumOfDice += dice[i].rollValue;
        }
    }

    public void printDice(){
        for(int i = 1; i < dice.length; i++){
            dice[i].printDice();
        }

    }
}
