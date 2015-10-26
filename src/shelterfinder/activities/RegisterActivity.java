package shelterfinder.activities;


import shelterfinder.tools.UserFunctions;

import com.shelterfinder.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener {
	Button submitButton;
	EditText usernameEditText;
	EditText passwordEditText;
	EditText retypeEditText;
	EditText emailEditText;
	EditText fullNameEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_register);
		
		submitButton = (Button) findViewById(R.id.submitButton);
		submitButton.setOnClickListener(this);
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		retypeEditText = (EditText) findViewById(R.id.retypeEditText);
		emailEditText = (EditText) findViewById(R.id.emailEditText);
		fullNameEditText = (EditText) findViewById(R.id.fullNameEditText);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submitButton:
			registerUser();
			break;
		}
		
	}
	
	private void registerUser() {
		String username = usernameEditText.getText().toString().trim();
		String password = passwordEditText.getText().toString().trim();
		String retypePassword = retypeEditText.getText().toString().trim();
		String email = emailEditText.getText().toString().trim();
		String fullName = fullNameEditText.getText().toString().trim();
		
		if (username.equals("") || password.equals("")
				|| retypePassword.equals("") || email.equals("")
				|| fullName.equals("")) {
			Toast.makeText(getApplicationContext(), "Vui lòng nhập hết dữ liệu yêu cầu!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (!password.equals(retypePassword)) {
			Toast.makeText(getApplicationContext(), "Mật khẩu và mật khẩu nhập lại không khớp!", Toast.LENGTH_SHORT).show();
			passwordEditText.setText("");
			retypeEditText.setText("");
			return;
		}
		
		UserFunctions userFunctions = new UserFunctions();
		String avatar = "test";
		boolean registerOK = userFunctions.registerUser(username, password, email, fullName, avatar);
		if (registerOK) {
			Toast.makeText(getApplicationContext(), "Bạn đã đăng ký thành công!", Toast.LENGTH_SHORT).show();
			finish();
		}
		else {
			Toast.makeText(getApplicationContext(), "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
		}
		
	}
	
}
