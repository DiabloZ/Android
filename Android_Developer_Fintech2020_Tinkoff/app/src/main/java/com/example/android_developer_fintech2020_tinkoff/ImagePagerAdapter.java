package com.example.android_developer_fintech2020_tinkoff;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

class ImagePagerAdapter extends FragmentStateAdapter {
    public ImagePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SectionLastFragment();
            case 1:
                return new SectionBestFragment();
            default:
                return new SectionHotFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
