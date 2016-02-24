package id.co.imwizz.bolpax.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.bolpax.response.MerchantBolpax;
import id.co.imwizz.bolpax.rest.Logout;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bimosektiw on 1/27/16.
 */
public class MerchantReportIssueActivity extends AppCompatActivity {

    private ListView mListView;
    @Bind(R.id.back) TextView back;
    @Bind(R.id.button2) Button button2;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    MenuItem createstore,switchtomerchant,buyername;
    String email,name,phone,token,nama;
    Long bolpax,merchantId,userid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_reportissue);
        ButterKnife.bind(this);
        setToolbar();

        String[] names = new String[] { "Item Delay", "Claim Payment"};
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice, android.R.id.text1, names));
        ListView listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        back.setText("<");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MerchantReportIssueActivity.this, MerchantReportIssueActivity2.class);
                startActivity(i);
            }
        });

    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MerchantReportIssueActivity.this, MerchantHomeActivity.class);
                startActivity(i);
            }
        });
        toolbar.setTitle("");
        toolbarTitle.setText("BOLPAX");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        createstore = menu.findItem(R.id.create_store);
        switchtomerchant = menu.findItem(R.id.switchto_merchant);
        buyername = menu.findItem(R.id.profile);

        RestClient.getBolpax().getMerchantProfile(userid.toString(), token.toString(), new Callback<MerchantBolpax>() {
            @Override
            public void success(MerchantBolpax merchantBolpax, Response response) {
                nama = merchantBolpax.getMerchantName();
                buyername.setTitle(nama.toString());
                createstore.setVisible(false);
                switchtomerchant.setTitle("Switch To Buyer");
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
                Intent i = new Intent(MerchantReportIssueActivity.this, MerchantProfile.class);
                startActivity(i);

                return true;
            case R.id.switchto_merchant:
                Intent i3 = new Intent(MerchantReportIssueActivity.this, BuyerHomeActivity.class);
                startActivity(i3);

                return true;

            case R.id.quit:
                RestClient.getBolpax().getLogout(token, phone, new Callback<Logout>() {
                    @Override
                    public void success(Logout s, Response response) {

                        String success = s.getStatus();
                        if (success.contains("SUCCESS")) {
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MerchantReportIssueActivity.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {
//                        Log.e(TAG, error.getMessage());

                    }
                });

                return true;

            case R.id.action_settings:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
    protected ListView getListView() {
        if (mListView == null) {
            mListView = (ListView) findViewById(android.R.id.list);
        }
        return mListView;
    }

    protected void setListAdapter(ListAdapter adapter) {
        getListView().setAdapter(adapter);
    }

    protected ListAdapter getListAdapter() {
        ListAdapter adapter = getListView().getAdapter();
        if (adapter instanceof HeaderViewListAdapter) {
            return ((HeaderViewListAdapter)adapter).getWrappedAdapter();
        } else {
            return adapter;
        }
    }
}