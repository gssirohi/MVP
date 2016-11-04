package com.gssirohi.app.ui.customview;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gssirohi.app.R;
import com.gssirohi.app.base.AppCore;
import com.gssirohi.app.constant.AppErrors;
import com.gssirohi.app.constant.UseCases;
import com.gssirohi.app.domain.interactor.FetchImageInteractor;
import com.gssirohi.app.domain.interactor.LoadImageUseCase;
import com.gssirohi.app.ui.viewmodel.contract.ICharactersListItemViewModel;


/**
 * TODO: document your custom view class.
 */

public class CharacterListItemView extends FrameLayout {
    private ICharactersListItemViewModel viewModel;
    private FetchImageInteractor interactor;

    public CharacterListItemView(ViewGroup parent) {
        super(parent.getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        init(parent);

    }


    private void init(ViewGroup parent) {
        ViewGroup itemView =
                (ViewGroup) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.characters_list_item_layout, parent, false);
        addView(itemView);
    }

    public void fillDetails(ICharactersListItemViewModel viewModel) {
        this.viewModel = viewModel;
        final ImageView imageView = (ImageView) this.findViewById(R.id.iv_image);
        final ProgressBar progress = (ProgressBar) this.findViewById(R.id.progressBar);
        ((TextView) this.findViewById(R.id.tv_id)).setText(viewModel.getId());
        ((TextView) this.findViewById(R.id.tv_name)).setText(viewModel.getName());
        ((TextView) this.findViewById(R.id.tv_comics)).setText("" + viewModel.getComicsCount());
        ((TextView) this.findViewById(R.id.tv_series)).setText("" + viewModel.getSeriesCount());
        ((TextView) this.findViewById(R.id.tv_events)).setText("" + viewModel.getEventsCount());
        ((TextView) this.findViewById(R.id.tv_stories)).setText("" + viewModel.getStoriesCount());

        String url = viewModel.getImageUrl();
        imageView.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        if (interactor != null)
            interactor.setCancelled(true);//discard previous results since you have new  imageUrl
        interactor = (FetchImageInteractor) AppCore.getInstance().getProvider().getUseCaseImpl(getContext(), UseCases.FETCH_PRODUCT_IMAGE);
        interactor.execute(new LoadImageUseCase.Callback() {
            @Override
            public void onError(AppErrors error) {

            }

            @Override
            public void onImage(Bitmap image) {
                imageView.setImageBitmap(image);
                imageView.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
            }
        }, url, false);
    }

    public ICharactersListItemViewModel getViewModel() {
        return viewModel;
    }
}
