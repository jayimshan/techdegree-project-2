package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Team implements Comparable<Team> {
  
  private static final int MAX_MEMBERS = 11;
  private static final int MIN_MEMBERS = 0;
  private String mTeamName;
  private String mCoachName;
  private Set<Player> listOfPlayers;
  private Map<Player, Boolean> experienceList;
  
  public Team(String teamName, String coachName) {
    mTeamName = teamName;
    mCoachName = coachName;
    listOfPlayers = new TreeSet<Player>();
    experienceList = new HashMap<Player, Boolean>();
  }
  
  public int getSize() {
    return listOfPlayers.size();  
  }
  
  @Override
  public int compareTo(Team other) {
    int cmp = mTeamName.compareTo(other.mTeamName);
    return cmp;
  }
  
  public String getTeamName() {
    return mTeamName;  
  }
  
  public String getCoachName() {
    return mCoachName;  
  }
  /*
  public Map<Player, Boolean> getExperienceMap() {
    for (Player player : listOfPlayers) {
      if (player.isPreviousExperience()) {
        experienceList.put(player, true);  
      } else {
        experienceList.put(player, false);  
      }
    }
    return experienceList;
  }
  */
  public int getShortCount() {
    int i = 0;
    for (Player player : listOfPlayers) {
      if (player.getHeightInInches() >= 35 && player.getHeightInInches() <= 40) {
        i++;  
      }
    }
    return i;
  }
  
  public int getMediumCount() {
    int i = 0;
    for (Player player : listOfPlayers) {
      if (player.getHeightInInches() >= 41 && player.getHeightInInches() <= 46) {
        i++;  
      }
    }
    return i;
  }
  
  public int getTallCount() {
    int i = 0;
    for (Player player : listOfPlayers) {
      if (player.getHeightInInches() >= 47 && player.getHeightInInches() <= 50) {
        i++;  
      }
    }
    return i;
  }
  
  public Player[] getPlayers() {
    return listOfPlayers.toArray(new Player[listOfPlayers.size()]);
  }
  
  public Player getPlayer(int i) {
    Player[] playerList = getPlayers();
    return playerList[i-1];
  }
  
  public void clear() {
    listOfPlayers.clear();  
  }
  
  public boolean canAdd() {
    if (listOfPlayers.size() < MAX_MEMBERS) {
      return true;
    }
    System.out.printf("Unable to add player. Too many players on this team. You can have up to %d players on a team.\n", MAX_MEMBERS);
    return false;
  }
  
  public boolean canRemove() {
    if (listOfPlayers.size() > MIN_MEMBERS) {
      return true;  
    }
    System.out.printf("Unable to remove player. There are no players on this team. You must have at least %d players on a team.\n", MIN_MEMBERS);
    return false; 
  }
  
  public void addPlayer(Player player) {
    listOfPlayers.add(player);
  }
  
  public void removePlayer(Player player) {
    listOfPlayers.remove(player);  
  }
}