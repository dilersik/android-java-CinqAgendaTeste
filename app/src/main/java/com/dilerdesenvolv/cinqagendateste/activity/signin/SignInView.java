package com.dilerdesenvolv.cinqagendateste.activity.signin;

public interface SignInView {

    void showProgress(final boolean show);

    void setError(String resId);

    void setErrorNome();

    void setErrorEmail();

    void setErrorSenha();

    void requestFocus();

    void showToastSucesso(String msg);

    void showToastFalha();

}
