package com.example.firstdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends BasicActivity {

    public static final String EXTRA_MESSAGE = "com.example.firstdemo.MESSAGE";

    private String inputMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("MainActivity", "log onCreate");
        if (savedInstanceState != null) {
            String preData = savedInstanceState.getString("inputMessage");
            EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
            editText.setText(preData);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("MainActivity", "save inputMessage");
        outState.putString("inputMessage", inputMessage);
    }

    /**
     * Called when the user taps the Send button
     */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        inputMessage = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, inputMessage);
        startActivity(intent);
    }

    public void cancel(View view) {
        // Do something in response to button
        returnDataToParent("You click cancel button!");
    }

    @Override
    public void onBackPressed() {
        returnDataToParent("You click back button!");
    }

    private void returnDataToParent(String data) {
        Intent intent = new Intent();
        intent.putExtra("return_data", data);
        setResult(RESULT_OK, intent);

        finish();
    }
}