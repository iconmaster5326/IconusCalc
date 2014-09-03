
package com.iconmaster.iconuscalc.manager;

import com.iconmaster.iconuscalc.gui.InputType;
import com.iconmaster.iconuscalc.IconusCalc;
import com.iconmaster.iconuscalc.element.Entry;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.function.IQuickCommand;
import com.iconmaster.iconuscalc.gui.KeyInput;
import com.iconmaster.iconuscalc.render.IScreenRenderer;
import com.iconmaster.iconuscalc.render.TextGridRenderer;
import com.iconmaster.iconuscalc.parse.CodeExecutor;
import com.iconmaster.iconuscalc.util.EntryStack;
import com.iconmaster.iconuscalc.util.StringUtils;
import java.util.List;

/**
 *
 * @author iconmaster
 */
public class HomeScreenManager implements IControlManager,IApplication {
    public final TextGridRenderer renderer = new TextGridRenderer();
    
    public InputManager input;
    
    public EntryStack stack = new EntryStack();
    
    public HomeScreenManager() {
        renderScreen();
    }

    @Override
    public IScreenRenderer getRenderer() {
        return renderer;
    }

    @Override
       public void onKey(KeyInput e) {
        if (e.type==InputType.PRESS) {
            if (checkCommandKeys(e)) {
                return;
            }
            
            if (e.key==KeyInput.BACK_SPACE) {
                stack.pop();
            } else if (e.key==KeyInput.ENTER) {
                Entry peek = stack.peekEntry();
                if (peek!=null) {
                    stack.push(new Entry("",peek.getAnswer()));
                }
            } else if (e.key==KeyInput.ESCAPE) {
                MenuManager.openAppMenu(renderer.getParent(),new String[] {"Clear Stack"},new MenuManager.MenuResult() {
                    @Override
                    public void getResult(MenuManager.Menu menu, int id, Object object) {
                        if (id==0) {
                            stack.clear();
                        }
                    }
                });
            } else if (e.key!=KeyInput.UNDEFINED) {
                input = new InputManager(renderer,0,renderer.cols-1,Character.toString(e.key),new InputManager.InputResult() {
                    @Override
                    public void getResult(String got) {
                        if (got!= null && !got.isEmpty()) {
                            //Element[] elements = new Element[0];
                            try {
                                CodeExecutor.execute(got,stack,renderer.getParent().getNamspace());
                            } catch (IconusCalcException ex) {
                                renderer.getParent().displayError(ex);
                            }
//                            boolean first = true;
//                            for (Element e : elements) {
//                                stack.push(new Entry(first?got:"",e));
//                                first = false;
//                            }
                        }
                        
                        input = null;
                    }
                    
                });
                renderer.getParent().addManager(input);
            }
        }
        
        renderScreen();
    }
    
    public void renderScreen() {
        renderer.clearScreen();
        
        int offset = 0;
        if (input!=null) {
            input.renderInput();
            offset = 1;
        }
        
        
        for (int pos=1;pos<=renderer.cols-offset;pos++) {
            renderer.drawString(pos+":", 0, renderer.cols-pos-offset);
        }
        
        List<Entry> list = stack.toEntryList();
        int i=renderer.cols-offset;
        for (Entry item : list) {
            i--;
            
            String entryString = StringUtils.truncateString(item.getEntry()==null?"":item.getEntry(), 14);
            renderer.drawString(entryString, 4, i);
            
            String answerString = StringUtils.truncateString(item.getAnswer().getDisplayString(), 14);
            renderer.drawStringRightJustified(answerString, renderer.rows-1, i);
        }
    }

    private boolean checkCommandKeys(KeyInput e) {
        for (Function fn : IconusCalc.getGlobalNamespace().functions.values()) {
            if (fn instanceof IQuickCommand && ((IQuickCommand)fn).isCommandKey(e)) {
                try {
                    CodeExecutor.executeFunction(fn, stack, renderer.getParent().getNamspace());
                } catch (IconusCalcException ex) {
                    renderer.getParent().displayError(ex);
                }
                renderScreen();
                return true;
            }
        }
        return false;
    }

    @Override
    public IControlManager getNewWindow() {
        return new HomeScreenManager();
    }
    
    @Override
    public String getAppName() {
        return "Scratchpad";
    }
}
