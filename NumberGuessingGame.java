import java.util.*;
public class NumberGuessingGame {
    public static void main(String [] args)
    {
        playNumberGuessingGame();
    }
    public static void playNumberGuessingGame()
    {
        Scanner s=new Scanner(System.in);
        Random r=new Random();
        int lowerbound=1;
        int upperbound=100;
        int generatedNumber=r.nextInt(upperbound-lowerbound+1)+lowerbound;
        int maxAttempts=10;
        int score=0;
        System.out.println("WELCOME TO NUMBER GUESSING GAME...");
        System.out.println("I HAVE SELECTED A NUMBERS BETWEEN " +lowerbound+ " and " +upperbound);
        for (int attempts=1;attempts<=maxAttempts;attempts++)
        {
            System.out.println("enter your guess (Attempt"+attempts+"/"+maxAttempts+"):");
            int userGuess=s.nextInt();
            if(userGuess==generatedNumber)
            {
                System.out.println("Congratulations! you guessed the correct number");
                score +=calculateScore(attempts,maxAttempts);
                break;
            }
            else if(userGuess<generatedNumber)
            {
                System.out.println("Too Low! Please Try Again");
            }
            else{
                System.out.println("Too High! Please Try Again");
            }
            if(attempts==maxAttempts)
            {
                System.out.println("Sorry!you've run out of attempts.the correct number was:"+generatedNumber);
            }
        }
        System.out.println("Game Over.Your Score:"+score);
        s.close();
    }
    public static int calculateScore( int attempts,int maxAttempts)
    {
        int baseScore=100;
        int score=(int) ((double)baseScore*(maxAttempts-attempts+1)/maxAttempts);
        return score;
    }
}
