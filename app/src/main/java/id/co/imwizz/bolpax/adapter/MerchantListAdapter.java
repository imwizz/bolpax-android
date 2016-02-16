package id.co.imwizz.bolpax.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.MerchantList;
import id.co.imwizz.bolpax.data.entity.bolpax.response.MerchantBolpax;


/**
 * Created by MDeveloper on 1/18/2016.
 */
public class MerchantListAdapter extends ArrayAdapter<MerchantBolpax> {
//    MerchantBolpax[] merchant;merchantz

    private List<MerchantBolpax> merchant;

    public MerchantListAdapter(Context context, List<MerchantBolpax> merchant) {
        super(context, R.layout.merchant_list, merchant);
        this.merchant = merchant;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/SEGOEUI.TTF");
        LayoutInflater categoryInflater = LayoutInflater.from(getContext());
        View customView = categoryInflater.inflate(R.layout.merchant_list, parent, false);

        MerchantBolpax merchant = getItem(position);
//        TextView businessDay = (TextView) customView.findViewById(R.id.businessOpen);
        TextView businessOpen = (TextView) customView.findViewById(R.id.businessOpen);
//        TextView businessClose = (TextView) customView.findViewById(R.id.businessClose);

        ImageView categoryImage = (ImageView) customView.findViewById(R.id.imageView7);
        //int test = businessHourses.getDay();
        //int[] test = new int[businessHourses.getDay()];
        businessOpen.setText(merchant.getMerchantName());
        //if (test != null){
        String Test = merchant.getMerchantName();


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
