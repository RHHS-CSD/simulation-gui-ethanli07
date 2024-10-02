/*
 *************************************************
EXPLANATION OF CODE HERE
**************************************************
A - CardLayout
1) Set the layout of your frame to CardLayout (done from design using the navigator)
2) Add your panels one by one to the frame.
3) For each panel, in design at the bottom of the properties section
you need to give it a Card Name.  You will use this name to perform the swap
4) Whenever you want to change panels, you use this code:
CardLayout cl = (CardLayout)getContentPane().getLayout();
        cl.show(  getContentPane(), "card4");  /replace card4 with card you want to go to

(Optional)If you need to switch cards from the panel, then you need to have some code to find your frame
CardLayout cl = (CardLayout)this.getRootPane().getContentPane().getLayout();

B - Keyboard
GamePanel uses keyboard bindings starting on line 50

C- Mouse
GamePanel implements MouseListener and then adds the listener to the panel

D - Animation Timer - is set up in the Constructor of GamePanel
 */
import utils.CardSwitcher;
import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import javax.swing.JPanel;

/**
 *
 * @author michael.roy-diclemen
 */
public class SimulationFrame extends javax.swing.JFrame implements CardSwitcher {

    CardLayout cl;
    GamePanel gp;

    /**
     * Creates new form FrameForGame
     */
    public SimulationFrame() {
        this.setSize(420, 420);
        
        //card layout shows one panel at a time
        cl = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cl);
        this.add(cardPanel);
        
        //add 3 panels to the CardLayout
        addPanels();
        switchToCard(GamePanel.CARD_NAME);

        //some focus stuff for the game panel to capture key events
        gp.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                gp.requestFocusInWindow();
            }
        });
    }

    private void addPanels() {
        gp = new GamePanel(this);
        cardPanel.add(gp, GamePanel.CARD_NAME);
        cardPanel.add(new IntroPanel(this),IntroPanel.CARD_NAME);
        cardPanel.add(new InfoPanel(this),InfoPanel.CARD_NAME);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SimulationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SimulationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SimulationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimulationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SimulationFrame().setVisible(true);
            }
        });
    }

    @Override
    public void switchToCard(String cardName) {
        cl.show(cardPanel, cardName);
    }
               
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private javax.swing.JPanel cardPanel;
}
