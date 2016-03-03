package id.co.imwizz.bolpax.ui.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.bolpax.request.UserRqs;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 08/01/2016.
 */
public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @Bind(R.id.text_name)EditText textName;
    @Bind(R.id.text_last_name) EditText textLastname;
    @Bind(R.id.text_email) EditText textEmail;
    @Bind(R.id.text_phone) EditText textPhone;
    @Bind(R.id.text_password) EditText textPassword;
    @Bind(R.id.button_signup)Button buttonSignup;
    @Bind(R.id.toolbar)Toolbar toolbar;
    @Bind(R.id.text_toolbar_title)TextView textToolbarTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setToolbar();

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        textToolbarTitle.setText("BOLPAX");
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        buttonSignup.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String fullname = textName.getText().toString() +" "+ textLastname.getText().toString();
        String email = textEmail.getText().toString();
        String phone = textPhone.getText().toString();
        String password = textPassword.getText().toString();

        UserRqs user = new UserRqs();
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);


        // TODO: Implement your own signup logic here.
        RestClient.getBolpax().getRegister(user, new Callback <String>(){

            @Override
            public void success(String string, Response response) {
                Toast.makeText(getBaseContext(), "LoginActivity failed", Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        new android.os.Handler().postDelayed(

                new Runnable() {
                    public void run() {
                        onSignupSuccess();
                        Intent i = new Intent(RegisterActivity.this,BuyerHomeActivity.class);
                        startActivity(i);
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        buttonSignup.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "LoginActivity failed", Toast.LENGTH_LONG).show();

        buttonSignup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = textName.getText().toString();
        String lastname = textLastname.getText().toString();
        String email = textEmail.getText().toString();
        String phone = textPhone.getText().toString();
        String password = textPassword.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            textName.setError("at least 3 characters");
            valid = false;
        } else {
            textName.setError(null);
        }

        if (lastname.isEmpty() || name.length() < 3) {
            textLastname.setError("at least 3 characters");
            valid = false;
        } else {
            textLastname.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textEmail.setError("enter a valid email address");
            valid = false;
        } else {
            textEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            textPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            textPassword.setError(null);
        }

        return valid;
    }
}