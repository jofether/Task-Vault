package com.jethers.reglogwdb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class InfraredActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infrared);

        ImageView thumbnailImageView = findViewById(R.id.thumbnailImageView);
        String videoUrl = "https://www.youtube.com/watch?v=vw6hBJ-24GM";
        String thumbnailUrl = "https://img.youtube.com/vi/vw6hBJ-24GM/0.jpg";

        Picasso.get().load(thumbnailUrl).into(thumbnailImageView);

        TextView informationTextView = findViewById(R.id.informationTextView);
        String infoText = "Infrared Communication Overview\n\n" +
                "Infrared communication involves the transmission of data using infrared light waves, which are not visible to the human eye.\n\n" +
                "Frequency Range: Typically operates in the frequency range of 300 GHz to 400 THz.\n\n" +
                "Key Characteristics:\n" +
                "- Line of Sight: Requires a direct line of sight between the transmitter and receiver.\n" +
                "- Short Range: Effective over short distances, ideal for personal area networks.\n" +
                "- Data Rate: Supports data rates ranging from kbps to Mbps.\n\n" +
                "Applications:\n" +
                "- Remote Controls: Used in remote controls for TVs and air conditioners.\n" +
                "- Wireless Data Transfer: Device-to-device data transfer (e.g., between smartphones or laptops).\n" +
                "- Medical Devices: Used in monitoring and data transfer.\n\n" +
                "Advantages:\n" +
                "- Low Interference: Operates in a specific light spectrum.\n" +
                "- Secure Communication: Difficult to intercept, suitable for short-range applications.\n\n" +
                "Disadvantages:\n" +
                "- Limited Range: Ineffective over long distances.\n" +
                "- Obstruction Sensitivity: Performance can be degraded by obstacles.\n";

        informationTextView.setText(infoText);

        thumbnailImageView.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
            startActivity(intent);
        });
    }
}
