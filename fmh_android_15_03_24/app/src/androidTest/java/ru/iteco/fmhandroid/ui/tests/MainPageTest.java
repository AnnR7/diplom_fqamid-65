package ru.iteco.fmhandroid.ui.tests;

import androidx.test.espresso.PerformException;

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
import ru.iteco.fmhandroid.ui.steps.GeneralStep;
import ru.iteco.fmhandroid.ui.steps.LoveStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.NewsStep;
import ru.iteco.fmhandroid.ui.steps.SplashStep;

@RunWith(AllureAndroidJUnit4.class)
public class MainPageTest {
    AuthStep authStep = new AuthStep();
    MainStep mainStep = new MainStep();
    NewsStep newsStep = new NewsStep();
    AboutStep aboutStep = new AboutStep();
    LoveStep loveStep = new LoveStep();
    GeneralStep generalStep = new GeneralStep();
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
        }
    }

//    @Test
//    @DisplayName("Отображение разделов меню")
//    @Description("В списке есть разделы Main, News, About")
//    public void testCheckMenuScreenList() {
//        mainStep.clickMenuButton();
//        mainStep.checkMenuList();
//    }

    @Test
    @DisplayName("Переход из бокового меню в разделы \"News\", \"Main\", \"About" )
    @Description("Переход на соответствующую вкладку из меню приложения")
    public void testCheckTransitionFromMenu() {
        mainStep.goToNews();
        newsStep.checkNewsElements();
        mainStep.goToMain();
        mainStep.checkMainElements();
        mainStep.goToAbout();
        aboutStep.checkScreenElementsAbout();
    }

    @Test
    @DisplayName("Переход на вкладку с цитатами Love is all")
    @Description("Переход на вкладку Love is all из главного экрана приложения, с экрана News")
    public void testCheckTransitionToMissionScreen() {
        mainStep.clickMissionButton();
        loveStep.checkMissionElements();
        mainStep.goToNews();
        newsStep.checkNewsElements();
        mainStep.clickMissionButton();
        loveStep.checkMissionElements();
    }

    @Test
    @DisplayName("Переход на вкладку News через All News и возврат на главный экран")
    @Description("На главном экране, нажав кнопку All News, происходит переход на вкладку News")
    public void testCheckAllNewsButton() {
        mainStep.clickAllNews();
        newsStep.checkNewsElements();
        mainStep.goToMain();
        mainStep.checkMainElements();
    }

    @Test
    @DisplayName("Развернуть и свернуть блок новостей (News)")
    @Description("Блок новостей при нажатии сворачивается, при повторном нажатии - разворачивается")
    public void testExpandAndCollapseNewsBlock() {
        mainStep.checkAllNews();
        mainStep.allNewsNotDisplay();
        mainStep.checkAllNews();
        mainStep.allNewsDisplay();
    }

    @Test
    @DisplayName("Развернуть отдельную новость")
    @Description("При нажатии на отдельную новость отображается ее описание")
    public void testExpandSeparateNewsItem() {
        mainStep.checkOneNews(1);
        mainStep.descriptionIsDisplay(1);
    }
}