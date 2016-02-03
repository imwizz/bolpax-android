package id.co.imwizz.bolpax.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;

/**
 * Created by User on 08/01/2016.
 */
public class BuyerPaymentActivity extends AppCompatActivity implements View.OnClickListener {

    protected Context mContext;
    String email,name,phone,merchants;
    Integer balance;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.textView) TextView merchantName;
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
        merchants = i.getStringExtra("merchant").toString();

        merchantName.setText(merchants);


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
                Toast.makeText(BuyerPaymentActivity.this, "Thank You", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(BuyerPaymentActivity.this,BuyerTransactionDetailActivity.class);
                startActivity(i2);
                break;


        }
    }
}
