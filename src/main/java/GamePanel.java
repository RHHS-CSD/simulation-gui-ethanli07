/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import utils.CardSwitcher;
import utils.ImageUtil;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import gameCode.PredatorPreyModels;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import gameCode.SimulationUpdateData;

/**
 * Panel where the simulation of the game occurs
 * @author Ethan Li
 */
public class GamePanel extends javax.swing.JPanel implements MouseListener {
    //initialize random
    Random r = new Random();
    
    public static final String CARD_NAME = "game";

    CardSwitcher switcher; // This is the parent panel
    
    //Timer
    Timer animTimer;
    
    //Images
    BufferedImage preyImg;
    BufferedImage predImg;
    BufferedImage obstacleImg;
    BufferedImage backgroundImg;
    
    //position strings
    String prey = "";
    String predator = "";
    String obstacle = "";
    
    //pred info strings
    String predAte = "";
    String predMoved = "";
    
    //create the grid
    int gridSize = 20;
    String[][] grid = new String[gridSize][gridSize];
    
    //game variables
    boolean startGame = false;
    int preyRepRate = 12;
    int count = 0;
    int speed = 50;
    
    
    /**
     * Creates new form GamePanel
     */
    public GamePanel(CardSwitcher p) {
        initComponents();
        
        //initialize the image
        preyImg = ImageUtil.loadAndResizeImage("chocolate.png", (320/gridSize), (320/gridSize));
        predImg = ImageUtil.loadAndResizeImage("Bob.png", (320/gridSize), (320/gridSize));
        obstacleImg = ImageUtil.loadAndResizeImage("gradingHomework.png", (320/gridSize), (320/gridSize));
        backgroundImg = ImageUtil.loadAndResizeImage("floor.jpg", 256, 144);
        
        this.setFocusable(true);

        // tell the program we want to listen to the mouse
        addMouseListener(this);
        
        //tells us the panel that controls this one
        switcher = p;
        
        //create and start a Timer for animation
        animTimer = new Timer(10, new AnimTimerTick());
        animTimer.start();

        //set up the key bindings
        setupKeys();
        
        //setup the grid
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = ".";
            }
        }
    }

    private void setupKeys() {
        //these lines map a physical key, to a name, and then a name to an 'action'.  You will change the key, name and action to suit your needs
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftKey");
        this.getActionMap().put("leftKey", new Move("LEFT"));

        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "wKey");
        this.getActionMap().put("wKey", new Move("w"));

        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "dKey");
        this.getActionMap().put("dKey", new Move("d"));

        this.getInputMap().put(KeyStroke.getKeyStroke("X"), "xKey");
        this.getActionMap().put("xKey", new Move("x"));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //draw background image
        for (int i = 0; i < 3; i++) {
            g.drawImage(backgroundImg, 0, 144 * i, this);
            g.drawImage(backgroundImg, 256, 144 * i, this);
        }
        
        //draw side panel
        g.setColor(new Color(211, 211, 211, 180));
        g.fillRect(0, 0, 76, 404);
        
        //draw grid
        g.setColor(Color.WHITE);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        
        for (int i = 0; i < 21; i++) {
            g.drawLine(i * (320/gridSize) + 92, 20, i * (320/gridSize) + 92, 340);
        }
        for (int i = 0; i < 21; i++) {
            g.drawLine(92, i * (320/gridSize) + 20, 412, i * (320/gridSize) + 20);
        } 
        
        //draw prey, pred, and obstacles on the panel
        for (int i = 0; i < (predator.length() + 1)/3; i++) {
            g.drawImage(predImg, (predator.charAt(i * 3) - 65) * (320/gridSize) + 92, (predator.charAt(i * 3 + 1) - 65) * (320/gridSize) + 20, this);
        }
        for (int i = 0; i < (prey.length() + 1)/3; i++) {
            g.drawImage(preyImg, (prey.charAt(i * 3) - 65) * (320/gridSize) + 92, (prey.charAt(i * 3 + 1) - 65) * (320/gridSize) + 20, this);
        }
        for (int i = 0; i < (obstacle.length())/3; i++) {
            g.drawImage(obstacleImg, (obstacle.charAt(i * 3) - 65) * (320/gridSize) + 92, (obstacle.charAt(i * 3 + 1) - 65) * (320/gridSize) + 20, this);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ResetButton = new javax.swing.JButton();
        StartButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        repRateSlider = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        gridSizeField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        speedSlider = new javax.swing.JSlider();

        ResetButton.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        ResetButton.setText("RESET");
        ResetButton.setPreferredSize(new java.awt.Dimension(104, 40));
        ResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetButtonActionPerformed(evt);
            }
        });

        StartButton.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        StartButton.setText("START");
        StartButton.setPreferredSize(new java.awt.Dimension(104, 40));
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Prey Rep");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Rate:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Grid Size:");

        gridSizeField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gridSizeField.setText("20");
        gridSizeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gridSizeFieldActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Speed:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(ResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(repRateSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(8, 8, 8))
                            .addComponent(jLabel4)
                            .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gridSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(repRateSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetButtonActionPerformed
        startGame = false;
        
        //reset the variables
        prey = "";
        predator = "";
        obstacle = "";
        grid = new String[gridSize][gridSize];
        
        //reenable the start button
        StartButton.setEnabled(true);
    }//GEN-LAST:event_ResetButtonActionPerformed

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        //start the game
        startGame = true;
        
        //set the prey reproduction rate and speed based on the slider values
        preyRepRate = (100 - repRateSlider.getValue())/5;
        speed = (101 - speedSlider.getValue());
        
        //adjust the string size
        prey = prey.substring(0, prey.length() - 1);
        predator = predator.substring(0, predator.length() - 1);
        
        //create the pred info strings
        for (int i = 0; i < (predator.length() + 1)/3; i++) {
            predAte += "T ";
        }
        for (int i = 0; i < (predator.length() + 1)/3; i++) {
            predMoved += "F ";
        }
        
        //disable the start button to prevent the user from breaking the game
        StartButton.setEnabled(false);
    }//GEN-LAST:event_StartButtonActionPerformed

    private void gridSizeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gridSizeFieldActionPerformed
        gridSize = Integer.parseInt(gridSizeField.getText());
    }//GEN-LAST:event_gridSizeFieldActionPerformed
    
    //mouse events
    public void mouseClicked(MouseEvent me) {
        if (me.getX() > 92 && me.getX() < 412 && me.getY() > 20 && me.getY() < 340) { //make sure that the mouse is on the grid
            if (me.getButton() == MouseEvent.BUTTON1) { //place a prey on the grid if the user left clicks
                prey += ((char) ((me.getX() - 92)/(320/gridSize) + 65) + "" + (char) ((me.getY() - 20)/(320/gridSize) + 65) + " ");
                grid[(me.getX() - 92)/(320/gridSize)][(me.getY() - 20)/(320/gridSize)] = "@";
            }
            else if (me.getButton() == MouseEvent.BUTTON2) { //place an obstacle on the grid if the user middle clicks
                obstacle += ((char) ((me.getX() - 92)/(320/gridSize) + 65) + "" + (char) ((me.getY() - 20)/(320/gridSize) + 65) + " ");
                grid[(me.getX() - 92)/(320/gridSize)][(me.getY() - 20)/(320/gridSize)] = "*";
            }
            else if (me.getButton() == MouseEvent.BUTTON3) { //place a predator on the grid if the user right clicks
                predator += ((char) ((me.getX() - 92)/(320/gridSize) + 65) + "" + (char) ((me.getY() - 20)/(320/gridSize) + 65) + " ");
                grid[(me.getX() - 92)/(320/gridSize)][(me.getY() - 20)/(320/gridSize)] = "P";
            }
        }
    }
    public void mousePressed(MouseEvent me) {
    }
    public void mouseReleased(MouseEvent me) {
    }
    public void mouseEntered(MouseEvent me) {
    }
    public void mouseExited(MouseEvent me) {
        System.out.println("Exit: " + me.getX() + ":" + me.getY());
    }

    /**
     * Everything inside here happens when you click on a captured key.
     */
    private class Move extends AbstractAction {
        String key;

        public Move(String akey) {
            key = akey;
        }

        public void actionPerformed(ActionEvent ae) {
        }
    }

    /**
     * Everything inside this actionPerformed will happen every time the
     * animation timer clicks.
     */
    private class AnimTimerTick implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            if (startGame) {
                count += 1; //update the game clock
                if (count % speed == 0) { //if enough time has passed, simulate the program once
                    SimulationUpdateData data = PredatorPreyModels.simulateProgramOnce(prey, predator, obstacle, predAte, predMoved, preyRepRate, grid, 20);
                    
                    //update the simulation's data based on the values returned by the program
                    prey = data.getPrey();
                    predator = data.getPredator();
                    predAte = data.getPredAte();
                    predMoved = data.getPredMoved();
                    preyRepRate = data.getPreyRepRate();
                    grid = data.getGrid();
                }
            }
            
            //force redraw
            repaint();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ResetButton;
    private javax.swing.JButton StartButton;
    private javax.swing.JTextField gridSizeField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSlider repRateSlider;
    private javax.swing.JSlider speedSlider;
    // End of variables declaration//GEN-END:variables
}
