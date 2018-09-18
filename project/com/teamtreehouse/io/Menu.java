package com.teamtreehouse.io;

import java.util.Map;
import java.util.LinkedHashMap;

public class Menu {
  
  private Map<String, String> menu;
  
  public Menu() {
    
    menu = new LinkedHashMap<>();
    
    menu.put("Create  ", "Create a new team                  |");
    menu.put("Add     ", "Add a player to a team             |");
    menu.put("Remove  ", "Remove a player from a team        |");
    menu.put("Report  ", "View a report of a team by height  |");
    menu.put("Balance ", "View the League Balance Report     |");
    menu.put("Roster  ", "View roster                        |");
    menu.put("Quit    ", "Exits the program                  |");
  }
  
  public Map<String, String> getMenu() {
    return menu;
  }
}