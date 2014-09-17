
package com.iconmaster.iconuscalc.manager;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalArguentCountException;
import com.iconmaster.iconuscalc.exception.IllegalNumberException;
import com.iconmaster.iconuscalc.gui.InputType;
import com.iconmaster.iconuscalc.gui.KeyInput;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.manager.dialog.Dialog;
import com.iconmaster.iconuscalc.manager.dialog.DialogEntry;
import com.iconmaster.iconuscalc.manager.dialog.EntryType;
import com.iconmaster.iconuscalc.manager.dialog.IButtonData;
import com.iconmaster.iconuscalc.parse.CodeExecutor;
import com.iconmaster.iconuscalc.render.DialogRenderer;
import com.iconmaster.iconuscalc.render.GridRenderer;
import com.iconmaster.iconuscalc.render.IScreenRenderer;
import com.iconmaster.iconuscalc.util.EntryStack;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class DialogManager implements IControlManager {
    public interface DialogResult {
        public void onConfirm(ArrayList returned);
    }
    
    public final DialogResult result;
    public DialogRenderer renderer;
    public Dialog dialog;
    public ArrayList<ArrayList<DialogEntry>> pages = new ArrayList<>();
    public boolean canCancel = true;
    public int pos = -1;
    public int page = 0;
    public int bpos = 0;
    public boolean onButton = false;
    public boolean editing = false;
    public boolean bkspc;
    
    private Window gui;
    
    public DialogManager(Dialog dialog, DialogResult result) {
        renderer = new DialogRenderer();
        renderer.manager = this;
        this.dialog = dialog;
        this.result = result;
        
        int es = 0;
        int maxes = GridRenderer.COLS-6;
        ArrayList<DialogEntry> cPage = new ArrayList<>();
        
        for (DialogEntry entry : dialog.entries) {
            if (es+entry.getSize()>maxes) {
                pages.add(cPage);
                cPage = new ArrayList<>();
                es = 0;
            }
            cPage.add(entry);
            es+=entry.getSize();
        }
        if (!cPage.isEmpty()) {
            pages.add(cPage);
        }
        
        nextEntry();
    }

    @Override
    public IScreenRenderer getRenderer() {
        return renderer;
    }

    @Override
    public void onKey(KeyInput e) {
        if (e.type==InputType.DOWN) {
            if (e.key == KeyInput.UP) {
                prevEntry();
                renderer.repaint();
            } else if (e.key == KeyInput.DOWN) {
                nextEntry();
                renderer.repaint();
            } else if (!onButton && e.key == KeyInput.LEFT && page>0) {
                page--;
                pos = -1;
                nextEntry();
                renderer.repaint();
            } else if (!onButton && e.key == KeyInput.RIGHT && page<pages.size()-1) {
                page++;
                pos = -1;
                nextEntry();
                renderer.repaint();
            }
            
            if (onButton && canCancel && (e.key == KeyInput.RIGHT || e.key == KeyInput.LEFT)) {
                bpos = bpos==0?1:0;
                renderer.repaint();
            }
        } else if (e.type==InputType.PRESS) {
            if (e.key==KeyInput.ESCAPE) {
                closeDialog(false);
            } else if (e.key==KeyInput.ENTER || e.key == KeyInput.BACK_SPACE) {
                bkspc = e.key == KeyInput.BACK_SPACE;
                if (onButton) {
                    if (!bkspc) {
                        closeDialog(bpos==0);
                    }
                } else {
                    switch (entry().type) {
                        case STRING:
                            startInput((String)entry().value);
                            break;
                        case EXPRESSION:
                            startInput(entry().value==null?null:(((Element)entry().value).getDisplayString()));
                            break;
                        case INTEGER:
                            startInput(entry().value==null?null:entry().value.toString());
                            break;
                        case BUTTON:
                            ((IButtonData)entry().value).onPress();
                            renderer.repaint();
                            break;
                        case COLOR:
                            ColorPickManager cpm = new ColorPickManager(((Color)entry().value),(c)->{
                                if (c!=null) {
                                    entry().value = c;
                                }
                            });
                            getParent().addManager(cpm);
                            renderer.repaint();
                            break;
                    }
                }
            }
        }
        
    }

    @Override
    public boolean showStatusBar() {
        return false;
    }
    
    @Override
    public void setParent(Window gui) {
        this.gui = gui;
    }

    @Override
    public Window getParent() {
        return gui;
    }
    
    public void nextEntry() {
        int sel = pos;
        ArrayList<DialogEntry> ents = pages.get(page);
        while (true) {
            sel++;
            if (sel>-1 && sel<ents.size() && ents.get(sel).type!=EntryType.TEXT) {
                pos = sel;
                onButton = false;
                break;
            }
            if (sel>ents.size()-1) {
                onButton = true;
                break;
            }
        }
    }
    
    public void prevEntry() {
        if (onButton) {
            pos++;
        }
        int sel = pos;
        ArrayList<DialogEntry> ents = pages.get(page);
        while (true) {
            sel--;
            if (sel>-1 && sel<ents.size() && ents.get(sel).type!=EntryType.TEXT) {
                pos = sel;
                onButton = false;
                break;
            }
            if (sel<0) {
                break;
            }
        }
    }
    
    public DialogEntry entry() {
        if (onButton) {
            return null;
        }
        return pages.get(page).get(pos);
    }
    
    public void closeDialog(boolean ret) {
        ArrayList a = null;
        if (ret) {
            a = new ArrayList();
            for (DialogEntry entry : dialog.entries) {
                if (entry.type!=EntryType.TEXT) {
                    a.add(entry.value);
                }
            }
        }
        result.onConfirm(a);
        getParent().closeManager();
    }
    
    public void startInput(String input) {
        if (bkspc) {
            input = null;
        }
        editing = true;
        InputManager man = new InputManager(entry().label.length()+3,pos+2,input==null?"":input,renderer.rows-entry().label.length()-6,(output)->{
            if (output!=null) {
                switch (entry().type) {
                    case STRING:
                        entry().value = output;
                        break;
                    case EXPRESSION:
                        EntryStack stack = new EntryStack();
                        try {
                            CodeExecutor.execute(output, stack, gui.getNamspace(), gui);
                        } catch (IconusCalcException ex) {
                            getParent().displayError(ex);
                        } finally {
                            if (stack.size()!=1) {
                                getParent().displayError(new IllegalArguentCountException(stack.size()<1?"Needs a value":"Too many values given"));
                            } else {
                                entry().value = stack.pop();
                            }
                        }
                        break;
                    case INTEGER:
                        try {
                            entry().value = Integer.parseInt(output);
                        } catch (NumberFormatException ex) {
                            getParent().displayError(new IllegalNumberException());
                        }
                        break;
                }
            }
            editing = false;
            renderer.repaint();
        });
        getParent().addManager(man);
    }
}
