package id.co.imwizz.bolpax.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.response.MerchantRsp;
import id.co.imwizz.bolpax.data.entity.bolpax.response.LogoutRsp;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bimosektiw on 1/27/16.
 */
public class MerchantReportIssueActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MerchantReportIssueActivity.class.getSimpleName();
    private ListView mListView,listView;
    private String trxid,selectedFromList;
    private MenuItem createstore,switchtomerchant,buyername;
    private String phone,token,nama;
    private Long userid;

    @Bind(R.id.text_back) TextView textBack;
    @Bind(R.id.button_next) Button buttonNext;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.text_toolbar_title) TextView textToolbarTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_reportissue);
        ButterKnife.bind(this);
        setToolbar();
        Intent x = getIntent();
        trxid = x.getStringExtra("trxid").toString();
        userid = BolpaxStatic.getUserid();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();

        String[] names = new String[] { "Item Delay", "Claim Payment"};
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice, android.R.id.text1, names));
        final ListView listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedFromList =(listView.getItemAtPosition(position).toString());
            }
        });

        textBack.setText("<");
        buttonNext.setOnClickListener(this);

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
        textToolbarTitle.setText("BOLPAX");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        createstore = menu.findItem(R.id.create_store);
        switchtomerchant = menu.findItem(R.id.switchto_merchant);
        buyername = menu.findItem(R.id.profile);

        RestClient.getBolpax().getMerchantProfile(userid.toString(), token.toString(), new Callback<MerchantRsp>() {
            @Override
            public void success(MerchantRsp merchantRsp, Response response) {
                nama = merchantRsp.getMerchantName();
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
                Intent i = new Intent(MerchantReportIssueActivity.this, MerchantProfileActivity.class);
                startActivity(i);

                return true;
            case R.id.switchto_merchant:
                Intent i3 = new Intent(MerchantReportIssueActivity.this, BuyerHomeActivity.class);
                startActivity(i3);

                return true;

            case R.id.quit:
                RestClient.getBolpax().getLogout(token, phone, new Callback<LogoutRsp>() {
                    @Override
                    public void success(LogoutRsp s, Response response) {

                        String success = s.getStatus();
                        if (success.contains("SUCCESS")) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MerchantReportIssueActivity.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getMessage());

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
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_next:
                Intent myIntent = new Intent(MerchantReportIssueActivity.this, MerchantCreateReportActivity.class);
                myIntent.putExtra("subject",selectedFromList);
                myIntent.putExtra("trxid",trxid);
                startActivity(myIntent);
                break;


        }
    }
}