package com.example.callsandsmsmanger;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InternationalCaller extends AppCompatActivity {
    String prefix="";
    String telNumber="";
    final int CHOOSE_PREFIX_REQUEST = 1;
    final int CHOOSE_TELL_NUMBER_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_international_caller);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        Intent intent = getIntent();
//        prefix = intent.getStringExtra("prefix");
//        TextView prefixText = findViewById(R.id.selectedCountryCode);
//        prefixText.setText(prefix);
    }

    public void compose (View v)
    {
        if(!prefix.trim().isEmpty() &&  !telNumber.trim().isEmpty()){
            TextView edn =(TextView)findViewById(R.id.callinternationalInput);
            Intent intentImplicit=new Intent(Intent.ACTION_DIAL);
            String uri="tel:"+prefix+telNumber.toString();
            intentImplicit.setData(Uri.parse(uri));
            startActivity(intentImplicit);
        }else{
            View rootView = findViewById(android.R.id.content);
            Snackbar.make(rootView, "Error! please Enter phone number correctly", Snackbar.LENGTH_SHORT).show();
        }
    }

    public void makeCall (View v)
    {
        if(!prefix.trim().isEmpty() &&  !telNumber.trim().isEmpty()){

            TextView edn =(TextView)findViewById(R.id.callinternationalInput);
        Intent intentImplicit=new Intent(Intent.ACTION_CALL);
        String uri="tel:"+prefix+telNumber.toString();
        intentImplicit.setData(Uri.parse(uri));
        try {
            startActivity(intentImplicit);
        } catch (SecurityException e) {
            ActivityCompat.requestPermissions(
                    InternationalCaller.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);
        }
    }else{
        View rootView = findViewById(android.R.id.content);
        Snackbar.make(rootView, "Error! please Enter phone number correctly", Snackbar.LENGTH_SHORT).show();
    }
        return;
    }

    public void choosePrefix(View v){

        // create the intent
        Intent intent = new Intent(this, InternationalCallSelector.class);
        // start the activity
        startActivityForResult(intent, CHOOSE_PREFIX_REQUEST);
        return;
    }
    public void chooseTelNumber(View v){
        // create the intent
        Intent intent = new Intent(this, international_tel_phone_selector.class);
        // start the activity
        startActivityForResult(intent, CHOOSE_TELL_NUMBER_REQUEST);
        return;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_TELL_NUMBER_REQUEST) { // Identify activity
            if (resultCode == RESULT_OK) { // Activity succeeded
                String reply = data.getStringExtra(international_tel_phone_selector.EXTRA_REPLY);
                telNumber=reply;
                TextView telPhoneText = findViewById(R.id.callinternationalInput);
                telPhoneText.setText(reply);
                }}
        else if(requestCode == CHOOSE_PREFIX_REQUEST){
            if (resultCode == RESULT_OK) { // Activity succeeded
                String reply = data.getStringExtra(InternationalCallSelector.EXTRA_REPLY);
                prefix=reply;
                TextView prefixText = findViewById(R.id.selectedCountryCode);
                prefixText.setText(reply);
            }
        }
    }

}