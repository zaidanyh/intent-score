package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView Result;
    private String Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Result = findViewById(R.id.textView3);

        Bundle bun = getIntent().getExtras();
        if (bun != null) {
            Message = bun.getString("RESULT_KEY");
            Result.setText(Message);
        }
    }
}
