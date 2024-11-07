package com.jethers.reglogwdb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    Button helloWorldButton, calculatorButton, countriesFlagButton, infraredButton, bluetoothButton, logoutButton, bluetoothStudyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        helloWorldButton = findViewById(R.id.buttonHelloWorld);
        calculatorButton = findViewById(R.id.buttonCalculator);
        countriesFlagButton = findViewById(R.id.buttonCountriesFlag);
        infraredButton = findViewById(R.id.buttonInfrared);
        bluetoothButton = findViewById(R.id.buttonBluetooth);
        logoutButton = findViewById(R.id.buttonLogout);
        bluetoothStudyButton = findViewById(R.id.buttonBluetoothStudy);

        helloWorldButton.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, HelloWorldActivity.class);
            startActivity(intent);
        });

        calculatorButton.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });

        countriesFlagButton.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, CountriesFlagActivity.class);
            startActivity(intent);
        });

        infraredButton.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, InfraredActivity.class);
            startActivity(intent);
        });

        bluetoothButton.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, BluetoothActivity.class);
            startActivity(intent);
        });

        bluetoothStudyButton.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, StudyBluetoothActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MenuActivity.this, Login.class);
            startActivity(intent);
            finish();
        });
    }
}
