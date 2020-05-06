package de.undertrox.orihimemod.keybind;

import java.awt.event.KeyEvent;

public class Keybind {

    public static final int BUTTON = 0;
    public static final int CHECKBOX = 1;

    private int componentID;
    private int keyCode;
    private boolean shift;
    private boolean ctrl;
    private boolean alt;
    private int type;

    public Keybind(int type, int componentID, int keyCode, boolean shift, boolean ctrl, boolean alt) {
        this.type = type;
        this.componentID = componentID;
        this.keyCode = keyCode;
        this.shift = shift;
        this.ctrl = ctrl;
        this.alt = alt;
    }

    public Keybind(int type, int componentID, int keyCode) {
        this(type, componentID, keyCode, false, false, false);
    }

    public Keybind(int componentID, int keyCode, boolean shift, boolean ctrl, boolean alt) {
        this(BUTTON, componentID, keyCode, shift, ctrl, alt);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if (ctrl) b.append("CTRL+");
        if(alt) b.append("ALT+");
        if(shift) b.append("SHIFT+");
        b.append(KeyEvent.getKeyText(keyCode));
        return b.toString();
    }

    public Keybind(int componentID, char key, boolean shift, boolean ctrl, boolean alt){
        this(componentID, KeyEvent.getExtendedKeyCodeForChar(key), shift, ctrl, alt);
    }

    public Keybind(int componentID, char key) {
        this(componentID, key, false, false, false);
    }

    public int getComponentID() {
        return componentID;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getType() {
        return type;
    }

    public boolean hasShift() {
        return shift;
    }

    public boolean hasCtrl() {
        return ctrl;
    }

    public boolean hasAlt() {
        return alt;
    }

    /**
     *
     * @param event KeyEvent to test
     * @return true, if the event matches the parameters of the keybind
     */
    public boolean matches(KeyEvent event) {
        return this.getKeyCode() == event.getExtendedKeyCode() && modifiersMatch(event.getModifiersEx());
    }

    /**
     * returns true if the modifier mask in the argument matches the modifiers of this keybind
     */
    public boolean modifiersMatch(int modifiers) {
        int onmask = 0;
        int offmask = 0;
        if (hasAlt()) {
            onmask |= KeyEvent.ALT_DOWN_MASK;
        } else {
            offmask |= KeyEvent.ALT_DOWN_MASK;
        }
        if (hasShift()) {
            onmask |= KeyEvent.SHIFT_DOWN_MASK;
        } else {
            offmask |= KeyEvent.SHIFT_DOWN_MASK;
        }
        if (hasCtrl()) {
            onmask |= KeyEvent.CTRL_DOWN_MASK;
        } else {
            offmask |= KeyEvent.CTRL_DOWN_MASK;
        }
        return (modifiers & (onmask|offmask)) == onmask;
    }

}
