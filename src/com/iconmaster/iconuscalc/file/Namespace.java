
package com.iconmaster.iconuscalc.file;

import java.util.HashMap;

/**
 *
 * @author iconmaster
 */
public class Namespace {
    private Namespace parent;
    public HashMap<String,Variable> vars = new HashMap<>();
    public HashMap<String,Namespace> folders = new HashMap<>();
    private String name;
    
    public Namespace(String name, Namespace parent) {
        this.name = name;
        this.parent = parent;
    }

    public Namespace getParent() {
        return parent;
    }

    public void setParent(Namespace parent) {
        this.parent = parent;
    }
    
    public void addVar(Variable fn) {
        if (vars.containsKey(fn.name)) {
            vars.get(fn.name).value = fn.value;
            IFileListener.fireEvent(IFileListener.ChangeType.CHANGE, this, vars.get(fn.name));
        } else {
            vars.put(fn.name.toUpperCase(), fn);
            IFileListener.fireEvent(IFileListener.ChangeType.CREATE, this, fn);
        }
    }
    
    public Variable getVar(String name) {
        return vars.get(name.toUpperCase());
    }
    
    public void delVar(String name) {
        if (vars.containsKey(name)) {
            Variable deleted = vars.remove(name);
            IFileListener.fireEvent(IFileListener.ChangeType.DELETE, this, deleted);
        }
    }
    
    public String getPathName() {
        return parent==null?"":parent.getPathName()+"/"+name;
    }
    
    public void addFolder(Namespace ns) {
        Namespace old = folders.get(ns.getName());
        folders.put(ns.getName().toUpperCase(), ns);
        if (old==null) {
            IFileListener.fireEvent(IFileListener.ChangeType.CREATE, this, ns);
        } else {
            IFileListener.fireEvent(IFileListener.ChangeType.CHANGE, this, ns);
        }
    }
    
    public void delFolder(Namespace ns) {
        if (folders.containsKey(name)) {
            Namespace deleted = folders.remove(name);
            IFileListener.fireEvent(IFileListener.ChangeType.DELETE, this, deleted);
        }
    }
    
    public Namespace getFolder(String name) {
        return folders.get(name.toUpperCase());
    }

    public String getName() {
        return name;
    }
}
