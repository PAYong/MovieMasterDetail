package com.example.android.itunesappandroidv2.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.android.itunesappandroidv2.R;
import com.example.android.itunesappandroidv2.views.AllMovieListFragment;
import com.example.android.itunesappandroidv2.views.SavedMovieFragment;

public class SectionPageAdapter extends FragmentPagerAdapter {

    private int[] mTitles = {R.string.tab_text_1, R.string.tab_text_2};
    private static int NUM_ITEMS = 2;
    private Context mCtx;

    public SectionPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        mCtx = context;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return AllMovieListFragment.newInstance();
            case 1:
                return SavedMovieFragment.newInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mCtx.getString(mTitles[position]);

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

}
