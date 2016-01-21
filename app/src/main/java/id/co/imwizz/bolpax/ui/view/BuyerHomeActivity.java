package id.co.imwizz.bolpax.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.Profile;
import id.co.imwizz.bolpax.data.service.DummyAPI;

/**
 * Created by User on 08/01/2016.
 */
public class BuyerHomeActivity extends AppCompatActivity {

    protected Context mContext;
    String email,name,phone;
    Integer balance;
    LinearLayout merchant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_home);


        String json = DummyAPI.getJson(BuyerHomeActivity.this, R.raw.profile);
        Gson gson = new Gson();
        Profile profile = gson.fromJson(json, Profile.class);
        email = profile.getEmail();
        name = profile.getName();
        phone = profile.getPhone();
        balance = profile.getBalance();
    merchant = (LinearLayout)findViewById(R.id.merchant);
    merchant.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(BuyerHomeActivity.this,MerchantList_Activity.class);
            startActivity(i);
        }
    });

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
            case R.id.phone:
                Intent i = new Intent(BuyerHomeActivity.this, ProfileActivity.class);
                i.putExtra("email", email);
                i.putExtra("name", name);
                i.putExtra("phone", phone);
                i.putExtra("balance", balance);
                startActivity(i);

                return true;

            case R.id.computer:
                Intent i2 = new Intent(BuyerHomeActivity.this, ProfileActivity.class);
                i2.putExtra("email", email);
                i2.putExtra("name", name);
                i2.putExtra("phone", phone);
                i2.putExtra("balance", balance);
                startActivity(i2);

                return true;

            case R.id.gamepad:

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
