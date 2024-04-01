package com.ilkdeneme.gameagency;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private CheckBox checkBoxRememberMe;
    private RadioGroup radioGroupGender;
    private Button buttonLogin;
    private Button buttonRegister; // Kayıt Ol butonu için değişken eklendi

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Firebase Auth instance'ını başlat
        mAuth = FirebaseAuth.getInstance();

        // UI Komponentlerini bağla
        editTextUsername = findViewById(R.id.editText_username);
        editTextPassword = findViewById(R.id.editText_password);
        checkBoxRememberMe = findViewById(R.id.checkBox_rememberMe);
        radioGroupGender = findViewById(R.id.radioGroup_gender);
        buttonLogin = findViewById(R.id.button_login);
        buttonRegister = findViewById(R.id.button_register); // Kayıt Ol butonunu bağla

        // Giriş Yap butonu dinleyicisi
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        // Kayıt Ol butonu dinleyicisi
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // RegisterActivity'e geçiş yap
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser() {
        String email = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Basit bir validasyon
        if (email.isEmpty() || password.isEmpty()) {
            showMessage("Kullanıcı adı ve şifre boş bırakılamaz");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Giriş başarılı, kullanıcı bilgileri ile devam et
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // Giriş başarısız, kullanıcıyı bilgilendir
                        showMessage("Giriş başarısız: " + task.getException().getMessage());
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // Kullanıcı giriş yaptı, ana ekrana yönlendir veya giriş başarılı mesajı göster
            showMessage("Giriş başarılı: " + user.getEmail());
            // Intent ile ana ekrana yönlendirme işlemi burada yapılabilir
        } else {
            // Kullanıcı giriş yapamadı, hata mesajı göster
        }
    }

    private void showMessage(String message) {
        Snackbar.make(findViewById(R.id.button_login), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Uygulama başlatıldığında, kullanıcının zaten giriş yapıp yapmadığını kontrol et
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
}
