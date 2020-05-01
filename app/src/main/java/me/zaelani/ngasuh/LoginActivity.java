package me.zaelani.ngasuh;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import me.zaelani.ngasuh.model.UserModel;
import me.zaelani.ngasuh.utils.Preferences;

public class LoginActivity extends AppCompatActivity {

    private EditText editUsername;
    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView rvRegister = findViewById(R.id.txt_login_register);
        TextView tvLogin = findViewById(R.id.txt_login_masuk);

        editUsername = findViewById(R.id.edt_login_username);
        editPassword = findViewById(R.id.edt_login_password);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginValidate();
            }
        });

        rvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())) {
            startActivity(new Intent(getBaseContext(), HomeActivity.class));
            finish();
        }
    }


    private void loginValidate() {
        editUsername.setError(null);
        editPassword.setError(null);

        View fokus = null;
        boolean cancel = false;

        String userName = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            editUsername.setError("Required");
            fokus = editUsername;
            cancel = true;
        } else if (!cekUser(userName)) {
            editUsername.setError("Username not found");
            fokus = editUsername;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            editPassword.setError("Required");
            fokus = editPassword;
            cancel = true;
        } else if (!cekPassword(password)) {
            editPassword.setError("Password not match");
            fokus = editPassword;
            cancel = true;
        }

        if (cancel) {
            fokus.requestFocus();
        } else {
            UserModel userModel = new UserModel();
            userModel.setUsername(userName);
            userModel.setPassword(password);

            Preferences.setUserPreferences(getBaseContext(), userModel);

            startActivity(new Intent(getBaseContext(), HomeActivity.class));
            finish();
        }

    }

    private boolean cekUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }

    private boolean cekPassword(String password) {
        return password.equals(Preferences.getRegisteredPassword(getBaseContext()));
    }


}
