package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

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
    public ActivityScenarioRule<AppActivity> ActivityTestRule =
            new ActivityScenarioRule<>(AppActivity.class);

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
    @DisplayName("Переход в раздел About с экрана Main через Action menu id6")
    @Description("Проверка отображения всех элементов экрана About")
    public void testCheckTransitionToAboutScreen() {
        aboutStep.checkScreenElementsAbout();
    }

    @Test
    @DisplayName("Переход по ссылке Privacy Policy id95")
    public void testCheckClickabilityLink1() {
        aboutStep.clickLinkPrivacyPolicy();
    }

    @Test
    @DisplayName("Переход по ссылке Terms of Use id96")
    public void testCheckClickabilityLink2() {
        aboutStep.clickLinkTermsofUse();
    }

    @Test
    @DisplayName("Выход из раздела About по кнопке Назад на экране id10")
    public void testCheckGoBackMainScreen() {
        aboutStep.checkScreenElementsAbout();
        aboutStep.checkReturnButton();
        mainStep.mainScreenLoad();
        mainStep.checkMainElements();
    }
}