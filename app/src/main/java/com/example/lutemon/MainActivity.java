package com.example.lutemon;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);

        tabLayout.selectTab(tabLayout.getTabAt(1));
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new Home()).addToBackStack(null).commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Storage storage = Storage.getInstance();
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        if (storage.getLutemons().isEmpty()) {
                            fragment = new Home();
                            tabLayout.selectTab(tabLayout.getTabAt(1));
                            break;
                        } else {
                            fragment = new TrainingArea();
                            break;
                        }

                    case 1:
                        fragment = new Home();
                        break;
                    case 2:
                        if (storage.getLutemons().isEmpty()) {
                            fragment = new Home();
                            tabLayout.selectTab(tabLayout.getTabAt(1));
                            break;
                        } else {
                            fragment = new BattleField();
                            break;
                        }
                }
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.framelayout, fragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .commit();

                }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public void switchToAddLutemon(View view) {
        Intent intent = new Intent(this, AddLutemonActivity.class);
        startActivity(intent);
    }
}
