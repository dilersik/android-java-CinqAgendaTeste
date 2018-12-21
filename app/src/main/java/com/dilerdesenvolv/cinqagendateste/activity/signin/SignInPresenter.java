package com.dilerdesenvolv.cinqagendateste.activity.signin;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.dilerdesenvolv.cinqagendateste.domain.dao.AppDB;
import com.dilerdesenvolv.cinqagendateste.domain.model.User;

public class SignInPresenter {

    private SignInView mView;

    public SignInPresenter(SignInView view) {
        mView = view;
    }

    public void onClickAdd(User user) {
        mView.setError(null);

        boolean cancel = false;

        if (!isNomeValid(user.getNome())) {
            mView.setErrorNome();
            cancel = true;

        } else if (!isEmailValid(user.getEmail())) {
            mView.setErrorEmail();
            cancel = true;

        } else if (TextUtils.isEmpty(user.getSenha()) || !isPasswordValid(user.getSenha())) {
            mView.setErrorSenha();
            cancel = true;
        }

        if (cancel) {
            mView.requestFocus();

        } else {
            mView.showProgress(true);
            new SignInTask(user).execute((Void) null);
        }
    }

    private boolean isNomeValid(String email) {
        return email.length() > 1 && email.length() <= 50;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".") && email.length() <= 50;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 1 && password.length() <= 50;
    }

    public class SignInTask extends AsyncTask<Void, Void, Boolean> {

        private final User mUser;

        SignInTask(User user) {
            mUser = user;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                if (mUser.getId() > 0) {
                    AppDB.getInstance().userDao().update(mUser);
                } else {
                    AppDB.getInstance().userDao().insert(mUser);
                }

//                for (User user : AppDB.getInstance().userDao().getAll()) {
//                    Log.d("SIGNIN", user.getId() + " " + user.getNome());
//                }

            } catch (Exception e) {
                Log.d("SIGNIN", e.getMessage());
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                mView.showToastSucesso(mUser.getId() > 0 ? "Alterado com sucesso" : "Cadastrado com sucesso");

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

}
