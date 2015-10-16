package shelterfinder.activities;

import shelterfinder.objects.User;
import shelterfinder.tools.UserFunctions;

import com.shelterfinder.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
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
	
	private void login() {
		String username = usernameEditText.getText().toString().trim();
		String password = passwordEditText.getText().toString().trim();
		UserFunctions userFunctions = new UserFunctions();
		User userLogin = userFunctions.loginUser(username, password);
		// đăng nhập thành công
		if (userLogin != null) {
			Log.i(getClass().getName(), userLogin.toString());
			Toast.makeText(getApplicationContext(), "Bạn đã đăng nhập thành công!", Toast.LENGTH_SHORT).show();
			finish();
		}
		
		else {
			Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void register() {
		Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivity(registerIntent);
	}

}
