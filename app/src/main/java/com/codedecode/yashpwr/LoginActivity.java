package com.codedecode.yashpwr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
	
	// Email, password edittext
	EditText txtUsername, txtPassword;
	
	// login button
	Button btnLogin;
	
	// Alert Dialog Manager
	AlertDialogManager alert = new AlertDialogManager();
	
	// Session Manager Class
	SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Session Manager
        session = new SessionManager(getApplicationContext());

        // Email, Password input text
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

		/**
		 * if SharedPreferences contains username and password then directly redirect to Home activity
		 * */
		if(session.isLoggedIn()){
			startActivity(new Intent(LoginActivity.this,MainActivity.class));
			finish();   //finish current activity
		}
        
        // Login button
        btnLogin = findViewById(R.id.btnLogin);

        // Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// Get username, password from EditText
				String username = txtUsername.getText().toString();
				String password = txtPassword.getText().toString();
				
				// Check if username, password is filled				
				if(username.trim().length() > 0 && password.trim().length() > 0){
					// For testing puspose username, password is checked with sample data
					// username = yashpwr
					// password = 123
					if(username.equals("yashpwr") && password.equals("123")){

						// Creating user login session
						// For testing i am stroing name, email as follow
						// Use user real data
						session.createLoginSession("CodeDecode", "demo@gmail.com");

						// Staring MainActivity
						Intent i = new Intent(getApplicationContext(), MainActivity.class);
						startActivity(i);
						finish();
						
					}else{
						// username / password doesn't match
						alert.showAlertDialog(LoginActivity.this, "Login failed..", "Username/Password is Incorrect", false);
					}				
				}else{
					// user didn't entered username or password
					// Show alert asking him to enter the details
					alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please Enter Username and Password", false);
				}
			}
		});
    }        
}