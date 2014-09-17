
package com.iconmaster.iconuscalc;

import com.iconmaster.iconuscalc.file.GlobalNamespace;
import com.iconmaster.iconuscalc.gui.MainGui;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.manager.FileManager;
import com.iconmaster.iconuscalc.manager.HomeScreenManager;
import com.iconmaster.iconuscalc.manager.IApplication;
import com.iconmaster.iconuscalc.manager.IControlManager;
import com.iconmaster.iconuscalc.math.Simplifier;
import com.iconmaster.iconuscalc.parse.Parser;
import com.iconmaster.iconuscalc.render.GridRenderer;
import com.iconmaster.iconuscalc.tokenize.Tokenizer;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class IconusCalc {
    private static final GlobalNamespace homeDir = GlobalNamespace.createGlobalNamespace();
    private static final ArrayList<IApplication> apps = new ArrayList<>();
    
    public static final ArrayList<Window> windows = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //setup
        Tokenizer.addDefaultHandlers();
        Parser.addDefaultHandlers();
        Simplifier.registerRules();

        registerApp(new HomeScreenManager());
        registerApp(new FileManager());
                
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Window(new HomeScreenManager());
        });
    }
    
    public static GlobalNamespace getGlobalNamespace() {
        return homeDir;
    }
    
    public static void registerApp(IApplication app) {
        apps.add(app);
    }
    
    public static ArrayList<IApplication> getApps() {
        return (ArrayList<IApplication>) apps.clone();
    }
    
    public static void repaintAll() {
        for (Window w : windows) {
            w.repaint();
        }
    }
    
    public static void renameAll() {
        for (Window w : windows) {
            w.nameWindow(w.getManagers().lastElement());
        }
    }
    
    public static void resizeAll(int w, int h) {
        for (Window win : windows) {
            for (IControlManager man : win.getManagers()) {
                if (man.getRenderer() instanceof GridRenderer) {
                    ((GridRenderer)man.getRenderer()).setSize(w, h);
                    ((GridRenderer)man.getRenderer()).onResize();
                }
            }
            win.repaint();
        }
    }
}
