package shelterfinder.activities;

import shelterfinder.objects.MotelRoom;
import shelterfinder.tools.GoogleMapFunctions;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.shelterfinder.R;
import com.shelterfinder.R.id;
import com.shelterfinder.R.layout;
import com.shelterfinder.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class GoogleMapActivity extends FragmentActivity {
	MotelRoom motelRoom;
	GoogleMap googleMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_google_map);
		Intent data = getIntent();
		motelRoom = (MotelRoom) data.getSerializableExtra("MotelRoom");
		Log.i(getClass().getName(), motelRoom.toString());
		if (googleMap == null) {
			googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_google)).getMap();
			if (googleMap == null) return;
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			googleMap.setMyLocationEnabled(true);
    		googleMap.getUiSettings().setZoomControlsEnabled(true);
    		GoogleMapFunctions mapFunctions = new GoogleMapFunctions(googleMap, this);
    		double latitude = motelRoom.getLatitude();
    		double longitude = motelRoom.getLongitude();
    		mapFunctions.moveCameraToLatLng(latitude, longitude, motelRoom.getAddress(), "");
    		
		}
	}
}
