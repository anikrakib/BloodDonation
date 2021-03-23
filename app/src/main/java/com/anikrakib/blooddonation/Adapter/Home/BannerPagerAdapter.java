package com.anikrakib.blooddonation.Adapter.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;


import com.anikrakib.blooddonation.Model.CampaignModel;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class BannerPagerAdapter extends PagerAdapter {

    private Context mContext ;
    private List<CampaignModel> mList ;


    public BannerPagerAdapter(Context mContext, List<CampaignModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.item_banner,null);

        CampaignModel campaignModel = mList.get(position);

        ImageView image = slideLayout.findViewById(R.id.bannerImage);
        TextView title = slideLayout.findViewById(R.id.title);
        TextView date = slideLayout.findViewById(R.id.date);
        TextView host = slideLayout.findViewById(R.id.host);
        TextView time = slideLayout.findViewById(R.id.time);

        Glide.with(mContext)
                .load(campaignModel.getCampaignImage())
                .into(image);
        title.setText(campaignModel.getCampaignTitle());
        host.setText("by "+campaignModel.getCampaignHost());
        //time.setText(campaignModel.getCampaignTime().toDate());

        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
        String currentDate = sdf.format(calendar.getTime());

        long dayPassed = HelperClass.getBloodDayRemaining(currentDate,campaignModel.getCampaignDate());

        if(dayPassed == 0){
            date.setText("Held On Today");
        }else {
            date.setText("Held On "+campaignModel.getCampaignDate());
        }

        String timesAgo = (String) DateUtils.getRelativeTimeSpanString(campaignModel.getCampaignTime().getSeconds()*1000);
        time.setText(timesAgo);

        container.addView(slideLayout);
        return slideLayout;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
