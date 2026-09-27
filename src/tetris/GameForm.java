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
        
        iMap.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        iMap.put(KeyStroke.getKeyStroke("LEFT"), "left");
        iMap.put(KeyStroke.getKeyStroke("UP"), "up");
        iMap.put(KeyStroke.getKeyStroke("DOWN"), "down");
        
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
                ga.hardDrop();
            }        
        });
        
    }
    
    public void startGame()
    {
        new GameThread(ga, this).start();
    }
    
    public void updateScore(int score)
    {
        scoreValue.setText("" + score);
    }
    
    public void updateLevel(int level)
    {
        levelValue.setText("" + level);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        etchedBorder1 = (javax.swing.border.EtchedBorder)javax.swing.BorderFactory.createEtchedBorder();
        gameAreaCanvas = new javax.swing.JPanel();
        dashPanel = new javax.swing.JPanel();
        scoreLabel = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        lineCountLabel = new javax.swing.JLabel();
        scoreValue = new javax.swing.JLabel();
        levelValue = new javax.swing.JLabel();
        lineCountValue = new javax.swing.JLabel();
        nextQueuePanel = new javax.swing.JPanel();

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        setResizable(false);

        gameAreaCanvas.setBackground(new java.awt.Color(0, 0, 0));
        gameAreaCanvas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        gameAreaCanvas.setToolTipText("");
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

        dashPanel.setBackground(new java.awt.Color(0, 0, 0));
        dashPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        dashPanel.setForeground(new java.awt.Color(255, 255, 255));
        dashPanel.setPreferredSize(new java.awt.Dimension(150, 500));
        dashPanel.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dashPanelAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        scoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        scoreLabel.setText("SCORE");

        levelLabel.setForeground(new java.awt.Color(255, 255, 255));
        levelLabel.setText("LEVEL");

        lineCountLabel.setForeground(new java.awt.Color(255, 255, 255));
        lineCountLabel.setText("LINES");

        scoreValue.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        scoreValue.setForeground(new java.awt.Color(255, 255, 255));
        scoreValue.setText("0");

        levelValue.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        levelValue.setForeground(new java.awt.Color(255, 255, 255));
        levelValue.setText("1");

        lineCountValue.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lineCountValue.setForeground(new java.awt.Color(255, 255, 255));
        lineCountValue.setText("0");

        javax.swing.GroupLayout nextQueuePanelLayout = new javax.swing.GroupLayout(nextQueuePanel);
        nextQueuePanel.setLayout(nextQueuePanelLayout);
        nextQueuePanelLayout.setHorizontalGroup(
            nextQueuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        nextQueuePanelLayout.setVerticalGroup(
            nextQueuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout dashPanelLayout = new javax.swing.GroupLayout(dashPanel);
        dashPanel.setLayout(dashPanelLayout);
        dashPanelLayout.setHorizontalGroup(
            dashPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dashPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scoreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(levelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lineCountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scoreValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(levelValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lineCountValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dashPanelLayout.createSequentialGroup()
                        .addComponent(nextQueuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        dashPanelLayout.setVerticalGroup(
            dashPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nextQueuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scoreLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(levelLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(levelValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lineCountLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineCountValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(gameAreaCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(dashPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(gameAreaCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dashPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dashPanelAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dashPanelAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_dashPanelAncestorAdded

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
    private javax.swing.JPanel dashPanel;
    private javax.swing.border.EtchedBorder etchedBorder1;
    private javax.swing.JPanel gameAreaCanvas;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JLabel levelValue;
    private javax.swing.JLabel lineCountLabel;
    private javax.swing.JLabel lineCountValue;
    private javax.swing.JPanel nextQueuePanel;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JLabel scoreValue;
    // End of variables declaration//GEN-END:variables
}
