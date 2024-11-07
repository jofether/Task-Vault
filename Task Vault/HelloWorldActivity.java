package com.jethers.reglogwdb;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HelloWorldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        TextView helloWorldTextView = findViewById(R.id.helloWorldTextView);
        helloWorldTextView.setText(R.string.hello_world);
    }
}
