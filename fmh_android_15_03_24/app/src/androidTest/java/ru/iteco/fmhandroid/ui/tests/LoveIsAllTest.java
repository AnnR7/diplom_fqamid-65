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
import ru.iteco.fmhandroid.ui.data.Data;
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.steps.AuthStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.LoveStep;
import ru.iteco.fmhandroid.ui.steps.SplashStep;

@RunWith(AllureAndroidJUnit4.class)
public class LoveIsAllTest {

    AuthStep authStep = new AuthStep();
    LoveStep loveStep = new LoveStep();
    MainStep mainStep = new MainStep();
    SplashStep splashStep = new SplashStep();
    Data data = new Data();

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
            mainStep.clickMissionButton();
        }
    }

    @Test
    @DisplayName("Проверка элементов экрана с цитатами")
    @Description("Корректность отображения всех элементов экрана с цитатами")
    public void testCheckMissionScreenElements() {loveStep.checkMissionElements();
    }

    @Test
    @DisplayName("Развернуть и свернуть цитату, во вкладке \"Love is all\" приложения")
    @Description("При нажатии - разворачивается содержимое цитаты")
    public void testExpandAndCollapseQuote() {
        loveStep.checkQuote(2);
        loveStep.descriptionIsDisplay(data.quoteText);
        loveStep.checkQuote(2);
        loveStep.descriptionNotDisplay(data.quoteText);
    }
}