package com.estoreid.estoredriver.dashboard.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.estoreid.estoredriver.R;
import com.estoreid.estoredriver.dashboard.Congrats_Screen;

public class OTP_Screen extends AppCompatActivity {

    ImageButton deliver_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp__screen);

        init();
        listeners();
    }

    private void listeners() {
        deliver_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OTP_Screen.this, Congrats_Screen.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        deliver_bt = findViewById(R.id.deliver_bt);
    }
}
