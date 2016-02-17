package id.co.imwizz.bolpax.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.bolpax.request.MerchantIssueListPojo;

//import com.squareup.picasso.Picasso;
//import id.co.imwizz.gokeel.R;
//import id.co.imwizz.gokeel.domain.service.Category;

public class MerchantIssueListAdapter extends ArrayAdapter<MerchantIssueListPojo> {


    private List<MerchantIssueListPojo> issueList;
    //    IssueList[] issueList2;
    Context mContext;

    public MerchantIssueListAdapter(Context context, List<MerchantIssueListPojo> issueList) {
        super(context, R.layout.issue_list, issueList);
        this.issueList = issueList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/SEGOEUI.TTF");
        LayoutInflater categoryInflater = LayoutInflater.from(getContext());
        View customView = categoryInflater.inflate(R.layout.issue_list, parent, false);

        MerchantIssueListPojo category = getItem(position);

        TextView dateText = (TextView) customView.findViewById(R.id.tvdate);
        TextView statusText = (TextView) customView.findViewById(R.id.tvsatus);
        TextView nominalText = (TextView) customView.findViewById(R.id.tvnominal);
//        ImageView categoryImage = (ImageView) customView.findViewById(R.id.categoryImage);

//        String status = category.getTrxLastStatus();

        dateText.setText(category.getIssueDate());
        statusText.setText(category.getIssueLastStatus());
        statusText.setTextColor(Color.YELLOW);


        nominalText.setText(category.getSuspect());
//        categoryText.setTypeface(tf);
//        if(category.getIcon() != null) {
//            Uri uri = Uri.parse(category.getIcon());
//            Context context = categoryImage.getContext();
//            Picasso.with(context).load(uri).into(categoryImage);
//        } else {
//            categoryImage.setImageResource(R.drawable.ic_launcher);
//        }
//        customView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//
//                // Send single item click data to SingleItemView Class
//                Intent intent = new Intent(mContext, BuyerPaymentActivity.class);
////                // Pass all data rank
//                intent.putExtra("dbdest",(merchant2.get));
//                mContext.startActivity(intent);
//
//            }
//        });

        return customView;
    }
}
