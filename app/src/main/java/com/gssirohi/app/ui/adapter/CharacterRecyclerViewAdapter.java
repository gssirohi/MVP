package com.gssirohi.app.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.gssirohi.app.R;
import com.gssirohi.app.domain.model.pojo.MCharacter;
import com.gssirohi.app.ui.customview.CharacterListItemView;
import com.gssirohi.app.ui.fragment.CharactersListFragment;
import com.gssirohi.app.ui.viewmodel.CharactersListItemViewModel;

import java.util.List;

public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private int visibleThreshold = 2;

    private List<MCharacter> mValues;
    private final CharactersListFragment.OnListFragmentInteractionListener mListener;

    public CharacterRecyclerViewAdapter(List<MCharacter> items, CharactersListFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            CharacterListItemView view = new CharacterListItemView(parent);
            return new DataViewHolder(view);
        } else if (viewType == VIEW_PROG) {
            View view = View.inflate(parent.getContext(), R.layout.characters_list_item_loading_layout, null);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(params);
            return new ProgressViewHolder(view);
        } else
            return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DataViewHolder) {
            // holder.mItem = mValues.get(position);
            ((DataViewHolder) holder).mView.fillDetails(new CharactersListItemViewModel(mValues.get(position)));
            ((DataViewHolder) holder).mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        MCharacter character = ((DataViewHolder) holder).getView().getViewModel().getModel();
                        mListener.onListFragmentItemInteraction(new CharactersListItemViewModel(character));
                    }
                }
            });
        } else {
            ProgressBar pb = (ProgressBar) ((ProgressViewHolder) holder).progressView.findViewById(R.id.progress);
            pb.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    @Override
    public int getItemViewType(int position) {
        return mValues.get(position).getId() != 1234 ? VIEW_ITEM : VIEW_PROG;
    }

    public List<MCharacter> getValues() {
        return mValues;
    }

    public void setValues(List<MCharacter> mValues) {
        this.mValues = mValues;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        public final CharacterListItemView mView;

        public DataViewHolder(CharacterListItemView view) {
            super(view);
            mView = view;
        }

        public CharacterListItemView getView() {
            return mView;
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public View progressView;

        public ProgressViewHolder(View v) {
            super(v);
            progressView = v;
        }
    }
}
