package com.gssirohi.app.ui.adapter;

/**
 * Created by gssirohi on 18/7/16.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gssirohi.app.ui.fragment.CharactersListFragment;


/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class CharacterSectionsPagerAdapter extends FragmentPagerAdapter {

    private final CharactersListFragment.OnListFragmentInteractionListener listner;
    private CharactersListFragment mAvengerFragment;
    private CharactersListFragment mOtherFragment;

    public CharacterSectionsPagerAdapter(FragmentManager fm, CharactersListFragment.OnListFragmentInteractionListener listner) {
        super(fm);
        this.listner = listner;
    }

    public CharactersListFragment getAvengerFragment() {
        return mAvengerFragment;
    }

    public CharactersListFragment getOtherFragment() {
        return mOtherFragment;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                if (mAvengerFragment == null) {
                    mAvengerFragment = CharactersListFragment.newInstance(0);
                    mAvengerFragment.setItemClickListner(listner);
                }
                return mAvengerFragment;
            case 1:
                if (mOtherFragment == null) {
                    mOtherFragment = CharactersListFragment.newInstance(1);
                    mOtherFragment.setItemClickListner(listner);
                }
                return mOtherFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "AVENGERS";
            case 1:
                return "OTHER CHARACTERS";
        }
        return null;
    }
}
