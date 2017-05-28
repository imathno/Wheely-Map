package aia.com.wheely_map.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import aia.com.wheely_map.R;
import aia.com.wheely_map.user.User;
import aia.com.wheely_map.user.UserManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameTextBox;
    private EditText passwordTextBox;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameTextBox = (EditText) findViewById(R.id.text_input_username);
        passwordTextBox = (EditText) findViewById(R.id.text_input_password);

        Button loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_login) {
            if (validLogin()) {
                UserManager.loginUser(user);
                this.finish();
            } else {
                Toast.makeText(this, "Wrong Username/Password", Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean validLogin() {
        user = UserManager.findUser(usernameTextBox.getText().toString());
        if (user != null && user.getPassword().equals(passwordTextBox.getText().toString())) {
            return true;
        }
        return false;
    }
}
