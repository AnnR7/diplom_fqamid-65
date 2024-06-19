package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static ru.iteco.fmhandroid.ui.data.Helper.elementWaiting;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import static ru.iteco.fmhandroid.ui.screenElement.SplashElement.*;

public class SplashStep {

    public void checkSplashElements() {
        Allure.step("Элементы экрана загрузки");
        splashImage.check(matches(isDisplayed()));
        splashProgressIndicator.check(matches(isDisplayed()));
        splashText.check(matches(isDisplayed()));
    }

    public void appDownload() {
        Allure.step("Загрузка приложения");
        elementWaiting(withId(R.id.splashscreen_image_view), 5000);
    }
}