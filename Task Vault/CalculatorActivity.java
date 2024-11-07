package com.jethers.reglogwdb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class CalculatorActivity extends AppCompatActivity {

    private TextView display;
    private TextView historyText;
    private TextView resultTextView;
    private ScrollView historyScrollView;
    private final StringBuilder currentInput = new StringBuilder();
    private final List<String> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.display);
        historyText = findViewById(R.id.historyText);
        resultTextView = findViewById(R.id.resultTextView);
        historyScrollView = findViewById(R.id.historyScrollView);

        setButtonListeners();
    }

    private void setButtonListeners() {
        int[] numberButtons = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9
        };

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(v -> onDigitPressed(((Button) v).getText().toString()));
        }

        findViewById(R.id.buttonPlus).setOnClickListener(v -> onOperatorPressed("+"));
        findViewById(R.id.buttonMinus).setOnClickListener(v -> onOperatorPressed("-"));
        findViewById(R.id.buttonMultiply).setOnClickListener(v -> onOperatorPressed("*"));
        findViewById(R.id.buttonDivide).setOnClickListener(v -> onOperatorPressed("/"));
        findViewById(R.id.buttonDot).setOnClickListener(v -> onDotPressed());
        findViewById(R.id.buttonEquals).setOnClickListener(v -> onEqualsPressed());
        findViewById(R.id.buttonClear).setOnClickListener(v -> onClearPressed());
        findViewById(R.id.buttonBackspace).setOnClickListener(v -> onBackspacePressed());
    }

    private void onDigitPressed(String digit) {
        currentInput.append(digit);
        updateDisplay();
    }

    private void onOperatorPressed(String operator) {
        currentInput.append(" ").append(operator).append(" ");
        updateDisplay();
    }

    private void onDotPressed() {
        String input = currentInput.toString();
        int lastOperatorIndex = Math.max(input.lastIndexOf("+"),
                Math.max(input.lastIndexOf("-"),
                        Math.max(input.lastIndexOf("*"), input.lastIndexOf("/"))));

        String lastNumber = input.substring(lastOperatorIndex + 1).trim();
        if (!lastNumber.contains(".")) {
            currentInput.append(".");
            updateDisplay();
        }
    }

    private void onClearPressed() {
        currentInput.setLength(0);
        display.setText("0");
        resultTextView.setText("Result: ");
    }

    private void onBackspacePressed() {
        int length = currentInput.length();
        if (length > 0) {
            currentInput.deleteCharAt(length - 1);
            updateDisplay();
        }
    }

    private void onEqualsPressed() {
        try {
            double result = new SimpleParser().evaluate(currentInput.toString());
            addToHistory(currentInput.toString() + " = " + result);
            currentInput.setLength(0);
            resultTextView.setText("Result: " + result);
        } catch (Exception e) {
            display.setText("Error");
            resultTextView.setText("Result: ");
        } finally {
            updateDisplayAfterEquals();
        }
    }

    private void updateDisplayAfterEquals() {
        display.setText("0");
        currentInput.setLength(0);
    }

    private void updateDisplay() {
        display.setText(currentInput.length() > 0 ? currentInput.toString() : "0");
    }

    private void addToHistory(String entry) {
        history.add(0, entry);
        if (history.size() > 3) {
            history.remove(history.size() - 1);
        }
        historyText.setText(String.join("\n", history));

        historyScrollView.post(() -> historyScrollView.fullScroll(View.FOCUS_DOWN));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
