package com.dilerdesenvolv.cinqagendateste.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dilerdesenvolv.cinqagendateste.R;
import com.dilerdesenvolv.cinqagendateste.activity.gallery.GalleryPresenter;
import com.dilerdesenvolv.cinqagendateste.domain.model.Gallery;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private Context mContext;
    private GalleryPresenter mGalleryPresenter;
    private List<Gallery> mList;

    public GalleryAdapter(Context context, GalleryPresenter presenter, List<Gallery> myDataset) {
        mContext = context;
        mGalleryPresenter = presenter;
        mList = myDataset;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
        }
    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.adapter_gallery, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtHeader.setText(mList.get(position).getTitle());

        holder.layout.findViewById(R.id.row).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}