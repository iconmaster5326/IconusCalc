
package com.iconmaster.iconuscalc.manager;

import com.iconmaster.iconuscalc.IconusCalc;
import com.iconmaster.iconuscalc.gui.InputType;
import com.iconmaster.iconuscalc.gui.KeyInput;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.manager.dialog.Dialog;
import com.iconmaster.iconuscalc.manager.dialog.DialogEntry;
import com.iconmaster.iconuscalc.manager.dialog.EntryType;
import com.iconmaster.iconuscalc.render.GridRenderer;
import com.iconmaster.iconuscalc.render.IScreenRenderer;
import com.iconmaster.iconuscalc.render.MenuRenderer;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author iconmaster
 */
public class MenuManager implements IControlManager {
    public static final String hotkeys = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    private Window gui;

    @Override
    public boolean showStatusBar() {
        return false;
    }
    
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
            gui.repaint();
        }
        
        if (e.type==InputType.DOWN && e.key==KeyInput.UP) {
            if (renderer.choice==0) {
                renderer.choice = menu.content.size()-1;
            } else {
                renderer.choice--;
            }
            gui.repaint();
        }
        
        if (e.type==InputType.PRESS && e.key==KeyInput.ENTER) {
            executeMenuChoice(menu,renderer.choice);
            gui.repaint();
        }
        
        if (e.type==InputType.DOWN && e.key==KeyInput.RIGHT && menu.content.get(renderer.choice) instanceof Menu) {
            executeMenuChoice(menu,renderer.choice);
            gui.repaint();
        }
        
        if (e.type==InputType.DOWN && e.key==KeyInput.LEFT) {
            gui.closeManager();
            gui.repaint();
        }
        
        if (e.type==InputType.PRESS && e.key==KeyInput.ESCAPE) {
            gui.closeManager();
            gui.repaint();
        } else if (e.type==InputType.PRESS && hotkeys.contains(""+e.key)) {
            int hotkey = hotkeys.indexOf(e.key);
            if (hotkey<menu.content.size()) {
                executeMenuChoice(menu, hotkey);
                gui.repaint();
            }
        }
    }
    
    public void executeMenuChoice(Menu menu, int choice) {
            if (menu.content.get(choice) instanceof Menu) {
                final MenuManager man;
                man = new MenuManager((Menu) menu.content.get(choice),renderer.x+renderer.longestString+1,renderer.y,new MenuResult() {

                    @Override
                    public void getResult(Menu menu, int id, Object object) {
                        if (!(object instanceof Menu)) {
                            gui.closeManager();
                            callback.getResult(menu, id, object);
                            gui.repaint();
                        }
                    }

                });
                gui.addManager(man);
            } else {
                gui.closeManager();
                callback.getResult(menu, choice, menu.content.get(choice));
                gui.repaint();
            }
    }
    
    public static MenuManager openAppMenu(final Window window, final String[] customs, final MenuResult result) {
        Menu menu = new Menu("ROOT",new Menu("Open App…",IconusCalc.getApps()),customs,"Options…","Close App");
        MenuManager man = new MenuManager(menu,0,0, (Menu menu1, int id, Object object) -> {
            //System.out.println(object);
            if (menu1.name.equals("Open App…")) {
                Window win = new Window(((IApplication)object).getNewWindow());
            } else if (id > 0 && id < 2+customs.length-1) {
                result.getResult(null, id-1, object);
            } else if (id==1+customs.length) {
                            DialogManager dlg = new DialogManager(new Dialog(new DialogEntry(EntryType.TEXT,"System Settings"),new DialogEntry(EntryType.TEXT,""),new DialogEntry(EntryType.INTEGER,"Screen X:",GridRenderer.ROWS),new DialogEntry(EntryType.TEXT,""),new DialogEntry(EntryType.INTEGER,"Screen Y:",GridRenderer.COLS)),(ret)->{
                                    if (ret!=null) {
                                    Integer sx = (Integer)ret.get(0);
                                    Integer sy = (Integer)ret.get(1);

                                    if (sx!=null && sy!=null && sx>=16 && sy>=10) {
                                        GridRenderer.ROWS = sx;
                                        GridRenderer.COLS = sy;

                                        IconusCalc.resizeAll(GridRenderer.ROWS, GridRenderer.COLS);
                                    }
                                }
                            });
                            window.addManager(dlg);
            } else if (id==2+customs.length) {
                if (IconusCalc.windows.size()==1) {
                    final SimpleDialogManager dlg = new SimpleDialogManager("Are you sure you want to exit IconusCalc?",new String[] {"Yes","No"}, (int id1, String str) -> {
                        if (id1==0) {
                            window.close();
                        }
                    });
                    window.addManager(dlg);
                } else {
                    window.close();
                }
            }
        });
        window.addManager(man);
        return man;
    }
    
    public static MenuManager openMenu(Menu menu, int x, int y,  Window window, MenuResult result) {
        MenuManager man = new MenuManager(menu,x,y,result);
        window.addManager(man);
        return man;
    }
    
    @Override
    public void setParent(Window gui) {
        this.gui = gui;
    }

    @Override
    public Window getParent() {
        return gui;
    }
}
