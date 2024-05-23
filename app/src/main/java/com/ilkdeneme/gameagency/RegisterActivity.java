package com.ilkdeneme.gameagency;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    DatabaseHelper db;
    RadioGroup gender;
    RadioButton checkedGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);

        editTextEmail = findViewById(R.id.editTextEmail1);
        editTextPassword = findViewById(R.id.editTextPassword1);
        gender = findViewById(R.id.radioGroup_gender);

        Button buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Log.d("ACTV",gender.getCheckedRadioButtonId()+"");
                checkedGender = findViewById(gender.getCheckedRadioButtonId());
                registerUser();
            }
        });
    }

    private void registerUser() {
        if (checkedGender != null && db != null) {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String gender = checkedGender.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Formdaki bütün alanları doldurunuz", Toast.LENGTH_SHORT).show();
                return;
            }

            db.addUser(email, password, gender);
            Toast.makeText(RegisterActivity.this, "Kayıt başarılı!", Toast.LENGTH_SHORT).show();
            Intent login = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(login);
            finish();

        }else {
            Toast.makeText(RegisterActivity.this, "Formdaki bütün alanları doldurunuz", Toast.LENGTH_SHORT).show();
        }


    }
}
