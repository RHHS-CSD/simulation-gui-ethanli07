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
    String obstacle;
    String predAte;
    String predMoved;
    int preyRepRate;
    String[][] grid;
    int gridSize;
    
    public SimulationUpdateData(String prey, String predator, String obstacle, String predAte, String predMoved, int preyRepRate, String[][] grid) {
        this.prey = prey;
        this.predator = predator;
        this.obstacle = obstacle;
        this.predAte = predAte;
        this.predMoved = predMoved;
        this.preyRepRate = preyRepRate;
        this.grid = grid;
        this.gridSize = gridSize;
    }
    
    public String getPrey() {
        return prey;
    }
    
    public String getPredator() {
        return predator;
    }
    
    public String getObstacle() {
        return obstacle;
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
