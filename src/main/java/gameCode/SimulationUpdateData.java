/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameCode;

/**
 * Class that stores the updated values of the required simulation data
 * @author Ethan Li
 */
public class SimulationUpdateData {
    String prey;
    String predator;
    String predAte;
    String predMoved;
    int preyRepRate;
    String[][] grid;
    
    /**
     * Creates an object storing all the necessary update data
     * @param prey The current prey position string
     * @param predator The current predator position string
     * @param predAte The current predAte string
     * @param predMoved The current predMoved string
     * @param preyRepRate The current value of preyRepRate
     * @param grid The current state of the grid
     */
    public SimulationUpdateData(String prey, String predator, String predAte, String predMoved, int preyRepRate, String[][] grid) {
        this.prey = prey;
        this.predator = predator;
        this.predAte = predAte;
        this.predMoved = predMoved;
        this.preyRepRate = preyRepRate;
        this.grid = grid;
    }
    
    //Getters
    public String getPrey() {
        return prey;
    }
    
    public String getPredator() {
        return predator;
    }
    
    public String getPredAte() {
        return predAte;
    }
    
    public String getPredMoved() {
        return predMoved;
    }
    
    public int getPreyRepRate() {
        return preyRepRate;
    }
    
    public String[][] getGrid() {
        return grid;
    }
}
