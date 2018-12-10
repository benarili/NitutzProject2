package DatabaseController;

import main.java.Entries.ASimpleEntry;
import main.java.Entries.DataBaseUsable;
import main.java.Entries.IDataBaseEntry;
import main.java.Entries.DataBaseUsable;

import java.util.Collection;

public interface DataBaseController {

    public boolean insert(DataBaseUsable entry) throws Exception;
    public String read(String username) throws Exception;
    public String readPW(String oldUser);
    public void update(String sql);
    public boolean execute(String s);
    //Collection<IDataBaseEntry> querry(String s);

    /**
     * selects all entries from table matching criteria
     *
     * make sure tableName describes real table, fields are real fields
     *
     * make sure fieldsWithCoinditions, conditions, conditionValues are arrays of the same length.
     * @param tableName- name of table to select from
     * @param fieldsToSelect- fields to select
     * @param fieldsWithConditions- fields with conditions to be met
     * @param conditions - conditions of fields (i.g =, >=, ...)
     * @param conditionValues- values of condition
     * @return
     */
    public Collection<IDataBaseEntry> simpleSelect(String tableName, String[] fieldsToSelect, String[] fieldsWithConditions, String[] conditions, String[] conditionValues);
}
