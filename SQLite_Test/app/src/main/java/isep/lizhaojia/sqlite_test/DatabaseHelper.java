package isep.lizhaojia.sqlite_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "test_table";
    private static final String _table = "OMG";
    private static final String col1 = "ID";
    private static final String col2 = "name";

    public DatabaseHelper( Context context) {
        super(context, TABLE_NAME,  null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatTable = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + col2 + " VARCHAR(50))";
        //String SQL = " CREATE TABLE IF NOT EXISTS " + _table + "(_id INTEGER PRIMARY KEY AUTOINCREMENT ,_title VARCHAR(50))";
        db.execSQL(creatTable);
        //db.execSQL(SQL);
        Log.d(TAG,"you have created a new table.");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE " + TABLE_NAME);
        //db.execSQL(" DROP TABLE " + _table);
        onCreate(db);
    }

    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,item.toString());

        Log.d(TAG, " add_Data: Adding " + item + " to " + TABLE_NAME);

        long result =  db.insert(TABLE_NAME, null , contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_NAME ;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db =this.getWritableDatabase();
        String query = " SELECT " + col1 + " FROM " + TABLE_NAME + " WHERE " + col2 + "= '" + name + "'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public void updateName(String newName, int id, String oldName){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = " UPDATE " + TABLE_NAME + " SET " + col2  + " = '" + newName+
                "' WHERE " + col1 + "= '" + id + "'" + "AND" +col2 + "= '" +  oldName + "'";
        Log.d(TAG, "updateName: query" + query);
        Log.d(TAG, "updateName: setting name to "+ newName);
        db.execSQL(query);

    }

    public void deleteName(int id, String name){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = " DELETE FROM " + TABLE_NAME + " WHERE " + col1  + " = '" + id +
                " AND " + col2 + " = '" + name + "'";
        db.execSQL(query);
    }

}





















