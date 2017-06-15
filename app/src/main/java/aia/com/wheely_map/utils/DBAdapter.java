package aia.com.wheely_map.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

	private static final String TAG = DBAdapter.class.getSimpleName();
	
	// DB Fields
	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;

	public static final String KEY_USERID = "userId";
	public static final String KEY_LAT = "rampLat";
	public static final String KEY_LNG = "rampLng";

	public static final int COL_NAME = 1;
	public static final int COL_STUDENTNUM = 2;
	public static final int COL_FAVCOLOUR = 3;

	
	public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_USERID, KEY_LAT, KEY_LNG};

	public static final String DATABASE_NAME = "RegisteredRampsDB";
	public static final String DATABASE_TABLE = "mainTable";
	public static final int DATABASE_VERSION = 2;	
	
	private static final String DATABASE_CREATE_SQL = 
			"create table " + DATABASE_TABLE 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			
			/*
			 * CHANGE 2:
			 */
			// TODO: Place your fields here!
			// + KEY_{...} + " {type} not null"
			//	- Key is the column name you created above.
			//	- {type} is one of: text, integer, real, blob
			//		(http://www.sqlite.org/datatype3.html)
			//  - "not null" means it is a required field (must be given a value).
			// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
			+ KEY_USERID + " text not null, "
			+ KEY_LAT + " integer not null, "
			+ KEY_LNG + " string not null"
			
			// Rest  of creation:
			+ ");";
	
	// Context of application who uses us.
	private final Context context;
	
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}

	public DBAdapter open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		myDBHelper.close();
	}

	public long insertRow(String userId, double rampLat, double rampLng) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_USERID, userId);
		initialValues.put(KEY_LAT, rampLat);
		initialValues.put(KEY_LNG, rampLng);

		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}
	
	public void deleteAll() {
		Cursor c = getAllRows();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRow(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}

	public Cursor getAllRows() {
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	public boolean updateRow(long rowId, String userId, double rampLat, double rampLng) {
		String where = KEY_ROWID + "=" + rowId;

		ContentValues newValues = new ContentValues();
		newValues.put(KEY_USERID, userId);
		newValues.put(KEY_LAT, rampLat);
		newValues.put(KEY_LNG, rampLng);

		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
	}

	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(DATABASE_CREATE_SQL);			
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");

			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

			onCreate(_db);
		}
	}
}
