package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.libmyparser.SimpleParser;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {
    private static String LOG_TAG = "MainActivity";

    private TextView expression;
    private TextView result;

    private int cnt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vertical_orientation_keyboard);

        expression = findViewById(R.id.screen_expression);
        result = findViewById(R.id.screen_result);


    }

    public void onClick(View view){
        Button button = (Button)view;
        if (expression.getText() == "") {
            expression.setText(button.getText());
        } else {
            expression.append(button.getText());
        }
    }

    public void onBackClick(View view){
        if (expression.getText().length() > 0) {
            expression.setText(expression.getText().subSequence(0,expression.getText().length()-1));
        }
    }

    public void onClearClick(View view) {
        expression.setText("");
        result.setText("");
    }

    public void onEqualClick(View view) {
        try {
            Log.d(LOG_TAG, expression.getText().toString());
            double res = new SimpleParser().parse(expression.getText().toString());
            //result.setText(String.valueOf(res));
            if (res % 1 == 0) {
                result.setText(String.valueOf((int)res));
            } else {
                result.setText(String.valueOf(res));
            }

        } catch (Exception ex) {
            Log.d("Exception", " message : " + ex.getMessage());
            result.setText(getString(R.string.exception));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("CUR_EXPR",expression.getText().toString());
        outState.putString("CUR_RES", result.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState){
        super.onRestoreInstanceState(savedState);
        expression.setText(savedState.getString("CUR_EXPR"));
        result.setText(savedState.getString("CUR_RES"));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy: ");
    }
}