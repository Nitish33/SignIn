package com.example.nitishprasad.signin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    EditText userName, password;
    TextInputLayout passwordLayout, userNameLayout;
    boolean isAutoSignin = false;
    Checkable autoSign;

    String uName, pass;

    public static String AUTO_SIGNIN = "auto_signin";
    public static String IS_AUTO_SIGNIN = "is_auto";
    public static String USERNAME = "username";
    public static String PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        autoSign = (CheckBox) findViewById(R.id.autoSign);

        passwordLayout = (TextInputLayout) findViewById(R.id.passwordHolder);
        userNameLayout = (TextInputLayout) findViewById(R.id.userNameField);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        setEditTextChangeListener();
        checkAutoSignin();

    }

    private void checkAutoSignin() {

        SharedPreferences prefences = getSharedPreferences(AUTO_SIGNIN, MODE_PRIVATE);
        isAutoSignin = prefences.getBoolean(IS_AUTO_SIGNIN, false);

        if (isAutoSignin) {

            uName = prefences.getString(USERNAME, "");
            pass = prefences.getString(PASSWORD, "");

            if (!(TextUtils.isEmpty(uName) || TextUtils.isEmpty(pass))) {

                    validate();
            }
        }
    }

    private void setEditTextChangeListener() {


        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (b) {
                    passwordLayout.setErrorEnabled(false);
                }
            }
        });

        userName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (b) {
                    userNameLayout.setErrorEnabled(false);
                }
            }
        });

    }

    public void click(View view) {

        boolean isValid = true;

        uName = userName.getText().toString();
        pass = password.getText().toString();

        isAutoSignin = autoSign.isChecked();

        if (TextUtils.isEmpty(uName)) {
            isValid = false;
            userNameLayout.setError("Please Enter your Email");
        }

        if (TextUtils.isEmpty(pass)) {
            isValid = false;
            passwordLayout.setError("Please Enter a valid Password");

        }

        if (!uName.endsWith("@gmail.com")) {
            isValid = false;
            userNameLayout.setError("Please a Valid Email");
        }


        if (isValid) {
            validate();

            if (isAutoSignin) {
                SharedPreferences prefences = getSharedPreferences(AUTO_SIGNIN, MODE_PRIVATE);
                SharedPreferences.Editor edit = prefences.edit();
                edit.putBoolean(IS_AUTO_SIGNIN, isAutoSignin);
                edit.putString(USERNAME, uName);
                edit.putString(PASSWORD, pass);
                edit.commit();

            }
        }
    }

    private void validate() {

     final    ProgressDialog dialog = new ProgressDialog(this);
        dialog.show();


        reference.child("LOGIN").addListenerForSingleValueEvent(new ValueEventListener() {

            String un, p;


            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                boolean matchFound = false;


                for (DataSnapshot d : dataSnapshot.getChildren()) {


                    un = d.child("USERNAME").getValue(String.class);
                    p = d.child("PASSWORD").getValue(String.class);



                    if (!(TextUtils.isEmpty(un) || TextUtils.isEmpty(p))) {

                    }

                    if (un.equals(uName) && p.equals(pass)) {

                        Intent intent = new Intent(MainActivity.this, topPage.class);
                        matchFound = true;
                        startActivity(intent);
                        finish();

                    }
                }

                dialog.dismiss();

                if(!matchFound){

                    Toast.makeText(MainActivity.this,"No Match Found Pls Check Username and Password",Toast.LENGTH_SHORT).show();
                    passwordLayout.setError("");
                    userNameLayout.setError("");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
