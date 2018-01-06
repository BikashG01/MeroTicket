import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import static com.infobrain.meroticket.SqliteDB.DBHelper.COLUMN_ID;
import static com.infobrain.meroticket.SqliteDB.DBHelper.COLUMN_LOCATION;
import static com.infobrain.meroticket.SqliteDB.DBHelper.TABLE_LOCATION;


public class SQLiteOperations {
    DBHelper dbHelper;
    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;


    public SQLiteOperations(SQLiteOpenHelper dbhandler, SQLiteDatabase database) {
        this.dbhandler = dbhandler;
        this.database = database;
    }

//ADDING LOCATION OPERATION/METHOD
    public void addLocation(String location) {
        SQLiteDatabase db = this.database;
        ContentValues values = new ContentValues();

        Log.e("COLUMN ID", COLUMN_ID);
        Log.e("COLUMN_LOCATION", COLUMN_LOCATION);

        values.put(COLUMN_LOCATION, location);
        Long insertid = db.insert(TABLE_LOCATION, null, values);
        Log.e("Main InsertID", String.valueOf(insertid));

    }


}
