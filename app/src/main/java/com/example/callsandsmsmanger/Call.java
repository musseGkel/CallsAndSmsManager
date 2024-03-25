package com.example.callsandsmsmanger;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest;


public class Call extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_call);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void compose (View v)
    {
        EditText edn =(EditText)findViewById(R.id.editTextPhone);
        Intent intentImplicit=new Intent(Intent.ACTION_DIAL);
        String uri="tel:"+edn.getText().toString();
        intentImplicit.setData(Uri.parse(uri));
        startActivity(intentImplicit);
    }

    public void makeCall (View v)
    {
        EditText edn =(EditText)findViewById(R.id.editTextPhone);
        Intent intentImplicit=new Intent(Intent.ACTION_CALL);
        String uri="tel:"+edn.getText().toString();
        intentImplicit.setData(Uri.parse(uri));
        try {
            startActivity(intentImplicit);
        } catch (SecurityException e) {
            ActivityCompat.requestPermissions(
                    Call.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);
        }
        return;
    }
}