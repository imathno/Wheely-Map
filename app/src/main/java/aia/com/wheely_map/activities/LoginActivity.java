package aia.com.wheely_map.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import aia.com.wheely_map.R;
import aia.com.wheely_map.User;
import aia.com.wheely_map.managers.UserManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_login) {
            if (validLogin()) {
                UserManager.loginUser(user);
            } else {
                Toast.makeText(this, "Wrong Username/Password", Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean validLogin() {
        username = (EditText) findViewById(R.id.text_input_username);
        password = (EditText) findViewById(R.id.text_input_password);
        user = UserManager.findUser(username.getText().toString());
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
