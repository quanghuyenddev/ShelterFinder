package shelterfinder.fragments;

import java.util.ArrayList;

import shelterfinder.objects.MotelRoom;
import shelterfinder.tools.MotelRoomFunctions;

import com.shelterfinder.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class SearchFragment extends Fragment implements OnClickListener {
	Button getRoomsButton;
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_search, container, false);
		getRoomsButton = (Button) view.findViewById(R.id.getRoomsButton);
		getRoomsButton.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.getRoomsButton:
			getAllRooms();
			break;
		}
	}

	private void getAllRooms() {
		MotelRoomFunctions motelRoomFunctions = new MotelRoomFunctions();
		ArrayList<MotelRoom> allRooms = motelRoomFunctions.getAllMotelRoom();
		if (allRooms.size() > 0) {
			for (int i = 0; i < allRooms.size(); i++) {
				Log.i(getClass().getName(), allRooms.get(i).toString());
			}
		}
		else {
			Log.e(getClass().getName(), "Không có dữ liệu");
		}
		
	}
}
