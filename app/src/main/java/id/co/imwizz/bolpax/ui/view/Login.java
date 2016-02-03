package id.co.imwizz.bolpax.ui.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;

public class Login extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.btnlogin) Button login;
    @Bind(R.id.btnregister) Button register;
    @Bind(R.id.btnfb) ImageButton fb;


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

                if(userName.contains("")&&password.contains(""))
                {
                    Intent nextActivity1 = new Intent(Login.this,BuyerHomeActivity.class);
                    startActivity(nextActivity1);
                    //Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
                else
                {

                    Toast.makeText(Login.this, "User Name and Does Not Matches", Toast.LENGTH_LONG).show();
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
