package shelterfinder.fragments;

import shelterfinder.activities.LoginActivity;
import shelterfinder.activities.MainActivity;
import shelterfinder.objects.User;
import shelterfinder.tools.Constants;
import android.R.color;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shelterfinder.R;

public class UserFragment extends Fragment {
	LinearLayout informationUserLayout;
	TextView fullNameTextView;
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_user, container, false);
		informationUserLayout = (LinearLayout) view.findViewById(R.id.informationUserLayout);
		fullNameTextView = (TextView) view.findViewById(R.id.fullNameTextView);
		informationUserLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (MainActivity.getUserLogin() != null) {
					
				}
				else {
					Intent loginIntent = new Intent(UserFragment.this.getActivity(), LoginActivity.class);
					startActivityForResult(loginIntent, Constants.LOGIN_CODE);
				}
			}
		});
		
		return view;

	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Constants.LOGIN_CODE) {
			if (resultCode == Constants.GET_USER_LOGIN_CODE) {
				fullNameTextView.setText(data.getStringExtra("FullName"));
			}
		}
	}
}
