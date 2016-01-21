package id.co.imwizz.bolpax.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.MerchantList;
import id.co.imwizz.bolpax.data.service.DummyAPI;

/**
 * Created by User on 08/01/2016.
 */
public class MerchantList_Activity extends AppCompatActivity {

    protected Context mContext;
    String email,name,phone;
    Integer balance;
//    LinearLayout merchant;
    ListView listView;
    MerchantList merchant;
    List<MerchantList> merchant2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_list);


//        email = merchant.getMerchant();
        listView=(ListView)findViewById(R.id.listView);
        String json = DummyAPI.getJson(MerchantList_Activity.this, R.raw.merchant_list);
        Gson gson = new Gson();
//        JSONArray jsonArr = new JSONArray();

        MerchantList[] merchant = gson.fromJson(json, MerchantList[].class);

//        String Test = merchant.getMerchant();
//        String[] catValues5 = new String[merchant.size() + 1];
//        for (int i = 0; i < merchant.size(); i++) {
//
//        }
        MerchantListAdapter merchantlistAdapter = new MerchantListAdapter(MerchantList_Activity.this, merchant);
        listView.setAdapter(merchantlistAdapter);
//        name = profile.getName();
//        phone = profile.getPhone();
//        balance = profile.getBalance();


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
//                Intent i = new Intent(BuyerHomeActivity.this, ProfileActivity.class);
//                i.putExtra("email", email);
//                i.putExtra("name", name);
//                i.putExtra("phone", phone);
//                i.putExtra("balance", balance);
//                startActivity(i);

                return true;

            case R.id.computer:
//                Intent i2 = new Intent(BuyerHomeActivity.this, ProfileActivity.class);
//                i2.putExtra("email", email);
//                i2.putExtra("name", name);
//                i2.putExtra("phone", phone);
//                i2.putExtra("balance", balance);
//                startActivity(i2);
//
//                return true;

            case R.id.gamepad:

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
