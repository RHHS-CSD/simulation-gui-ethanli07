/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.predatorpreymodels;

import java.util.Random;

/**
 *
 * @author Ethan
 */
public class PredatorPreyModels {
    public static void main(String[] args) {
        ///// Variables /////
        //Random
        Random r = new Random();
        
        //Find initial population positions
        String predator[] = new String[10];
        for (int i = 0; i < predator.length; i++) {
            predator[i] = ((char)('a' + r.nextInt(20)) + "" + (char)('a' + r.nextInt(20)));
        }
       
        String prey[] = new String[100];
        for (int i = 0; i < prey.length; i++) {
            prey[i] = ((char)('a' + r.nextInt(20)) + "" + (char)('a' + r.nextInt(20)));
        }
        
        //Build Grid
        String grid[][] = new String[20][20];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {    
                grid[i][j] = "*";
            }
        }
        
        //Assign predator and prey positions
        for (int i = 0; i < predator.length; i++) {
            grid[predator[i].charAt(0) - 65][predator[i].charAt(1) - 65] = "P";
        }
        for (int i = 0; i < prey.length; i++) {
            if (!grid[prey[i].charAt(0) - 65][prey[i].charAt(1) - 65].equals("P")) {
                grid[prey[i].charAt(0) - 65][prey[i].charAt(1) - 65] = "@";
            }
            else {
                prey[i] = ((char)('a' + r.nextInt(20)) + "" + (char)('a' + r.nextInt(20)));
                i -= 1;
            }
        }
        
        //Predator movement and hunger checks
        int predAte[] = new int[10];
        
        //simulate the program
        while (true) {
            //check if a predator is next to prey
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    //if the square has a predator on it
                    if (grid[i][j].equals("P")) {
                        //predator movement
                        if (grid[i + 1][j].equals("@")) {
                            grid[i + 1][j] = "P";
                        } 
                        else if (grid[i - 1][j].equals("@")) {
                            grid[i - 1][j] = "P";
                        }
                        else if (grid[i][j + 1].equals("@")) {
                            grid[i][j + 1] = "P";
                        }
                        else if (grid[i][j + 1].equals("@")) {
                            grid[i][j - 1] = "P";
                        }
                        else {
                            grid[i][j] = "*";
                            switch (r.nextInt(4)) {
                                case 0:
                                    grid[i + 1][j] = "P";
                                    break;
                                case 1:
                                    grid[i - 1][j] = "P";
                                    break;
                                case 2:
                                    grid[i][j + 1] = "P";
                                    break;
                                case 3:
                                    grid[i][j - 1] = "P";
                                    break;
                                default:
                                    break;
                            }
                        }
                    } //end of if pred is on the square
                    
                    //if the square has a prey on it
                    if (grid[i][j].equals("@")) {
                        //prey reproduction
                        if (r.nextInt(10) == 0) {
                            grid[i][j] = "P";
                        }
                        else {
                            grid[i][j] = "*";
                        }
                        
                        //prey movement
                        switch (r.nextInt(4)) {
                            case 0:
                                grid[i + 1][j] = "P";
                                break;
                            case 1:
                                grid[i - 1][j] = "P";
                                break;
                            case 2:
                                grid[i][j + 1] = "P";
                                break;
                            case 3:
                                grid[i][j - 1] = "P";
                                break;
                            default:
                                break;
                        }
                    } //end of if prey is on the square
                }
            }
        } //end of while true loop
    }
}
