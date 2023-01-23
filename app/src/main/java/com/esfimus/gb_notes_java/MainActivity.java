package com.esfimus.gb_notes_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.esfimus.gb_notes_java.menu.MenuAboutFragment;
import com.esfimus.gb_notes_java.menu.MenuSearchFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initDrawer();
    }

    private void initView() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_notes, new NotesFragment())
                .commit();
    }

    @SuppressLint("NonConstantResourceId")
    private void initDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.drawer_about: {
                            openToolbarFragment(MenuAboutFragment.newInstance());
                            drawer.close();
                            return true;
                        }
                        case R.id.drawer_search: {
                            openToolbarFragment(MenuSearchFragment.newInstance());
                            drawer.close();
                            return true;
                        }
                    }
                    return false;
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_about: {
                openToolbarFragment(MenuAboutFragment.newInstance());
                return true;
            }
            case R.id.toolbar_search: {
                openToolbarFragment(MenuSearchFragment.newInstance());
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void openToolbarFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_notes, fragment)
                .addToBackStack("")
                .commit();
    }

    private void toastCheck() {
        Toast.makeText(this, "button works", Toast.LENGTH_SHORT).show();
    }

}