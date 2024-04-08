package com.example.callsandsmsmanger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class international_tel_phone_selector extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.callsandsmsmanager";;
    String selectedTelNumber="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_international_tel_phone_selector);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void selectTelNumber(View v){
        // Find the RadioGroup
        RadioGroup radioGroup = findViewById(R.id.countryCodeRadio);
        // Get the ID of the selected RadioButton
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) { // Ensure a RadioButton is selected
            // Find the selected RadioButton
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            // Get the text of the selected RadioButton
            selectedTelNumber = selectedRadioButton.getText().toString();
        }
        String reply = selectedTelNumber;
        // Create an intent
        Intent replyIntent = new Intent();
        // Put the data to return into the extra
        replyIntent.putExtra(EXTRA_REPLY, reply);
        // Set the activity's result to RESULT_OK
        setResult(RESULT_OK, replyIntent);
        // Finish the current activity
        finish();
    }
}