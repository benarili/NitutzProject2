package Entries;;

public interface DataBaseUsable {
    public String getInsertCommand();
    public String getCreateTableCommand();
    public String getDeleteTableCommand();
    public String getUpdateCommand();
    public String[] getValues();
    public String getTableName();
    public String[] getKeyNames();
    public String[] getKeyValues();

}