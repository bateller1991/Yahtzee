public class User {
     String name;
     ScoreCard sc;
     int currTurn;
     Turn[] turns;





     public User(String[] go, String name){
        currTurn = 1;
        this.name = name;
        turns = new Turn[go.length];
        sc = new ScoreCard(this.name, go);
         initializeScoreCard(go);
        initializeTurns();
    }

    public void runTurn(int i){
         try {
             this.sc = turns[i].runTurn(this.sc);
         } catch(ArrayIndexOutOfBoundsException e){
             System.out.println("\n\n\nGAME OVER.");
         }
    }

    private void initializeTurns(){
         for(int i = 1; i < turns.length; i++){
             turns[i] = new Turn(i, this.name);
         }
    }

    public void initializeScoreCard(String[] gameObj){
        for(int i = 1; i < gameObj.length; i++){
            sc.gameObjectives[i] = new gameObjective(gameObj[i]);
        }
    }

    public void addScore(int obj, int score){
         if(sc.gameObjectives[obj].isScored == false){
             sc.gameObjectives[obj].score = score;
             sc.gameObjectives[obj].isScored = true;
         }
         else{
             System.out.println("\nThat game objective was already scored.\n");
         }
    }


}
