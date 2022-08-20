package com.eminencetechnologies.firebaseconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();
    DatabaseReference backgroundReference = reference.child("background");

    Button red, green, blue;

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.layout_background);

        red = findViewById(R.id.buttonRed);
        green = findViewById(R.id.buttonGreen);
        blue = findViewById(R.id.buttonBlue);

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundReference.setValue("Red");
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundReference.setValue("Green");
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundReference.setValue("Blue");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        backgroundReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String backgroundColour = snapshot.getValue().toString();
                switch (backgroundColour) {
                    case "Red":
                        constraintLayout.setBackgroundColor(Color.rgb(255, 0, 0));
                        break;
                    case "Green":
                        constraintLayout.setBackgroundColor(Color.rgb(0, 255, 0));
                        break;
                    case "Blue":
                        constraintLayout.setBackgroundColor(Color.rgb(0,0,255));
                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}