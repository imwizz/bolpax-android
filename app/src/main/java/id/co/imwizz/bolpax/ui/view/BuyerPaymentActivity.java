package id.co.imwizz.bolpax.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.request.Payment;
import id.co.imwizz.bolpax.rest.PaymentResponse;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 08/01/2016.
 */
public class BuyerPaymentActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BuyerPaymentActivity.class.getSimpleName();
    protected Context mContext;
    String email,name,phone,merchantname,token,productName,amountString;
    Integer balance;
    Long  userid,merchantid,trxid;
    double amount;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.merchantNameText) TextView merchantNameText;
    @Bind(R.id.amountText)
    EditText amountText;
    @Bind(R.id.productNameText) EditText productNameText;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.payBut) Button pay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_payment);
        ButterKnife.bind(this);
        toolbar.setOnClickListener(this);
        pay.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setTitle("");

        toolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BuyerPaymentActivity.this,BuyerHomeActivity.class);
                startActivity(i);
            }
        });

        Intent i = getIntent();
        merchantid = i.getLongExtra("merchantId", 1L);
        merchantname = i.getStringExtra("merchantName").toString();

        merchantNameText.setText(merchantname);
        userid = BolpaxStatic.getUserid();
        token = BolpaxStatic.getToken();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
                Intent i = new Intent(BuyerPaymentActivity.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(BuyerPaymentActivity.this, CreateStoreActivity.class);
                startActivity(i2);

                return true;

            case R.id.quit:
                finish();

                return true;

            case R.id.action_settings:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.payBut:
                amountString = amountText.getText().toString();
                productName= productNameText.getText().toString();
                amount = Double.parseDouble(amountString);
                Payment payment = new Payment();
                payment.setUserId(userid);
                payment.setMerchantId(merchantid);
                payment.setAmount(amount);
                payment.setProduct(productName);
                payment.setToken(token);

                RestClient.getBolpax().getBuyerPayment(payment, new Callback<PaymentResponse>() {
                    @Override
                    public void success(PaymentResponse s, Response response) {
                        trxid = s.getTrxId();
                        String test = s.getAmount();
                        String test2=s.getFromAccount();
//                        Toast.makeText(getBaseContext(), "cek di database", Toast.LENGTH_LONG).show();
                        Intent i2 = new Intent(BuyerPaymentActivity.this,BuyerTransactionDetailActivity.class);
                        i2.putExtra("trxid", trxid);
                        startActivity(i2);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getMessage());
                    }
                });
                break;


        }
    }
}
