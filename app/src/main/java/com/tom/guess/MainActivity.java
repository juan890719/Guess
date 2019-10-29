package com.tom.guess;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView ed_Number;
    private TextView message;
    private ImageView result;
    private TextView edCounter;
    int counter;
    int secret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ed_Number = findViewById(R.id.num);
        message = findViewById(R.id.message);
        result = findViewById(R.id.result);
        edCounter = findViewById(R.id.counter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        reset();
        Log.d("MainActivity","Secrert : "+secret);
    }
    public void reset() {
        secret = new Random().nextInt(10) + 1;
        counter = 0;
        edCounter.setText(counter + "");
    }

    public void guess(View view){
        int number = Integer.parseInt(ed_Number.getText().toString());
        result.setAlpha(1.0f);
        result.setVisibility(View.VISIBLE);
        counter++;
        edCounter.setText(counter+"");

        if(secret > number){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Hello")
                    .setMessage("Bigger")
                    .setPositiveButton("OK",null)
                    .show();
            result.setImageResource(R.drawable.sad);
            result.animate().alpha(0.0f).setDuration(10000);
        }
        else if(secret < number){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Hello")
                    .setMessage("Smaller")
                    .setPositiveButton("OK",null)
                    .show();
            result.setImageResource(R.drawable.sad);
            result.animate().alpha(0.0f).setDuration(10000);
        }
        else{
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Hello")
                    .setMessage("Bingo !the secret number is "+secret)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            reset();
                        }
                    })
                    .show();
            result.setImageResource(R.drawable.good);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
