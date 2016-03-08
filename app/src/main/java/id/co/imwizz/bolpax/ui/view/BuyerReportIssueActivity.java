package id.co.imwizz.bolpax.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;

/**
 * This activity is used to display buyer report issue page 1
 *
 * @author bimosektiw
 */
public class BuyerReportIssueActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView mListView,listView;
    private TextView back;
    private String selectedFromList,trxid;

    @Bind(R.id.button_next) Button buttonNext;
    @Bind(R.id.progress_bar) ProgressBar progressBar;
    @Bind(R.id.text_notification) TextView textNotification;
    @Bind(R.id.text_toolbar_title) TextView textToolbarTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_reportissue);
        ButterKnife.bind(this);
        buttonNext.setOnClickListener(this);
        Intent x = getIntent();
        trxid = x.getStringExtra("trxid").toString();
        setToolbar();

        String[] names = new String[] { "Payment Pending", "Wrong Item", "Item Not Delivered" };
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice, android.R.id.text1, names));
        listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedFromList =(listView.getItemAtPosition(position).toString());
            }
        });

        back = (TextView) findViewById(R.id.text_back);
        back.setText("<");

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_next:
                progressBar.setVisibility(View.VISIBLE);
                textNotification.setVisibility(View.VISIBLE);
                Intent myIntent = new Intent(BuyerReportIssueActivity.this, BuyerCreateReportActivity.class);
                myIntent.putExtra("subject", selectedFromList);
                myIntent.putExtra("trxid", trxid);
                startActivity(myIntent);
                progressBar.setVisibility(View.GONE);
                textNotification.setVisibility(View.GONE);
                break;


        }
    }

    private void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BuyerReportIssueActivity.this, BuyerHomeActivity.class);
                startActivity(i);
            }
        });
        toolbar.setTitle("");
        textToolbarTitle.setText("BOLPAX");

    }
    /**
     * Method to get list view
     */
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