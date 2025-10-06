package com.example.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView showTv;
    Button btnLogut;
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
                showTv = findViewById(R.id.TView);
                Intent int1 = getIntent();
                String email = "Welecom : \n" +int1.getStringExtra("email");
                showTv.setText(email);

             btnLogut = findViewById(R.id.btnLogout);

             btnLogut.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     SharedPreferences sp = getSharedPreferences("LoginPrefs",MODE_PRIVATE);
                     SharedPreferences.Editor editor = sp.edit();
                     editor.clear();
                     editor.apply();
                     Intent inten = new Intent(MainActivity.this , Login.class);
                     startActivity(inten);
                     finish();


                 }
             });







    }
}