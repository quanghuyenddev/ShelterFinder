package shelterfinder.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.shelterfinder.R;

import shelterfinder.activities.MotelRoomActivity;
import shelterfinder.adapters.StatusFragmentAdapter;
import shelterfinder.objects.MotelRoom;
import shelterfinder.objects.StatusPost;
import shelterfinder.objects.User;
import shelterfinder.tools.CheckNetwork;
import shelterfinder.tools.MotelRoomFunctions;
import shelterfinder.tools.UserFunctions;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class StatusFragment extends Fragment implements OnItemClickListener {
	ListView listViewStatus;
	StatusFragmentAdapter adapterStatus = null;
	ArrayList<MotelRoom> motelRooms;
	ArrayList<StatusPost> listStatus = new ArrayList<StatusPost>();
	ArrayList<User> userPostedList = new ArrayList<User>();
	
	public StatusFragment() {
		motelRooms = new ArrayList<MotelRoom>();
		new LoadStatusPostTask().execute();
	}

	class LoadStatusPostTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			MotelRoomFunctions motelRoomFunctions = new MotelRoomFunctions();
			UserFunctions userFunctions = new UserFunctions();
			motelRooms = motelRoomFunctions.getAllMotelRoom();
			for (int i = 0; i < motelRooms.size(); i++) {
				userPostedList.add(userFunctions.getUserByID(motelRooms.get(i).getUserIDPosted()));
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			for (int i = 0; i < motelRooms.size(); i++) {
				MotelRoom room = motelRooms.get(i);
				User userPosted = userPostedList.get(i);
				listStatus
						.add(new StatusPost(userPosted.getAvatar(),
								userPosted.getFullName(), room.getTimePosted(), room
										.getAddress(), room.getArea() + "", room
										.getPrice() + "", room.getImages()));
			}
			adapterStatus.notifyDataSetChanged();
			Log.i(getClass().getName(), "Tải xong dữ liệu phòng trọ");
		}
		
	}
	@SuppressLint("SimpleDateFormat")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_status, container, false);
		listViewStatus = (ListView) v.findViewById(R.id.listview_status);
		adapterStatus = new StatusFragmentAdapter(getActivity(),
				R.layout.fragment_status_item, listStatus);
		listViewStatus.setOnItemClickListener(this);
		listViewStatus.setAdapter(adapterStatus);
		
		return v;
		
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent motelRoomIntent = new Intent(StatusFragment.this.getActivity(), MotelRoomActivity.class);
		motelRoomIntent.putExtra("MotelRoom", motelRooms.get(position));
		startActivity(motelRoomIntent);
		
	}
	
	
}
