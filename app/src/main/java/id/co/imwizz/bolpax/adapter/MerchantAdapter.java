package id.co.imwizz.bolpax.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.bolpax.response.MerchantRsp;


/**
 * Created by MDeveloper on 1/18/2016.
 */
public class MerchantAdapter extends ArrayAdapter<MerchantRsp> {

    private List<MerchantRsp> merchant;

    public MerchantAdapter(Context context, List<MerchantRsp> merchant) {
        super(context, R.layout.item_merchant, merchant);
        this.merchant = merchant;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater categoryInflater = LayoutInflater.from(getContext());
        View customView = categoryInflater.inflate(R.layout.item_merchant, parent, false);

        MerchantRsp merchant = getItem(position);
        TextView textMerchant = (TextView) customView.findViewById(R.id.text_merchant);
        textMerchant.setText(merchant.getMerchantName());
        return customView;


    }
}
