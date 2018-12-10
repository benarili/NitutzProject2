package Entries;;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomEntry implements IDataBaseEntry{
    String[] titles;
    Map <String, String> values;


    public void  setTitles(String[] titles){
        this.titles = titles;
        values = new HashMap<>();
        for (String s: titles) {
            String sCopy = StringChecker.copy(s);
            values.put(sCopy,null);
        }
    }

    public void setValue(String title, String newValue){
        values.replace(title,newValue);
    }
    @Override
    public String[] getTitleValues() {
        return titles;
    }

    @Override
    public String[] getValues() {
        ArrayList<String> vals = new ArrayList();
        for (String t: titles) {
            vals.add(values.get(t));
        }
        return (String[]) vals.toArray();
    }

    public String getValue(String title){
        return values.get(title);
    }
}
