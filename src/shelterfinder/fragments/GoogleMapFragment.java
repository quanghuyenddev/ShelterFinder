package shelterfinder.fragments;

import java.util.ArrayList;

import shelterfinder.activities.MainActivity;
import shelterfinder.objects.MotelRoom;
import shelterfinder.objects.StatusPost;
import shelterfinder.objects.User;
import shelterfinder.tools.Constants;
import shelterfinder.tools.GoogleMapFunctions;
import shelterfinder.tools.ImageLoader;
import shelterfinder.tools.MotelRoomFunctions;
import shelterfinder.tools.UserFunctions;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.shelterfinder.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;

public class GoogleMapFragment extends Fragment {
	GoogleMap googleMap = null;
	ArrayList<MotelRoom> motelRooms = new ArrayList<MotelRoom>();
	
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_google_map, container, false);
		new LoadStatusPostTask().execute();
		return view;
	}
	
	class LoadStatusPostTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			MotelRoomFunctions motelRoomFunctions = new MotelRoomFunctions();
			motelRooms = motelRoomFunctions.getAllMotelRoom();
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			if (googleMap == null) {
				googleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_google_viewpager)).getMap();
				if (googleMap == null) return;
				googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				googleMap.setMyLocationEnabled(true);
	    		googleMap.getUiSettings().setZoomControlsEnabled(true);
	    		GoogleMapFunctions mapFunctions = new GoogleMapFunctions(googleMap, getActivity());
	    		
	    		for (int i = 0; i < motelRooms.size(); i++) {
	    			double latitude = motelRooms.get(i).getLatitude();
		    		double longitude = motelRooms.get(i).getLongitude();
		    		mapFunctions.addMarker(latitude, longitude, "", "");
	    		}
	    		
	    		mapFunctions.moveCameraToLatLng(motelRooms.get(0).getLatitude(), motelRooms.get(0).getLongitude(), "", "");
	    		
			}
		}
		
	}
}
