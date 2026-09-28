package tetris;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class GameForm extends JFrame 
{
    private GameArea ga;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GameForm.class.getName());

    public GameForm()   
    {
        initComponents();
        
        ga = new GameArea(gameAreaCanvas, 10);
        this.add( ga );
        
        initControls();
        
        startGame();
    }
    
    private void initControls()
    {
        InputMap iMap = getRootPane().getInputMap();
        ActionMap aMap = getRootPane().getActionMap();
        
        iMap.put(KeyStroke.getKeyStroke("D"), "right");
        iMap.put(KeyStroke.getKeyStroke("A"), "left");
        iMap.put(KeyStroke.getKeyStroke("W"), "up");
        iMap.put(KeyStroke.getKeyStroke("S"), "down");
        
        aMap.put("right", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                ga.moveBlockRight();
            }        
        });
        
        aMap.put("left", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                ga.moveBlockLeft();
            }        
        });
        
        
        aMap.put("up", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                ga.rotateBlock();
            }        
        });
        
        
        aMap.put("down", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                ga.dropBlock();
            }        
        });
        
    }
    
    public void startGame()
    {
        new GameThread(ga, this).start();
    }
    
    public void updateScore(int score)
    {
        scoreValue.setText(score + "");
    }
    
    public void updateLvl(int level)
    {
        levelValue.setText(level + "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameAreaCanvas = new javax.swing.JPanel();
        dashPanelRight = new javax.swing.JPanel();
        btnPause = new javax.swing.JButton();
        scoreLabel = new java.awt.Label();
        lvlLabel = new java.awt.Label();
        scoreValue = new javax.swing.JLabel();
        levelValue = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TETRIS");
        setBackground(new java.awt.Color(0, 0, 0));
        setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        setResizable(false);

        gameAreaCanvas.setBackground(new java.awt.Color(0, 0, 0));
        gameAreaCanvas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        gameAreaCanvas.setForeground(new java.awt.Color(255, 255, 255));
        gameAreaCanvas.setToolTipText("");
        gameAreaCanvas.setMaximumSize(new java.awt.Dimension(250, 500));
        gameAreaCanvas.setMinimumSize(new java.awt.Dimension(250, 500));
        gameAreaCanvas.setPreferredSize(new java.awt.Dimension(250, 500));

        javax.swing.GroupLayout gameAreaCanvasLayout = new javax.swing.GroupLayout(gameAreaCanvas);
        gameAreaCanvas.setLayout(gameAreaCanvasLayout);
        gameAreaCanvasLayout.setHorizontalGroup(
            gameAreaCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );
        gameAreaCanvasLayout.setVerticalGroup(
            gameAreaCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        dashPanelRight.setBackground(new java.awt.Color(0, 0, 0));
        dashPanelRight.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        dashPanelRight.setForeground(new java.awt.Color(255, 255, 255));
        dashPanelRight.setMaximumSize(new java.awt.Dimension(150, 500));
        dashPanelRight.setMinimumSize(new java.awt.Dimension(150, 500));
        dashPanelRight.setPreferredSize(new java.awt.Dimension(150, 500));

        btnPause.setBackground(new java.awt.Color(51, 51, 51));
        btnPause.setForeground(new java.awt.Color(255, 255, 255));
        btnPause.setText("PAUSE");
        btnPause.addActionListener(this::btnPauseActionPerformed);

        scoreLabel.setAlignment(java.awt.Label.CENTER);
        scoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        scoreLabel.setText("SCORE");

        lvlLabel.setAlignment(java.awt.Label.CENTER);
        lvlLabel.setForeground(new java.awt.Color(255, 255, 255));
        lvlLabel.setText("LEVEL");

        scoreValue.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        scoreValue.setForeground(new java.awt.Color(255, 255, 255));
        scoreValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        scoreValue.setText("0");

        levelValue.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        levelValue.setForeground(new java.awt.Color(255, 255, 255));
        levelValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        levelValue.setText("1");

        javax.swing.GroupLayout dashPanelRightLayout = new javax.swing.GroupLayout(dashPanelRight);
        dashPanelRight.setLayout(dashPanelRightLayout);
        dashPanelRightLayout.setHorizontalGroup(
            dashPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashPanelRightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dashPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPause, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scoreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lvlLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scoreValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(levelValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                .addContainerGap())
        );
        dashPanelRightLayout.setVerticalGroup(
            dashPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dashPanelRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scoreValue)
                .addGap(30, 30, 30)
                .addComponent(lvlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(levelValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 331, Short.MAX_VALUE)
                .addComponent(btnPause)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(gameAreaCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(dashPanelRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gameAreaCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(dashPanelRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPauseActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new GameForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPause;
    private javax.swing.JPanel dashPanelRight;
    private javax.swing.JPanel gameAreaCanvas;
    private javax.swing.JLabel levelValue;
    private java.awt.Label lvlLabel;
    private java.awt.Label scoreLabel;
    private javax.swing.JLabel scoreValue;
    // End of variables declaration//GEN-END:variables
}
