package com.dilerdesenvolv.cinqagendateste.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.dilerdesenvolv.cinqagendateste.R;
import com.dilerdesenvolv.cinqagendateste.activity.main.MainPresenter;
import com.dilerdesenvolv.cinqagendateste.activity.signin.SignInActivity;
import com.dilerdesenvolv.cinqagendateste.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> implements Filterable {

    private Context mContext;
    private MainPresenter mMainPresenter;
    private List<User> mList;
    private List<User> mListFiltered;

    public UserAdapter(Context context, MainPresenter presenter) {
        mContext = context;
        mMainPresenter = presenter;
//        mList = myDataset;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
        }
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.adapter_user, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtHeader.setText(mListFiltered.get(position).getNome());
        holder.txtFooter.setText(mListFiltered.get(position).getEmail());

        holder.layout.findViewById(R.id.row).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SignInActivity.class);
                intent.putExtra("user", mListFiltered.get(position));
                mContext.startActivity(intent);
            }
        });

        holder.layout.findViewById(R.id.row).setOnLongClickListener(new View.OnLongClickListener() {
            boolean mReturn = false;

            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("Delete");
                alert.setMessage("Are you sure you want to delete '" + mListFiltered.get(position).getNome() + "'?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mMainPresenter.delete(mListFiltered.get(position));
                        mReturn = true;
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();

                return mReturn;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mListFiltered.size();
        } else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mListFiltered = mList;
                } else {
                    List<User> filteredList = new ArrayList<>();
                    for (User user : mList) {
                        if (user.getNome().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(user);
                        }
                    }
                    mListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListFiltered = (ArrayList<User>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }

    public void setUserList(Context context, MainPresenter presenter, final List<User> list){
        mContext = context;
        mMainPresenter = presenter;

        if (this.mList == null) {
            this.mList = list;
            this.mListFiltered = list;
//            notifyItemChanged(0, mListFiltered.size());
            notifyDataSetChanged();

        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return UserAdapter.this.mList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return UserAdapter.this.mList.get(oldItemPosition).getNome() == list.get(newItemPosition).getNome();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    User newUser = UserAdapter.this.mList.get(oldItemPosition);
                    User oldUser = list.get(newItemPosition);

                    return newUser.getNome() == oldUser.getNome();
                }
            });
            this.mList = list;
            this.mListFiltered = list;
            result.dispatchUpdatesTo(this);
        }
    }

}