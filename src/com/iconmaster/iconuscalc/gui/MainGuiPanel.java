
package com.iconmaster.iconuscalc.gui;

import com.iconmaster.iconuscalc.manager.IControlManager;
import com.iconmaster.iconuscalc.render.IScreenRenderer;
import com.iconmaster.iconuscalc.render.TextGridRenderer;
import com.iconmaster.iconuscalc.util.RenderUtils;
import java.util.Stack;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author iconmaster
 */
public class MainGuiPanel extends javax.swing.JPanel {
	//private IScreenRenderer renderer;
	private MainGui gui;

	/**
	 * Creates new form MainGuiPanel
	 */
	public MainGuiPanel(MainGui gui) {
		this.gui = gui;
		initComponents();
		//this.setRenderer(new TextGridRenderer());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int w = getWidth();
		int h = getHeight();
		
		g.setColor(ColorScheme.bk);
		g.fillRect(0, 0, w, h);
		
		Stack<IControlManager> managers = gui.window.getManagers();
		
		boolean showStatusBar = false;
		for (IControlManager manager : managers) {
			if (manager.showStatusBar()) {
				showStatusBar = true;
			}
		}
		
		int SBAR_H = h/25;
		
		if (showStatusBar) {
			drawStatusBar(g, w, SBAR_H);
			g.translate(0, (SBAR_H-2));
		}

		for (IControlManager manager : managers) {
			IScreenRenderer r = manager.getRenderer();
			if (showStatusBar) {
				r.paint(g, w, h-SBAR_H);
			} else {
				r.paint(g, w, h);
			}
		}
		
		if (showStatusBar) {
			g.translate(0, -(SBAR_H-2));
		}
	}
	
	public void drawStatusBar(Graphics g, int w, int h) {
		g.setColor(ColorScheme.bd);
		g.setFont(RenderUtils.getFont(h, h));
		g.drawString(gui.window.getNamspace().getPathName(), 0, h);
		g.drawString(new SimpleDateFormat("hh:mm a").format(new Date(System.currentTimeMillis())) , w-h*7-6, h);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 400, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 300, Short.MAX_VALUE)
		);
	}// </editor-fold>//GEN-END:initComponents


	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
}
