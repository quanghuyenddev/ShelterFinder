package shelterfinder.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.shelterfinder.R;

import shelterfinder.adapters.StatusFragmentAdapter;
import shelterfinder.objects.StatusPost;

import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class StatusFragment extends Fragment {
	ListView listViewStatus;
	StatusFragmentAdapter adapterStatus = null;

	@SuppressLint("SimpleDateFormat")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ArrayList<StatusPost> listStatus = new ArrayList<StatusPost>();
		View v = inflater.inflate(R.layout.fragment_status, container, false);
		listViewStatus = (ListView) v.findViewById(R.id.listview_status);
		SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyy hh:mm:ss a");
		listStatus.add(new StatusPost(R.drawable.ic_facebook_pressed,
				"Lê Viết Nhựt", dfm.format(new Date()),
				"Kiệt 47 Nguyễn Lương Bằng Đà Nẵng",
				"50 mét vuông",
				"1000000/tháng", R.drawable.anh_1));
		listStatus.add(new StatusPost(R.drawable.ic_facebook_pressed,
				"Lê Viết Nhựt", dfm.format(new Date()),
				"Kiệt 82 Nguyễn Lương Bằng Đà Nẵng",
				"30 mét vuông",
				"1600000/tháng", R.drawable.anh_3));
		listStatus.add(new StatusPost(R.drawable.ic_facebook_pressed,
				"Lê Viết Nhựt", dfm.format(new Date()),
				"467 Điện Biên Phủ, Đà Nẵng",
				"35 mét vuông",
				"1340000/tháng", R.drawable.anh_2));
		adapterStatus = new StatusFragmentAdapter(getActivity(),
				R.layout.fragment_status_item, listStatus);
		listViewStatus.setAdapter(adapterStatus);
		
		return v;
	}
}
