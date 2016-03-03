package id.co.imwizz.bolpax.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.request.AddHistoryIssueBolpax;
import id.co.imwizz.bolpax.data.entity.bolpax.request.Report;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bimosektiw on 1/27/16.
 */
public class MerchantReportIssue2Activity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MerchantReportIssue2Activity.class.getSimpleName();
    private String createSubjectReport,createDescReport,token,subject;
    private Long  userid,issueId,bolpax;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.text_toolbar_title) TextView textToolbarTitle;
    @Bind(R.id.text_subject) TextView textSubject;
    @Bind(R.id.edit_description) EditText editDescription;
    @Bind(R.id.button_submit) Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_reportissue2);
        ButterKnife.bind(this);
        buttonSubmit.setOnClickListener(this);
        setToolbar();
        Intent i = getIntent();
        subject = i.getStringExtra("Subject");
        issueId = i.getLongExtra("issueid",0);
        textSubject.setText(subject);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.button_submit:
                bolpax = BolpaxStatic.getUserid();
                userid = BolpaxStatic.getUserid();
                token = BolpaxStatic.getToken();
                createSubjectReport = textSubject.getText().toString();
                createDescReport = editDescription.getText().toString();
                Report report = new Report();
                report.setSubject(createDescReport);
                report.setDesc(createDescReport);
                report.setRole("buyer");
                report.setTrxId(1);

                AddHistoryIssueBolpax addHistoryIssueBolpax = new AddHistoryIssueBolpax();
                addHistoryIssueBolpax.setFromAdmin("N");
                addHistoryIssueBolpax.setMessage(createDescReport);
                addHistoryIssueBolpax.setIssueId(issueId);
                addHistoryIssueBolpax.setIssueStatusId(Long.valueOf(3));
                RestClient.getBolpax().postAddHistoryIssue(addHistoryIssueBolpax, new Callback<String>() {
                    @Override
                    public void success(String s, Response response) {

                        Intent i = new Intent(MerchantReportIssue2Activity.this, BuyerIssueDetailActivity.class);
                        i.putExtra("issueId", issueId);
                        startActivity(i);
                        Toast.makeText(MerchantReportIssue2Activity.this, "Report Submitted", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getMessage());

                    }
                });

                break;
        }
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MerchantReportIssue2Activity.this, MerchantHomeActivity.class);
                startActivity(i);
            }
        });
        toolbar.setTitle("");
        textToolbarTitle.setText("BOLPAX");

    }
}
