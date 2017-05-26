package aia.com.wheely_map.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import aia.com.wheely_map.R;
import aia.com.wheely_map.managers.UserManager;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerButton = (Button) findViewById(R.id.button_register_user);
        registerButton.setOnClickListener(this);
    }

    //TODO: ADD SO IT CHECKS USERNAME BEFORE MAKING THE ACCOUNT
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_register_user) {
            EditText userName = (EditText) findViewById(R.id.text_input_desired_username);
            EditText password = (EditText) findViewById(R.id.text_input_desired_password);
            EditText confirmPassword = (EditText) findViewById(R.id.text_input_confirm_password);

            if (!password.getText().equals(confirmPassword.getText())) {
                Toast.makeText(this, "Your password doesn't match", Toast.LENGTH_LONG).show();
            } else if (UserManager.findUser(userName.getText().toString()) != null) {
                Toast.makeText(this, "Username already taken", Toast.LENGTH_LONG).show();
            } else {
                UserManager.registerUser(userName);
            }
        }
    }
}
