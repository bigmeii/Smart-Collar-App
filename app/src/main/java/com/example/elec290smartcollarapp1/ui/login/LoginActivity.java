package com.example.elec290smartcollarapp1.ui.login;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elec290smartcollarapp1.ui.main.MainActivity;
import com.example.elec290smartcollarapp1.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Link XML to Java
        usernameField = findViewById(R.id.field_username);
        passwordField = findViewById(R.id.field_password);
        loginButton = findViewById(R.id.button_login);

        // Handle log in button press
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this,
                            "Please enter username and password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Simple fake authentication
                if (username.equals("admin") && password.equals("1234")) {
                    Toast.makeText(LoginActivity.this,
                            "Login successful!",
                            Toast.LENGTH_SHORT).show();

                    // Go to the main app screen
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();  // Prevent going back to login
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Incorrect username or password",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}