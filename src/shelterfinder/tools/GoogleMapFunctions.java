package shelterfinder.tools;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.shelterfinder.R;

public class GoogleMapFunctions {
	GoogleMap googleMap;
	Context context;
	public GoogleMapFunctions(GoogleMap googleMap, Context context) {
		this.googleMap = googleMap;
		this.context = context;
	}
	
	public void moveCameraToLatLng(double latitude, double longitude, String title, String snippet) {
		
		LatLng latLng = new LatLng(latitude, longitude);
		MarkerOptions markerOps = new MarkerOptions();
		markerOps.position(latLng);
		markerOps.title(title).snippet(snippet);
		markerOps.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_motelroom));
    	Marker markerRes = googleMap.addMarker(markerOps);
    	markerRes.showInfoWindow();
    	googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
	}
	
	public void addMarker(double latitude, double longitude, String title, String snippet) {
		LatLng latLng = new LatLng(latitude, longitude);
		MarkerOptions markerOps = new MarkerOptions();
		markerOps.position(latLng);
		markerOps.title(title).snippet(snippet);
		markerOps.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_motelroom));
    	Marker markerRes = googleMap.addMarker(markerOps);
    	markerRes.showInfoWindow();
	}
	
	public void moveCameraToCurrentPosition() {
		LocationManager locationManager = (LocationManager) context.getSystemService(Service.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
		if (location == null) {
			return;
		}
		else {
			double latitude = location.getLatitude();
			double longitude = location.getLongitude();
			LatLng currentLatLng = new LatLng(latitude, longitude);
			googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng));
			googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
		}
		
	}
	
	public static Bitmap getGoogleMapThumbnail(double lati, double longi) {
		String URL = "http://maps.google.com/maps/api/staticmap?center="
				+ lati
				+ ","
				+ longi
				+ "&zoom=17&size=400x200&sensor=false&markers=size:mid%7Ccolor:0x0000ff%7Clabel:R%7C"
				+ lati + "," + longi;
		Bitmap bmp = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet request = new HttpGet(URL);

		InputStream in = null;
		try {
			in = httpclient.execute(request).getEntity().getContent();
			bmp = BitmapFactory.decodeStream(in);
			in.close();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bmp;
	}
	
}
