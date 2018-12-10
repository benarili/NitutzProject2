package DatabaseController;

import main.java.DataBase.IRelationalDB;
import main.java.Entries.ASimpleEntry;
import main.java.Entries.CustomEntry;
import main.java.Entries.DataBaseUsable;
import main.java.Entries.IDataBaseEntry;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public class MyDataBaseController implements DataBaseController  {

    protected IRelationalDB dataBase;
    protected Collection<String> allowedTypes;

    public MyDataBaseController(IRelationalDB toControl){
        this.dataBase=toControl;
        this.allowedTypes=new ArrayList<>();
    }


    @Override
    public boolean insert(DataBaseUsable entry) throws Exception {
       return dataBase.executeInsertCommand(entry.getValues(),entry.getInsertCommand());
    }

    @Override
    public String read(String username) throws Exception {
        return dataBase.executeSelectCommand( username );
    }



    @Override
    public String readPW(String oldUser) {
        return dataBase.executeSelectPWCommand( oldUser );
    }

    @Override
    public void update(String sql) {
        dataBase.executeSQLCommand(sql);
    }

    @Override
    public boolean execute(String s) {
        return dataBase.executeSQLCommand(s);
    }

    @Override
    public Collection<IDataBaseEntry> simpleSelect(String tableName, String[] fieldsToSelect, String[] fieldsWithConditions, String[] conditions, String[] conditionValues) {
        //SELECT .....
        String sql = "SELECT";
        for(int i=0; i<fieldsToSelect.length; i++){
            sql+=" "+fieldsToSelect[i];
            if(i!=fieldsToSelect.length-1){
                sql+=",";
            }
        }
        //FROM
        sql+=" FROM ";
        sql+=tableName;
        //WHERE
        sql+=" WHERE";
        for (int i = 0; i<fieldsWithConditions.length; i++){
            sql+= " "+fieldsWithConditions[i]+" "+conditions[i]+" "+"'"+conditionValues[i]+"'";
            if(i!=fieldsWithConditions.length-1){
                sql+=" AND";
            }
        }

        //get results
        ResultSet rs = dataBase.querry(sql);
        Collection <IDataBaseEntry>results = new ArrayList();
        try {
            while (rs.next()) {
                CustomEntry ce = new CustomEntry();
                ce.setTitles(fieldsToSelect);
                for (String t: fieldsToSelect) {
                    ce.setValue(t, rs.getString(t));
                }
                results.add(ce);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return results;
    }

    /*@Override
    public Collection<IDataBaseEntry> querry(String [] tableNames, String[][] titles, String[][] conditions, String[][] conditionValues) {
        String sql = "SELECT FROM";

        ResultSet set = dataBase.querry(s);
        while (set.next()){

        }
    }*/

}
