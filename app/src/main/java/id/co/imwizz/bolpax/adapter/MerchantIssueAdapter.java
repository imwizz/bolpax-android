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
import id.co.imwizz.bolpax.data.entity.bolpax.request.MerchantIssueRqs;


public class MerchantIssueAdapter extends ArrayAdapter<MerchantIssueRqs> {


    private List<MerchantIssueRqs> issueList;
    Context mContext;

    public MerchantIssueAdapter(Context context, List<MerchantIssueRqs> issueList) {
        super(context, R.layout.issue_list, issueList);
        this.issueList = issueList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater categoryInflater = LayoutInflater.from(getContext());
        View customView = categoryInflater.inflate(R.layout.issue_list, parent, false);

        MerchantIssueRqs category = getItem(position);

        TextView textDate = (TextView) customView.findViewById(R.id.text_date);
        TextView textStatus = (TextView) customView.findViewById(R.id.text_satus);
        TextView textNominal = (TextView) customView.findViewById(R.id.text_nominal);

        textDate.setText(category.getIssueDate());
        textStatus.setText(category.getIssueLastStatus());
        textStatus.setTextColor(Color.parseColor("#d36a04"));


        textNominal.setText(category.getSuspect());

        return customView;
    }
}
