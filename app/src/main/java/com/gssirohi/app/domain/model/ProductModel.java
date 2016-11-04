package com.gssirohi.app.domain.model;

import com.gssirohi.app.domain.model.pojo.CharacterSummaryListContainer;
import com.gssirohi.app.domain.model.pojo.ComicSummaryListContainer;
import com.gssirohi.app.domain.model.pojo.CreatorSummaryListContainer;
import com.gssirohi.app.domain.model.pojo.EventSummaryListContainer;
import com.gssirohi.app.domain.model.pojo.SeriesSummaryListContainer;
import com.gssirohi.app.domain.model.pojo.StorySummaryListContainer;
import com.gssirohi.app.domain.model.pojo.Thumbnail;

/**
 * Created by gssirohi on 25/8/16.
 */
public abstract class ProductModel extends Model {

    public abstract Thumbnail getThumbnail();

    public abstract String getName();

    public abstract Integer getId();

    public abstract CharacterSummaryListContainer getCharacterSummaryListContainer();

    public abstract ComicSummaryListContainer getComicSummaryListContainer();

    public abstract EventSummaryListContainer getEventSummaryListContainer();

    public abstract SeriesSummaryListContainer getSeriesSummaryListContainer();

    public abstract StorySummaryListContainer getStorySummaryListContainer();

    public abstract CreatorSummaryListContainer getCreatorSummaryListContainer();

    public abstract String getDescription();
}