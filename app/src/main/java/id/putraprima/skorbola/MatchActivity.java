package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import id.putraprima.skorbola.model.Skor;

public class MatchActivity extends AppCompatActivity {
    private TextView TextHome, TextAway, scoreH, scoreA;
    private int scoreHome, scoreAway;
    private ImageView HomeImage, AwayImage;
    private Bitmap HomeBitmap, AwayBitmap;
    private Uri homeUri, awayUri;
    private String TeamHome, TeamAway, result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
        TextHome = findViewById(R.id.txt_home);
        TextAway = findViewById(R.id.txt_away);
        HomeImage = findViewById(R.id.home_logo);
        AwayImage = findViewById(R.id.away_logo);
        scoreH = findViewById(R.id.score_home);
        scoreA = findViewById(R.id.score_away);
        TeamHome = TextHome.getText().toString();
        TeamAway = TextAway.getText().toString();

        Bundle bun = getIntent().getExtras();
        Skor skor = bun.getParcelable("skor");

        if (!bun.isEmpty()) {
            TeamHome = skor.getHometext();
            TeamAway = skor.getAwaytext();
            TextHome.setText(skor.getHometext());
            TextAway.setText(skor.getAwaytext());
            homeUri = skor.getHomeUri();
            awayUri = skor.getAwayUri();
            scoreH.setText("0");
            scoreA.setText("0");
            scoreHome = Integer.parseInt(scoreH.getText().toString());
            scoreAway = Integer.parseInt(scoreA.getText().toString());

            try {
                HomeBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeUri);
            } catch(IOException e) {
                e.printStackTrace();
            }
            try {
                AwayBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayUri);
            } catch(IOException e) {
                e.printStackTrace();
            }
            HomeImage.setImageBitmap(HomeBitmap);
            AwayImage.setImageBitmap(AwayBitmap);
        }
    }

    public void handleHomeScore(View view) {
        scoreHome += 1;
        scoreH.setText(Integer.toString(scoreHome));
    }

    public void handleAwayScore(View view) {
        scoreAway += 1;
        scoreA.setText(Integer.toString(scoreAway));
    }

    public void handleResult(View view) {
        if (scoreHome > scoreAway) {
            result = TeamHome+" WIN";
        } else if (scoreHome < scoreAway) {
            result = TeamAway+" WIN";
        } else {
            result = "DRAW";
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("RESULT_KEY", result);
        startActivity(intent);
    }
}
