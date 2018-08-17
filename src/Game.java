import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Game {
  private int points;
  //Displays the progress
  private String myRightGuest;
  private String myFails;
  //Movie to guess
  private String myMovie;
  private List<String> movies;

  int getPoints() {
    return points;
  }

  String getMyRightGuest() {
    return myRightGuest;
  }

  String getMyFails() {
    return myFails;
  }

  String getMyMovie() {
    return myMovie;
  }

  //Initial state's game
  Game() {
    this.points = 10;
    this.movies = getMovies();
    this.myMovie = getNewMovie();
    this.myRightGuest = setUnderscore();
    this.myFails = "";
  }

  //Get a random movie from a list of movies
  private String getNewMovie() {
    int random = (int) (Math.random() * movies.size()) + 1;
    return movies.get(random);
  }

  //Fill a list of movies from a file
  private List<String> getMovies() {
    //Read the file
    File file = new File("Movies.txt");
    List<String> movies = new ArrayList<>();
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        //Add movie to the list
        movies.add(scanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return movies;
  }

  // Fill the string to display with underscores
  private String setUnderscore() {
    StringBuilder underscores = new StringBuilder();
    for (int i = 0; myMovie.length() > i; i++) {
      underscores.append("_");
    }
    return underscores.toString();
  }

  // Search a letter in the movie name, update my right guest
  void sendLetter(char letter) {
    StringBuilder string = new StringBuilder();
    if (myMovie.contains("" + letter)) {
      // When guess displays the letter
      for (int i = 0; i < myMovie.length(); i++) {
        if (myMovie.charAt(i) == letter) {
          string.append(letter);
        } else {
          string.append(myRightGuest.charAt(i));
        }
      }
      myRightGuest = string.toString();
      // When fails decrease the points
    } else {
      myFails = myFails + letter;
      points--;
    }
  }

   boolean isVictory() {
    return myMovie.equalsIgnoreCase(myRightGuest);
  }
}

