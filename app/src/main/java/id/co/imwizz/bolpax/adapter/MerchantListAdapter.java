package id.co.imwizz.bolpax.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.MerchantList;


/**
 * Created by MDeveloper on 1/18/2016.
 */
public class MerchantListAdapter extends ArrayAdapter<MerchantList> {
    MerchantList[] merchant;
//List<MerchantList> merchant;

    public MerchantListAdapter(Context context, MerchantList[] merchant) {
        super(context, R.layout.merchantadater, merchant);
        this.merchant = merchant;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/SEGOEUI.TTF");
        LayoutInflater categoryInflater = LayoutInflater.from(getContext());
        View customView = categoryInflater.inflate(R.layout.business_hour_list, parent, false);

        MerchantList merchant = getItem(position);
        TextView businessDay = (TextView) customView.findViewById(R.id.businessOpen);
//        TextView businessOpen = (TextView) customView.findViewById(R.id.businessOpen);
//        TextView businessClose = (TextView) customView.findViewById(R.id.businessClose);

        ImageView categoryImage = (ImageView) customView.findViewById(R.id.imageView7);
        //int test = businessHourses.getDay();
        //int[] test = new int[businessHourses.getDay()];
        businessDay.setText(merchant.getMerchant());
        //if (test != null){


        //}
//        String test = businessHourses.getCloseTime();
//        if (test.contains("-1")){
//            businessDay.setText("Setiap Hari");
//            businessDay.setTypeface(tf);
//            businessOpen.setText("00:00");
//            businessOpen.setTypeface(tf);
//            businessClose.setText("24:00");
//            businessClose.setTypeface(tf);
//        }else {
//            businessDay.setText(businessHourses.getConverterDay());
//            businessDay.setTypeface(tf);
//            businessOpen.setText((CharSequence) businessHourses.getOpenTime());
//            businessOpen.setTypeface(tf);
//            businessClose.setText((CharSequence) businessHourses.getCloseTime());
//            businessClose.setTypeface(tf);
//        }



        /*if(businessHourses.getIcon() != null) {
            Uri uri = Uri.parse(businessHourses.getIcon());
            Context context = categoryImage.getContext();
            Picasso.with(context).load(uri).into(categoryImage);
        } else {
            categoryImage.setImageResource(R.drawable.ic_launcher);

        }*/


        return customView;


    }
}
