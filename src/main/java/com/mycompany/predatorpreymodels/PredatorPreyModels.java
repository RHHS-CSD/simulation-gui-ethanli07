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
        final int predPop = 2;
        final int preyPop = 4;
        final int gridSize = 4;
        
        //Find initial population positions
        String predator = "";
        for (int i = 0; i < predPop; i++) {
            predator += ((char)('A' + r.nextInt(gridSize)) + "" + (char)('A' + r.nextInt(gridSize)) + " ");
        }
        predator = predator.substring(0, predator.length()-1);
       
        String prey = "";
        for (int i = 0; i < preyPop; i++) {
            prey += ((char)('A' + r.nextInt(gridSize)) + "" + (char)('A' + r.nextInt(gridSize)) + " ");
        }
        prey = prey.substring(0, prey.length() - 1);
        
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
                predator = predator.substring(0, i) + (char)('A' + r.nextInt(gridSize)) + (char)('A' + r.nextInt(gridSize)) + predator.substring(i + 2);
                i -= 3;
            }
        }
        for (int i = 0; i < prey.length(); i += 3) {
            if (grid[prey.charAt(i) - 65][prey.charAt(i + 1) - 65].equals("*")) {
                grid[prey.charAt(i) - 65][prey.charAt(i + 1) - 65] = "@";
            }
            else {
                prey = prey.substring(0, i) + (char)('A' + r.nextInt(gridSize)) + (char)('A' + r.nextInt(gridSize)) + prey.substring(i + 2);
                i -= 3;
            }
        }
        
        //build and output the initial positions
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
        
        //Predator movement and hunger checks
        String predAte = "";
        for (int i = 0; i < predPop; i++) {
            predAte += ('A' + gridSize - 1) + " ";
        }
        
        String predMoved = "";
        for (int i = 0; i < predPop; i++) {
            predMoved += "F ";
        }
        
        //simulate the program
        for (int a = 0; a < 10; a++) {
            for (int i = 0; i < predator.length(); i += 3) {
                for (int j = 0; j < prey.length(); j += 3) {
                    //predator movement if prey is nearby
                    if (((predator.charAt(i) + 1) == prey.charAt(j)) && ((predator.charAt(i + 1)) == prey.charAt(j + 1))) {
                        predator = predator.substring(0, i) + prey.charAt(j) + predator.substring(i + 1);
                        if (j == prey.length() - 2) {
                            if (j > 0) {
                                prey = prey.substring(0, j - 1);
                            }
                            else {
                                prey = prey.substring(0, j);
                            }
                        }
                        else {
                            prey = prey.substring(0, j) + prey.substring(j + 3);
                        }
                        predAte = predAte.substring(0, i*2/3) + "T" + predAte.substring(i*2/3 + 1);
                        predMoved = predMoved.substring(0, i*2/3) + "T" + predMoved.substring(i*2/3 + 1);
                    } 
                    else if (((predator.charAt(i) - 1) == prey.charAt(j)) && ((predator.charAt(i + 1)) == prey.charAt(j + 1))) {
                        predator = predator.substring(0, i) + prey.charAt(j) + predator.substring(i + 1);
                        if (j == prey.length() - 2) {
                            if (j > 0) {
                                prey = prey.substring(0, j - 1);
                            }
                            else {
                                prey = prey.substring(0, j);
                            }
                        }
                        else {
                            prey = prey.substring(0, j) + prey.substring(j + 3);
                        }
                        predAte = predAte.substring(0, i*2/3) + "T" + predAte.substring(i*2/3 + 1);
                        predMoved = predMoved.substring(0, i*2/3) + "T" + predMoved.substring(i*2/3 + 1);
                    } 
                    else if (((predator.charAt(i)) == prey.charAt(j)) && ((predator.charAt(i + 1) + 1) == prey.charAt(j + 1))) {
                        predator = predator.substring(0, i + 1) + prey.charAt(j + 1) + predator.substring(i + 2);
                        if (j == prey.length() - 2) {
                            if (j > 0) {
                                prey = prey.substring(0, j - 1);
                            }
                            else {
                                prey = prey.substring(0, j);
                            }
                        }
                        else {
                            prey = prey.substring(0, j) + prey.substring(j + 3);
                        }
                        predAte = predAte.substring(0, i*2/3) + "T" + predAte.substring(i*2/3 + 1);
                        predMoved = predMoved.substring(0, i*2/3) + "T" + predMoved.substring(i*2/3 + 1);
                    }
                    else if (((predator.charAt(i)) == prey.charAt(j)) && ((predator.charAt(i + 1) - 1) == prey.charAt(j + 1))) {
                        predator = predator.substring(0, i + 1) + prey.charAt(j + 1) + predator.substring(i + 2);
                        if (j == prey.length() - 2) {
                            if (j > 0) {
                                prey = prey.substring(0, j - 1);
                            }
                            else {
                                prey = prey.substring(0, j);
                            }
                        }
                        else {
                            prey = prey.substring(0, j) + prey.substring(j + 3);
                        }
                        predAte = predAte.substring(0, i*2/3) + "T" + predAte.substring(i*2/3 + 1);
                        predMoved = predMoved.substring(0, i*2/3) + "T" + predMoved.substring(i*2/3 + 1);
                    } 
                } //end of prey loop
                
                //predator movement if prey is not nearby
                if (predMoved.charAt(i/3) == 'F') {
                    predAte = predAte.substring(0, i*2/3) + (char) (predAte.charAt(i*2/3) - 1) + predAte.substring(i*2/3 + 1);
                    String predatorOP = predator.substring(i, i + 2);
                    switch (r.nextInt(4)) {
                        case 0:
                            if (predator.charAt(i) == ('A' + gridSize - 1)) {
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
                                predator = predator.substring(0, i) + (char) ('A' + gridSize - 1) + predator.substring(i + 1);
                            }
                            else {
                                if (grid[predator.charAt(i) - 66][predator.charAt(i + 1) - 65].equals("*")) {
                                    predator = predator.substring(0, i) + (char) (predator.charAt(i) - 1) + predator.substring(i + 1);
                                }
                            }
                            break;
                        case 2:
                            if (predator.charAt(i + 1) == ('A' + gridSize - 1)) {
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
                                predator = predator.substring(0, i + 1) + (char) ('A' + gridSize - 1) + predator.substring(i + 2);
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
                    if (i == predator.length() - 2) {
                        if (i > 0) {
                            prey = prey.substring(0, i - 1);
                        }
                        else {
                            prey = prey.substring(0, i);
                        }
                    }
                    else {
                        predator = predator.substring(0, i) + predator.substring(i + 3);
                    }
                    
                    if (predAte.charAt(i*2/3) == predAte.length() - 1) {
                        if (i*2/3 > 0) {
                            predAte = predAte.substring(0, i*2/3 - 1);
                        }
                        else {
                            predAte = predAte.substring(0, i*2/3);
                        }
                    }
                    else {
                        predAte = predAte.substring(0, i*2/3) + predAte.substring(i*2/3 + 2);
                    }
                }
                
                //update predator position on the grid
                grid = new String[gridSize][gridSize];
                for (int j = 0; j < grid.length; j++) {
                    for (int k = 0; k < grid.length; k++) {    
                        grid[j][k] = "*";
                    }
                }
                
                System.out.println(predator);
                for (int j = 0; j < predator.length(); j += 3) {
                    grid[predator.charAt(j) - 65][predator.charAt(j + 1) - 65] = "P";
                }
                for (int j = 0; j < prey.length(); j += 3) {
                    grid[prey.charAt(j) - 65][prey.charAt(j + 1) - 65] = "@";
                }
            } //end of predator string
                    
            //prey movement
            for (int i = 0; i < prey.length(); i += 3) {
                String preyOP = prey.substring(i, i + 2);
                switch (r.nextInt(4)) {
                    case 0:
                        if (prey.charAt(i) == ('A' + gridSize - 1)) {
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
                                prey = prey.substring(0, i) + (char) ('A' + gridSize - 1) + prey.substring(i + 1);
                        }
                        else {    
                            if (grid[prey.charAt(i) - 66][prey.charAt(i + 1) - 65].equals("*")) {
                                prey = prey.substring(0, i) + (char) (prey.charAt(i) - 1) + prey.substring(i + 1);
                            }
                        }
                        break;
                    case 2:
                        if (prey.charAt(i + 1) == ('A' + gridSize - 1)) {
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
                                prey = prey.substring(0, i + 1) + (char) ('A' + gridSize - 1) + prey.substring(i + 2);
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
                
                //update prey position on the grid
                grid = new String[gridSize][gridSize];
                for (int j = 0; j < grid.length; j++) {
                    for (int k = 0; k < grid.length; k++) {    
                        grid[j][k] = "*";
                    }
                }
                
                System.out.println(prey);
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
        
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    System.out.print(grid[i][j]);
                }
                System.out.println("");
            }
            System.out.println("");
        } //end of while true loop
    }
}
