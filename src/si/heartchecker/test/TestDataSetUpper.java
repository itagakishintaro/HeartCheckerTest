package si.heartchecker.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import si.heartchecker.HeartLogDAO;
import si.heartchecker.HeartTypes;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class TestDataSetUpper {
	SQLiteDatabase db = null;

	public TestDataSetUpper(final SQLiteDatabase db) {
		this.db = db;
	}

	public void deleteAll() {
		db.delete("HeartLog", null, null);
	}

	public void insert0123() {
		HeartLogDAO heartLogDAO = new HeartLogDAO(db);
		heartLogDAO.recordHeart(HeartTypes.SAD.toString());
		heartLogDAO.recordHeart(HeartTypes.ANGRY.toString());
		heartLogDAO.recordHeart(HeartTypes.ANGRY.toString());
		heartLogDAO.recordHeart(HeartTypes.DOKIDOKI.toString());
		heartLogDAO.recordHeart(HeartTypes.DOKIDOKI.toString());
		heartLogDAO.recordHeart(HeartTypes.DOKIDOKI.toString());
	}

	public void insertYesterday3210andToday0123() {
		// Yesterday3210
		recordHeartWithDate(HeartTypes.HAPPY.toString(), getYesterday());
		recordHeartWithDate(HeartTypes.HAPPY.toString(), getYesterday());
		recordHeartWithDate(HeartTypes.HAPPY.toString(), getYesterday());
		recordHeartWithDate(HeartTypes.SAD.toString(), getYesterday());
		recordHeartWithDate(HeartTypes.SAD.toString(), getYesterday());
		recordHeartWithDate(HeartTypes.ANGRY.toString(), getYesterday());

		// Today0123
		insert0123();
	}

	public String getYesterday(){
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DATE, -1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return df.format(yesterday.getTime());
	}

	public void recordHeartWithDate(final String heartType, final String date) {
		ContentValues values = new ContentValues();
		values.put("Time", date);
		values.put("HeartType", heartType);
		db.insert("HeartLog", null, values);
	}
}