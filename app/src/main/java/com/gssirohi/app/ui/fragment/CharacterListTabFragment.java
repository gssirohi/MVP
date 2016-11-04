package com.gssirohi.app.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gssirohi.app.R;
import com.gssirohi.app.base.BaseActivity;
import com.gssirohi.app.domain.model.pojo.MCharacter;
import com.gssirohi.app.ui.activity.CharacterListActivity;
import com.gssirohi.app.ui.adapter.CharacterSectionsPagerAdapter;
import com.gssirohi.app.ui.presenter.CharactersScreenPresenter;
import com.gssirohi.app.ui.presenter.ICharactersScreenPresenter;
import com.gssirohi.app.ui.viewcontract.CharactersScreenViewContract;
import com.gssirohi.app.ui.viewmodel.CharactersListItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link CharactersListFragment.OnListFragmentInteractionListener}
 * interface.
 */
public class CharacterListTabFragment extends Fragment implements CharactersListFragment.OnListFragmentInteractionListener
        , CharactersScreenViewContract {

    private CharacterSectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private List<MCharacter> characters = new ArrayList<>();
    private ICharactersScreenPresenter iMyTripPresenter;
    private CharactersListFragment.OnListFragmentInteractionListener mListener;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CharacterListTabFragment() {
    }

    public static CharacterListTabFragment newInstance() {
        CharacterListTabFragment fragment = new CharacterListTabFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_layout, container, false);

        initializeTripList(view);
        return view;
    }

    public void initializeTripList(View view) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);

        initializePagerAdapter(view);
        iMyTripPresenter = new CharactersScreenPresenter(getContext());
        iMyTripPresenter.setView(this);
        iMyTripPresenter.onFetchAllTripRequest(true);
    }


    public void initializePagerAdapter(View view) {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        if (mSectionsPagerAdapter == null)
            mSectionsPagerAdapter = new CharacterSectionsPagerAdapter(getActivity().getSupportFragmentManager(), this);

        // Set up the ViewPager with the sections adapter.
        if (mViewPager == null)
            mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onLoadMoreDataRequest() {
        iMyTripPresenter.onFetchAllTripRequest(false);
    }

    @Override
    public void onListFragmentItemInteraction(CharactersListItemViewModel item) {
        //getNavigator().navigateToCharacterDetailsScreen(item);
        Toast.makeText(getActivity(), item.getName(), Toast.LENGTH_SHORT).show();
        // Create new fragment and transaction
        MCharacter character = (MCharacter) item.getModel();
        ProductDetailsFragment newFragment = ProductDetailsFragment.newInstance(character.getResourceURI());
        android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed

        //TODO: put tablet and mobile logic here
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack("label");

// Commit the transaction
        transaction.commit();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof CharactersListFragment.OnListFragmentInteractionListener) {
//            mListener = (CharactersListFragment.OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public List<MCharacter> getCharacterList(int tabPosition) {
        return ((CharacterListActivity) getActivity()).getCharactersList(tabPosition);

    }

    public List<MCharacter> getAllCharacters() {
        return ((CharacterListActivity) getActivity()).getCharacters();
    }

    public void setAllCharacters(List<MCharacter> characters) {
        if (getAllCharacters() != null) {
            getAllCharacters().addAll(characters);
            mSectionsPagerAdapter.notifyDataSetChanged();
            mSectionsPagerAdapter.getAvengerFragment().getRecyclerAdapter().setValues(getCharacterList(0));
            mSectionsPagerAdapter.getOtherFragment().getRecyclerAdapter().setValues(getCharacterList(1));
            mSectionsPagerAdapter.getAvengerFragment().getRecyclerAdapter().notifyDataSetChanged();
            mSectionsPagerAdapter.getOtherFragment().getRecyclerAdapter().notifyDataSetChanged();
        }
    }


    @Override
    public void onError(String s) {
        Snackbar.make(null, s, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onAllCharacterFetched(List<MCharacter> characters) {
        setAllCharacters(characters);
    }

}
