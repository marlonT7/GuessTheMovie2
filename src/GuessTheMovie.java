import java.util.Scanner;

public class GuessTheMovie {
  public static void main(String[] args) {
    //Begins the game
    Game game = new Game();
    Scanner scanner = new Scanner(System.in);
    String letter;
    boolean isCharacter;
    while (game.getPoints() > 0) {
      System.out.println("You are guessing: " + game.getMyRightGuest());
      System.out.println("You have guessed (" + (10 - game.getPoints()) + ") wrong letters: " + game.getMyFails());
      do {
        System.out.print("Guess a letter: ");
        //Read the letter
        try {
          letter = scanner.nextLine();
          //Set the letter to display or decrease the points, only uses the first letter
          game.sendLetter(letter.charAt(0));
          isCharacter = true;
        } catch (Exception ex) {
          isCharacter = false;
          System.out.println("Don't have text to compare");
        }
      } while (!isCharacter);

      if (game.isVictory()) {
        //The name of the movie is complete
        System.out.println("You win!");
        System.out.println("You have guessed '" + game.getMyMovie() + "' correctly.");
        break;
      } else if (game.getPoints() == 0) {
        //The points are 0
        System.out.println("You lose");
        System.out.println("The movie is :" + game.getMyMovie());
      }
    }
  }
}
