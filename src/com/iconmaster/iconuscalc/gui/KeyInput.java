
package com.iconmaster.iconuscalc.gui;

import java.awt.event.KeyEvent;

/**
 *
 * @author iconmaster
 */
public class KeyInput {
    public static final char BACK_SPACE = KeyEvent.VK_BACK_SPACE;
    public static final char ENTER = KeyEvent.VK_ENTER;
    public static final char LEFT = KeyEvent.VK_LEFT;
    public static final char RIGHT = KeyEvent.VK_RIGHT;
    public static final char UP = KeyEvent.VK_UP;
    public static final char DOWN = KeyEvent.VK_DOWN;
    public static final char ESCAPE = KeyEvent.VK_ESCAPE;
    public static final char UNDEFINED = KeyEvent.CHAR_UNDEFINED;
    public static final char DELETE = KeyEvent.VK_DELETE;
    
    public InputType type;
    public char key;
    public char rawKey;

    KeyInput(InputType type, char key) {
        this.type = type;
        this.key = key;
        this.rawKey = key;
    }
}
