/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gameCode;

import java.util.Random;
import gameCode.SimulationUpdateData;

/**
 * Program Model that simulates the population dynamics between predators and prey using a 2D grid
 * @author Ethan
 */
public class PredatorPreyModels {
    public static void main(String[] args) {
        ///// Variables /////
        //Random initialization
        Random r = new Random();
        
        //constants
        final int predPop = 10;
        final int preyPop = 100;
        final int gridSize = 20;
        final int obstacleNum = 20;
        
        //prey reproduction rate
        int preyRepRate = 10;
        
        //Set initial prey + predator population & obstacle positions
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
        
        String obstacle = "";
        for (int i = 0; i < obstacleNum; i++) {
            obstacle += ((char)('A' + r.nextInt(gridSize)) + "" + (char)('A' + r.nextInt(gridSize)) + " ");
        }
        obstacle = obstacle.substring(0, obstacle.length() - 1);
        
        //Build the 2D Grid
        String grid[][] = new String[gridSize][gridSize];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {    
                grid[i][j] = ".";
            }
        }
        
        //Assign initial predator, prey, and obstacle positions on the grid
        for (int i = 0; i < predator.length(); i += 3) {
            if (grid[predator.charAt(i) - 65][predator.charAt(i + 1) - 65].equals(".")) {
                grid[predator.charAt(i) - 65][predator.charAt(i + 1) - 65] = "P";
            }
            else {
                predator = predator.substring(0, i) + (char)('A' + r.nextInt(gridSize)) + (char)('A' + r.nextInt(gridSize)) + predator.substring(i + 2);
                i -= 3;
            }
        }
        
        for (int i = 0; i < prey.length(); i += 3) {
            if (grid[prey.charAt(i) - 65][prey.charAt(i + 1) - 65].equals(".")) {
                grid[prey.charAt(i) - 65][prey.charAt(i + 1) - 65] = "@";
            }
            else {
                prey = prey.substring(0, i) + (char)('A' + r.nextInt(gridSize)) + (char)('A' + r.nextInt(gridSize)) + prey.substring(i + 2);
                i -= 3;
            }
        }
        
        for (int i = 0; i < obstacle.length(); i += 3) {
            if (grid[obstacle.charAt(i) - 65][obstacle.charAt(i + 1) - 65].equals(".")) {
                grid[obstacle.charAt(i) - 65][obstacle.charAt(i + 1) - 65] = "*";
            }
            else {
                obstacle = obstacle.substring(0, i) + (char)('A' + r.nextInt(gridSize)) + (char)('A' + r.nextInt(gridSize)) + obstacle.substring(i + 2);
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
        
        //Create predator movement and hunger strings to keep track of each variable respectively
        String predAte = "";
        for (int i = 0; i < predPop; i++) {
            predAte += ("T ");
        }
        
        String predMoved = "";
        for (int i = 0; i < predPop; i++) {
            predMoved += "F ";
        }
        
        //run the main program
        while (true) {
            simulateProgramOnce(prey, predator, obstacle, predAte, predMoved, preyRepRate, grid, gridSize);
        }
    }
    
    /**
     * Simulates one instance of the program
     * @param prey The current prey position string
     * @param predator The current predator position string
     * @param obstacle The obstacle (unwalkable tile) positions
     * @param predAte The current predAte string
     * @param predMoved The current predMoved string
     * @param preyRepRate The current value of preyRepRate
     * @param grid The current state of the grid
     * @param gridSize The dimensions of the grid for the simulation
     * @return obj An object containing all the required information to update the simulation values
     */
    public static SimulationUpdateData simulateProgramOnce(String prey, String predator, String obstacle, String predAte, String predMoved, int preyRepRate, String[][] grid, int gridSize) {
        Random r = new Random();

        //Simulate the program
        for (int i = 0; i < predator.length(); i += 3) {
            for (int j = 0; j < prey.length(); j += 3) {
            //for each predator, check if a prey is on an adjacent square
                if (((predator.charAt(i) + 1) == prey.charAt(j)) && ((predator.charAt(i + 1)) == prey.charAt(j + 1))) { //bottom tile
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
                else if (((predator.charAt(i) - 1) == prey.charAt(j)) && ((predator.charAt(i + 1)) == prey.charAt(j + 1))) { //up tile
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
                else if (((predator.charAt(i)) == prey.charAt(j)) && ((predator.charAt(i + 1) + 1) == prey.charAt(j + 1))) { //right tile
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
                else if (((predator.charAt(i)) == prey.charAt(j)) && ((predator.charAt(i + 1) - 1) == prey.charAt(j + 1))) { //left tile
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
                    case 0: //move down
                        if (predator.charAt(i) == ('A' + gridSize - 1)) {
                            predator = predator.substring(0, i) + 'A' + predator.substring(i + 1);
                        }
                        else {
                            if (grid[predator.charAt(i) - 64][predator.charAt(i + 1) - 65].equals(".")) {
                                predator = predator.substring(0, i) + (char) (predator.charAt(i) + 1) + predator.substring(i + 1);
                            }
                        }
                        break;
                    case 1: //move up
                        if (predator.charAt(i) == 'A') {
                            predator = predator.substring(0, i) + (char) ('A' + gridSize - 1) + predator.substring(i + 1);
                        }
                        else {
                            if (grid[predator.charAt(i) - 66][predator.charAt(i + 1) - 65].equals(".")) {
                                predator = predator.substring(0, i) + (char) (predator.charAt(i) - 1) + predator.substring(i + 1);
                            }
                        }
                        break;
                    case 2: //move right
                        if (predator.charAt(i + 1) == ('A' + gridSize - 1)) {
                            predator = predator.substring(0, i + 1) + 'A' + predator.substring(i + 2);
                        }
                        else {
                            if (grid[predator.charAt(i) - 65][predator.charAt(i + 1) - 64].equals(".")) {
                                predator = predator.substring(0, i + 1) + (char) (predator.charAt(i + 1) + 1) + predator.substring(i + 2);
                            }
                        }
                        break;
                    case 3: //move left
                        if (predator.charAt(i + 1) == 'A') {
                            predator = predator.substring(0, i + 1) + (char) ('A' + gridSize - 1) + predator.substring(i + 2);
                        }
                        else {
                            if (grid[predator.charAt(i) - 65][predator.charAt(i + 1) - 66].equals(".")) {
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
                    predAte += "F ";
                    predMoved += "T ";
                } 
            }
                
            //checks if the predator should die and updates strings if they should
            if (predAte.charAt(i*2/3) < 'A') {
                if (i == predator.length() - 2) {
                    if (i > 0) {
                        predator = predator.substring(0, i - 1);
                    }
                    else {
                        predator = predator.substring(0, i);
                    }
                }
                else {
                    predator = predator.substring(0, i) + predator.substring(i + 3);
                }
                
                if (predAte.charAt(i*2/3) == predAte.length() - 1) {
                    if (i*2/3 > 0) {
                        predAte = predAte.substring(0, i*2/3 - 1);
                        predMoved = predMoved.substring(0, i*2/3 - 1);
                    }
                    else {
                        predAte = predAte.substring(0, i*2/3);
                        predMoved = predMoved.substring(0, i*2/3);
                    }
                }
                else {
                    predAte = predAte.substring(0, i*2/3) + predAte.substring(i*2/3 + 2);
                    predMoved = predMoved.substring(0, i*2/3) + predMoved.substring(i*2/3 + 2);
                }
            }
                
            //update predator position on the grid
            grid = new String[gridSize][gridSize];
            for (int j = 0; j < grid.length; j++) {
                for (int k = 0; k < grid.length; k++) {    
                    grid[j][k] = ".";
                }
            }
            
            //remap the grid
            for (int j = 0; j < predator.length(); j += 3) {
                grid[predator.charAt(j) - 65][predator.charAt(j + 1) - 65] = "P";
            }
            for (int j = 0; j < prey.length(); j += 3) {
                grid[prey.charAt(j) - 65][prey.charAt(j + 1) - 65] = "@";
            }
            for (int j = 0; j < obstacle.length(); j += 3) {
                grid[obstacle.charAt(j) - 65][obstacle.charAt(j + 1) - 65] = "*";
            }
        } //end of predator string
                    
        //randomly move each prey
        for (int i = 0; i < prey.length(); i += 3) {
            String preyOP = prey.substring(i, i + 2);
            switch (r.nextInt(4)) {
                case 0:
                    if (prey.charAt(i) == ('A' + gridSize - 1)) { //move down
                        prey = prey.substring(0, i) + 'A' + prey.substring(i + 1);
                    }
                    else {
                        if (grid[prey.charAt(i) - 64][prey.charAt(i + 1) - 65].equals(".")) {
                            prey = prey.substring(0, i) + (char) (prey.charAt(i) + 1) + prey.substring(i + 1);
                        }
                    }
                    break;
                case 1:
                    if (prey.charAt(i) == 'A') { //move up
                        prey = prey.substring(0, i) + (char) ('A' + gridSize - 1) + prey.substring(i + 1);
                    }
                    else {    
                        if (grid[prey.charAt(i) - 66][prey.charAt(i + 1) - 65].equals(".")) {
                            prey = prey.substring(0, i) + (char) (prey.charAt(i) - 1) + prey.substring(i + 1);
                        }
                    }
                    break;
                case 2:
                    if (prey.charAt(i + 1) == ('A' + gridSize - 1)) { //move right
                        prey = prey.substring(0, i + 1) + 'A' + prey.substring(i + 2);
                    }
                    else {
                        if (grid[prey.charAt(i) - 65][prey.charAt(i + 1) - 64].equals(".")) {
                            prey = prey.substring(0, i + 1) + (char) (prey.charAt(i + 1) + 1) + prey.substring(i + 2);
                        }
                    }
                    break;
                case 3:
                    if (prey.charAt(i + 1) == 'A') { //move left
                        prey = prey.substring(0, i + 1) + (char) ('A' + gridSize - 1) + prey.substring(i + 2);
                    }
                    else {
                        if (grid[prey.charAt(i) - 65][prey.charAt(i + 1) - 66].equals(".")) {
                            prey = prey.substring(0, i + 1) + (char) (prey.charAt(i + 1) - 1) + prey.substring(i + 2);
                        }
                    }
                    break;
                default:
                    break;
            }
            if (r.nextInt(preyRepRate) == 0) { //randomly check if the prey should reproduce
                prey += (" " + preyOP);
            }
            
            //update the prey position on the grid
            grid = new String[gridSize][gridSize];
            for (int j = 0; j < grid.length; j++) {
                for (int k = 0; k < grid.length; k++) {    
                    grid[j][k] = ".";
                }
            }
            
            //remap the grid
            for (int j = 0; j < predator.length(); j += 3) {
                grid[predator.charAt(j) - 65][predator.charAt(j + 1) - 65] = "P";
            }
            for (int j = 0; j < prey.length(); j += 3) {
                grid[prey.charAt(j) - 65][prey.charAt(j + 1) - 65] = "@";
            }
            for (int j = 0; j < obstacle.length(); j += 3) {
                grid[obstacle.charAt(j) - 65][obstacle.charAt(j + 1) - 65] = "*";
            }
        }// end of prey loop
            
        //reset variables
        predMoved = "";
        for (int i = 0; i < predator.length(); i += 3) {
            predMoved += "F ";
        }
        
        //randomly affect the chance for prey to reproduce (due to lack/surplus of food in a given period due to environmental factors)
        int cropGrowthRate = r.nextInt(12);
        if (cropGrowthRate == 0) {
            preyRepRate += 1; //decreases chance for reproduction
        }
        if (cropGrowthRate == 1) {
            preyRepRate -= 1; //increases chance for reproduction
        }
        
        //output the new grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
        
        //return an object containing the updated simulation data
        return new SimulationUpdateData(prey, predator, predAte, predMoved, preyRepRate, grid);
    } //end of method
}