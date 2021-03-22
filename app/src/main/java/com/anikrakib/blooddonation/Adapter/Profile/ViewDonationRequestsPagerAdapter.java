package com.anikrakib.blooddonation.Adapter.Profile;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.anikrakib.blooddonation.Fragment.Profile.DonationFragment;
import com.anikrakib.blooddonation.Fragment.Profile.RequestsFragment;
import com.anikrakib.blooddonation.databinding.FragmentMainProfileDataBinding;


public class ViewDonationRequestsPagerAdapter extends FragmentPagerAdapter {
    FragmentMainProfileDataBinding fragmentMainProfileDataBinding;

    public ViewDonationRequestsPagerAdapter(FragmentManager fm, FragmentMainProfileDataBinding fragmentMainProfileDataBinding) {
        super(fm);
        this.fragmentMainProfileDataBinding = fragmentMainProfileDataBinding;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DonationFragment(fragmentMainProfileDataBinding);
            case 1:
                return new RequestsFragment();
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
                return "Donation";
            case 1:
                return "Requests";
            default:
                return null;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}