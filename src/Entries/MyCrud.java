package Entries;;

import main.java.DatabaseController.DataBaseController;
import main.java.DatabaseController.MyDataBaseController;

import java.sql.ResultSet;
import java.util.Collection;

public class MyCrud implements ICrud {
    private DataBaseController dbController;


    public MyCrud(DataBaseController db){
        this.dbController = db;
    }
    @Override
    public boolean add(DataBaseUsable usable) {
        String insert = usable.getInsertCommand();
        return dbController.execute(insert);
    }

    @Override
    public boolean update(DataBaseUsable usable, String[] fieldNamesToUpdate, String[] newValues) {
        String tableName = usable.getTableName();
        //get the part of command used to updateTables
        String updateTables = fieldNamesToUpdate[0] + " = " + newValues[0];
        for(int i =0; i < fieldNamesToUpdate.length; i++){
            updateTables+=", "+fieldNamesToUpdate[i] + " = " + newValues[i];
        }

        String whereCondition = usable.getKeyNames()[0] + " = " + usable.getValues()[0];
        for (int i = 0; i < usable.getKeyNames().length; i++) {
            whereCondition+=", "+usable.getKeyNames()[i] + " = " + usable.getValues()[0];
        }
        String sql = "Update" + tableName + " SET" + updateTables + " WHERE " + whereCondition;
        return dbController.execute(sql);
    }

    @Override
    public boolean delete(String name, String[] fieldNamesToDelete, String[] fieldValues) {
        boolean exists  =exists(name,fieldNamesToDelete,fieldValues);
        if(exists) {
            String whereCondition = fieldNamesToDelete[0] + " = " + "'" + fieldValues[0] + "'";
            for (int i = 1; i < fieldNamesToDelete.length; i++) {
                whereCondition += " AND " + fieldNamesToDelete[i] + " = " + "'" + fieldValues[i] + "'";
            }
            String sql = "DELETE FROM " + name + " WHERE " + whereCondition;
            exists = dbController.execute(sql);
        }
        return exists;
    }

    public boolean exists(String type, String[] fieldNamesToCheckExists, String[] fieldValues){
        String[] desired =  {"*"};
        String[] equals = new String[fieldNamesToCheckExists.length];
        for (int i = 0; i < equals.length; i++) {
            equals[i] = "=";
        }
        Collection collection = simpleSelect(type, desired, fieldNamesToCheckExists,equals,fieldValues);
        return collection!=null && collection.size()>0;
    }



    @Override
    public Collection<IDataBaseEntry> simpleSelect(String type, String[] desiredFields, String[] fieldsWithConditions, String[] conditions, String[] conditionValues){
        /*String selectParams  = desiredFields[0];
        for (int i = 1; i < desiredFields.length; i++) {
            selectParams+=", " + desiredFields[i];
        }

        String whereCondition = fieldsWithConditions[0] + " "+ conditions[0] + conditionValues[0];
        for (int i = 1; i < fieldsWithConditions.length; i++) {
            whereCondition += fieldsWithConditions[i] + " "+ conditions[i] + conditionValues[i];
        }
        String sql = "SELECT "+selectParams+" FROM " + type + " WHERE " + whereCondition;
        ResultSet rs = dbController.simpleSelect()*/
        //return dbController.execute(sql);
        return dbController.simpleSelect(type,desiredFields,fieldsWithConditions,conditions,conditionValues);
    }
}
