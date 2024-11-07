package com.jethers.reglogwdb;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudyBluetoothActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studybluetooth);

        TextView bluetoothStudySummary = findViewById(R.id.text_bluetooth_study);

        String study = "Bluetooth is a wireless technology standard used for exchanging data over short distances using short-wavelength UHF radio waves in the ISM band from 2.4 to 2.485 GHz. Developed by Ericsson in 1994 as a wireless alternative to RS-232 data cables, Bluetooth enables the creation of personal area networks (PANs) for fixed and mobile devices.\n\n" +
                "The technology is managed by the Bluetooth Special Interest Group (SIG), which comprises over 30,000 companies in telecommunications, computing, networking, and consumer electronics. While the IEEE initially standardized Bluetooth as IEEE 802.15.1, it no longer maintains the standard; instead, the Bluetooth SIG oversees its development, qualification program, and trademark protection. Manufacturers must meet SIG standards to market their products as Bluetooth devices.\n\n" +
                "Implementation of Bluetooth involves a packet-based protocol with a master-slave structure. A master device can communicate with up to seven slave devices in a piconet, sharing the master's clock. Data packets are exchanged based on the master's clock, which ticks at intervals of 312.5 μs, forming slots and slot pairs for transmission and reception. Masters transmit in even slots, while slaves transmit in odd slots.\n\n" +
                "Connection establishment and communication allow devices to switch roles, enabling a slave to become a master and vice versa, enhancing flexibility. Data transfer occurs between the master and one slave device at a time, with the master rapidly switching between slaves in a round-robin fashion. Being a master is less burdensome than being a slave due to the listening requirements. The Bluetooth specification is vague regarding behavior in complex network topologies like scatternets.";

        bluetoothStudySummary.setText(study);
    }
}


