/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.predatorpreymodels;

import java.util.Arrays;
import java.util.Random;

/**
 * Program that simulates the behavior of predators and prey
 * @author Ethan
 */
public class PredatorPreyModels {
    public static void main(String[] args) {
        ///// Variables /////
        //Random
        Random r = new Random();
        
        //constants
        final int predPop = 10;
        final int preyPop = 100;
        final int gridSize = 20;
        
        //Find initial population positions
        String predator = "";
        for (int i = 0; i < predPop; i++) {
            predator += ((char)('A' + r.nextInt(20)) + "" + (char)('A' + r.nextInt(20)) + " ");
        }
       
        String prey = "";
        for (int i = 0; i < preyPop; i++) {
            prey += ((char)('A' + r.nextInt(20)) + "" + (char)('A' + r.nextInt(20)) + " ");
        }
        
        //Build Grid
        String grid[][] = new String[gridSize][gridSize];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {    
                grid[i][j] = "*";
            }
        }
        
        //Assign initial predator and prey positions
        for (int i = 0; i < predator.length(); i += 3) {
            if (grid[predator.charAt(i) - 65][predator.charAt(i + 1) - 65].equals("*")) {
                grid[predator.charAt(i) - 65][predator.charAt(i + 1) - 65] = "P";
            }
            else {
                predator = predator.substring(0, i) + (char)('A' + r.nextInt(20)) + (char)('A' + r.nextInt(20)) + predator.substring(i + 2);
                i -= 3;
            }
        }
        for (int i = 0; i < prey.length(); i += 3) {
            if (grid[prey.charAt(i) - 65][prey.charAt(i + 1) - 65].equals("*")) {
                grid[prey.charAt(i) - 65][prey.charAt(i + 1) - 65] = "@";
            }
            else {
                prey = prey.substring(0, i) + (char)('A' + r.nextInt(20)) + (char)('A' + r.nextInt(20)) + prey.substring(i + 2);
                i -= 3;
            }
        }
        
        //Predator movement and hunger checks
        String predAte = "T T T T T T T T T T";
        String predMoved = "F F F F F F F F F F";
        
        //simulate the program
        for (int a = 0; a < 10; a++) {
            for (int i = 0; i < predator.length(); i += 3) {
                for (int j = 0; j < prey.length(); j += 3) {
                    //predator movement if prey is nearby
                    if (((predator.charAt(i) + 1) == prey.charAt(j)) && ((predator.charAt(i + 1)) == prey.charAt(j + 1))) {
                        predator = predator.substring(0, i) + prey.charAt(j) + predator.substring(i + 1);
                        prey = prey.substring(0, j) + prey.substring(j + 3);
                        predAte = predAte.substring(0, i*2/3) + "T" + predAte.substring(i*2/3 + 1);
                        predMoved = predMoved.substring(0, i*2/3) + "T" + predMoved.substring(i*2/3 + 1);
                    } 
                    else if (((predator.charAt(i) - 1) == prey.charAt(j)) && ((predator.charAt(i + 1)) == prey.charAt(j + 1))) {
                        predator = predator.substring(0, i) + prey.charAt(j) + predator.substring(i + 1);
                        prey = prey.substring(0, j) + prey.substring(j + 3);
                        predAte = predAte.substring(0, i*2/3) + "T" + predAte.substring(i*2/3 + 1);
                        predMoved = predMoved.substring(0, i*2/3) + "T" + predMoved.substring(i*2/3 + 1);
                    } 
                    else if (((predator.charAt(i)) == prey.charAt(j)) && ((predator.charAt(i + 1) + 1) == prey.charAt(j + 1))) {
                        predator = predator.substring(0, i + 1) + prey.charAt(j + 1) + predator.substring(i + 2);
                        prey = prey.substring(0, j) + prey.substring(j + 3);
                        predAte = predAte.substring(0, i*2/3) + "T" + predAte.substring(i*2/3 + 1);
                        predMoved = predMoved.substring(0, i*2/3) + "T" + predMoved.substring(i*2/3 + 1);
                    }
                    else if (((predator.charAt(i)) == prey.charAt(j)) && ((predator.charAt(i + 1) - 1) == prey.charAt(j + 1))) {
                        predator = predator.substring(0, i + 1) + prey.charAt(j + 1) + predator.substring(i + 2);
                        prey = prey.substring(0, j) + prey.substring(j + 3);
                        predAte = predAte.substring(0, i*2/3) + "T" + predAte.substring(i*2/3 + 1);
                        predMoved = predMoved.substring(0, i*2/3) + "T" + predMoved.substring(i*2/3 + 1);
                    } 
                } //end of prey loop
                
                //predator movement if prey is not nearby
                if (predMoved.charAt(i/3) == 'F') {
                    predAte = predAte.substring(0, i*2/3) + (predAte.charAt(i*2/3) - 1) + predAte.substring(i*2/3 + 1);
                    String predatorOP = predator.substring(i, i + 2);
                    System.out.println(predatorOP);
                    System.out.println(predator);
                    switch (r.nextInt(4)) {
                        case 0:
                            if (predator.charAt(i) == 'T') {
                                predator = predator.substring(0, i) + 'A' + predator.substring(i + 1);
                            }
                            else {
                                if (grid[predator.charAt(i) - 64][predator.charAt(i + 1) - 65].equals("*")) {
                                    predator = predator.substring(0, i) + (char) (predator.charAt(i) + 1) + predator.substring(i + 1);
                                }
                            }
                            break;
                        case 1:
                            if (predator.charAt(i) == 'A') {
                                predator = predator.substring(0, i) + 'T' + predator.substring(i + 1);
                            }
                            else {
                                if (grid[predator.charAt(i) - 66][predator.charAt(i + 1) - 65].equals("*")) {
                                    predator = predator.substring(0, i) + (char) (predator.charAt(i) - 1) + predator.substring(i + 1);
                                }
                            }
                            break;
                        case 2:
                            if (predator.charAt(i + 1) == 'T') {
                                predator = predator.substring(0, i + 1) + 'A' + predator.substring(i + 2);
                            }
                            else {
                                if (grid[predator.charAt(i) - 65][predator.charAt(i + 1) - 64].equals("*")) {
                                    predator = predator.substring(0, i + 1) + (char) (predator.charAt(i + 1) + 1) + predator.substring(i + 2);
                                }
                            }
                            break;
                        case 3:
                            if (predator.charAt(i + 1) == 'A') {
                                predator = predator.substring(0, i + 1) + 'T' + predator.substring(i + 2);
                            }
                            else {
                                if (grid[predator.charAt(i) - 65][predator.charAt(i + 1) - 66].equals("*")) {
                                    predator = predator.substring(0, i + 1) + (char) (predator.charAt(i + 1) - 1) + predator.substring(i + 2);
                                }
                            }
                            break;
                        default:
                            break;
                    }
                    
                    //random check for if the predator should reproduce
                    if (r.nextInt(20) == 0 && predAte.charAt(i*2/3) >= 'J') {
                        predator += (" " + predatorOP);
                    } 
                }
                
                //checks if the predator should die
                if (predAte.charAt(i*2/3) < 'A') {
                    predator = predator.substring(0, i) + predator.substring(i + 3);
                    predAte = predAte.substring(0, i*2/3) + predAte.substring(i*2/3 + 2);
                }
                
                //update predator position on the grid
                grid = new String[gridSize][gridSize];
                for (int j = 0; j < grid.length; j++) {
                    for (int k = 0; k < grid.length; k++) {    
                        grid[j][k] = "*";
                    }
                }
                
                for (int j = 0; j < predator.length(); j += 3) {
                    System.out.println(predator);
                    grid[predator.charAt(j) - 65][predator.charAt(j + 1) - 65] = "P";
                }
                for (int j = 0; j < prey.length(); j += 3) {
                    grid[prey.charAt(j) - 65][prey.charAt(j + 1) - 65] = "@";
                }
            } //end of predator string
                    
            //prey movement
            for (int i = 0; i < prey.length(); i += 3) {
                String preyOP = prey.substring(i, i + 2);
                System.out.println(preyOP);
                if (!grid[prey.charAt(i) - 64][prey.charAt(i + 1) - 65].equals("*") && !grid[prey.charAt(i) - 66][prey.charAt(i + 1) - 65].equals("*") && !grid[prey.charAt(i) - 65][prey.charAt(i + 1) - 64].equals("*") && !grid[prey.charAt(i) - 65][prey.charAt(i + 1) - 66].equals("*")) {
                    prey = prey.substring(0, i) + prey.substring(i + 3);
                }
                else {
                    switch (r.nextInt(4)) {
                        case 0:
                            if (prey.charAt(i) == 'T') {
                                    prey = prey.substring(0, i) + 'A' + prey.substring(i + 1);
                            }
                            else {
                                if (grid[prey.charAt(i) - 64][prey.charAt(i + 1) - 65].equals("*")) {
                                    prey = prey.substring(0, i) + (char) (prey.charAt(i) + 1) + prey.substring(i + 1);
                                }
                            }
                            break;
                        case 1:
                            if (prey.charAt(i) == 'A') {
                                    prey = prey.substring(0, i) + 'T' + prey.substring(i + 1);
                            }
                            else {    
                                if (grid[prey.charAt(i) - 66][prey.charAt(i - 1) - 65].equals("*")) {
                                    prey = prey.substring(0, i) + (char) (prey.charAt(i) - 1) + prey.substring(i + 1);
                                }
                            }
                            break;
                        case 2:
                            if (prey.charAt(i + 1) == 'T') {
                                    prey = prey.substring(0, i + 1) + 'A' + prey.substring(i + 2);
                            }
                            else {
                                if (grid[prey.charAt(i) - 65][prey.charAt(i + 1) - 64].equals("*")) {
                                    prey = prey.substring(0, i + 1) + (char) (prey.charAt(i + 1) + 1) + prey.substring(i + 2);
                                }
                            }
                            break;
                        case 3:
                            if (prey.charAt(i + 1) == 'A') {
                                    prey = prey.substring(0, i + 1) + 'T' + prey.substring(i + 2);
                            }
                            else {
                                if (grid[prey.charAt(i) - 65][prey.charAt(i + 1) - 66].equals("*")) {
                                    prey = prey.substring(0, i + 1) + (char) (prey.charAt(i + 1) - 1) + prey.substring(i + 2);
                                }
                            }
                            break;
                        default:
                            break;
                    }
                    if (r.nextInt(10) == 0) {
                        prey += (" " + preyOP);
                    }
                }
                
                //update prey position on the grid
                grid = new String[gridSize][gridSize];
                for (int j = 0; j < grid.length; j++) {
                    for (int k = 0; k < grid.length; k++) {    
                        grid[j][k] = "*";
                    }
                }
                
                for (int j = 0; j < predator.length(); j += 3) {
                    grid[predator.charAt(j) - 65][predator.charAt(j + 1) - 65] = "P";
                }
                for (int j = 0; j < prey.length(); j += 3) {
                    grid[prey.charAt(j) - 65][prey.charAt(j + 1) - 65] = "@";
                }
            }// end of prey loop
            
            //reset variables
            predMoved = "";
            for (int i = 0; i < predator.length(); i += 3) {
                predMoved += "F ";
            }
        
        System.out.println(Arrays.deepToString(grid));
        } //end of while true loop
    }
}
