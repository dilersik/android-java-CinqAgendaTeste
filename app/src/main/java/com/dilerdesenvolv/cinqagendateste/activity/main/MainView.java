package com.dilerdesenvolv.cinqagendateste.activity.main;

import com.dilerdesenvolv.cinqagendateste.domain.model.User;

import java.util.List;

public interface MainView {

    void redirectLogin();

//    void updateView(List<User> users);

    void updateView();

    void showProgress();

    void hideProgress();

    void showToast(String msg);

    void redirectUserNotLogged();

}
