
package com.iconmaster.iconuscalc.manager;

import com.iconmaster.iconuscalc.IconusCalc;
import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.file.IFileListener;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.file.Variable;
import com.iconmaster.iconuscalc.gui.InputType;
import com.iconmaster.iconuscalc.gui.KeyInput;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.manager.MenuManager.Menu;
import com.iconmaster.iconuscalc.manager.MenuManager.MenuResult;
import com.iconmaster.iconuscalc.manager.dialog.Dialog;
import com.iconmaster.iconuscalc.manager.dialog.DialogEntry;
import com.iconmaster.iconuscalc.manager.dialog.EntryType;
import com.iconmaster.iconuscalc.render.IScreenRenderer;
import com.iconmaster.iconuscalc.render.TextGridRenderer;
import com.iconmaster.iconuscalc.util.StringUtils;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class FileManager implements IControlManager,IApplication,IFileListener {
	public TextGridRenderer renderer;
	public int pos = 0;
	public int offset = 0;
	public ArrayList content;
	private Window gui;
	
	public FileManager() {
		renderer = new TextGridRenderer();
		
		IFileListener.registerListener(this);
	}

	@Override
	public IScreenRenderer getRenderer() {
		return renderer;
	}

	@Override
	public void onKey(KeyInput e) {
		if (e.type==InputType.PRESS) { 
			if (e.key==KeyInput.ESCAPE) {
				MenuManager.openAppMenu(gui,new String[] {"New file…","New folder… ","Go Home"}, (menu, id, object) -> {
					switch (id) {
						case (0):
							DialogManager man = new DialogManager(new Dialog(new DialogEntry(EntryType.TEXT,"Create New Variable"),new DialogEntry(EntryType.TEXT,""),new DialogEntry(EntryType.STRING,"Name"),new DialogEntry(EntryType.TEXT,""),new DialogEntry(EntryType.EXPRESSION,"Value")),(ret)->{
								if (ret != null) {
									String name = (String) ret.get(0);
									Element value = (Element) ret.get(1);
									
									if (name!=null && value!=null) {
										getParent().getNamspace().addVar(new Variable(name,value));
									}
								}
							});
							getParent().addManager(man);
							break;
						case (1):
							DialogManager man2 = new DialogManager(new Dialog(new DialogEntry(EntryType.TEXT,"Create New Folder"),new DialogEntry(EntryType.TEXT,""),new DialogEntry(EntryType.STRING,"Name")),(ret)->{
								if (ret != null) {
									String name = (String) ret.get(0);
									
									if (name!=null) {
										Namespace ns = new Namespace(name,getParent().getNamspace());
										getParent().getNamspace().addFolder(ns);
									}
								}
							});
							getParent().addManager(man2);
							break;
						case (2):
							openFolder(IconusCalc.getGlobalNamespace());
							break;
					}
				});
			} else if (e.key==KeyInput.ENTER) {
				if (content.get(pos) instanceof Namespace) {
					MenuManager.openMenu(new Menu("ROOT","Go Into","Move","Copy","Rename","Delete"),6,Math.min(pos+1,renderer.cols-8), gui, new MenuResult() {

						@Override
						public void getResult(Menu menu, int id, Object object) {
							switch (id) {
								case (0):
									openFolder((Namespace)content.get(pos));
									pos = 0;
									break;
								case (1):
									break;
								case (2):
									break;
								case (3):
									break;
								case (4):
									gui.getNamspace().delFolder(((Namespace)content.get(pos)).getName());
									openFolder(gui.getNamspace());
									break;
							}
						}
						
					});
				} else if (content.get(pos) instanceof String && gui.getNamspace().getParent()!=null) {
					pos = 0;
					offset = 0;
					openFolder(gui.getNamspace().getParent());
				} else if (content.get(pos) instanceof Variable) {
					MenuManager.openMenu(new Menu("ROOT","Show Value","Edit","Move","Copy","Rename","Delete"),6,Math.min(pos+1,renderer.cols-8), gui, new MenuResult() {

						@Override
						public void getResult(Menu menu, int id, Object object) {
							switch (id) {
								case (0):
									break;
								case (1):
									break;
								case (2):
									break;
								case (3):
									break;
								case (4):
									break;
								case (5):
									gui.getNamspace().delVar(((Variable)content.get(pos)).name);
									openFolder(gui.getNamspace());
									break;
							}
						}
						
					});
				}
			}
		} else if (e.type == InputType.DOWN) {
			if (e.key==KeyInput.DOWN) {
				pos++;
				if (pos>=content.size()) {
					pos = 0;
				}
			} else if (e.key==KeyInput.UP) {
				pos--;
				if (pos<0) {
					pos = content.size()-1;
				}
			} else if (e.key==KeyInput.RIGHT && content.get(pos) instanceof Namespace) {
				openFolder((Namespace) content.get(pos));
				pos = 0;
				offset = 0;
				renderScreen();
				return;
			} else if (e.key==KeyInput.LEFT && gui.getNamspace().getParent()!=null) {
				pos = 0;
				offset = 0;
				openFolder(gui.getNamspace().getParent());
			}
		}
				
		openFolder(gui.getNamspace());
	}
	
	public void renderScreen() {
		renderer.clearScreen();
		
		if (content.isEmpty()) {
			renderer.removeCursor();
			pos = 0;
			renderer.putString("No files.", (renderer.rows-8)/2, renderer.cols/2);
		} else {
			renderer.moveCursorEx(5, pos, renderer.rows-6, 1);

			int i=0;
			for (Object e : content) {
				String str;
				if (e instanceof Variable) {
					str = StringUtils.padLeft(((Variable)e).value.getDataTypeName(),4)+" "+((Variable)e).name;
				} else if (e instanceof Namespace) {
					str = "     "+((Namespace)e).getName();
				} else {
					str = "     "+e.toString();
				}
				renderer.putString(str, 0, i+offset);
				i++;
			}
		}
	}

	@Override
	public boolean showStatusBar() {
		return true;
	}

	@Override
	public IControlManager getNewWindow() {
		return new FileManager();
	}

	@Override
	public String getAppName() {
		return "File Manager";
	}

	private void openFolder(Namespace ns) {
		gui.setNamespace(ns);
		content = new ArrayList();
		if (ns.getParent()!=null) {
			content.add("..");
		}
		for (Namespace n : ns.folders.values()) {
			content.add(n);
		}
		for (Variable n : ns.vars.values()) {
			content.add(n);
		}
		
		if (pos>content.size()-1) {
			pos = content.size()-1;
		}
		
		renderScreen();
	}
	
	@Override
	public void setParent(Window gui) {
		this.gui = gui;
		openFolder(gui.getNamspace());
	}

	@Override
	public Window getParent() {
		return gui;
	}

	@Override
	public void onFileChange(ChangeType type, Namespace ns, Object actedOn) {
		if (getParent()!=null)
			openFolder(getParent().getNamspace());
	}
	
}
