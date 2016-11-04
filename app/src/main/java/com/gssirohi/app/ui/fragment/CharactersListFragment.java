package com.gssirohi.app.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gssirohi.app.R;
import com.gssirohi.app.constant.Key;
import com.gssirohi.app.domain.model.pojo.MCharacter;
import com.gssirohi.app.ui.activity.CharacterListActivity;
import com.gssirohi.app.ui.adapter.CharacterRecyclerViewAdapter;
import com.gssirohi.app.ui.adapter.EndlessRecyclerOnScrollListener;
import com.gssirohi.app.ui.viewmodel.CharactersListItemViewModel;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CharactersListFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    private int tabPosition;
    private LinearLayoutManager mLayoutManager;
    private CharacterRecyclerViewAdapter mRecyclerAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CharactersListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CharactersListFragment newInstance(int tabPosition) {
        CharactersListFragment fragment = new CharactersListFragment();
        Bundle args = new Bundle();
        args.putInt(Key.TAB_POSITION, tabPosition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            tabPosition = args.getInt(Key.TAB_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_characters_list_layout, container, false);

        initializeTripList(view);
        return view;
    }

    public void initializeTripList(View view) {
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            this.mLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(mLayoutManager);

            mRecyclerAdapter = new CharacterRecyclerViewAdapter(getCharacterList(), mListener);
            recyclerView.setAdapter(mRecyclerAdapter);
            recyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager) {
                @Override
                public void onLoadMore(int current_page) {
                    MCharacter character = new MCharacter();
                    character.setId(1234);
                    character.setName("Dummy Character");
                    mRecyclerAdapter.getValues().add(character);
                    mRecyclerAdapter.notifyDataSetChanged();
                    loadMoreData();
                }
            });
        }
    }

    public CharacterRecyclerViewAdapter getRecyclerAdapter() {
        return mRecyclerAdapter;
    }

    private void loadMoreData() {
        if (mListener != null) {
            mListener.onLoadMoreDataRequest();
        }
    }

    private List<MCharacter> getCharacterList() {
        return ((CharacterListActivity) getActivity()).getCharactersList(tabPosition);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
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

    public void setItemClickListner(OnListFragmentInteractionListener listner) {
        mListener = listner;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentItemInteraction(CharactersListItemViewModel item);

        void onLoadMoreDataRequest();
    }
}
