package com.example.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Login extends AppCompatActivity {

    EditText user,pass;
    Button btnLog;

    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        sp = getSharedPreferences("LoginPrefs" , MODE_PRIVATE);
        boolean isLogeedIn = sp.getBoolean("isLoggedIn",false);
        if(isLogeedIn){
            Intent intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        

        user = findViewById(R.id.userEdit);
        pass = findViewById(R.id.passwordEdit);
        btnLog = findViewById(R.id.btnLogin);


        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1 = user.getText().toString();
                String pass1 = pass.getText().toString();
                if(TextUtils.isEmpty(user1)){
                user.setError(" Email must not  be null ");
                }
                else if(TextUtils.isEmpty(pass1)){
                    pass.setError(" Password must not be null ");
                }else {

                    if(Patterns.EMAIL_ADDRESS.matcher(user1).matches()){
                        Toast.makeText(Login.this,"تم تسجيل الدخول",Toast.LENGTH_SHORT).show();

                        sp = getSharedPreferences("LoginPrefs",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("isLoggedIn",true);
                        editor.apply();
                        Intent int1 = new Intent(Login.this,MainActivity.class);
                        int1.putExtra("email",user1);
                        startActivity(int1);
                    }else {
                        user.setError(" ادخل الايميل بشكل صحيح ");
                    }

                }
            }
        });





    }
}