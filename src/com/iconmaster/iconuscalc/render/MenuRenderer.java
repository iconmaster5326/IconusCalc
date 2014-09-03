
package com.iconmaster.iconuscalc.render;

import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.manager.IApplication;
import com.iconmaster.iconuscalc.manager.MenuManager.Menu;
import com.iconmaster.iconuscalc.util.RenderUtils;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author iconmaster
 */
public class MenuRenderer implements IScreenRenderer {
    Window gui;
    public Menu menu;
    public int choice = 0;
    public int x;
    public int y;
    public int longestString;
    
    public MenuRenderer(Menu content) {
        this.menu = content;
        
        //calculate the longest element in the list
        for (Object obj : menu.content) {
            longestString = Math.max(longestString,getMenuItemString(obj).length());
        }
    }

    @Override
    public void paint(Graphics g, int w, int h) {
        g.setFont(RenderUtils.getFont(w/TextGridRenderer.ROWS,h/TextGridRenderer.COLS));
        g.setColor(Color.WHITE);
        g.fillRect((x*(w/TextGridRenderer.ROWS)), (y*(h/TextGridRenderer.COLS))+1, (w/TextGridRenderer.ROWS)*longestString, y+(h/TextGridRenderer.COLS)*menu.content.size()+3);
        g.setColor(new Color(0,0,0,128));
        g.fillRect((x*(w/TextGridRenderer.ROWS)), (y*(h/TextGridRenderer.COLS))+(h/TextGridRenderer.COLS)*choice+2, (w/TextGridRenderer.ROWS)*longestString, y+(h/TextGridRenderer.COLS)+2);
        g.setColor(Color.BLACK);
        g.drawRect((x*(w/TextGridRenderer.ROWS)), (y*(h/TextGridRenderer.COLS))+1, (w/TextGridRenderer.ROWS)*longestString, (h/TextGridRenderer.COLS)*menu.content.size()+3);
        for (int i=0;i<menu.content.size();i++) {
            g.drawString(getMenuItemString(menu.content.get(i)), (x*(w/TextGridRenderer.ROWS))+2, (y*(h/TextGridRenderer.COLS))+(h/TextGridRenderer.COLS)*(i+1)+2);
        }
    }

    @Override
    public void setParent(Window gui) {
        this.gui = gui;
    }

    @Override
    public Window getParent() {
        return gui;
    }
    
    public static String getMenuItemString(Object obj) {
        if (obj instanceof String) {
            return (String)obj;
        } else if (obj instanceof Menu) {
            return ((Menu)obj).name;
        } else if (obj instanceof IApplication) {
            return ((IApplication)obj).getAppName();
        } else {
            return obj.toString();
        }
    }
    
}
