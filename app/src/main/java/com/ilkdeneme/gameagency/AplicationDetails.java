package com.ilkdeneme.gameagency;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AplicationDetails extends AppCompatActivity {

    private TextView appName;
    private ImageView image1, image2, image3;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplication_details);

        // View bileşenlerini tanımla
        appName = findViewById(R.id.app_name);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        ratingBar = findViewById(R.id.ratingBar);

        // Varsayılan değerleri ayarla (gerekiyorsa)
        appName.setText("Örnek Uygulama İsmi");

        // ImageView'lara örnek resimler ekle (gerekiyorsa)
        image1.setImageResource(R.drawable.ic_launcher_foreground);
        image2.setImageResource(R.drawable.ic_launcher_foreground);
        image3.setImageResource(R.drawable.ic_launcher_foreground);

        // RatingBar'da yapılacak işlemler
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    // Kullanıcı puanı değiştirdiğinde yapılacak işlemler

                    // RatingBar id'sine göre puan ver
                    int ratingBarId = ratingBar.getId();

                    // Puanı bir TextView'da göster
                    appName.setText("Puanınız: " + rating);

                    // Puanı bir veritabanına kaydet (örnek olarak SQLite kullanımı)
                    SQLiteDatabase db = openOrCreateDatabase("RatingsDB", MODE_PRIVATE, null);
                    db.execSQL("CREATE TABLE IF NOT EXISTS Ratings (RatingBarId INTEGER, Rating FLOAT);");
                    db.execSQL("INSERT INTO Ratings (RatingBarId, Rating) VALUES (" + ratingBarId + ", " + rating + ");");

                    // Veritabanını kapat
                    db.close();
                }
            }
        });
    }
}
