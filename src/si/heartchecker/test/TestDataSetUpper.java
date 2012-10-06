package si.heartchecker.test;

import si.heartchecker.CreateHeartLogHelper;
import si.heartchecker.HeartLogDAO;
import si.heartchecker.HeartTypes;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TestDataSetUpper {
	CreateHeartLogHelper helper = null;
	SQLiteDatabase db = null;

	public TestDataSetUpper(final CreateHeartLogHelper helper) {
		this.helper = helper;
		deleteAll();
	}

	public void deleteAll() {
		try {
			db = helper.getWritableDatabase();
			db.beginTransaction();
			db.delete("HeartLog", null, null);
			db.setTransactionSuccessful();
			db.endTransaction();
		} catch (Exception e) {
			Log.e("ERROR", e.toString());
		} finally {
			db.close();
		}
	}

	public void insert0123() {
		HeartLogDAO heartLogDAO = new HeartLogDAO(helper);

		heartLogDAO.recordHeart(HeartTypes.SAD.toString());
		heartLogDAO.recordHeart(HeartTypes.ANGRY.toString());
		heartLogDAO.recordHeart(HeartTypes.ANGRY.toString());
		heartLogDAO.recordHeart(HeartTypes.DOKIDOKI.toString());
		heartLogDAO.recordHeart(HeartTypes.DOKIDOKI.toString());
		heartLogDAO.recordHeart(HeartTypes.DOKIDOKI.toString());
	}
}