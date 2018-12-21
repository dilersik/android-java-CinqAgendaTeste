package com.dilerdesenvolv.cinqagendateste.activity.login;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.dilerdesenvolv.cinqagendateste.domain.dao.AppDB;
import com.dilerdesenvolv.cinqagendateste.domain.model.User;
import com.dilerdesenvolv.cinqagendateste.utils.PrefUtils;

public class LoginPresenter {

    private LoginView mView;

    public LoginPresenter(LoginView view) {
        mView = view;
    }

    public void attemptLogin(User user) {
        mView.setError(null);

        boolean cancel = false;

        if (TextUtils.isEmpty(user.getEmail())) {
            mView.setErrorEmail();
            cancel = true;

        } else if (TextUtils.isEmpty(user.getSenha())) {
            mView.setErrorSenha();
            cancel = true;
        }

        if (cancel) {
            mView.requestFocus();

        } else {
            mView.showProgress(true);
            new LoginPresenter.LoginTask(user).execute((Void) null);
        }
    }

    public class LoginTask extends AsyncTask<Void, Void, Boolean> {

        private User mUser;

        LoginTask(User user) {
            mUser = user;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            User user = null;

            try {
                user = AppDB.getInstance().userDao().getAuth(mUser.getEmail(), mUser.getSenha());

            } catch (Exception e) {
                Log.d("LOGIN", e.getMessage());
            }

            if (user != null) {
                mUser = user;
                return true;
            }


            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                PrefUtils.setInt("user_id", mUser.getId());
                PrefUtils.setString("user_nome", mUser.getNome());
                PrefUtils.setString("user_email", mUser.getEmail());

                mView.redirectUserLogged();
            } else {
                mView.showProgress(false);
                mView.showToastFalha();
            }
        }

        @Override
        protected void onCancelled() {
            mView.showProgress(false);
        }
    }

    public void verifyLogged() {
        if (PrefUtils.getInt("user_id") > 0) {
            mView.redirectUserLogged();
        }
    }

}
