
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
        vars.put(fn.name.toUpperCase(), fn);
    }
    
    public Variable getVar(String name) {
        return vars.get(name.toUpperCase());
    }
    
    public String getPathName() {
        return parent==null?"":parent.getPathName()+"/"+name;
    }
    
    public void addFolder(Namespace ns) {
        folders.put(ns.getName().toUpperCase(), ns);
    }
    
    public Namespace getFolder(String name) {
        return folders.get(name.toUpperCase());
    }

    private String getName() {
        return name;
    }
}
