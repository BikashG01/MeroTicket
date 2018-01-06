import android.app.ProgressDialog;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "locationData.db";
    private static final String DATABASE_NAME_PART = "locationData";

    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    public static final String TABLE_LOCATION = "location";

    public static final String COLUMN_ID = "loc_id";
    public static final String COLUMN_LOCATION = "loc_name";

    private static final String CREATE_LOCATION_TABLE =

            "CREATE TABLE " + TABLE_LOCATION + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY , " +
                    COLUMN_LOCATION + " VARCHAR" + ")";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_LOCATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
        sqLiteDatabase.execSQL(CREATE_LOCATION_TABLE);
    }

}
