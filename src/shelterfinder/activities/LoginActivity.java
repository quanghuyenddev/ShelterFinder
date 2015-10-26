package shelterfinder.activities;

import shelterfinder.objects.User;
import shelterfinder.tools.Constants;
import shelterfinder.tools.UserFunctions;

import com.shelterfinder.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{
	Button loginButton;
	Button registerButton;
	EditText usernameEditText;
	EditText passwordEditText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_login);
		
		loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(this);
		registerButton = (Button) findViewById(R.id.registerButton);
		registerButton.setOnClickListener(this);
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.loginButton:
			login();
			break;
		case R.id.registerButton:
			register();
			break;
		}
	}
	
	class LoginTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			String username = usernameEditText.getText().toString().trim();
			String password = passwordEditText.getText().toString().trim();
			UserFunctions userFunctions = new UserFunctions();
			MainActivity.userLogin = userFunctions.loginUser(username, password);
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			if (MainActivity.userLogin != null) {
				Log.i(getClass().getName(), MainActivity.userLogin.toString());
				Toast.makeText(getApplicationContext(), "Bạn đã đăng nhập thành công!", Toast.LENGTH_SHORT).show();
				Intent data = new Intent();
				data.putExtra("FullName", MainActivity.userLogin.getFullName());
				data.putExtra("Avatar", MainActivity.userLogin.getAvatar());
				setResult(Constants.GET_USER_LOGIN_CODE, data);
				finish();
			}
			
			else {
				Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	private void login() {
		
		new LoginTask().execute();
	}
	
	private void register() {
		Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivity(registerIntent);
	}

}
