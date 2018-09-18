import com.teamtreehouse.io.Menu;
import com.teamtreehouse.io.Prompter;
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Teams;

public class LeagueManager {

  public static void main(String[] args) {
    Player[] players = Players.load();
    System.out.printf("There are currently %d registered players.%n", players.length);
    // Your code here!
    Menu menu = new Menu();
    Teams team = new Teams();
    Prompter prompter = new Prompter(menu, team, players);
    prompter.promptMenu();
  }

}
