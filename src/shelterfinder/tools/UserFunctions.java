package shelterfinder.tools;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import shelterfinder.objects.User;



import android.content.Context;
import android.util.Log;

public class UserFunctions {
	JSONParser jsonParser;
	public static final String USER_ID = "UserID";
	public static final String USERNAME = "Username";
	public static final String PASSWORD = "Password";
	public static final String EMAIL = "Email";
	public static final String FULL_NAME = "FullName";
	public static final String AVATAR = "Avatar";
	
	public UserFunctions() {
		jsonParser = new JSONParser();
	}

	// thực hiện công việc đăng nhập với username và password
	public User loginUser(String username, String password) {	
		// xây dựng các giá trị gửi đi để đăng nhập
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", Constants.LOGIN_TAG));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = jsonParser.getJSONFromUrl(Constants.HOST_URL, params);
		
		// phân tích đối tượng JSON thành một user
		try {
			User userLogin = new User();
			userLogin.setUserId(Integer.parseInt(json.getString(USER_ID)));
			userLogin.setUsername(json.getString(USERNAME));
			userLogin.setPassword(json.getString(PASSWORD));
			userLogin.setEmail(json.getString(EMAIL));
			userLogin.setFullName(json.getString(FULL_NAME));
			userLogin.setAvatar(json.getString(AVATAR));
			return userLogin;
		}
		catch (JSONException e) {
			Log.e(getClass().getName(), "Đăng nhập không thành công!");
			return null;
		}
	}
	
	public boolean submitField(String username, String field, String value) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", Constants.SUBMIT_FIELD_USER));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("field", field));
		params.add(new BasicNameValuePair("value", value));
		JSONObject json = jsonParser.getJSONFromUrl(Constants.HOST_URL, params);
		
		// phân tích json trả về
		try {
			if (Integer.parseInt(json.getString(Constants.RESPONSE_CODE)) == Constants.OK) {
				return true;
			}
		} catch (Exception e) {
			Log.e(getClass().getName(), "Lỗi thay đổi dữ liệu tài khoản");
		}

		return false;

	}

	// thực hiện công việc đăng ký tài khoản
	public boolean registerUser(String username, String password,
			String email, String fullName, String avatar) {
		// xây dựng các giá trị để gửi lên server đăng ký tài khoản
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", Constants.REGISTER_TAG));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("fullName", fullName));
		params.add(new BasicNameValuePair("avatar", avatar));
		JSONObject json = jsonParser.getJSONFromUrl(Constants.HOST_URL, params);
		
		// phân tích json trả về?
		try {
			if (Integer.parseInt(json.getString(Constants.RESPONSE_CODE)) == Constants.OK) {
				return true;
			}	
		}
		catch (Exception e) {
			Log.e(getClass().getName(), "Lỗi đăng ký tài khoản");
		}
		
		return false;
	}

}
