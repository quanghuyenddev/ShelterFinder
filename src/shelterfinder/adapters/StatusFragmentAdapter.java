package shelterfinder.adapters;

import java.util.ArrayList;

import com.shelterfinder.R;

import shelterfinder.objects.StatusPost;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StatusFragmentAdapter extends ArrayAdapter<StatusPost>{
	Activity context;
	int layoutId;
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
	
		img_avatar_item.setImageResource(status.getImagesAvatar());
		txt_user_name_item.setText(status.getUserName());
		txt_address_item.setText("Địa chỉ: " + status.getAddress());
		txt_date_up_item.setText(status.getDateUp());
		txt_area_item.setText("Diện tích: " + status.getArea());
		txt_price_item.setText("Giá: " + status.getPrice());
		img_description_item.setImageResource(status.getImagesDescription());
		
		return convertView;
	}
	
}