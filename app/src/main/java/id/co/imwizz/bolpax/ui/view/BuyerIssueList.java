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
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.IssueListAdapter;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.request.BuyerIssueListPojo;
import id.co.imwizz.bolpax.data.entity.bolpax.response.MerchantBolpax;
import id.co.imwizz.bolpax.data.entity.bolpax.response.ProfileBolpax;
import id.co.imwizz.bolpax.rest.Logout;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 08/01/2016.
 */
public class BuyerIssueList extends AppCompatActivity implements View.OnClickListener {

    protected Context mContext;
    String email,name,phone,merchants,userid,token,nama;
    private static final String TAG = BuyerIssueList.class.getSimpleName();
    Integer balance;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.listviewIssue) ListView issue;
    List<BuyerIssueListPojo> buyerIssueListPojos;
    BuyerIssueListPojo issuelist;
    final Context context = this;
    Long bolpax,id;
    MenuItem createstore,switchtomerchant,buyername;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuelist);
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
                Intent i = new Intent(BuyerIssueList.this, BuyerHomeActivity.class);
                startActivity(i);
            }
        });

        bolpax = BolpaxStatic.getUserid();
        userid = bolpax.toString();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();

        RestClient.getBolpax().getBuyerIssuelist(userid.toString(), new Callback<List<BuyerIssueListPojo>>() {
                    @Override
                    public void success(List<BuyerIssueListPojo> result, Response response) {

                        if(result==null){
                            Toast.makeText(BuyerIssueList.this, "No Transaction Found", Toast.LENGTH_SHORT).show();
                        }else{
                        buyerIssueListPojos = new ArrayList<BuyerIssueListPojo>(result);
                        for (int i = 0; i < buyerIssueListPojos.size(); i++) {
                            id = buyerIssueListPojos.get(i).getIssueId();
                            String date = buyerIssueListPojos.get(i).getIssueDate();
                            String status = buyerIssueListPojos.get(i).getIssueLastStatus();
                            Double amount = buyerIssueListPojos.get(i).getAmount();
                            String suspect = buyerIssueListPojos.get(i).getSuspect();

                            ListAdapter issueListAdapter = new IssueListAdapter(BuyerIssueList.this, buyerIssueListPojos);
                            issue.setAdapter(issueListAdapter);

                            issue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    issuelist = (BuyerIssueListPojo) parent.getItemAtPosition(position);
                                    Intent myIntent = new Intent(BuyerIssueList.this, BuyerIssueDetailActivity.class);
                                    myIntent.putExtra("issueId", (issuelist.getIssueId()));
                                    startActivity(myIntent);

                                }
                            });
                        }


                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getMessage());

                    }
                });


//                String json = DummyAPI.getJson(BuyerIssueList.this, R.raw.issue_list);
//        Gson gson = new Gson();
//        IssueList[] issueList = gson.fromJson(json, IssueList[].class);
//
//        ListAdapter issueListAdapter = new IssueListAdapter(BuyerIssueList.this, issueList);
//        issue.setAdapter(issueListAdapter);






    }
    @OnItemClick(R.id.listviewIssue)
    void onItemClick(int position) {
        Intent i2 = new Intent(BuyerIssueList.this,BuyerIssueDetailActivity.class);
        startActivity(i2);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        createstore = menu.findItem(R.id.create_store);
        switchtomerchant = menu.findItem(R.id.switchto_merchant);
        buyername = menu.findItem(R.id.profile);
        RestClient.getBolpax().getProfile(userid.toString(), token.toString(), new Callback<ProfileBolpax>() {
            @Override
            public void success(ProfileBolpax profileBolpax, Response response) {
                nama = profileBolpax.getFullname();
                buyername.setTitle(nama.toString());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        RestClient.getBolpax().getMerchantProfile(userid.toString(), token.toString(), new Callback<MerchantBolpax>() {
            @Override
            public void success(MerchantBolpax merchantBolpax, Response response) {
                if (merchantBolpax != null){
                    createstore.setVisible(false);
                } else {
                    switchtomerchant.setVisible(false);

                }
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
                Intent i = new Intent(BuyerIssueList.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(BuyerIssueList.this, CreateStoreActivity.class);
                startActivity(i2);

                return true;
            case R.id.switchto_merchant:
                Intent i3 = new Intent(BuyerIssueList.this, MerchantHomeActivity.class);
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
                            Toast.makeText(BuyerIssueList.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {



        }
    }
}
