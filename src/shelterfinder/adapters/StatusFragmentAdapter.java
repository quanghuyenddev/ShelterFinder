package shelterfinder.adapters;

import java.util.ArrayList;

import com.shelterfinder.R;

import shelterfinder.objects.StatusPost;
import shelterfinder.tools.Constants;
import shelterfinder.tools.ImageLoader;
import shelterfinder.tools.LoadImageTask;
import shelterfinder.tools.UserFunctions;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StatusFragmentAdapter extends ArrayAdapter<StatusPost>{
	Activity context;
	int layoutId;
	int layoutId2;
	ArrayList<StatusPost> list= null;
	public StatusFragmentAdapter(Activity context, int resource,
			ArrayList<StatusPost> list) {
		super(context, resource, list);
		this.context=context;
		this.layoutId=resource;
		this.list=list;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = context.getLayoutInflater().inflate(layoutId, null);
		ImageView img_avatar_item = (ImageView) convertView.findViewById(R.id.img_avatar);
		TextView txt_user_name_item = (TextView) convertView.findViewById(R.id.txt_user_name);
		TextView txt_address_item = (TextView) convertView.findViewById(R.id.txt_address);
		TextView txt_date_up_item = (TextView) convertView.findViewById(R.id.txt_date_up);
		TextView txt_area_item = (TextView) convertView.findViewById(R.id.txt_area);
		TextView txt_price_item = (TextView) convertView.findViewById(R.id.txt_price);
		ImageView img_description_item = (ImageView) convertView.findViewById(R.id.img_description);
		
		StatusPost status = list.get(position);
		txt_user_name_item.setText(status.getUserName());
		txt_address_item.setText("Địa chỉ: " + status.getAddress());
		txt_date_up_item.setText(status.getDateUp());
		txt_area_item.setText("Diện tích: " + status.getArea() + " mét vuông");
		txt_price_item.setText("Giá: " + status.getPrice() + " đồng/tháng");
		
		new LoadImageTask(getContext(), img_avatar_item, R.drawable.user_avatar)
				.execute(Constants.USER_AVATARS_URL + status.getImagesAvatar());
		
		new LoadImageTask(getContext(), img_description_item,
				R.drawable.google_map_img)
				.execute(Constants.MOTELROOM_IMAGES_URL
						+ status.getImagesDescription());
		

		
		return convertView;
	}
	
}
