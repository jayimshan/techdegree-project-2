package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Teams {

  public Set<Team> listOfTeams;
  
  public void Teams() {
    listOfTeams = new TreeSet<Team>();
  }
  
  public void addTeamToList(Team team) {
    if (listOfTeams == null) {
      listOfTeams = new TreeSet<Team>();  
    }
    listOfTeams.add(team);
  }
  
  public int getNumberOfTeams() {
    if (listOfTeams == null) {
      listOfTeams = new TreeSet<Team>();  
    }
    return listOfTeams.size();  
  }
  
  public String[] getListOfTeams() {
    if (listOfTeams == null) {
      listOfTeams = new TreeSet<Team>();
    }
    String[] availableTeams = new String[listOfTeams.size()];
    int i = 0;
    for (Team teamName : listOfTeams) {
      availableTeams[i] = teamName.getTeamName();
      i++;
    }
    return availableTeams;
  }
  
  public Team getTeam(String teamName) {
    if (listOfTeams == null) {
      listOfTeams = new TreeSet<Team>();  
    }
    Map<String, Team> teamMap = new HashMap<>();
    for (Team team : listOfTeams) {
      teamMap.put(team.getTeamName(), team);  
    }
    return teamMap.get(teamName);
  }
  
  public boolean putPlayerIntoTeam(Player player, String teamName) {
    if (listOfTeams == null) {
      listOfTeams = new TreeSet<Team>();  
    }
    for (Team t : listOfTeams) {
      if (t.getTeamName() == teamName) {
        if (t.canAdd()) {
          t.addPlayer(player);
          return true;
        }
        //t.addPlayer(player);  
      }
    }
    return false;
  }
  
  public boolean removePlayerFromTeam(Player player, String teamName) {
    if (listOfTeams == null) {
      listOfTeams = new TreeSet<Team>();  
    }
    for (Team t : listOfTeams) {
      if (t.getTeamName() == teamName) {
        if (t.canRemove()) {
          t.removePlayer(player);
          return true;
        }
      }
    }
    return false;
  }
}