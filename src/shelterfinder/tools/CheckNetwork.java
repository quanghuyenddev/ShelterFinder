package shelterfinder.tools;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckNetwork
{
	public static boolean isOnline(Context paramContext) {
	    ConnectivityManager cm =
	        (ConnectivityManager) paramContext.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    return netInfo != null && netInfo.isConnectedOrConnecting();
	}
}