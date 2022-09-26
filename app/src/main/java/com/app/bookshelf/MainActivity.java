package com.app.bookshelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.app.bookshelf.Api.ApiInterface;
import com.app.bookshelf.Api.RetrofitApi;
import com.app.bookshelf.R;
import com.app.bookshelf.model.MyBooks;
import com.app.bookshelf.model.MyList;
import com.app.bookshelf.model.MyResults;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);

        //add nav bottom
        BottomNavigationView bottomNav = findViewById(R.id.bottom_app_bar);
        NavigationUI.setupWithNavController(bottomNav, navController);

        NavInflater navInflater = navController.getNavInflater();
        NavGraph graph = navInflater.inflate(R.navigation.mobile_navigation);
        graph.setStartDestination(R.id.action_topStories);
        navController.setGraph(graph);

    }
}