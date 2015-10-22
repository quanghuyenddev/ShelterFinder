package shelterfinder.activities;

import java.util.ArrayList;

import shelterfinder.adapters.MotelRoomCommentAdapter;
import shelterfinder.objects.MotelRoom;
import shelterfinder.objects.MotelRoomComment;
import shelterfinder.objects.User;
import shelterfinder.tools.Constants;
import shelterfinder.tools.LoadImageTask;
import shelterfinder.tools.UserFunctions;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.shelterfinder.R;

public class MotelRoomActivity extends Activity {
	MotelRoom motelRoom;
	ImageView imageAvatarPosted;
	User userPosted;
	ListView lvComment;
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
		
		lvComment = (ListView) findViewById(R.id.listview_comment);
		ArrayList<MotelRoomComment> commentList = new ArrayList<MotelRoomComment>();
		commentList.add(new MotelRoomComment("1", "Không có việc gì khó", "12/12/2014", "1", "1"));
		commentList.add(new MotelRoomComment("2", "Không có việc gì không khó", "12/12/2014", "1", "1"));
		commentList.add(new MotelRoomComment("3", "Không có việc gì không dễ", "12/12/2014", "1", "1"));
		commentList.add(new MotelRoomComment("4", "Không có việc gì dễ", "12/12/2014", "1", "1"));
		
		
		MotelRoomCommentAdapter commentAdapter = new MotelRoomCommentAdapter(
				this, R.layout.comment_motel_room, commentList);
		lvComment.setAdapter(commentAdapter);

		
	}
	

	
}
