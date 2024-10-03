/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameCode;

/**
 *
 * @author Ethan
 */
public class SimulationUpdateData {
    String prey;
    String predator;
    String predAte;
    String predMoved;
    int preyRepRate;
    String[][] grid;
    
    public SimulationUpdateData(String prey, String predator, String predAte, String predMoved, int preyRepRate, String[][] grid) {
        this.prey = prey;
        this.predator = predator;
        this.predAte = predAte;
        this.predMoved = predMoved;
        this.preyRepRate = preyRepRate;
        this.grid = grid;
    }
    
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
    
    public String[][] grid() {
        return grid;
    }
}
