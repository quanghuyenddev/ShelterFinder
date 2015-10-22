package shelterfinder.adapters;

import java.util.ArrayList;

import com.shelterfinder.R;

import shelterfinder.objects.MotelRoomComment;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MotelRoomCommentAdapter extends ArrayAdapter<MotelRoomComment> {
	Activity context;
	int layoutId;
	ArrayList<MotelRoomComment> commentList = null;
	public MotelRoomCommentAdapter(Activity context, int resource,
			ArrayList<MotelRoomComment> list) {
		super(context, resource, list);
		this.context = context;
		layoutId = resource;
		commentList = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = context.getLayoutInflater().inflate(layoutId, null);
		MotelRoomComment comment = commentList.get(position);
		TextView txtTimePosted = (TextView) convertView.findViewById(R.id.txt_time_posted);
		TextView txtFullName = (TextView) convertView.findViewById(R.id.txt_full_name);
		TextView txtComment = (TextView) convertView.findViewById(R.id.txt_comment);
		txtTimePosted.setText(comment.getTimePosted());
		txtComment.setText(comment.getComment());
		
		return convertView;
	}
}
