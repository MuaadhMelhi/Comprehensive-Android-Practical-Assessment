package nyc.muaadh_melhi_develpoer.finaltestandriod;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static String SHARED_PREF_KEY = "sharedPrefsTesting";
    private EditText username, password;
    private Button loginButton;
    private SharedPreferences login;
    // public static String USER_NAME_K = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpView();

        String existUser = login.getString("username", "");
        if (existUser != null) {
            goIntentToBreedActivity();
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCheck();
            }
        });
    }

    private void loginCheck() {
        String userName = username.getText().toString();
        String userPassword = password.getText().toString();
        boolean contains = userPassword.contains(userName);
        if (userName.length() == 0 && userPassword.length() == 0) {
            Toast.makeText(this, "Pleaes Enter UserName and Password", Toast.LENGTH_SHORT).show();
        } else if (userName.length() == 0) {
            Toast.makeText(this, "Pleaes Enter UserName", Toast.LENGTH_SHORT).show();
        } else if (userPassword.length() == 0) {
            Toast.makeText(this, "Pleaes Enter your Password", Toast.LENGTH_SHORT).show();
        } else if (userName.length() > 0 && userPassword.length() > 0 && contains == false) {
            SharedPreferences.Editor editor = login.edit();
            editor.putString("username", username.getText().toString());
            editor.commit();
            goIntentToBreedActivity();
        } else {
            Toast.makeText(this, "Password cannot contain username", Toast.LENGTH_SHORT).show();
        }
    }

    private void goIntentToBreedActivity() {
        Intent intent = new Intent(LoginActivity.this, BreedsActivity.class);
        intent.putExtra(getString(R.string.shared_name), SHARED_PREF_KEY);
        startActivity(intent);
    }

    private void setUpView() {
        username = findViewById(R.id.username_edittext);
        password = findViewById(R.id.password_edittext);
        loginButton = findViewById(R.id.login_button);
        login = getApplicationContext().getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);
    }
}
