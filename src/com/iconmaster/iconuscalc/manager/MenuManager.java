
package com.iconmaster.iconuscalc.manager;

import com.iconmaster.iconuscalc.IconusCalc;
import com.iconmaster.iconuscalc.gui.InputType;
import com.iconmaster.iconuscalc.gui.KeyInput;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.render.IScreenRenderer;
import com.iconmaster.iconuscalc.render.MenuRenderer;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author iconmaster
 */
public class MenuManager implements IControlManager {
    public static interface MenuResult {
        public void getResult(Menu menu, int id, Object object);
    }
    
    public static class Menu {
        public ArrayList content;
        public String name;
        
        public Menu(String name, Object... names) {
            this.name = name;
            this.content = new ArrayList();
            for (int i=0;i<names.length;i++) {
                if (names[i] instanceof Object[]) {
                    for (Object obj : (Object[])names[i]) {
                        content.add(obj);
                    }
                } else if (names[i] instanceof Collection) {
                    content.addAll((Collection) names[i]);
                } else {
                    content.add(names[i]);
                }
            }
        }
    }
        
    public MenuRenderer renderer;
    public Menu menu;
    public MenuResult callback;
    
    public MenuManager(Menu menu, int x, int y, MenuResult result) {
        renderer = new MenuRenderer(menu);
        renderer.x = x;
        renderer.y = y;
        this.menu = menu;
        this.callback = result;
    }

    @Override
    public IScreenRenderer getRenderer() {
        return renderer;
    }

    @Override
    public void onKey(KeyInput e) {
        if (e.type==InputType.DOWN && e.key==KeyInput.DOWN) {
            if (renderer.choice==menu.content.size()-1) {
                renderer.choice = 0;
            } else {
                renderer.choice++;
            }
            renderer.getParent().repaint();
        }
        
        if (e.type==InputType.DOWN && e.key==KeyInput.UP) {
            if (renderer.choice==0) {
                renderer.choice = menu.content.size()-1;
            } else {
                renderer.choice--;
            }
            renderer.getParent().repaint();
        }
        
        if (e.type==InputType.PRESS && e.key==KeyInput.ENTER) {
            executeMenuChoice(menu,renderer.choice);
            renderer.getParent().repaint();
        }
        
        if (e.type==InputType.DOWN && e.key==KeyInput.RIGHT && menu.content.get(renderer.choice) instanceof Menu) {
            executeMenuChoice(menu,renderer.choice);
            renderer.getParent().repaint();
        }
        
        if (e.type==InputType.DOWN && e.key==KeyInput.LEFT) {
            renderer.getParent().closeManager();
            renderer.getParent().repaint();
        }
        
        if (e.type==InputType.PRESS && e.key==KeyInput.ESCAPE) {
            renderer.getParent().closeManager();
            renderer.getParent().repaint();
        }
    }
    
    public void executeMenuChoice(Menu menu, int choice) {
            if (menu.content.get(choice) instanceof Menu) {
                final MenuManager man;
                man = new MenuManager((Menu) menu.content.get(choice),renderer.x+renderer.longestString+1,renderer.y,new MenuResult() {

                    @Override
                    public void getResult(Menu menu, int id, Object object) {
                        if (!(object instanceof Menu)) {
                            callback.getResult(menu, id, object);
                            renderer.getParent().closeManager();
                            renderer.getParent().repaint();
                        }
                    }

                });
                renderer.getParent().addManager(man);
            } else {
                callback.getResult(menu, renderer.choice, menu.content.get(renderer.choice));
                renderer.getParent().closeManager();
                renderer.getParent().repaint();
            }
    }
    
    public static MenuManager openAppMenu(final Window window, final String[] customs, final MenuResult result) {
        Menu menu = new Menu("ROOT",new Menu("Open App...",IconusCalc.getApps()),customs,"Close App");
        MenuManager man = new MenuManager(menu,0,0,new MenuResult() {

            @Override
            public void getResult(Menu menu, int id, Object object) {
                //System.out.println(object);
                if (menu.name.equals("Open App...")) {
                    Window win = new Window(((IApplication)object).getNewWindow());
                } else if (id > 0 && id < 2+customs.length-1) {
                    result.getResult(null, id-1, object);
                } else if (id==1+customs.length) {
                    if (IconusCalc.windowsOpen==1) {
                        //make confirm dialog here
                    }
                    window.close();
                }
            }
            
        });
        window.addManager(man);
        return man;
    }
    
}
