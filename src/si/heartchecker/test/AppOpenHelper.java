package si.heartchecker.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AppOpenHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "app";
	private static final int LATEST_VERSION = 1;

	public AppOpenHelper(Context context) {
		this(context, LATEST_VERSION);
	}

	public AppOpenHelper(Context context, int version) {
		super(context, DB_NAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			String sql = "create table HeartLog(" + "Time text primary key,"
					+ "HeartType text not null)";

			db.execSQL(sql);
		} catch (Exception e) {
			Log.e("ERROR", e.toString());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table HeartLog");
		onCreate(db);
	}
}
