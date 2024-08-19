package com.example.myviews;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import com.example.mylibrary.circleprogress.CircleProgress;
import com.example.mylibrary.initialsindicator.InitialsIndicator;
import com.example.mylibrary.loadingbutton.LoadingButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] colors = new int[]{R.color.black, R.color.red, R.color.green, R.color.blue, R.color.yellow};
        Button random = findViewById(R.id.random);
        CircleProgress circleProgress = findViewById(R.id.circle_progress);
        random.setOnClickListener(v -> {
            Random rand = new Random();
            int bgIndex = rand.nextInt(5);
            int colorIndex;
            while ((colorIndex = rand.nextInt(5)) == bgIndex) {}
            int max = rand.nextInt(10) + 1;
            int step = rand.nextInt(max);
            circleProgress.setStrokeColor(colors[colorIndex]);
            circleProgress.setCircleBgColor(colors[bgIndex]);
            circleProgress.setSteps(step, max);
            circleProgress.setText(step + "/" + max);
        });

        InitialsIndicator initialsIndicator = findViewById(R.id.indicator);
        initialsIndicator.setImageUrl("https://encrypted-tbn2.gstatic.com/licensed-image?q=tbn:ANd9GcRbxE0r37OJ2A3W1VjwimRJX2WwxBoYbYAubAOLlj4P_TH2YMKz43r-uWuFcnJYHcNfWMZDvfvZlK7QZqg");

        LoadingButton loadingButton = findViewById(R.id.loader);
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_LONG).show();
            }
        });


    }
}