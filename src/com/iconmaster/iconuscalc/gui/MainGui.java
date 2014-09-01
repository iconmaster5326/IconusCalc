
package com.iconmaster.iconuscalc.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author iconmaster
 */
public class MainGui extends javax.swing.JFrame {
    public Window window = null;
    /**
     * Creates new form MainGui
     */
    public MainGui() {
        initComponents();
        registerKeyHooks();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new MainGuiPanel(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setName("mainPanel"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.getAccessibleContext().setAccessibleName("");
        mainPanel.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables

    public void registerKeyHooks() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                handleKeyPress(e,InputType.PRESS);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e,InputType.DOWN);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyPress(e,InputType.UP);
            }
        });
    }
    
    public void handleKeyPress(KeyEvent e, InputType type) {
        window.onKey(new KeyInput(type,e.getKeyChar()));
    }
}
