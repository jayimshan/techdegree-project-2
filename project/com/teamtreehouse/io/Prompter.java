package com.teamtreehouse.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Team;
import com.teamtreehouse.model.Teams;

public class Prompter {
  
  private Menu mMenu;
  private Scanner scanner;
  private String mOption;
  private Teams teams;
  private List<Player> players;
  Map<Integer, String> keyToTeam;
  
  public Prompter(Menu menu, Teams team, Player[] playerList) {
    mMenu = menu;
    teams = team;
    keyToTeam = new TreeMap<Integer, String>();
    players = new ArrayList<Player>();
    for (Player p : playerList) {
      players.add(p);
    }
  }
  
  public void promptMenu() {
    System.out.println("\nMenu Options\n" +
                       "==============================================+\n" +
                       "                                              |");
    for (Map.Entry option : mMenu.getMenu().entrySet()) {
      System.out.printf("%s - %s%n", option.getKey(), option.getValue());
    }
    System.out.println("                                              |\n" +
                       "==============================================+");
    selectOption();
  }
  
  public void selectOption() {
    scanner = new Scanner(System.in);
    mOption = "unselected";
    while (mOption.equals("unselected")) {
      System.out.printf("%nSelect an option:  ");
      String selection = scanner.next();
      switch (selection.toLowerCase()) {
        case "create":
          System.out.println("You chose \"create\"");
          mOption = "create";
          createOption();
          break;
        case "add":
          System.out.println("You chose \"add\"");
          mOption = "add";
          addOption();
          break;
        case "remove":
          System.out.println("You chose \"remove\"");
          mOption = "remove";
          removeOption();
          break;
        case "report":
          System.out.println("You chose \"report\"");
          mOption = "report";
          reportOption();
          break;
        case "balance":
          System.out.println("You chose \"balance\"");
          mOption = "balance";
          balanceOption();
          break;
        case "roster":
          System.out.println("You chose \"roster\"");
          mOption = "roster";
          rosterOption();
          break;
        case "quit":
          System.out.println("You chose \"quit\"");
          mOption = "quit";
          quitOption();
          break;
        default:
          System.out.println("That is not an option, please try again");
      }
    }
  }
  
  public void createOption() {
    if (!players.isEmpty()) {
      scanner = new Scanner(System.in);
      System.out.printf("%nWhat is the team name?  ");
      String teamName = scanner.nextLine();
      System.out.print("What is the coaches name?  ");
      String coachName = scanner.nextLine();
      Team team = new Team(teamName, coachName);
      teams.addTeamToList(team);
      System.out.printf("%nTeam %s coached by %s has been added.%n", team.getTeamName(), team.getCoachName());
    } else {
      System.out.println("There are no players to create a team.");  
    }
    promptMenu();
  }
  
  public void addOption() {
    if (teams.getNumberOfTeams() == 0) {
      System.out.println("There are no teams created. Please create a team first.");
      promptMenu();
    }
    selectTeamOption(true);
    addPlayerOption();
  }
  
  public void showTeams() {
    System.out.printf("There are %d team(s) available:  \n", teams.getNumberOfTeams());
    int i = 0;
    for (String team : teams.getListOfTeams()) {
      System.out.printf("%d.) Team:  %s%n", i+1, team);  
      keyToTeam.put(i, team);
      i++;
    }
  }
  
  public void showPlayers() {
    Collections.sort(players);
    int i = 0;
    for (Player player : players) {
      System.out.printf("%d.) %s %s (%d - )%n", i + 1, player.getFirstName(), player.getLastName(), player.getHeightInInches());
      i++;
    }
  }
  
  public void selectTeamOption(boolean adding) {
    scanner = new Scanner(System.in);
    showTeams();
    if (adding) {
      System.out.printf("%nPlease select a team to add players to:  ");
    } else {
      System.out.printf("%nPlease select a team to remove players from:  ");
    }
    int selection = scanner.nextInt();
    while (selection > teams.getNumberOfTeams() || selection < 1) {
      System.out.println("That is not an option, please try again");
      System.out.printf("%nPlease select a team to add players to:  ");
      selection = scanner.nextInt();
    }
    if (adding) {
      System.out.printf("%nYou chose team:  %s%n%n", keyToTeam.get(selection-1));
      System.out.printf("==============================================+\n" +
                        "Add players for team:  %s\n" +
                        "==============================================+\n" +
                        "List of available players:\n\n", keyToTeam.get(selection-1));
    } else {
      System.out.printf("%nYou chose team:  %s%n%n", keyToTeam.get(selection-1));
      System.out.printf("==============================================+\n" +
                        "Remove players from team:  %s\n" +
                        "==============================================+\n" +
                        "List of available players:\n\n", keyToTeam.get(selection-1));
    }
    Collections.sort(players);
    if (adding) {
      int j = 0;
      for (Player player : players) {
        String exp = "inexperienced";
        if (player.isPreviousExperience()) {
          exp = "experienced";  
        }
        System.out.printf("%d.) %s %s (%d - %s)%n",j+1, player.getFirstName(), player.getLastName(), player.getHeightInInches(), exp);
        j++;
      }
      System.out.print("\nSelect a player:  ");
      int playerSelection = scanner.nextInt();
      if (teams.getTeam(keyToTeam.get(selection-1)).canAdd() == true) {
        String exp = "inexperienced";
        if (players.get(playerSelection-1).isPreviousExperience()) {
          exp = "experienced";  
        }
        teams.getTeam(keyToTeam.get(selection-1)).addPlayer(players.get(playerSelection-1));
        System.out.printf("%nPlayer:  %s %s (%d - %s) has been added to your team", players.get(playerSelection-1).getFirstName(),
                            players.get(playerSelection-1).getLastName(),
                            players.get(playerSelection-1).getHeightInInches(),
                            exp);
        players.remove(playerSelection-1);
      }
    } else {
      int k = 0;
      for (Player p : teams.getTeam(keyToTeam.get(selection-1)).getPlayers()) {
        String exp = "inexperienced";
        if (p.isPreviousExperience()) {
          exp = "experienced";  
        }
        System.out.printf("%d.) %s %s (%d - %s)%n", k + 1, p.getFirstName(), p.getLastName(), p.getHeightInInches(), exp);  
        k++;
      }
      System.out.print("\nSelect a player:  ");
      int playerSelection = 0;
      boolean go = true;
      do {
        playerSelection = scanner.nextInt();
        if (playerSelection <= teams.getNumberOfTeams() || playerSelection > 0) {
          go = false;
        }
      } while (go);
      if (teams.getTeam(keyToTeam.get(selection-1)).canRemove() == true) {
        String exp = "inexperienced";
        if (teams.getTeam(keyToTeam.get(selection-1)).getPlayer(playerSelection).isPreviousExperience()) {
          exp = "experienced";  
        }
        System.out.printf("%nPlayer:  %s %s (%d - %s) has been removed from team",
                          teams.getTeam(keyToTeam.get(selection-1)).getPlayer(playerSelection).getFirstName(),
                          teams.getTeam(keyToTeam.get(selection-1)).getPlayer(playerSelection).getLastName(),
                          teams.getTeam(keyToTeam.get(selection-1)).getPlayer(playerSelection).getHeightInInches(),
                          exp);
        players.add(teams.getTeam(keyToTeam.get(selection-1)).getPlayer(playerSelection));
        teams.getTeam(keyToTeam.get(selection-1)).removePlayer(teams.getTeam(keyToTeam.get(selection-1)).getPlayer(playerSelection));
      }
    }
  }
  
  public void addPlayerOption() {
    scanner = new Scanner(System.in);
    System.out.print("\n\nWhat would you like to do?\n\n" +
                     "1.) Continue adding\n" +
                     "2.) Back to menu\n");
    String continueOrBack = "none";
    while (continueOrBack.equals("none")) {
      int nextSelection = scanner.nextInt();
      switch (nextSelection) {
        case 1:
          continueOrBack = "other";
          addOption();
          break;
        case 2:
          System.out.println("Going back to menu...");
          continueOrBack = "other";
          promptMenu();
          break;
        default:
          System.out.println("That is not an option, please try again");
      }
    }
  }
  
  public void removePlayerOption() {
    scanner = new Scanner(System.in);
    System.out.print("\n\nWhat would you like to do?\n\n" +
                     "1.) Continue removing\n" +
                     "2.) Back to menu\n");
    String continueOrBack = "none";
    while (continueOrBack.equals("none")) {
      int nextSelection = scanner.nextInt();
      switch (nextSelection) {
        case 1:
          continueOrBack = "other";
          removeOption();
          break;
        case 2:
          System.out.println("Going back to menu...");
          continueOrBack = "other";
          promptMenu();
          break;
        default:
          System.out.println("That is not an option, please try again");
      }
    }
  }
  
  public void removeOption() {
    selectTeamOption(false);
    removePlayerOption();
  }
  
  public void reportOption() {
    scanner = new Scanner(System.in);
    showTeams();
    System.out.print("\nSelect a team:  ");
    int selection = scanner.nextInt();
    System.out.printf("\nList of players by height in team:  %s", keyToTeam.get(selection-1));
    System.out.printf("\n==============================================+\n" +
                      "Players 35in - 40in:  (%d players)\n" +
                      "==============================================+\n", teams.getTeam(keyToTeam.get(selection-1)).getShortCount());
    for (Player player : teams.getTeam(keyToTeam.get(selection-1)).getPlayers()) {
      String exp = "inexperienced";
      if (player.isPreviousExperience()) {
        exp = "experienced";
      }
      if (player.getHeightInInches() >= 35 && player.getHeightInInches() <= 40) {
        System.out.printf("%s %s (%d - %s)%n", player.getFirstName(), player.getLastName(), player.getHeightInInches(), exp);
      }
    }
    System.out.printf("\n==============================================+\n" +
                      "Players 41in - 46in:  (%d players)\n" +
                      "==============================================+\n", teams.getTeam(keyToTeam.get(selection-1)).getMediumCount());
    for (Player player : teams.getTeam(keyToTeam.get(selection-1)).getPlayers()) {
      String exp = "inexperienced";
      if (player.isPreviousExperience()) {
        exp = "experienced";
      }
      if (player.getHeightInInches() >= 41 && player.getHeightInInches() <= 46) {
        System.out.printf("%s %s (%d - %s)%n", player.getFirstName(), player.getLastName(), player.getHeightInInches(), exp);
      }
    }
    System.out.printf("\n==============================================+\n" +
                      "Players 47in - 50in:  (%d players)\n" +
                      "==============================================+\n", teams.getTeam(keyToTeam.get(selection-1)).getTallCount());
    for (Player player : teams.getTeam(keyToTeam.get(selection-1)).getPlayers()) {
      String exp = "inexperienced";
      if (player.isPreviousExperience()) {
        exp = "experienced";
      }
      if (player.getHeightInInches() >= 47 && player.getHeightInInches() <= 50) {
        System.out.printf("%s %s (%d - %s)%n", player.getFirstName(), player.getLastName(), player.getHeightInInches(), exp);
      }
    }
    promptMenu();
  }
  
  public void balanceOption() {
    scanner = new Scanner(System.in);
    showTeams();
    System.out.print("\nSelect a team:  ");
    int selection = scanner.nextInt();
    System.out.printf("\nList of players by experience in team:  %s", keyToTeam.get(selection-1));
    int exp = 0;
    for (Player player : teams.getTeam(keyToTeam.get(selection-1)).getPlayers()) {
      if (player.isPreviousExperience()) {
        exp++;  
      }
    }
    int percentage = (exp * 100)/teams.getTeam(keyToTeam.get(selection-1)).getSize();
    System.out.printf("\n==============================================+\n" +
                      "Experienced players:  %d%%\n" +
                      "==============================================+\n", percentage);
    for (Player player : teams.getTeam(keyToTeam.get(selection-1)).getPlayers()) {
      if (player.isPreviousExperience()) {
        System.out.printf("%s %s%n", player.getFirstName(), player.getLastName());  
      }
    }
    exp = 0;
    for (Player player : teams.getTeam(keyToTeam.get(selection-1)).getPlayers()) {
      if (!player.isPreviousExperience()) {
        exp++;  
      }
    }
    percentage = (exp * 100)/teams.getTeam(keyToTeam.get(selection-1)).getSize();
    System.out.printf("\n==============================================+\n" +
                      "Inexperienced players:  %d%%\n" +
                      "==============================================+\n", percentage);
    for (Player player : teams.getTeam(keyToTeam.get(selection-1)).getPlayers()) {
      if (!player.isPreviousExperience()) {
        System.out.printf("%s %s%n", player.getFirstName(), player.getLastName());  
      }
    }
    promptMenu();
  }
  
  public void rosterOption() {
    scanner = new Scanner(System.in);
    showTeams();
    System.out.print("Select a team to print roster:  ");
    int selection = scanner.nextInt();
    System.out.printf("\n==============================================+\n" +
                       "Roster for team:  %s\n" +
                       "==============================================+\n", teams.getTeam(keyToTeam.get(selection-1)).getTeamName());
    int i = 0;
    for (Player player : teams.getTeam(keyToTeam.get(selection-1)).getPlayers()) {
      String exp = "no";
      if (player.isPreviousExperience()) {
        exp = "yes";  
      }
      System.out.printf("%s.)  %s %s - HEIGHT: %d inches | EXPERIENCE: %s%n", i + 1, player.getFirstName(), player.getLastName(), player.getHeightInInches(), exp);
      i++;
    }
    mOption = "quit";
  }
  
  public void quitOption() {
    System.out.println("Thanks for using Soccer League Organizer!");
  }
}