package com.example.callsandsmsmanger;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InternationalCallSelector extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.callsandsmsmanager";
    String selectedPrefix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_international_call_selector);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



    }

    public void selectPrefix(View view) {
        // Find the RadioGroup
        RadioGroup radioGroup = findViewById(R.id.countryCodeRadio);

        // Get the ID of the selected RadioButton
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId != -1) { // Ensure a RadioButton is selected
            // Find the selected RadioButton
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

            // Get the text of the selected RadioButton
            String selectedCountry = selectedRadioButton.getText().toString();

            // Create an Intent to start the InternationalCaller activity
            Intent intent = new Intent(this, InternationalCaller.class);

            // Pass the selected country as an extra with the Intent
            intent.putExtra("prefix", selectedCountry);

            // Start the InternationalCaller activity
            startActivity(intent);
        } else {
            // No RadioButton is selected, handle this case (e.g., display a message)
            Toast.makeText(this, "Please select a country", Toast.LENGTH_SHORT).show();
        }
    }

    public void selectTelPrefix(View v){
        // Find the RadioGroup
        RadioGroup radioGroup = findViewById(R.id.countryCodeRadio);

        // Get the ID of the selected RadioButton
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) { // Ensure a RadioButton is selected
            // Find the selected RadioButton
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

            // Get the text of the selected RadioButton
             selectedPrefix = selectedRadioButton.getText().toString();
        }
        String reply = selectedPrefix;
        // Create an intent
        Intent replyIntent = new Intent();
        // Put the data to return into the extra
        replyIntent.putExtra(EXTRA_REPLY, reply);
        // Set the activity's result to RESULT_OK
        setResult(RESULT_OK, replyIntent);
        // Finish the current activity
        finish();
    }

    public void findCountryCodeInfo(View view){
        // Create an Intent with ACTION_VIEW and the URL as the data
        String url = "https://en.wikipedia.org/wiki/List_of_international_call_prefixes";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        // Verify that there is an app to handle this intent before starting
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // Handle the case where no app can handle the intent
            Toast.makeText(this, "No app found to handle this intent", Toast.LENGTH_SHORT).show();
        }
    }
}