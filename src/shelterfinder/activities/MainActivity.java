package shelterfinder.activities;



import java.util.ArrayList;
import java.util.List;

import shelterfinder.adapters.ShelterFinderPageAdapter;
import shelterfinder.fragments.AboutFragment;
import shelterfinder.fragments.GoogleMapFragment;
import shelterfinder.fragments.SearchFragment;
import shelterfinder.fragments.StatusFragment;
import shelterfinder.fragments.UserFragment;
import shelterfinder.objects.User;
import shelterfinder.tools.CheckNetwork;

import com.shelterfinder.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;

public class MainActivity extends FragmentActivity implements OnPageChangeListener, OnTabChangeListener {
	ViewPager viewPager;
	TabHost tabHost;
	public static User userLogin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (!CheckNetwork.isOnline(this)) {
			Toast.makeText(this, "Vui lòng kết nối mạng", Toast.LENGTH_SHORT).show();
			return;
		}
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		userLogin = null;
		initViewPager();
		initTabHost();
	}
	
	public static User getUserLogin() {
		return userLogin;
	}
	
	private void initTabHost() {
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		String[] tabNames = {"Status", "Search", "GoogleMap", "User"}; 
		int[] tabLogoIds = { R.drawable.ic_main, R.drawable.ic_search,
				R.drawable.ic_google_map, R.drawable.ic_info };
		for (int i = 0; i < tabNames.length; i++) {
			TabHost.TabSpec tabSpec;
			tabSpec = tabHost.newTabSpec(tabNames[i]);
			tabSpec.setIndicator("", getResources().getDrawable(tabLogoIds[i]));
			tabSpec.setContent(new FakeContent(getApplicationContext()));
			tabHost.addTab(tabSpec);
		}
		tabHost.setOnTabChangedListener(this);
		
	}
	
	public class FakeContent implements TabContentFactory {
		Context context;
		public FakeContent(Context context) {
			this.context = context;
		}
		@Override
		public View createTabContent(String arg0) {
			View fakeView = new View(context);
			fakeView.setMinimumHeight(0);
			fakeView.setMinimumWidth(0);
			return fakeView;
		}
		
	}

	private void initViewPager() {
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		List<Fragment> listFragments = new ArrayList<Fragment>();
		listFragments.add(Fragment.instantiate(this, StatusFragment.class.getName()));
		listFragments.add(Fragment.instantiate(this, SearchFragment.class.getName()));
		listFragments.add(Fragment.instantiate(this, GoogleMapFragment.class.getName()));
		listFragments.add(Fragment.instantiate(this, UserFragment.class.getName()));
		listFragments.add(Fragment.instantiate(this, AboutFragment.class.getName()));
		
		ShelterFinderPageAdapter pageAdapter = new ShelterFinderPageAdapter(getSupportFragmentManager(), listFragments);
		viewPager.setAdapter(pageAdapter);
		viewPager.setOffscreenPageLimit(listFragments.size());
		viewPager.setOnPageChangeListener(this);
	}

	@Override
	public void onTabChanged(String tabId) {
		int selectedItem = tabHost.getCurrentTab();
		viewPager.setCurrentItem(selectedItem);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
			
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int selectedItem) {
		if (selectedItem > 3) {
			tabHost.setCurrentTab(3);
		}
		else {
			tabHost.setCurrentTab(selectedItem);
		}
	}
	
	
	
}
