package shelterfinder.activities;

import shelterfinder.objects.MotelRoom;
import shelterfinder.objects.User;
import shelterfinder.tools.Constants;
import shelterfinder.tools.LoadImageTask;
import shelterfinder.tools.UserFunctions;

import com.shelterfinder.R;
import com.shelterfinder.R.id;
import com.shelterfinder.R.layout;
import com.shelterfinder.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MotelRoomActivity extends Activity {
	MotelRoom motelRoom;
	ImageView imageAvatarPosted;
	User userPosted;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Intent data = getIntent();
		motelRoom = (MotelRoom) data.getSerializableExtra("MotelRoom");
		setContentView(R.layout.activity_motel_room);
		
		ImageView imageRoom = (ImageView) findViewById(R.id.image_room);
		imageAvatarPosted = (ImageView) findViewById(R.id.img_avatar);
		TextView userNameTextView = (TextView) findViewById(R.id.txt_user_name);
		TextView dateUpTextView = (TextView) findViewById(R.id.txt_date_up);
		TextView addressTextView = (TextView) findViewById(R.id.txt_address);
		TextView areaTextView = (TextView) findViewById(R.id.txt_area);
		TextView priceTextView = (TextView) findViewById(R.id.txt_price);
		TextView descriptionTextView = (TextView) findViewById(R.id.txt_description);
		TextView phoneTextView = (TextView) findViewById(R.id.txt_phone_contact);
		
		dateUpTextView.setText(motelRoom.getTimePosted());
		addressTextView.setText("Địa chỉ: " + motelRoom.getAddress());
		areaTextView.setText("Diện tích: " + motelRoom.getArea() + " mét vuông");
		priceTextView.setText("Giá: " + motelRoom.getPrice() + " đồng/tháng");
		descriptionTextView.setText("Mô tả: " + motelRoom.getDescription());
		phoneTextView.setText("Điện thoại: " + motelRoom.getPhone());
		new LoadImageTask(getApplicationContext(), imageRoom,
				R.drawable.google_map_img)
				.execute(Constants.MOTELROOM_IMAGES_URL + motelRoom.getImages());
		UserFunctions userFunctions = new UserFunctions();
		userPosted = userFunctions.getUserByID(motelRoom.getUserIDPosted());
		userNameTextView.setText(userPosted.getFullName());
		
		new LoadImageTask(getApplicationContext(), imageAvatarPosted,
				R.drawable.user_avatar)
				.execute(Constants.USER_AVATARS_URL + userPosted.getAvatar());
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.motel_room, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
