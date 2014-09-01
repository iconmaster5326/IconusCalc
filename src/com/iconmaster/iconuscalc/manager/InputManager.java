
package com.iconmaster.iconuscalc.manager;

import com.iconmaster.iconuscalc.render.IScreenRenderer;
import com.iconmaster.iconuscalc.render.TextGridRenderer;
import com.iconmaster.iconuscalc.manager.IControlManager;
import com.iconmaster.iconuscalc.manager.InputType;
import java.awt.event.KeyEvent;

/**
 *
 * @author iconmaster
 */
public class InputManager implements IControlManager {
    public interface InputResult {
        public void getResult(String input);
    }
    
    private final TextGridRenderer renderer;
    private final int x;
    private final int y;
    private String input;
    private int cursor = 0;
    private InputResult callback;
    
    public InputManager(TextGridRenderer renderer,int x, int y, String initial, InputResult callback) {
        this.renderer = new TextGridRenderer(renderer.rows,renderer.cols);
        this.x = x;
        this.y = y;
        this.input = initial;
        this.callback = callback;
    }
    
    public InputManager(TextGridRenderer renderer,int x, int y, InputResult callback) {
        this(renderer,x,y,"",callback);
    }
    
    public void renderInput() {
        clearInput();
        renderer.drawString(input, x, y);
        renderer.moveCursor(x+cursor , y);
    }
    
    public void clearInput() {
        renderer.drawString("                         ", x, y);
    }
    
    @Override
    public void onKey(KeyEvent e, InputType type) {
        if (type==InputType.PRESS) {
            if (e.getKeyChar()==KeyEvent.VK_BACK_SPACE) {
                if (cursor >= 0) {
                    input = input.substring(0, cursor) + input.substring(cursor+1);
                    if (cursor>=0) {
                        cursor--;
                    }
                }
            } else if (e.getKeyChar()==KeyEvent.VK_DELETE) {
                if (cursor < input.length()-1) {
                    input = input.substring(0, cursor+1) + input.substring(cursor+2);
                    if (cursor>input.length()) {
                        cursor--;
                    }
                }
            } else if (e.getKeyChar()==KeyEvent.VK_ENTER) {
                endInput();
            } else if (e.getKeyChar()==KeyEvent.VK_ESCAPE) {
                input = null;
                endInput();
            } else if (e.getKeyChar()!=KeyEvent.CHAR_UNDEFINED) {
                input = input.substring(0, cursor+1) + e.getKeyChar() + input.substring(cursor+1);
                //input+=e.getKeyChar();
                cursor++;
            }
        } else if (type==InputType.DOWN) {
            if (e.getKeyCode()==KeyEvent.VK_LEFT) {
                if (cursor>=0) {
                    cursor--;
                }
            } else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
                if (cursor<input.length()-1) {
                    cursor++;
                }
            }
        }
        renderInput();
    }
    
    public void endInput() {
        renderer.getParent().closeManager();
        callback.getResult(input);
    }

    @Override
    public IScreenRenderer getRenderer() {
        return renderer;
    }
}
