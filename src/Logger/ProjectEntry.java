package Logger;

import javafx.beans.property.*;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 * Created by jonah on 12/20/16.
 */
public class ProjectEntry {
    // Declare properties
    private SimpleStringProperty programmer;
    private SimpleStringProperty packageName;
    private SimpleStringProperty status;
    private SimpleIntegerProperty size;

    // Constructor
    ProjectEntry(String prog, String pName, String st, int sz){
        programmer = new SimpleStringProperty(prog);
        packageName = new SimpleStringProperty(pName);
        status = new SimpleStringProperty(st);
        size = new SimpleIntegerProperty(sz);
    }

    public String getProgrammer(){  return programmer.get(); }
    public void setProgrammer(String prog){    programmer.set(prog);    }

    public String getPackageName(){ return packageName.get(); }
    public void setPackageName(String pName){   packageName.set(pName); }

    public String getStatus(){  return status.get(); }
    public void setStatus(String s){    status.set(s);  }

    public int getSize(){   return size.get(); }
    public void setSize(int i){ size.set(i);    }
}
