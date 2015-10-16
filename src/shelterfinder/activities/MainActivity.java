package shelterfinder.activities;



import java.util.ArrayList;
import java.util.List;

import shelterfinder.adapters.ShelterFinderPageAdapter;
import shelterfinder.fragments.GoogleMapFragment;
import shelterfinder.fragments.SearchFragment;
import shelterfinder.fragments.StatusFragment;
import shelterfinder.fragments.UserFragment;

import com.shelterfinder.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;

public class MainActivity extends FragmentActivity implements OnPageChangeListener, OnTabChangeListener {
	ViewPager viewPager;
	TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initViewPager();
		initTabHost();
	}
	
	private void initTabHost() {
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		String[] tabNames = {"Status", "Search", "GoogleMap", "User"}; 
		for (int i = 0; i < tabNames.length; i++) {
			TabHost.TabSpec tabSpec;
			tabSpec = tabHost.newTabSpec(tabNames[i]);
			tabSpec.setIndicator(tabNames[i]);
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
		listFragments.add(new StatusFragment());
		listFragments.add(new SearchFragment());
		listFragments.add(new GoogleMapFragment());
		listFragments.add(new UserFragment());
		
		ShelterFinderPageAdapter pageAdapter = new ShelterFinderPageAdapter(getSupportFragmentManager(), listFragments);
		viewPager.setAdapter(pageAdapter);
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
		tabHost.setCurrentTab(selectedItem);
	}
	
	
	
}
