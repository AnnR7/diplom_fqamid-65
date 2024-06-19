package ru.iteco.fmhandroid.ui.tests;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.steps.AboutStep;
import ru.iteco.fmhandroid.ui.steps.AuthStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.SplashStep;

@RunWith(AllureAndroidJUnit4.class)
public class AboutTest {

    AuthStep authStep = new AuthStep();
    AboutStep aboutStep = new AboutStep();
    MainStep mainStep = new MainStep();
    SplashStep splashStep = new SplashStep();


    @Rule
    public androidx.test.rule.ActivityTestRule<AppActivity> ActivityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        splashStep.appDownload();
        try {
            mainStep.mainScreenLoad();
        } catch (Exception e) {
            authStep.authWithValidLoginAndPass(Helper.authInfo());
            authStep.clickSignInButton();
        } finally {
            mainStep.mainScreenLoad();
            mainStep.goToAbout();
        }
    }

    @Test
    @DisplayName("Проверка элементов экрана About")
    @Description("Корректность отображения всех элементов экрана About")
    public void testCheckScreenElementsAbout() {
        aboutStep.checkScreenElementsAbout();
    }

    @Test
    @DisplayName("Проверка кликабельности ссылок")
    public void testCheckingClickabilityLinks() {
        aboutStep.clickLinkPrivacyPolicy();
        aboutStep.clickLinkTermsofUse();
    }

    @Test
    @DisplayName("Возврат на главный экран приложения со страницы About")
    public void testCheckGoBackMainScreen() {
        aboutStep.checkScreenElementsAbout();
        aboutStep.checkReturnButton();
        mainStep.mainScreenLoad();
        mainStep.checkMainElements();
    }
}