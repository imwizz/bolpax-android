package id.co.imwizz.bolpax.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import id.co.imwizz.bolpax.R;

/**
 * Created by User on 08/01/2016.
 */
public class MerchantProfile extends AppCompatActivity {

    protected Context mContext;
    String email,name,phone;
    Integer balance;
    TextView Name,Balance,Email,Call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_profile);

        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("email");
        name = bundle.getString("name");
        phone = bundle.getString("phone");
        balance = bundle.getInt("balance");

        Name = (TextView)findViewById(R.id.tvname);
        Email = (TextView)findViewById(R.id.tvmail);
        Call = (TextView)findViewById(R.id.tvcall);
        Balance = (TextView)findViewById(R.id.tvbalance);

        Name.setText(name);
        Email.setText(email);
        Call.setText(phone);
        Balance.setText("Rp. "+balance);



    }

}
