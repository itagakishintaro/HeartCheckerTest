package si.heartchecker.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import si.heartchecker.*;

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

		CreateHeartLogHelper helper = new CreateHeartLogHelper(activity);
		TestDataSetUpper testDataSetUpper = new TestDataSetUpper(helper);
		testDataSetUpper.insert0123();
	}

	@SmallTest
	public void testOnCreateInitialHappyCount() throws Exception {
		assertThat(
				((TextView) activity.findViewById(si.heartchecker.R.id.happy_count))
						.getText().toString(), is("0"));
	}

	@SmallTest
	public void testOnCreateInitialSadCount() throws Exception {
		assertThat(
				((TextView) activity.findViewById(si.heartchecker.R.id.sad_count))
						.getText().toString(), is("1"));
	}

	@SmallTest
	public void testOnCreateInitialAngryCount() throws Exception {
		assertThat(
				((TextView) activity.findViewById(si.heartchecker.R.id.angry_count))
						.getText().toString(), is("2"));
	}

	@SmallTest
	public void testOnCreateInitialDokiDokiCount() throws Exception {
		assertThat(
				((TextView) activity.findViewById(si.heartchecker.R.id.dokidoki_count))
						.getText().toString(), is("3"));
	}

	// スクリーンのロックを解除してから実行すること！
	@SmallTest
	public void testOnHeartButtonClickHappyButton() throws Exception {
		Button happyButton = (Button) activity.findViewById(si.heartchecker.R.id.happy_button);
		TouchUtils.clickView(this, happyButton);

		assertThat(
				((TextView) activity.findViewById(si.heartchecker.R.id.happy_count))
						.getText().toString(), is("1"));
	}

	// スクリーンのロックを解除してから実行すること！
		@SmallTest
		public void testOnHeartButtonClickSadButton() throws Exception {
			Button happyButton = (Button) activity.findViewById(si.heartchecker.R.id.sad_button);
			TouchUtils.clickView(this, happyButton);

			assertThat(
					((TextView) activity.findViewById(si.heartchecker.R.id.sad_count))
							.getText().toString(), is("2"));
		}

}
