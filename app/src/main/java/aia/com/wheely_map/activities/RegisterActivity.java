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

    private EditText usernameTextBox;
    private EditText passwordTextBox;
    private EditText confirmPasswordTextBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameTextBox = (EditText) findViewById(R.id.text_input_desired_username);
        passwordTextBox = (EditText) findViewById(R.id.text_input_desired_password);
        confirmPasswordTextBox = (EditText) findViewById(R.id.text_input_confirm_password);

        Button registerButton = (Button) findViewById(R.id.button_register_user);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_register_user) {
            String username = usernameTextBox.getText().toString();
            String password = passwordTextBox.getText().toString();

            if (!password.equals(confirmPasswordTextBox.getText().toString())) {
                Toast.makeText(this, "Your password doesn't match", Toast.LENGTH_LONG).show();
            } else if (UserManager.findUser(username) != null) {
                Toast.makeText(this, "Username already taken", Toast.LENGTH_LONG).show();
            } else {
                UserManager.registerUser(username, password);
                Toast.makeText(this, "User registered", Toast.LENGTH_LONG).show();
            }
        }
    }
}
