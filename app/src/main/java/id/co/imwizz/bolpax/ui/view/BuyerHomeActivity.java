package id.co.imwizz.bolpax.ui.view;

import android.content.Context;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;

/**
 * Created by User on 08/01/2016.
 */
public class BuyerHomeActivity extends AppCompatActivity implements View.OnClickListener {

    protected Context mContext;
    String email,name,phone;
    Integer balance;
    @Bind(R.id.merchant) LinearLayout merchant;
    @Bind(R.id.transaction) LinearLayout transaction;
    @Bind(R.id.issue) LinearLayout issue;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    //LinearLayout merchant,transaction,issue;
    //Toolbar toolbar;
//    TextView toolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_home);
        ButterKnife.bind(this);
        merchant.setOnClickListener(this);
        transaction.setOnClickListener(this);
        issue.setOnClickListener(this);
//        toolbar.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);

        toolbar.setTitle("");
        toolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BuyerHomeActivity.this, "This Home", Toast.LENGTH_SHORT).show();
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
            case R.id.profile:
                Intent i = new Intent(BuyerHomeActivity.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(BuyerHomeActivity.this, MerchantHomeActivity.class);
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
            case R.id.merchant:
                Intent i = new Intent(BuyerHomeActivity.this,MerchantList_Activity.class);
                startActivity(i);
                break;
            case R.id.transaction:
                Intent i2 = new Intent(BuyerHomeActivity.this,BuyerTransactionList.class);
                startActivity(i2);
                break;
            case R.id.issue:
                Intent i3 = new Intent(BuyerHomeActivity.this,BuyerIssueList.class);
                startActivity(i3);
                break;
        }

    }
}
