package id.co.imwizz.bolpax.ui.view;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.IssueHistoryAdapter;
import id.co.imwizz.bolpax.data.entity.IssueDetail;
import id.co.imwizz.bolpax.data.entity.IssueHistory;
import id.co.imwizz.bolpax.data.service.DummyAPI;

/**
 * Created by bimosektiw on 1/14/16.
 */
public class BuyerIssueDetailActivity extends AppCompatActivity {

    private TextView suspectText, amountText, issueLastStatusText;
    List<IssueHistory> issueHistory;
    private ListView issueDetailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_detail);
        initWidget();
        setToolbar();

        String json = DummyAPI.getJson(BuyerIssueDetailActivity.this ,R.raw.issue_detail);
        Gson gson = new Gson();
        IssueDetail issueDetail = gson.fromJson(json, IssueDetail.class);

        String suspect = issueDetail.getSuspect();
        String amount = issueDetail.getAmount();
        String product = issueDetail.getProduct();
        String laststatus = issueDetail.getIssueLastStatus();

        issueHistory = issueDetail.getIssueHistory();
        issueDetailText.setSelector(new ColorDrawable(0));
        String[] issue = new String[issueHistory.size() + 1];
        for (int i = 0; i < issueHistory.size(); i++) {
            issue[i] = issueHistory.get(i).getTime();
            issue[i] = issueHistory.get(i).getMessage();
        }
        ListAdapter listAdapter = new IssueHistoryAdapter(BuyerIssueDetailActivity.this, issueHistory);
        issueDetailText.setAdapter(listAdapter);
        suspectText.setText(suspect);
        amountText.setText("Rp "+amount +" for "+ product);
        issueLastStatusText.setText(laststatus);
    }

    private void initWidget(){
        suspectText = (TextView) findViewById(R.id.suspect);
        amountText = (TextView) findViewById(R.id.amount);
        issueLastStatusText = (TextView) findViewById(R.id.laststatus);
        issueDetailText = (ListView) findViewById(R.id.list_detail);
    }

    private void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
//        toolbar.setTitle("");
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("BOLPAX");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_switch) {
            return true;
        }
        if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
