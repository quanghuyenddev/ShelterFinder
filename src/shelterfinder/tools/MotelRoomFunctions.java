package shelterfinder.tools;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import shelterfinder.objects.MotelRoom;

public class MotelRoomFunctions {
	private JSONParser jsonParser;
	
	public static final String MOTEL_ROOM_ID = "MotelRoomID";
	public static final String ADDRESS = "Address";
	public static final String AREA = "Area";
	public static final String PRICE = "Price";
	public static final String PHONE = "Phone";
	public static final String LATITUDE = "Latitude";
	public static final String LONGITUDE = "Longitude";
	public static final String DESCRIPTION = "Description";
	public static final String CITY = "City";
	public static final String TIME_POSTED = "TimePosted";
	public static final String USER_ID_POSTED = "UserIDPosted";
	public static final String IMAGES = "Images";
	
	// bình luận của phòng trọ
	public static final String COMMENT_ID = "CommentID";
	public static final String COMMENT = "Comment";
	
	
	public MotelRoomFunctions() {
		jsonParser = new JSONParser();
	}
	
	public ArrayList<MotelRoom> getAllMotelRoom() {
		ArrayList<MotelRoom> motelRooms = new ArrayList<MotelRoom>();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", Constants.GET_ALL_MOTEL_ROOM));
		ArrayList<JSONObject> jsonRoomArray = jsonParser.getJSONArrayListFromUrl(
				Constants.HOST_URL, params);
		try {
			int numRooms = jsonRoomArray.size();
			for (int i = 0; i < numRooms; i++) {
				JSONObject jsonRoom = jsonRoomArray.get(i);
				MotelRoom room = new MotelRoom();
				room.setMotelRoomID(jsonRoom.getInt(MOTEL_ROOM_ID));
				room.setAddress(jsonRoom.getString(ADDRESS));
				room.setArea(jsonRoom.getDouble(AREA));
				room.setPrice(jsonRoom.getLong(PRICE));
				room.setPhone(jsonRoom.getString(PHONE));
				room.setLatitude(jsonRoom.getDouble(LATITUDE));
				room.setLongitude(jsonRoom.getDouble(LONGITUDE));
				room.setDescription(jsonRoom.getString(DESCRIPTION));
				room.setCity(jsonRoom.getString(CITY));
				room.setTimePosted(jsonRoom.getString(TIME_POSTED));
				room.setUserIDPosted(jsonRoom.getString(USER_ID_POSTED));
				room.setImages(jsonRoom.getString(IMAGES));
				motelRooms.add(room);
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return motelRooms;
	}
	
	public boolean submitCommentMotel(String comment, String userIDPosted, String motelRoomID) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", Constants.SUBMIT_COMMENT_MOTEL));
		params.add(new BasicNameValuePair("comment", COMMENT));
		params.add(new BasicNameValuePair("userIDPosted", USER_ID_POSTED));
		params.add(new BasicNameValuePair("motelRoomID", MOTEL_ROOM_ID));
		JSONObject json = jsonParser.getJSONFromUrl(Constants.HOST_URL, params);
		
		// phân tích json trả về?
		try {
			if (Integer.parseInt(json.getString(Constants.RESPONSE_CODE)) == Constants.OK) {
				return true;
			}	
		}
		catch (Exception e) {
			Log.e(getClass().getName(), "Lỗi đăng bình luận");
		}
		
		return false;
	}
	
	
}
