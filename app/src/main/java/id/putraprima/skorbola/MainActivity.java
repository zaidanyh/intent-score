package id.putraprima.skorbola;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import id.putraprima.skorbola.model.Skor;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    private ImageView home, away;
    private EditText homeText, awayText;
    private static final String KEY = "skor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home = findViewById(R.id.home_logo);
        away = findViewById(R.id.away_logo);
        homeText = findViewById(R.id.home_team);
        awayText = findViewById(R.id.away_team);

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == 1) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    home.setImageBitmap(bitmap);
                } catch(IOException e) {
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        } else if (requestCode == 2) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    away.setImageBitmap(bitmap);
                } catch(IOException e) {
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
    public void handleImageHome(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void handleImageAway(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    public void handleNextButton(View view) {
        String HOME = homeText.getText().toString();
        String AWAY = awayText.getText().toString();

        Skor skor = new Skor(HOME, AWAY);
        Intent intent = new Intent(this, MatchActivity.class);
        if (!HOME.isEmpty() && !AWAY.isEmpty()) {
            intent.putExtra(KEY, skor);
            startActivity(intent);
        } else if (HOME.isEmpty()){
            Toast.makeText(this, "Pastikan Nama Tim HOME Terisi!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Pastikan Nama Tim AWAY Terisi!", Toast.LENGTH_SHORT).show();
        }
    }
}
