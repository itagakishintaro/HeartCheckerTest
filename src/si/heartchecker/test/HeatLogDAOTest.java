package si.heartchecker.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import si.heartchecker.HeartLogDAO;
import si.heartchecker.HeartTypes;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.test.suitebuilder.annotation.*;

public class HeatLogDAOTest extends AndroidTestCase {
	private AppOpenHelper helper;

	public void setUp() throws Exception {
		super.setUp();

		Context context = new RenamingDelegatingContext(getContext(), "test_");
		helper = new AppOpenHelper(context);
	}

	public void tearDown() throws Exception {
		super.tearDown();

		helper.close();
	}

	@MediumTest
	public void testRecordHeart() {
		SQLiteDatabase db = helper.getWritableDatabase();
		HeartLogDAO target = new HeartLogDAO(db);

		TestDataSetUpper testDataSetUpper = new TestDataSetUpper(db);
		db.beginTransaction();
		testDataSetUpper.deleteAll();
		db.setTransactionSuccessful();
		db.endTransaction();

		for (HeartTypes heartType : HeartTypes.values()) {
			db.beginTransaction();
			target.recordHeart(heartType.toString());
			db.setTransactionSuccessful();
			db.endTransaction();
			assertThat(target.countHeart(heartType.toString()), is(1));
		}
	}

	@MediumTest
	public void testCountHeart() {
		SQLiteDatabase db = helper.getWritableDatabase();
		HeartLogDAO target = new HeartLogDAO(db);

		TestDataSetUpper testDataSetUpper = new TestDataSetUpper(db);
		db.beginTransaction();
		testDataSetUpper.deleteAll();
		testDataSetUpper.insertYesterday3210andToday0123();
		db.setTransactionSuccessful();
		db.endTransaction();
		for (HeartTypes heartType : HeartTypes.values()) {
			assertThat(target.countHeart(heartType.toString()), is(3));
		}
	}

	@MediumTest
	public void testCountHeartInDate() {
		SQLiteDatabase db = helper.getWritableDatabase();
		HeartLogDAO target = new HeartLogDAO(db);

		TestDataSetUpper testDataSetUpper = new TestDataSetUpper(db);
		db.beginTransaction();
		testDataSetUpper.deleteAll();
		testDataSetUpper.insertYesterday3210andToday0123();
		db.setTransactionSuccessful();
		db.endTransaction();

		Date today = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		assertThat(
				target.countHeartInDate(HeartTypes.HAPPY.toString(),
						df.format(today)), is(0));
		assertThat(
				target.countHeartInDate(HeartTypes.SAD.toString(),
						df.format(today)), is(1));
		// assertThat(
		// target.countHeartInDate(HeartTypes.ANGRY.toString(),
		// df.format(today)), is(2));
		// assertThat(target.countHeartInDate(HeartTypes.DOKIDOKI.toString(),
		// df.format(today)), is(3));

	}
}
