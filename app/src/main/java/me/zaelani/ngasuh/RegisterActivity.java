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

/**
 * Irfan Zaelani
 * 10117911
 * 01 May 2020
 *
 * Halaman daftar aplikasi
 *
 */
public class RegisterActivity extends AppCompatActivity {

    private TextView mLogin;
    private EditText editUserName;
    private EditText editPassWord;
    private EditText editRePassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        declareView();
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerValidate();
            }
        });

    }

    private void declareView() {
        mLogin = findViewById(R.id.txt_reg_masuk);
        editUserName = findViewById(R.id.edt_reg_username);
        editPassWord = findViewById(R.id.edt_reg_password);
        editRePassWord = findViewById(R.id.edt_reg_password_confirm);
    }

    private void registerValidate() {

        editUserName.setError(null);
        editPassWord.setError(null);
        editRePassWord.setError(null);
        View fokus = null;
        boolean cancel = false;

        String userName = editUserName.getText().toString();
        String password = editPassWord.getText().toString();
        String rePassword = editRePassWord.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            editUserName.setError("Required");
            fokus = editUserName;
            cancel = true;
        } else if (cekUser(userName)) {
            editUserName.setError("Username already registered");
            fokus = editUserName;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            editPassWord.setError("Required");
            fokus = editPassWord;
            cancel = true;
        } else if (!cekPassword(password, rePassword)) {
            editPassWord.setError("Password not match");
            fokus = editPassWord;
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

    private boolean cekPassword(String password, String repassword) {
        return password.equals(repassword);
    }

}
