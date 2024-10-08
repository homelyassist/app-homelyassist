package com.homelyassist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.homelyassist.databinding.ActivityMainBinding;
import com.homelyassist.ui.dashboard.DashboardFragment;
import com.homelyassist.ui.home.HomeFragment;
import com.homelyassist.ui.notifications.NotificationsFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadFragment(new HomeFragment());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            if (item.getItemId() == R.id.navigation_register_login) {
                //loadWebPage("http://192.168.1.65:8080/app/assist");
                selectedFragment = new DashboardFragment();
                loadFragment(selectedFragment);
                return true;
            } else if (item.getItemId() == R.id.navigation_help) {
                selectedFragment = new NotificationsFragment();
                loadFragment(selectedFragment);
                return true;
            }else if(item.getItemId() == R.id.navigation_home) {
                loadFragment(new HomeFragment());
                return true;
            }
            return false;
        });
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_register_login, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_main, fragment);
        transaction.commit();
    }
}