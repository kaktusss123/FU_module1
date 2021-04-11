package ru.fa.fu_module1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btnAdd;
    Button btnSub;
    Button btnDrop;
    int clicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.infoTextView);
        btnAdd = findViewById(R.id.buttonAdd);
        btnSub = findViewById(R.id.buttonSub);
        btnDrop = findViewById(R.id.buttonDrop);

        refreshInfo(clicks);

        btnAdd.setOnClickListener(v -> refreshInfo(clicks + 1));
        btnSub.setOnClickListener(v -> refreshInfo(Math.max(clicks - 1, 0)));
        btnDrop.setOnClickListener(v -> refreshInfo(0));
    }

    private void refreshInfo(int clicks) {
        this.clicks = clicks;
        textView.setText(String.format(getString(R.string.click_info) + (doWeNeedA(clicks) ? "а" : ""), clicks));
    }

    private boolean doWeNeedA(int number) {
        return "234".contains(String.valueOf(number % 10)) && (number < 12 || number > 14);
    }
}