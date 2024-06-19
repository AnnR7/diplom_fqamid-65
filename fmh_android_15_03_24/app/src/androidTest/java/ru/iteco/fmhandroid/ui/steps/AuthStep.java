package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static ru.iteco.fmhandroid.ui.data.Helper.authInfo;
import static ru.iteco.fmhandroid.ui.data.Helper.elementWaiting;
import static ru.iteco.fmhandroid.ui.data.Helper.emptyLogin;
import static ru.iteco.fmhandroid.ui.data.Helper.emptyPassword;
import static ru.iteco.fmhandroid.ui.data.Helper.invalidAuthData;
import static ru.iteco.fmhandroid.ui.data.Helper.invalidLoginData;
import static ru.iteco.fmhandroid.ui.data.Helper.invalidPassData;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Helper;
import static ru.iteco.fmhandroid.ui.screenElement.AuthElement.*;

public class AuthStep {
    public void loadAuthPage() {
        Allure.step("Загрузка страницы авторизации");
        elementWaiting(withId(R.id.enter_button), 10000);
    }

    public void checkAuthScreenElements() {
        Allure.step("Элементы экрана авторизации");
        nameAuthText.check(matches(isDisplayed()));
        loginText.check(matches(isDisplayed()));
        passText.check(matches(isDisplayed()));
        signButton.check(matches(isDisplayed()));
    }

    public void authWithValidLoginAndPass (Helper.User info) {
        Allure.step("Авторизация с валидным логином и паролем");
        checkAuthScreenElements();
        loginText.perform(replaceText(authInfo().getLogin()));
        passText.perform(replaceText(authInfo().getPass()));
    }

    public void authWithInvalidLoginAndPass (Helper.User info) {
        Allure.step("Авторизация с невалидными логином и паролем");
        checkAuthScreenElements();
        loginText.perform(replaceText(invalidAuthData().getLogin()));
        passText.perform(replaceText(invalidAuthData().getPass()));
    }

    public void authWithInvalidLogin (Helper.User info) {
        Allure.step("Авторизация с невалидным логином и валидным паролем");
        checkAuthScreenElements();
        loginText.perform(replaceText(invalidLoginData().getLogin()));
        passText.perform(replaceText(invalidLoginData().getPass()));
    }

    public void authWithInvalidPass (Helper.User info) {
        Allure.step("Авторизация с невалидным паролем и валидным логином");
        checkAuthScreenElements();
        loginText.perform(replaceText(invalidPassData().getLogin()));
        passText.perform(replaceText(invalidPassData().getPass()));
    }

    public void authWithEmptyPass (Helper.User info) {
        Allure.step("Авторизация с пустым паролем");
        loginText.perform(replaceText(emptyPassword().getLogin()));
        passText.perform(replaceText(emptyPassword().getPass()));
    }

    public void authWithEmptyLoginAndPass (Helper.User info) {
        Allure.step("Авторизация с пустым логином и пустым паролем");
        loginText.perform(replaceText(emptyLogin().getLogin()));
        passText.perform(replaceText(emptyPassword().getPass()));
    }

    public void clickSignInButton() {
        Allure.step("Нажать кнопку SignIn");
        signButton.perform(click());
    }
}