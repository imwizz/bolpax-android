package id.co.imwizz.bolpax.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.Profile;
import id.co.imwizz.bolpax.data.service.DummyAPI;

/**
 * Created by User on 08/01/2016.
 */
public class MerchantHomeActivity extends AppCompatActivity implements View.OnClickListener{
    String email,name,phone;
    Integer balance;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.transaction) LinearLayout transaction;
    @Bind(R.id.issue) LinearLayout issue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_home);
        ButterKnife.bind(this);
        setToolbar();

        String json = DummyAPI.getJson(MerchantHomeActivity.this, R.raw.profile);
        Gson gson = new Gson();
        Profile profile = gson.fromJson(json, Profile.class);
        email = profile.getEmail();
        name = profile.getName();
        phone = profile.getPhone();
        balance = profile.getBalance();
        transaction.setOnClickListener(this);
        issue.setOnClickListener(this);
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setNavigationOnClickListener(this);
        toolbar.setTitle("");
        toolbarTitle.setText("BOLPAX");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.transaction:
                Toast.makeText(MerchantHomeActivity.this, "Pindah ke merchant transaction list", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MerchantHomeActivity.this, MerchantTransactionList.class);
                startActivity(i);
                break;
            case R.id.issue:
                Toast.makeText(MerchantHomeActivity.this, "Pindah ke merchant issue list", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MerchantHomeActivity.this, MerchantIssueList.class);
                startActivity(myIntent);
                break;
            case R.id.toolbar:
                Toast.makeText(MerchantHomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_switch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
                Intent i = new Intent(MerchantHomeActivity.this, ProfileActivity.class);
                i.putExtra("email", email);
                i.putExtra("name", name);
                i.putExtra("phone", phone);
                i.putExtra("balance", balance);
                startActivity(i);

                return true;

            case R.id.switchto_buyer:
                Intent i2 = new Intent(MerchantHomeActivity.this, BuyerHomeActivity.class);
                i2.putExtra("email", email);
                i2.putExtra("name", name);
                i2.putExtra("phone", phone);
                i2.putExtra("balance", balance);
                startActivity(i2);

                return true;

            case R.id.quit:

                return true;

            case R.id.action_settings:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
