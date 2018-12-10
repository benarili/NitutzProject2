package Entries;;

import java.util.Collection;

public interface ICrud {
    /**
     * add to database
     * @param usable
     * @return true if successful, false otherwise
     */
    public boolean add(DataBaseUsable usable);

    /**
     * update entry
     * @param usable, created using keys!
     * @param fieldNamesToUpdate- list of all fields to update
     * @param newValues- list of new values for fields to update
     * @return true if successful, else false
     */
    public boolean update(DataBaseUsable usable, String[] fieldNamesToUpdate, String[] newValues);

    /**
     * deletes from database
     * @param name name of table
     * @param fieldNamesToDelete - titles of fields to delete
     * @param fieldValues- values of fieldsToDelete
     * @return
     */
    public boolean delete(String name, String[] fieldNamesToDelete, String[] fieldValues);

    public boolean exists(String type, String[] fieldNamesToCheckExists, String[] fieldValues);


    public Collection<IDataBaseEntry> simpleSelect(String type, String[] desiredFields, String[] fieldsWithConditions, String[] conditions, String[] conditionValues);
}
