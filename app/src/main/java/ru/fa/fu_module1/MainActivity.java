package ru.fa.fu_module1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;
    TextView debugTextView;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.infoTextView);
        editText = findViewById(R.id.editTextNumber);
        button = findViewById(R.id.button);
        debugTextView = findViewById(R.id.debugInfo);

        startGame();

    }

    private void startGame() {
        int currentNumber = random.nextInt(100) + 1; // 1-100
        textView.setText(R.string.try_to_guess);
        editText.setText("");
        button.setText(R.string.input_value);
        debugTextView.setText(String.valueOf(currentNumber));

        final int[] tries = {1};  // В лямбда-функции переменные должны быть final

        button.setOnClickListener(v -> {
            try {
                int input = Integer.parseInt(editText.getText().toString());
                if (input < 1 || input > 100) {
                    textView.setText(R.string.range_error);
                }
                else if (input < currentNumber) {
                    textView.setText(R.string.behind);
                    tries[0]++;
                } else if (input > currentNumber) {
                    textView.setText(R.string.ahead);
                    tries[0]++;
                } else {
                    gameOver(tries[0], currentNumber);
                }
            } catch (Exception e) {
                textView.setText(R.string.error);
            }
        });
    }

    private void gameOver(int tries, int number) {
        textView.setText(R.string.hit);
        button.setText(R.string.play_more);
        button.setOnClickListener(v -> startGame());
        Toast.makeText(this, String.format("Вы угадали число %d за %d попыток!", number, tries), Toast.LENGTH_LONG).show();
    }
}