package com.ilkdeneme.gameagency;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;

    private CheckBox checkBoxRememberMe;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();



    }


        db = new DatabaseHelper(this);

        Intent registerActivity = new Intent(MainActivity.this, RegisterActivity.class);


        editTextUsername = findViewById(R.id.editText_username);
        editTextPassword = findViewById(R.id.editText_password);
        checkBoxRememberMe = findViewById(R.id.checkBox_rememberMe);
        Button buttonLogin = findViewById(R.id.button_login);
        Button registerButton = findViewById(R.id.registerOpen);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerActivity);
                finish();
            }
        });
    }

    private void loginUser() {
        String email = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        Boolean rememberMe = checkBoxRememberMe.isChecked();

        Log.d("LOGIN", email + " " + password);
        if (email.isEmpty() || password.isEmpty()) {
            showMessage();
            return;
        }
        if (db != null){
            String user = db.checkUser(email, password , rememberMe);
            if (!user.isEmpty()) {
                Toast.makeText(MainActivity.this, "Giriş başarılı.", Toast.LENGTH_SHORT).show();
                Intent welcome = new Intent(MainActivity.this,DisplayMessageActivity.class);
                welcome.putExtra("NAME_SURNAME", user);
                startActivity(welcome);
            } else {
                Toast.makeText(MainActivity.this, "Giriş başarısız. Kullanıcı adı veya şifre yanlış.", Toast.LENGTH_SHORT).show();
            }
        }else {
            Log.d("DB", "DB NESNESİ NULL");
        }

    }

    private void showMessage() {
        Snackbar.make(findViewById(R.id.button_login), "Kullanıcı adı ve şifre boş bırakılamaz", Snackbar.LENGTH_LONG).show();
    }

}
