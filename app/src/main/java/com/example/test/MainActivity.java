package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button minus = (Button) findViewById(R.id.minus);
        Button plus = (Button) findViewById(R.id.plus);
        Button order = (Button) findViewById(R.id.order);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement();
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment();
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order();
            }
        });
    }
    int quantity = 0;
    public void showQuantity(int mQuantity){
        TextView orderQuantity = (TextView) findViewById(R.id.quantity);
        orderQuantity.setText("" + mQuantity);
    }
    public void increment(){
        if(quantity<10)
            quantity++;
        showQuantity(quantity);
    }
    public void decrement(){
        if(quantity>0)
            quantity--;
        showQuantity(quantity);
    }
    public void order(){
        int totalPrice;
        EditText name = (EditText) findViewById(R.id.name);
        TextView orderPrice = (TextView) findViewById(R.id.price);
        CheckBox choco = (CheckBox) findViewById(R.id.check1);
        CheckBox cream = (CheckBox) findViewById(R.id.check2);
        if(choco.isChecked() && cream.isChecked())
            totalPrice = quantity*10;
        else if(choco.isChecked())
            totalPrice = quantity*7;
        else if(cream.isChecked())
            totalPrice = quantity*8;
        else
            totalPrice = quantity*5;
        String str = "Name: " + name.getText().toString() + "\nChocolate: " + choco.isChecked() + "\nWhipped Cream: " + cream.isChecked() + "\nPrice: $" + totalPrice + "\nDone;)";
        orderPrice.setText(str);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Order for " + name.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT,str);
        try {
            startActivity(intent);
        } catch (Exception ActivityNotFoundException) {
            Toast.makeText(getApplicationContext(),"No mail apps found! Can't order!!!", Toast.LENGTH_LONG).show();
        }
    }
}