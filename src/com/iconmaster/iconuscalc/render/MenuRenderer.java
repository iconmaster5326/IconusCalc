
package com.iconmaster.iconuscalc.render;

import com.iconmaster.iconuscalc.manager.IApplication;
import com.iconmaster.iconuscalc.manager.MenuManager;
import com.iconmaster.iconuscalc.manager.MenuManager.Menu;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author iconmaster
 */
public class MenuRenderer extends GridRenderer {
    public Menu menu;
    public int choice = 0;
    public int x;
    public int y;
    public int longestString;
    
    public MenuRenderer(Menu content) {
        this.menu = content;
        
        //calculate the longest element in the list
        for (Object obj : menu.content) {
            longestString = Math.max(longestString,getMenuItemString(obj).length()+2);
        }
    }

    @Override
    public void paint(Graphics g, int w, int h) {
        setWindowSize(w,h);
        
        drawBorderedRect(g,x,y,longestString,menu.content.size());
        highlight(g,x,y+choice,longestString,1);
        for (int i=0;i<menu.content.size();i++) {
            drawString(g,MenuManager.hotkeys.substring(i, i+1)+": "+getMenuItemString(menu.content.get(i)),x,y+i);
        }
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
