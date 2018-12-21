package com.dilerdesenvolv.cinqagendateste.activity.gallery;

import com.dilerdesenvolv.cinqagendateste.domain.model.Gallery;

import java.util.List;

public interface GalleryView {

    void updateView(List<Gallery> albuns);

    void updateView();

    void showProgress();

    void hideProgress();

    void showToast(String msg);

}
