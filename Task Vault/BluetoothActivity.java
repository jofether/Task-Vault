package com.jethers.reglogwdb;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothActivity extends Activity {

    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_FILE_SELECT = 3;
    private static final UUID MY_UUID = UUID.randomUUID();
    private BluetoothAdapter bluetoothAdapter;
    private ProgressBar progressBar;
    private BluetoothSocket socket;

    private boolean bluetoothPermissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        Button btnSend = findViewById(R.id.btnSend);
        Button btnReceive = findViewById(R.id.btnReceive);
        progressBar = findViewById(R.id.progressBar);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        if (!bluetoothAdapter.isEnabled()) {
            requestBluetoothEnable();
        }

        checkPermissions();

        btnSend.setOnClickListener(v -> {
            if (bluetoothAdapter.isEnabled()) {
                showFilePicker();
            } else {
                requestBluetoothEnable();
            }
        });

        btnReceive.setOnClickListener(v -> {
            if (bluetoothAdapter.isEnabled()) {
                startReceiving();
            } else {
                requestBluetoothEnable();
            }
        });
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                    1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BLUETOOTH},
                    1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    2);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    3);
        }
    }

    private void requestBluetoothEnable() {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
    }

    private void showFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, REQUEST_FILE_SELECT);
    }

    private void startReceiving() {
        progressBar.setVisibility(View.VISIBLE);

        new Thread(() -> {
            try {
                BluetoothServerSocket serverSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord("MyApp", MY_UUID);
                socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();

                File file = new File(getExternalFilesDir(null), "received_file");
                FileOutputStream fileOutputStream = new FileOutputStream(file);

                byte[] buffer = new byte[1024];
                int bytes;
                while ((bytes = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytes);
                }

                fileOutputStream.close();
                inputStream.close();
                serverSocket.close();

                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, "File received successfully!", Toast.LENGTH_SHORT).show();
                });

            } catch (IOException e) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, "Error receiving file", Toast.LENGTH_SHORT).show();
                });
                e.printStackTrace();
            }
        }).start();
    }

    private void sendFile(Uri fileUri) {
        new Thread(() -> {
            try {
                BluetoothDevice device = bluetoothAdapter.getBondedDevices().iterator().next();
                socket = device.createRfcommSocketToServiceRecord(MY_UUID);
                socket.connect();

                OutputStream outputStream = socket.getOutputStream();
                InputStream fileInputStream = getContentResolver().openInputStream(fileUri);

                byte[] buffer = new byte[1024];
                int bytes;
                while ((bytes = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytes);
                }

                fileInputStream.close();
                outputStream.close();
                socket.close();

                runOnUiThread(() -> Toast.makeText(this, "File sent successfully!", Toast.LENGTH_SHORT).show());

            } catch (IOException e) {
                runOnUiThread(() -> Toast.makeText(this, "Error sending file", Toast.LENGTH_SHORT).show());
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
            } else {
                Toast.makeText(this, "Bluetooth is required to proceed", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == REQUEST_FILE_SELECT && resultCode == RESULT_OK && data != null) {
            Uri fileUri = data.getData();
            sendFile(fileUri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (!bluetoothPermissionGranted) {
                bluetoothPermissionGranted = true;
            }
        } else if (requestCode == 1) {
            Toast.makeText(this, "Bluetooth Opened", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == 2 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Read external storage permission granted", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == 3 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Write external storage permission granted", Toast.LENGTH_SHORT).show();
        }
    }
}
