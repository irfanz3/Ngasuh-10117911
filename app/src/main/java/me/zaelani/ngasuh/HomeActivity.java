package me.zaelani.ngasuh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import me.zaelani.ngasuh.utils.Preferences;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView mLogout = findViewById(R.id.txt_logout);
        TextView mName = findViewById(R.id.txtName);

        mName.setText(Preferences.getRegisteredUser(getBaseContext()));
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.setLogout(getBaseContext());

                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        });
    }
}
