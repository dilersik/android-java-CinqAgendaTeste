package com.dilerdesenvolv.cinqagendateste.activity.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dilerdesenvolv.cinqagendateste.R;
import com.dilerdesenvolv.cinqagendateste.activity.main.MainActivity;
import com.dilerdesenvolv.cinqagendateste.activity.signin.SignInActivity;
import com.dilerdesenvolv.cinqagendateste.domain.model.User;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter mPresenter;
    private View mFocusView = null;
    private TextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter = new LoginPresenter(this);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    mPresenter.attemptLogin(setUser());
                    return true;
                }
                return false;
            }
        });

        Button emailSignInButton = findViewById(R.id.email_sign_in_button);
        emailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.attemptLogin(setUser());
            }
        });

        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignInActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.verifyLogged();
    }

    @Override
    public void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    public void setError(String resId) {
        mEmailView.setError(resId);
        mPasswordView.setError(resId);
    }

    @Override
    public void setErrorEmail() {
        mEmailView.setError(getString(R.string.error_field_required));
        mFocusView = mEmailView;
    }

    @Override
    public void setErrorSenha() {
        mPasswordView.setError(getString(R.string.error_field_required));
        mFocusView = mPasswordView;
    }

    @Override
    public void requestFocus() {
        mFocusView.requestFocus();
    }

    @Override
    public void redirectUserLogged() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void showToastFalha() {
        Toast.makeText(LoginActivity.this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
    }

    private User setUser() {
        String email = mEmailView.getText().toString().trim();
        String password = mPasswordView.getText().toString();

        User user = new User();
        user.setEmail(email);
        user.setSenha(password);

        return user;
    }

}

