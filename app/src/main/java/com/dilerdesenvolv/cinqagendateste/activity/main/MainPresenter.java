package com.dilerdesenvolv.cinqagendateste.activity.main;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.dilerdesenvolv.cinqagendateste.adapter.UserAdapter;
import com.dilerdesenvolv.cinqagendateste.domain.dao.AppDB;
import com.dilerdesenvolv.cinqagendateste.domain.model.User;
import com.dilerdesenvolv.cinqagendateste.utils.PrefUtils;

import java.util.List;

public class MainPresenter {

    private MainView mView;
    private Context mContext;
    private UserAdapter mAdapter;

    public MainPresenter(MainView view) {
        mView = view;
    }

    public MainPresenter(MainView view, Context context, UserAdapter userAdapter) {
        mView = view;
        mContext = context;
        mAdapter = userAdapter;
    }

    public void logout() {
        PrefUtils.remove("user_id");
        PrefUtils.remove("user_nome");
        PrefUtils.remove("user_email");

        mView.redirectLogin();
    }

    public void listUsers() {
        mView.showProgress();
        new ListUsersTask().execute((Void) null);
    }

    public void delete(User user) {
        mView.showProgress();

        new MainPresenter.DeleteUserTask(user).execute((Void) null);
    }

    public class ListUsersTask extends AsyncTask<Void, Void, Boolean> {

        private List<User> mUsers = null;

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                mUsers = AppDB.getInstance().userDao().getAll();

            } catch (Exception e) {
                Log.d("MAINP", e.getMessage());
            }

            if (mUsers != null) {
                return true;
            }

            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                mAdapter.setUserList(mContext, MainPresenter.this, mUsers);
            }
            mView.hideProgress();
        }

        @Override
        protected void onCancelled() {
            mView.hideProgress();
        }
    }

    public class DeleteUserTask extends AsyncTask<Void, Void, Boolean> {

        private User mUser;

        public DeleteUserTask(User user) {
            mUser = user;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                Integer id = PrefUtils.getInt("user_id");
                if (id == mUser.getId()) {
                    Handler handler;
                    handler = new Handler(mContext.getMainLooper());
                    handler.post(new Runnable(){
                        public void run(){
                            mView.showToast("Você não pode excluir a você mesmo");
                        }
                    });

                    return false;
                }

                AppDB.getInstance().userDao().delete(mUser);
                return true;

            } catch (Exception e) {
                Log.d("MAINP", e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                mView.showToast("Usuário excluído com sucesso");
                mView.updateView();
            }

            mView.hideProgress();
        }

        @Override
        protected void onCancelled() {}
    }

    public void verifyLogged() {
        if (PrefUtils.getInt("user_id") <= 0) {
            mView.redirectUserNotLogged();
        }
    }

}
