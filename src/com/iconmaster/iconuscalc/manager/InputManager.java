
package com.iconmaster.iconuscalc.manager;

import com.iconmaster.iconuscalc.render.IScreenRenderer;
import com.iconmaster.iconuscalc.render.TextGridRenderer;
import com.iconmaster.iconuscalc.gui.InputType;
import com.iconmaster.iconuscalc.gui.KeyInput;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.render.GridRenderer;

/**
 *
 * @author iconmaster
 */
public class InputManager implements IControlManager {
    private Window gui;

    @Override
    public boolean showStatusBar() {
        return false;
    }

    public interface InputResult {
        public void getResult(String input);
    }
    
    private final TextGridRenderer renderer;
    private final int x;
    private final int y;
    private String input;
    private int cursor = 0;
    private int offset = 0;
    private InputResult callback;
    private int maxsize;

    public InputManager(int x, int y, String initial, int fieldSize, InputResult callback) {
        this.renderer = new TextGridRenderer(GridRenderer.ROWS,GridRenderer.COLS);
        this.x = x;
        this.y = y;
        this.input = initial;
        this.callback = callback;
        this.maxsize = fieldSize;
        this.cursor = initial.length()-1;
    }
    
    public void renderInput() {
        clearInput();
        if (!input.isEmpty()) {
            renderer.putString(input.substring(Math.max(-offset,0), Math.min(-offset+maxsize,input.length())), x, y);
        }
        renderer.moveCursor(x+cursor+offset , y);
    }
    
    public void clearInput() {
        renderer.clearScreen();
    }
    
    @Override
    public void onKey(KeyInput e) {
        if (e.type==InputType.PRESS) {
            if (e.key==KeyInput.BACK_SPACE) {
                if (cursor >= 0) {
                    input = input.substring(0, cursor) + input.substring(cursor+1);
                    if (cursor>=0) {
                        backupCursor();
                    }
                }
            } else if (e.key==KeyInput.DELETE) {
                if (cursor < input.length()-1) {
                    input = input.substring(0, cursor+1) + input.substring(cursor+2);
                    if (cursor>input.length()) {
                        backupCursor();
                    }
                }
            } else if (e.key==KeyInput.ENTER) {
                endInput();
            } else if (e.key==KeyInput.ESCAPE) {
                input = null;
                endInput();
            } else if (e.key!=KeyInput.UNDEFINED) {
                if (input.isEmpty()) {
                    input += e.key;
                    cursor = 0;
                } else {
                    input = input.substring(0, cursor+1) + e.key + input.substring(cursor+1);
                    advanceCursor();
                }
                //input+=e.getKeyChar();
                
            }
        } else if (e.type==InputType.DOWN) {
            if (e.key==KeyInput.LEFT) {
                if (cursor>=0) {
                    backupCursor();
                }
            } else if (e.key==KeyInput.RIGHT) {
                if (cursor<input.length()-1) {
                    advanceCursor();
                }
            }
        }
        renderInput();
    }
    
    public void endInput() {
        gui.closeManager();
        callback.getResult(input);
    }

    @Override
    public IScreenRenderer getRenderer() {
        return renderer;
    }
    
    public void advanceCursor() {
        cursor++;
        if (cursor - offset >= maxsize) {
            offset--;
        }
    }
    
    public void backupCursor() {
        cursor--;
        if (cursor + offset < -1) {
            offset+=6;
            if (offset>1)
                offset = 0;
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
}
