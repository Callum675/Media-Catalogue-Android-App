package com.example.movieandsongcatalogue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.movieandsongcatalogue.data.Detail;
import com.example.movieandsongcatalogue.data.DetailsRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bnv = findViewById(R.id.bottomNavigationView);
        bnv.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.miAppBarSettings:
                NavController navController = Navigation.findNavController(findViewById(R.id.fragmentContainerViewHome));
                int currentFragmentId = navController.getCurrentDestination().getId();
                if (currentFragmentId != R.id.settingsFragment) {
                    navController.navigate(R.id.settingsFragment);
                    return true;
                }
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(findViewById(R.id.fragmentContainerViewHome));
        int currentFragmentId = navController.getCurrentDestination().getId();
        if (item.getItemId() == R.id.miHome) {
            if (currentFragmentId != R.id.homeFragment) {
                navController.navigate(R.id.homeFragment);
            }
            return true;
        } else if (item.getItemId() == R.id.miSearch) {
            if (currentFragmentId != R.id.searchFragment) {
                navController.navigate(R.id.searchFragment);
            }
        }
        return true;
    }
}