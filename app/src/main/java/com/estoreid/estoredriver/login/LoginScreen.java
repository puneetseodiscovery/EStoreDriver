package com.estoreid.estoredriver.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.estoreid.estoredriver.R;
import com.estoreid.estoredriver.dashboard.MainActivity;
import com.estoreid.estoredriver.utils.Utils;

public class LoginScreen extends AppCompatActivity {

    Button login_login_bt;
    EditText login_password_et,login_email_et;
    TextView forgotpassowrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        init();
        Utils.checkPermissions(LoginScreen.this);
        listerners();
    }


    private void init() {
        login_login_bt = (Button)findViewById(R.id.login_login_bt);
        login_email_et =(EditText) findViewById(R.id.login_email_et);
        login_password_et = (EditText)findViewById(R.id.login_password_et);
        forgotpassowrd = (TextView)findViewById(R.id.forgotpassowrd);
    }

    private void listerners() {
        login_login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
