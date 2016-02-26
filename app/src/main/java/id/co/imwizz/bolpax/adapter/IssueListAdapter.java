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
import id.co.imwizz.bolpax.data.entity.bolpax.request.BuyerIssueListPojo;

//import com.squareup.picasso.Picasso;
//import id.co.imwizz.gokeel.R;
//import id.co.imwizz.gokeel.domain.service.Category;

public class IssueListAdapter extends ArrayAdapter<BuyerIssueListPojo> {


    private List<BuyerIssueListPojo> issueList;
//    IssueList[] issueList2;
    Context mContext;

    public IssueListAdapter(Context context, List<BuyerIssueListPojo> issueList) {
        super(context, R.layout.issue_list, issueList);
        this.issueList = issueList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/SEGOEUI.TTF");
        LayoutInflater categoryInflater = LayoutInflater.from(getContext());
        View customView = categoryInflater.inflate(R.layout.issue_list, parent, false);

        BuyerIssueListPojo category = getItem(position);

        TextView dateText = (TextView) customView.findViewById(R.id.tvdate);
        TextView statusText = (TextView) customView.findViewById(R.id.tvsatus);
        TextView nominalText = (TextView) customView.findViewById(R.id.tvnominal);
//        ImageView categoryImage = (ImageView) customView.findViewById(R.id.categoryImage);

//        String status = category.getTrxLastStatus();

        dateText.setText(category.getIssueDate());
        statusText.setText(category.getIssueLastStatus());
//        statusText.setTextColor(Color.YELLOW);
        statusText.setTextColor(Color.parseColor("#d36a04"));


        nominalText.setText(category.getSuspect());


        return customView;
    }
}
