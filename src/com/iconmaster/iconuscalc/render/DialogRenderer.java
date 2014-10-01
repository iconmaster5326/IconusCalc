
package com.iconmaster.iconuscalc.render;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.gui.ColorScheme;
import com.iconmaster.iconuscalc.manager.DialogManager;
import com.iconmaster.iconuscalc.manager.dialog.DialogEntry;
import com.iconmaster.iconuscalc.util.StringUtils;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class DialogRenderer extends GridRenderer {
	public DialogManager manager;

	@Override
	public void paint(Graphics g, int w, int h) {
		this.setWindowSize(w, h);
		
		this.drawBorderedRect(g, 1, 1, rows-2, cols-2);
		
		ArrayList<DialogEntry> page = manager.pages.get(manager.page);
		for (int i=0;i<page.size();i++) {
			DialogEntry e = page.get(i);
			drawEntry(g,2+i,e);
		}
		
		this.drawBorderedRect(g, 3, cols-3, 4, 1);
		this.drawString(g, "OK", 4, cols-3);
		if (manager.onButton && manager.bpos==0) {
			this.highlight(g, 3, cols-3, 4, 1);
		}
		if (manager.canCancel) {
			this.drawBorderedRect(g, rows-10, cols-3, 8, 1);
			this.drawStringRightJustified(g, "Cancel", rows-3, cols-3);
			if (manager.onButton && manager.bpos==1) {
				this.highlight(g, rows-10, cols-3, 8, 1);
			}
		}
		if (manager.pages.size()>1) {
			this.drawStringCentered(g, "Page "+(manager.page+1)+"/"+manager.pages.size(), rows/2, cols-3);
		}
	}
	
	public void drawEntry(Graphics g, int y, DialogEntry entry) {
		switch (entry.type) {
			case TEXT:
				drawString(g,entry.label,2,y);
				break;
			case STRING:
				drawString(g,entry.label,2,y);
				drawBorderedRect(g, 3+entry.label.length(), y, ROWS-entry.label.length()-6, 1);
				if (entry.value!=null && !(manager.entry()==entry && manager.editing)) {
					drawString(g, StringUtils.truncateString((String)entry.value,ROWS-entry.label.length()-6),3+entry.label.length(),y);
				}
				if (manager.entry()==entry) {
					highlight(g, 3+entry.label.length(), y, ROWS-entry.label.length()-6, 1);
				}
				break;
			case EXPRESSION:
				drawString(g,entry.label,2,y);
				drawBorderedRect(g, 3+entry.label.length(), y, ROWS-entry.label.length()-6, 1);
				if (entry.value!=null && !(manager.entry()==entry && manager.editing)) {
					drawString(g, StringUtils.truncateString(((Element)entry.value).getDisplayString(),ROWS-entry.label.length()-6),3+entry.label.length(),y);
				}
				if (manager.entry()==entry) {
					highlight(g, 3+entry.label.length(), y, ROWS-entry.label.length()-6, 1);
				}
				break;
			case INTEGER:
				drawString(g,entry.label,2,y);
				drawBorderedRect(g, 3+entry.label.length(), y, ROWS-entry.label.length()-6, 1);
				if (entry.value!=null && !(manager.entry()==entry && manager.editing)) {
					drawString(g, StringUtils.truncateString(StringUtils.renderNumber(((Integer)entry.value).doubleValue()),ROWS-entry.label.length()-6),3+entry.label.length(),y);
				}
				if (manager.entry()==entry) {
					highlight(g, 3+entry.label.length(), y, ROWS-entry.label.length()-6, 1);
				}
				break;
			case BUTTON:
				drawBorderedRect(g,2,y,entry.label.length()+2,1);
				drawString(g,entry.label,3,y);
				if (manager.entry()==entry) {
					highlight(g,2,y,entry.label.length()+2,1);
				}
				break;
			case COLOR:
				drawString(g,entry.label,2,y);
				Color c = entry.value==null?new Color(0,0,0,0):(Color)entry.value;
				drawBorderedRect(g,3+entry.label.length(),y,2,1, c, ColorScheme.bd);
				drawString(g,StringUtils.truncateString(c.getRed()+", "+c.getGreen()+", "+c.getBlue()+", "+c.getAlpha(),ROWS-entry.label.length()-8),6+entry.label.length(),y);
				if (manager.entry()==entry) {
					highlight(g,6+entry.label.length(),y,ROWS-entry.label.length()-8,1);
				}
				break;
		}
	}
}
