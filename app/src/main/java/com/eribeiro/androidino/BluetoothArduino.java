package com.eribeiro.androidino;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.hardware.SensorManager;
import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by anderson on 09/05/15.
 */
public class BluetoothArduino {

    private BluetoothAdapter mBluetoothAdapater;
    private BluetoothSocket mBluetoothSocket;
    private BluetoothDevice mBluetoothDevice;

    private BluetoothAdapter mBlueAdapter = null;
    private BluetoothSocket mBlueSocket = null;
    private BluetoothDevice mBlueRobo = null;
    OutputStream mOut;
    InputStream mIn;
    private boolean robotFound = false;
    private boolean connected = false;
    private int REQUEST_BLUE_ATIVAR = 10;
    private String robotName;
    private List<String> mMessages = new ArrayList<String>();
    private String TAG = "BluetoothConnector";
    private char DELIMITER = '#';
    private SensorManager sensorManager;
    private long lastUpdate;
    private static BluetoothArduino __blue = null;
    private boolean FlagMsg = true;


    public List<String> ListBluetoothAdapter() {
        try {

            List<String> bluetooth = new ArrayList<>();

            mBlueAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBlueAdapter == null) {
                Log.d("Erro bluet", "[#]Phone does not support bluetooth!!");
            }
            if (!mBlueAdapter.isEnabled()) {
                Log.d("Erro em bluet", "Bluetooth is not activated!!");
            }

            Set<BluetoothDevice> paired = mBlueAdapter.getBondedDevices();

            for (BluetoothDevice d : paired) {

                bluetooth.add(d.getName());

            }

            if (!robotFound) Log.d("Erro ROBOT", "There is not robot paired!!");

            return bluetooth;

        } catch (Exception e) {
            Log.d("Erro em bluetoot", "Erro creating Bluetooth! : " + e.getMessage());
            return null;
        }

    }

}