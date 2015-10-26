package shelterfinder.tools;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import shelterfinder.objects.MotelRoom;
import shelterfinder.objects.MotelRoomComment;

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
	
	public MotelRoomComment submitCommentMotel(String comment, String userIDPosted, String motelRoomID) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", Constants.SUBMIT_COMMENT_MOTEL));
		params.add(new BasicNameValuePair("comment", comment));
		params.add(new BasicNameValuePair("userIDPosted", userIDPosted));
		params.add(new BasicNameValuePair("motelRoomID", motelRoomID));
		JSONObject json = jsonParser.getJSONFromUrl(Constants.HOST_URL, params);
		
		// phân tích json trả về?
		try {
			MotelRoomComment commentMotel = new MotelRoomComment();
			commentMotel.setCommentID(json.getString(COMMENT_ID));
			commentMotel.setComment(json.getString(COMMENT));
			commentMotel.setMotelRoomIDPosted(json.getString(MOTEL_ROOM_ID));
			commentMotel.setTimePosted(json.getString(TIME_POSTED));
			commentMotel.setUserIDPosted(json.getString(USER_ID_POSTED));
			return commentMotel;
		}
		catch (Exception e) {
			Log.e(getClass().getName(), "Lỗi đăng bình luận");
			return null;
		}
	}
	
	public ArrayList<MotelRoomComment> getCommentByMotelRoom(String motelRoomID) {
		ArrayList<MotelRoomComment> comments = new ArrayList<MotelRoomComment>();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", Constants.GET_COMMENT_BY_MOTEL));
		params.add(new BasicNameValuePair("motelRoomID", motelRoomID));
		ArrayList<JSONObject> jsonCommentArray = jsonParser.getJSONArrayListFromUrl(
				Constants.HOST_URL, params);
		try {
			int numComments = jsonCommentArray.size();
			for (int i = 0; i < numComments; i++) {
				JSONObject jsonComment = jsonCommentArray.get(i);
				MotelRoomComment comment = new MotelRoomComment();
				comment.setCommentID(jsonComment.getString(COMMENT_ID));
				comment.setComment(jsonComment.getString(COMMENT));
				comment.setTimePosted(jsonComment.getString(TIME_POSTED));
				comment.setUserIDPosted(jsonComment.getString(USER_ID_POSTED));
				comment.setMotelRoomIDPosted(jsonComment.getString(MOTEL_ROOM_ID));
				comments.add(comment);
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return comments;
	}
	
	
}
