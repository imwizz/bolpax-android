package id.co.imwizz.bolpax.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.MerchantListAdapter;
import id.co.imwizz.bolpax.data.entity.MerchantList;
import id.co.imwizz.bolpax.data.service.DummyAPI;

/**
 * Created by User on 08/01/2016.
 */
public class MerchantList_Activity extends AppCompatActivity implements View.OnClickListener {

    protected Context mContext;
    String email,name,phone;
    Integer balance;
    List<MerchantList> merchant2;
    MerchantList merchants;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.listView) ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_list);
        ButterKnife.bind(this);
//        toolbar.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setTitle("");
        toolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MerchantList_Activity.this,BuyerHomeActivity.class);
                startActivity(i);
            }
        });

        String json = DummyAPI.getJson(MerchantList_Activity.this, R.raw.merchant_list);
        Gson gson = new Gson();
        MerchantList[] merchant = gson.fromJson(json, MerchantList[].class);

        ListAdapter merchantlistAdapter = new MerchantListAdapter(MerchantList_Activity.this, merchant);
        listView.setAdapter(merchantlistAdapter);

    }
    @OnItemClick(R.id.listView)
    void onItemClick(AdapterView<?> parent, View view,
                     int position, long id) {
        merchants = (MerchantList) parent.getItemAtPosition(position);
        Intent myIntent = new Intent(MerchantList_Activity.this, BuyerPaymentActivity.class);
        myIntent.putExtra("merchant", (merchants.getMerchant()));
        startActivity(myIntent);
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
                Intent i = new Intent(MerchantList_Activity.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(MerchantList_Activity.this, CreateStoreActivity.class);
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



        }
    }
}
