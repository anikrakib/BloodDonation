package com.anikrakib.blooddonation.Adapter.Profile;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.anikrakib.blooddonation.Fragment.Profile.ContactFragment;
import com.anikrakib.blooddonation.Fragment.Profile.DonationFragment;
import com.anikrakib.blooddonation.Fragment.Profile.InfoFragment;
import com.anikrakib.blooddonation.Fragment.Profile.RequestsFragment;
import com.anikrakib.blooddonation.databinding.FragmentMainProfileDataBinding;


public class ViewInfoContatctPagerAdapter extends FragmentPagerAdapter {

    public ViewInfoContatctPagerAdapter(FragmentManager fm, FragmentMainProfileDataBinding fragmentMainProfileDataBinding) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new InfoFragment();
            case 1:
                return new ContactFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Info";
            case 1:
                return "Contact";
            default:
                return null;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}