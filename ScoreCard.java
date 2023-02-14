public class ScoreCard {
    String name;
    gameObjective[] gameObjectives;
    int score;

    public ScoreCard(String newName, String[] obj){
        this.name = newName;
        this.gameObjectives = new gameObjective[obj.length];
        initializeGO(obj);
        initializeGameObj(obj);
    }

    private void initializeGO(String[] obj){
        for(int i = 0; i < gameObjectives.length; i++){
            gameObjectives[i] = new gameObjective(obj[i]);
        }
    }

    public void printScoreCard(){
        System.out.println("\n\n\t" + this.name + "'s Score Card:\n");
        for(int i = 1; i < this.gameObjectives.length; i++){
            System.out.print(gameObjectives[i].obj + ":   " + gameObjectives[i].score + "\n");
        }
        System.out.println("\n");
    }

    public boolean completeObjective(int indexObj, Dice[] d){
        gameObjective g = this.gameObjectives[indexObj];
        if(g.isScored == false && diceQualify(d, indexObj)){
            g.score = getSpecificScore(d, indexObj);
            g.isScored = true;
            g.obj = g.obj + " * COMPLETED *";
            this.gameObjectives[indexObj] = g;
            return true;
        }
        return false;
    }


    private int getSpecificScore(Dice[] d, int i){
        int t = 0;
        if(i >= 1 && i <= 6) {
            t = getSumOf1Through6(d, i);
            return t;
        }
        if(i == 7 || i ==8)
            return getSumOfAllDice(d, i);
        if(i == 9) {
            int s = 25;
            return s;
        }
        if(i == 10) {
            int s = 30;
            return s;
        }
        if(i == 11)
            return 40;
        if(i == 12)
            return getSumOfDice(d);
        if(i == 13){
            if(this.gameObjectives[i].isScored)
                return 100;
            return 50;
        }

        return t;
    }

    private int getSumOfDice(Dice[] d){
        int sum = 0;
        for(int i = 1;i < d.length; i++){
            sum += d[i].rollValue;
        }
        return sum;
    }

    private boolean diceQualify(Dice[] d, int i){
        if(i < 7 && i > 0){
            return true;
        }
        if(i == 7 || i == 8) {
            if (verify3Or4OfKind(d, i))
                return true;
        }
        if(i == 9){
            Dice[] temp;
            temp = d;
            temp = inOrder(temp);
            int frontVal = temp[1].rollValue, backVal = temp[temp.length-1].rollValue, frontInc = 0, backInc = 0;
            for(int k = 1; k < temp.length; k++){
                if(temp[k].rollValue == frontVal)
                    frontInc++;
                if(temp[k].rollValue == backVal)
                    backInc++;
            }
            System.out.println("\nfront = " + frontInc + "\nback = " + backInc + "\n");
            if((frontInc == 3 && backInc == 2) || (frontInc == 2 && backInc == 3))
                return true;
        }
        if(i == 10 || i == 11){
            Dice[] temp;
            temp = d;
            int need = 4, actual = 0;
            if(i == 11)
                need = 5;
            temp = inOrder(temp);
            boolean[] contains = new boolean[temp[1].sides.length + 1];
            for(int j = 1; j < temp.length; j++){
                contains[temp[j].rollValue] = true;
                System.out.println("contains(" + temp[j].rollValue + ").");
            }
            for(int j = 1; j < contains.length; j++){
                if(contains[j])
                    actual++;
                else{
                    actual = 0;
                }
                if(actual == need) {
                    System.out.println("actual = " + actual);
                    return true;
                }
                System.out.println("actual = " + actual);
            }
        }
        if(i == 12){
            return true;
        }
        if(i == 13){
            return allSame(d);
        }
        return false;
    }

    private boolean allSame(Dice[] d){
        int d1Value = d[1].rollValue;
        for(int i = 2; i < d.length; i++){
            if(d[i].rollValue != d1Value)
                return false;
        }
        return true;
    }



    private Dice[] inOrder(Dice[] temp){
        Dice t;
        for( int i = 1; i < temp.length; i++){
            System.out.print(temp[i].rollValue + " ");
        }
        System.out.println("\n");
        for(int i = 1; i < temp.length; i++){
            for(int j = i + 1; j < temp.length; j++){
                if(temp[j].rollValue < temp[i].rollValue){
                    t = temp[j];
                    temp[j] = temp[i];
                    temp[i] = t;
                }
            }
        }
        System.out.println("\nPrinting ScoreCard.inOrder()\n");
        for( int i = 1; i < temp.length; i++){
            System.out.print(temp[i].rollValue + " ");
        }
        System.out.println("\n");
        return temp;
    }



    private boolean verify3Or4OfKind(Dice[] d, int index){
        int numReq = 3, actual = 0, currVal;
        if(index == 8)
            numReq = 4;
        for(int i = 0; i < d[0].sides.length; i++){
            currVal = i+1;
            for(int j = 1; j < d.length; j++){
                if(d[j].rollValue == currVal){
                    actual++;
                    if(actual == numReq)
                        return true;
                }
            }
            actual = 0;
        }
        return false;
    }

    private int getSumOfAllDice(Dice[] d, int j){
        int sum =0;
        for(int i = 1; i < d.length; i++){
            sum += d[i].rollValue;
        }
        return sum;
    }

    private int getSumOf1Through6(Dice[] d, int i){
        int sum = 0;
        for(int j = 1; j < d.length; j++){
            if(d[j].rollValue == i)
                sum += i;
        }
        return sum;
    }

    public int getScore(){
        this.score = 0;
        for(int i = 1; i < gameObjectives.length; i++){
            score += gameObjectives[i].score;
        }
        return score;
    }

    private void initializeGameObj(String[] s){
        for(int i = 1; i < gameObjectives.length; i++){
            gameObjectives[i] = new gameObjective(s[i]);
        }
    }




}


