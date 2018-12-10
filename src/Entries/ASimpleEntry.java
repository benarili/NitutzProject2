package Entries;;

public abstract class ASimpleEntry implements DataBaseUsable {



    public abstract String getTableName();

    protected abstract String[] getValuesTitles();


    protected boolean isStringEmpty(String s){
        if(s==null || s.length()==0)
            return true;
        return false;
    }

    private String toStringValues(){
        String ans ="";
        for (int i = 0; i < getValues().length; i++) {
            String curr = getValues()[i];
            if(isStringEmpty(curr))
                curr="NULL";
            ans+="'"+curr+"'";
            if(i!=getValues().length-1)
                ans+=", ";
        }
        return ans;
    }
    private String toStringValuesTitles(){
        String ans=getValuesTitles()[0];
        for(int i=1; i<getValuesTitles().length;i++){
            ans+=", "+getValuesTitles()[i];
        }
        return ans;
    }
    @Override
    public String getInsertCommand() {
        String insertCommand = "INSERT INTO " +getTableName()+ '(' + toStringValuesTitles() + ')' + " VALUES(" + toStringValues() + ")";
        return insertCommand;
    }


    @Override
    public String getCreateTableCommand() {
        String[] values = getValuesTitles();
        String sqlCommand = "CREATE TABLE IF NOT EXISTS " + getTableName () + "(\n";
        sqlCommand+=values[0] + " text PRIMARY KEY,\n";
        for (int i = 1; i < values.length; i++) {
            if(i!=values.length-1)
                sqlCommand+=values[i]+" text,\n";
            else
                sqlCommand+=values[i]+" text\n";
        }
        sqlCommand+=");";
        return sqlCommand;
    }


    @Override
    public String getDeleteTableCommand() {
        return null;
    }

    @Override
    public String getUpdateCommand() {
        return null;
    }

    public  abstract String getDeleteByKeyCommand(String[] key) throws Exception;


}
