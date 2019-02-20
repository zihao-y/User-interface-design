package com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private EditText e1;
    private RadioGroup rg1;
    private RadioGroup rg2;
    private TextView t1;
    private static HashMap<String, Double> idAndPriceValue;
    private static String data = "";
    private fetchData process = new fetchData();

    public MainActivity (){
        idAndPriceValue = new HashMap<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.editText);
        rg1 = (RadioGroup) findViewById(R.id.RGroup1);
        rg2 = (RadioGroup) findViewById(R.id.RGroup2);
        t1 = (TextView) findViewById(R.id.textView);
        process.execute();
    }

    public void onButtonClick(View v) {
        String value = e1.getText().toString();
        if (value.matches("")) {
            Toast.makeText(this, "No value entered", Toast.LENGTH_SHORT).show();
            return;
        }
        double btcValue = idAndPriceValue.get("bitcoin");
        double dogeValue = idAndPriceValue.get("dogecoin");
        Log.d("btcValue is", String.valueOf(btcValue));
        Log.d("dogeValue is", String.valueOf(dogeValue));

        double num1 = Double.parseDouble(e1.getText().toString());
        // from USD, BTC, Doge
        if (rg1.getCheckedRadioButtonId() == R.id.radioButton1) {
            // USD to USD, BTC, Doge
            if (rg2.getCheckedRadioButtonId() == R.id.radioButton7) {
                num1 = num1;
                DecimalFormat df = new DecimalFormat("0.00");
                data = df.format(num1);
            } else if (rg2.getCheckedRadioButtonId() == R.id.radioButton8) {
                num1 = num1 / btcValue;
                DecimalFormat df = new DecimalFormat("0.0000000000");
                data = df.format(num1);
            } else if (rg2.getCheckedRadioButtonId() == R.id.radioButton9) {
                num1 = num1 / dogeValue;
                DecimalFormat df = new DecimalFormat("0.0000000000");
                data = df.format(num1);
            }
        } else if (rg1.getCheckedRadioButtonId() == R.id.radioButton2) {
            // BTC to USD, BTC, Doge
            if (rg2.getCheckedRadioButtonId() == R.id.radioButton7) {
                num1 = num1 * btcValue;
                DecimalFormat df = new DecimalFormat("0.00");
                data = df.format(num1);
            } else if (rg2.getCheckedRadioButtonId() == R.id.radioButton8) {
                num1 = num1;
                DecimalFormat df = new DecimalFormat("0.0000000000");
                data = df.format(num1);
            } else if (rg2.getCheckedRadioButtonId() == R.id.radioButton9) {
                num1 = (num1 * btcValue) / dogeValue;
                DecimalFormat df = new DecimalFormat("0.0000000000");
                data = df.format(num1);
            }
        } else if (rg1.getCheckedRadioButtonId() == R.id.radioButton3) {
            // Doge to USD, BTC, Doge
            if (rg2.getCheckedRadioButtonId() == R.id.radioButton7) {
                num1 = num1 * dogeValue;
                DecimalFormat df = new DecimalFormat("0.00");
                data = df.format(num1);
            } else if (rg2.getCheckedRadioButtonId() == R.id.radioButton8) {
                num1 = (num1 * dogeValue) / btcValue;
                DecimalFormat df = new DecimalFormat("0.0000000000");
                data = df.format(num1);
            } else if (rg2.getCheckedRadioButtonId() == R.id.radioButton9) {
                num1 = num1;
                DecimalFormat df = new DecimalFormat("0.0000000000");
                data = df.format(num1);
            }
        }
        t1.setText(data);
    }

    public void onButtonClick2 (View v){
        process.cancel(true);
        process = new fetchData();
        process.execute();
        onButtonClick(v);
    }

    public static void setIdAndPriceValue (String id, Double price_usd){
        idAndPriceValue.put(id, price_usd);
    }

    public static HashMap<String, Double> getIdAndPriceValue (){
        return idAndPriceValue;
    }
}
