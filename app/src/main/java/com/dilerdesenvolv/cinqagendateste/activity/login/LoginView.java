package com.dilerdesenvolv.cinqagendateste.activity.login;

public interface LoginView {

    void showProgress(final boolean show);

    void setError(String resId);

    void setErrorEmail();

    void setErrorSenha();

    void requestFocus();

    void redirectUserLogged();

    void showToastFalha();

}
