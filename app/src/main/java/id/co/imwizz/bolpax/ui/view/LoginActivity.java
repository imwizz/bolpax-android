package id.co.imwizz.bolpax.ui.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.response.LoginRsp;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private String phone, pass, status;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.text_toolbar_title) TextView textToolbarTitle;
    @Bind(R.id.button_login) Button buttonLogin;
    @Bind(R.id.button_register) Button buttonRegister;
    @Bind(R.id.text_notification) TextView textNotification;
    @Bind(R.id.progress_bar) ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        buttonRegister.setOnClickListener(this);
        setToolbar();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_register:
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                break;

        }
    }

    public void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        textToolbarTitle.setText("BOLPAX");
    }

    public void signIn(View V) {
        final Dialog dialog = new Dialog(LoginActivity.this, R.style.cust_dialog);

        dialog.setContentView(R.layout.dialog_login);
        dialog.setTitle("Log In With Bolpax");
        final EditText editTextUserName = (EditText) dialog.findViewById(R.id.edit_username);
        final EditText editTextPassword = (EditText) dialog.findViewById(R.id.edit_password);

        Button btnSignIn = (Button) dialog.findViewById(R.id.button_sign_in);
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                progressBar.setVisibility(View.VISIBLE);
                textNotification.setVisibility(View.VISIBLE);
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                BolpaxStatic.setPhonenumber(userName);

                if (userName.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Masukkan nomor telepon dan password", Toast.LENGTH_LONG).show();
                } else {
                    phone = userName;
                    pass = password;

                    RestClient.getBolpax().getLogin(phone.toString(), pass.toString(), new Callback<LoginRsp>() {
                        @Override
                        public void success(LoginRsp loginRsp, Response response) {
                            BolpaxStatic.setUserid(loginRsp.getUserId());
                            BolpaxStatic.setToken(loginRsp.getToken());
                            BolpaxStatic.setMerchantid(loginRsp.getMerchantId());
                            BolpaxStatic.setMerchantname(loginRsp.getMerchantName());
                            BolpaxStatic.setFullname(loginRsp.getFullname());
                            BolpaxStatic.setPhonenumber(loginRsp.getPhone());
                            String success = loginRsp.getStatus();
                            if(success.contains("VALID")) {
                                Intent i = new Intent(LoginActivity.this, BuyerHomeActivity.class);
                                progressBar.setVisibility(View.GONE);
                                textNotification.setVisibility(View.GONE);
                                startActivity(i);

                            }else{
                                textNotification.setText("Please Check Your Phone Number or Password");
                                progressBar.setVisibility(View.GONE);
                                textNotification.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.e(TAG, error.getMessage());

                        }
                    });
                }

            }
        });
        dialog.show();

    }

}
