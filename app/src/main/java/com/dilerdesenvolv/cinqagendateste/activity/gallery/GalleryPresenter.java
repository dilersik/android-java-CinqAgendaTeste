package com.dilerdesenvolv.cinqagendateste.activity.gallery;

import android.os.AsyncTask;
import android.util.Log;

import com.dilerdesenvolv.cinqagendateste.domain.model.Gallery;
import com.dilerdesenvolv.cinqagendateste.service.GalleryService;

import java.util.List;

public class GalleryPresenter {

    private GalleryView mView;

    public GalleryPresenter(GalleryView view) {
        mView = view;
    }

    public void listAlbuns() {
        mView.showProgress();
        new ListAlbunsTask().execute((Void) null);
    }

    public class ListAlbunsTask extends AsyncTask<Void, Void, Boolean> {

        private List<Gallery> mAlbuns = null;

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                mAlbuns = GalleryService.getInstance().getAlbuns();

            } catch (Exception e) {
                Log.d("GALP", e.getMessage());
            }

            if (mAlbuns != null) {
                return true;
            }

            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                mView.updateView(mAlbuns);
            }
            mView.hideProgress();
        }

        @Override
        protected void onCancelled() {
            mView.hideProgress();
        }
    }

}
