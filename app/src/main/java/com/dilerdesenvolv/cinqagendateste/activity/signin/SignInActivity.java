package com.dilerdesenvolv.cinqagendateste.activity.signin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dilerdesenvolv.cinqagendateste.R;
import com.dilerdesenvolv.cinqagendateste.activity.main.MainActivity;
import com.dilerdesenvolv.cinqagendateste.domain.model.User;

public class SignInActivity extends AppCompatActivity implements SignInView {

    private User mUser;
    private SignInPresenter mPresenter;
    private SignInView mView;
    private View mFocusView = null;
    private TextView mNomeView;
    private TextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mSignInFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mPresenter = new SignInPresenter(this);
        // edit
        mUser = getIntent().getParcelableExtra("user");

        mSignInFormView = findViewById(R.id.sign_in_form);
        mProgressView = findViewById(R.id.login_progress);
        mNomeView = findViewById(R.id.nome);
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);

        Button mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = mNomeView.getText().toString().trim();
                String email = mEmailView.getText().toString().trim();
                String password = mPasswordView.getText().toString();

                User user = new User();
                user.setNome(nome);
                user.setEmail(email);
                user.setSenha(password);

                if (mUser != null && mUser.getId() > 0) {
                    user.setId(mUser.getId());
                    user.setEmail(mUser.getEmail());
                }

                mPresenter.onClickAdd(user);
            }
        });

        // edit
        if (mUser != null && mUser.getId() > 0) {
            mNomeView.setText(mUser.getNome());
            mEmailView.setEnabled(false);
            mEmailView.setText(mUser.getEmail());
            mPasswordView.setText(mUser.getSenha());
            mBtnAdd.setText(R.string.alterar);
        }
    }

    @Override
    public void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mSignInFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    public void setError(String resId) {
        mEmailView.setError(resId);
        mPasswordView.setError(resId);
    }

    @Override
    public void setErrorNome() {
        mNomeView.setError(getString(R.string.error_invalid_nome));
        mFocusView = mNomeView;
    }

    @Override
    public void setErrorEmail() {
        mEmailView.setError(getString(R.string.error_invalid_email));
        mFocusView = mEmailView;
    }

    @Override
    public void setErrorSenha() {
        mPasswordView.setError(getString(R.string.error_invalid_password));
        mFocusView = mPasswordView;
    }

    @Override
    public void requestFocus() {
        mFocusView.requestFocus();
    }

    @Override
    public void showToastSucesso(String msg) {
        Toast.makeText(SignInActivity.this, msg, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showToastFalha() {
        Toast.makeText(SignInActivity.this, "ocorreu um erro", Toast.LENGTH_SHORT).show();
    }
}
