
package com.iconmaster.iconuscalc.manager;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalArguentCountException;
import com.iconmaster.iconuscalc.exception.IllegalNumberException;
import com.iconmaster.iconuscalc.gui.InputType;
import com.iconmaster.iconuscalc.gui.KeyInput;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.parse.CodeExecutor;
import com.iconmaster.iconuscalc.render.ColorPickRenderer;
import com.iconmaster.iconuscalc.render.IScreenRenderer;
import com.iconmaster.iconuscalc.util.EntryStack;
import java.awt.Color;

/**
 *
 * @author iconmaster
 */
public class ColorPickManager implements IControlManager {
    public interface ColorPickResult {
        public void onConfirm(Color returned);
    }
    
    public final ColorPickResult result;
    public ColorPickRenderer renderer;
    public Color color;
    public int pos = 0;
    public int bpos = 0;
    public boolean editing = false;
    
    private Window gui;
    
    public ColorPickManager(Color initC, ColorPickResult result) {
        renderer = new ColorPickRenderer();
        renderer.manager = this;
        this.color = initC;
        this.result = result;
    }

    @Override
    public IScreenRenderer getRenderer() {
        return renderer;
    }

    @Override
    public void onKey(KeyInput e) {
        if (e.type == InputType.PRESS) {
            if (e.key == KeyInput.ESCAPE) {
                executeResult(false);
            } else if (e.key == KeyInput.ENTER) {
                if (pos==4) {
                    executeResult(bpos==0);
                } else {
                    editing = true;
                    String str = "";
                    switch (pos) {
                        case (0):
                            str = Integer.toString(color.getRed());
                            break;
                        case (1):
                            str = Integer.toString(color.getGreen());
                            break;
                        case (2):
                            str = Integer.toString(color.getBlue());
                            break;
                        case (3):
                            str = Integer.toString(color.getAlpha());
                            break;
                    }
                    InputManager man = new InputManager(renderer.cols/2+10,pos+5,str,3,(output)->{
                        if (output!=null) {
                            try {
                                int num = Integer.parseUnsignedInt(output);
                                num = Math.max(0,Math.min(255,num));
                                
                                switch (pos) {
                                    case (0):
                                        color = new Color(num,color.getGreen(),color.getBlue(),color.getAlpha());
                                        break;
                                    case (1):
                                        color = new Color(color.getRed(),num,color.getBlue(),color.getAlpha());
                                        break;
                                    case (2):
                                        color = new Color(color.getRed(),color.getGreen(),num,color.getAlpha());
                                        break;
                                    case (3):
                                        color = new Color(color.getRed(),color.getGreen(),color.getBlue(),num);
                                        break;
                                }
                            } catch (NumberFormatException ex) {
                                getParent().displayError(new IllegalNumberException());
                            }
                        }
                        editing = false;
                        renderer.repaint();
                    });
                    getParent().addManager(man);
                }
            }
        } else if (e.type == InputType.DOWN) {
            if (e.key == KeyInput.UP && pos>0) {
                pos--;
                renderer.repaint();
            } else if (e.key == KeyInput.DOWN && pos<4) {
                pos++;
                renderer.repaint();
            } else if (e.key == KeyInput.LEFT) {
                switch (pos) {
                    case 0:
                        color = new Color(Math.max(0,color.getRed()-1),color.getGreen(),color.getBlue(),color.getAlpha());
                        break;
                    case 1:
                        color = new Color(color.getRed(),Math.max(0,color.getGreen()-1),color.getBlue(),color.getAlpha());
                        break;
                    case 2:
                        color = new Color(color.getRed(),color.getGreen(),Math.max(0,color.getBlue()-1),color.getAlpha());
                        break;
                    case 3:
                        color = new Color(color.getRed(),color.getGreen(),color.getBlue(),Math.max(0,color.getAlpha()-1));
                        break;
                    case 4:
                        bpos = bpos==0?1:0;
                        break;
                }
                renderer.repaint();
            } else if (e.key == KeyInput.RIGHT) {
                switch (pos) {
                    case 0:
                        color = new Color(Math.min(255,color.getRed()+1),color.getGreen(),color.getBlue(),color.getAlpha());
                        break;
                    case 1:
                        color = new Color(color.getRed(),Math.min(255,color.getGreen()+1),color.getBlue(),color.getAlpha());
                        break;
                    case 2:
                        color = new Color(color.getRed(),color.getGreen(),Math.min(255,color.getBlue()+1),color.getAlpha());
                        break;
                    case 3:
                        color = new Color(color.getRed(),color.getGreen(),color.getBlue(),Math.min(255,color.getAlpha()+1));
                        break;
                    case 4:
                        bpos = bpos==0?1:0;
                        break;
                }
                renderer.repaint();
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
    
    public void executeResult(boolean res) {
        getParent().closeManager();
        result.onConfirm(res?color:null);
    }
}
