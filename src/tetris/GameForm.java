package tetris;

import javax.swing.JFrame;

public class GameForm extends JFrame 
{
    private GameArea ga;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GameForm.class.getName());

    public GameForm()   
    {
        initComponents();
        
        ga = new GameArea(gameAreaCanvas, 10);
        this.add( ga );
        
        startGame();
    }
    
    public void startGame()
    {
        new GameThread(ga).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameAreaCanvas = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setFocusableWindowState(false);
        setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        setPreferredSize(new java.awt.Dimension(640, 720));
        setResizable(false);

        gameAreaCanvas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        gameAreaCanvas.setToolTipText("");
        gameAreaCanvas.setPreferredSize(new java.awt.Dimension(320, 640));

        javax.swing.GroupLayout gameAreaCanvasLayout = new javax.swing.GroupLayout(gameAreaCanvas);
        gameAreaCanvas.setLayout(gameAreaCanvasLayout);
        gameAreaCanvasLayout.setHorizontalGroup(
            gameAreaCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 316, Short.MAX_VALUE)
        );
        gameAreaCanvasLayout.setVerticalGroup(
            gameAreaCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gameAreaCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(314, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addComponent(gameAreaCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JPanel gameAreaCanvas;
    // End of variables declaration//GEN-END:variables
}
