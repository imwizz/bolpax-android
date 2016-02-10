package id.co.imwizz.bolpax.ui.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.mandiri.LoginMandiri;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = Login.class.getSimpleName();
    @Bind(R.id.btnlogin) Button login;
    @Bind(R.id.btnregister) Button register;
    @Bind(R.id.btnfb) ImageButton fb;
    private String uid, msisdn, credentials, status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        register.setOnClickListener(this);
        fb.setOnClickListener(this);
    }

    public void signIn(View V)
    {
        final Dialog dialog = new Dialog(Login.this,R.style.cust_dialog);

        dialog.setContentView(R.layout.login);
        dialog.setTitle("Log In With Bolpax");
//        dialog.getWindow().setLayout(1000, 700);

        // get the Refferences of views
        final EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
        final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);

        Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);

        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

                if(userName.isEmpty()||password.isEmpty())
                {
                    Toast.makeText(Login.this, "Masukkan nomor telepon dan password", Toast.LENGTH_LONG).show();
                }
                else
                {
                    uid = "123";
                    msisdn = userName;
                    credentials = password;

                    RestClient.getMandiri().getLogin(uid.toString(), msisdn.toString(), credentials.toString(), new Callback<LoginMandiri>() {
                        @Override
                        public void success(LoginMandiri loginMandiri, Response response) {
                            Log.i("response",loginMandiri.toString());
                            status = loginMandiri.getStatus();
                            if (status.contains("VALID")){
                                Intent nextActivity1 = new Intent(Login.this,BuyerHomeActivity.class);
                                startActivity(nextActivity1);
                                dialog.dismiss();

                            }else{
                                Toast.makeText(Login.this, "anda tertipu, coba lagi", Toast.LENGTH_SHORT).show();
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
                Intent i = new Intent(Login.this,RegisterActivity.class);
                startActivity(i);
                break;
            case R.id.btnfb:
                Toast.makeText(Login.this, "Login With Facebook", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
