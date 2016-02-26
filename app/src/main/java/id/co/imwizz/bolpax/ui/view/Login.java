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
import id.co.imwizz.bolpax.data.entity.bolpax.response.LoginBolpax;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = Login.class.getSimpleName();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.btnlogin)
    Button login;
    @Bind(R.id.btnregister)
    Button register;
    @Bind(R.id.notif)
    TextView notif;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    private String phone, pass, status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        register.setOnClickListener(this);
        setToolbar();

    }
    public void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MerchantProfile.this,BuyerHomeActivity.class);
//                startActivity(i);
//            }
//        });
        toolbar.setTitle("");
        toolbarTitle.setText("BOLPAX");
    }

    public void signIn(View V) {
        final Dialog dialog = new Dialog(Login.this, R.style.cust_dialog);

        dialog.setContentView(R.layout.login);
        dialog.setTitle("Log In With Bolpax");
//        dialog.getWindow().setLayout(1000, 700);

        // get the Refferences of views
        final EditText editTextUserName = (EditText) dialog.findViewById(R.id.editTextUserNameToLogin);
        final EditText editTextPassword = (EditText) dialog.findViewById(R.id.editTextPasswordToLogin);

        Button btnSignIn = (Button) dialog.findViewById(R.id.buttonSignIn);

        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                progressBar.setVisibility(View.VISIBLE);
                notif.setVisibility(View.VISIBLE);
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                BolpaxStatic.setPhonenumber(userName);

                if (userName.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Masukkan nomor telepon dan password", Toast.LENGTH_LONG).show();
                } else {
                    phone = userName;
                    pass = password;

                    RestClient.getBolpax().getLogin(phone.toString(), pass.toString(), new Callback<LoginBolpax>() {
                        @Override
                        public void success(LoginBolpax loginBolpax, Response response) {
                            BolpaxStatic.setUserid(loginBolpax.getUserId());
                            BolpaxStatic.setToken(loginBolpax.getToken());
                            BolpaxStatic.setMerchantid(loginBolpax.getMerchantId());
                            BolpaxStatic.setMerchantname(loginBolpax.getMerchantName());
                            BolpaxStatic.setFullname(loginBolpax.getFullname());
                            BolpaxStatic.setPhonenumber(loginBolpax.getPhone());
                            String success = loginBolpax.getStatus();
                            if(success.contains("VALID")) {
                                Intent i = new Intent(Login.this, BuyerHomeActivity.class);
                                progressBar.setVisibility(View.GONE);
                                notif.setVisibility(View.GONE);
                                startActivity(i);

                            }else{
                                notif.setText("Please Check Your Phone Number or Password");
                                progressBar.setVisibility(View.GONE);
                                notif.setVisibility(View.VISIBLE);
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnregister:
                Intent i = new Intent(Login.this, RegisterActivity.class);
                startActivity(i);
                break;

        }
    }

}
