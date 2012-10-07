package si.heartchecker.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import si.heartchecker.*;

import android.database.sqlite.SQLiteDatabase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.TextView;

public class HeartCheckerActivityTest extends
		ActivityInstrumentationTestCase2<HeartCheckerActivity> {

	private HeartCheckerActivity activity;

	public HeartCheckerActivityTest() {
		super("si.heartchecker", HeartCheckerActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
	}

	public void dbSetup() {
		CreateHeartLogHelper helper = new CreateHeartLogHelper(activity);
		SQLiteDatabase db = helper.getWritableDatabase();
		TestDataSetUpper testDataSetUpper = new TestDataSetUpper(db);
		try {
			db.beginTransaction();
			testDataSetUpper.deleteAll();
			testDataSetUpper.insert0123();
			db.setTransactionSuccessful();
			db.endTransaction();
		} finally {
			db.close();
		}
	}

	@SmallTest
	public void testOnCreateInitialHappyCount() throws Exception {
		dbSetup();
		assertThat(
				((TextView) activity.findViewById(si.heartchecker.R.id.happy_count))
						.getText().toString(), is("0"));
	}

	@SmallTest
	public void testOnCreateInitialSadCount() throws Exception {
		dbSetup();
		assertThat(
				((TextView) activity.findViewById(si.heartchecker.R.id.sad_count))
						.getText().toString(), is("1"));
	}

	@SmallTest
	public void testOnCreateInitialAngryCount() throws Exception {
		dbSetup();
		assertThat(
				((TextView) activity.findViewById(si.heartchecker.R.id.angry_count))
						.getText().toString(), is("2"));
	}

	@SmallTest
	public void testOnCreateInitialDokiDokiCount() throws Exception {
		dbSetup();
		assertThat(
				((TextView) activity.findViewById(si.heartchecker.R.id.dokidoki_count))
						.getText().toString(), is("3"));
	}

	// スクリーンのロックを解除してから実行すること！
	@SmallTest
	public void testOnHeartButtonClickHappyButton() throws Exception {
		dbSetup();
		Button happyButton = (Button) activity
				.findViewById(si.heartchecker.R.id.happy_button);
		TouchUtils.clickView(this, happyButton);

		assertThat(
				((TextView) activity.findViewById(si.heartchecker.R.id.happy_count))
						.getText().toString(), is("1"));
	}

	// スクリーンのロックを解除してから実行すること！
	@SmallTest
	public void testOnHeartButtonClickSadButton() throws Exception {
		dbSetup();
		Button sadButton = (Button) activity
				.findViewById(si.heartchecker.R.id.sad_button);
		TouchUtils.clickView(this, sadButton);

		assertThat(
				((TextView) activity.findViewById(si.heartchecker.R.id.sad_count))
						.getText().toString(), is("2"));
	}

	// スクリーンのロックを解除してから実行すること！
	@SmallTest
	public void testOnHeartButtonClickAngryButton() throws Exception {
		dbSetup();
		Button angryButton = (Button) activity
				.findViewById(si.heartchecker.R.id.angry_button);
		TouchUtils.clickView(this, angryButton);

		assertThat(
				((TextView) activity.findViewById(si.heartchecker.R.id.angry_count))
						.getText().toString(), is("3"));
	}

	// スクリーンのロックを解除してから実行すること！
	@SmallTest
	public void testOnHeartButtonClickDokidokiButton() throws Exception {
		dbSetup();
		Button dokidokiButton = (Button) activity
				.findViewById(si.heartchecker.R.id.dokidoki_button);
		TouchUtils.clickView(this, dokidokiButton);

		assertThat(
				((TextView) activity.findViewById(si.heartchecker.R.id.dokidoki_count))
						.getText().toString(), is("4"));
	}
}
