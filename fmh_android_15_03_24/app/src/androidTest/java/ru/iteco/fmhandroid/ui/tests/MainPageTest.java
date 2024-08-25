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

    @Test
    @DisplayName("Отображение разделов меню")
    @Description("В списке есть разделы Main, News, About")
    public void testCheckMenuScreenList() {
        mainStep.clickMenuButton();
        mainStep.checkMenuList();
    }

    @Test
    @DisplayName("Переход в раздел News с экрана Main через Action menu id5" )
    @Description("Проверка отображения элементов экрана News")
    public void testCheckTransitionToNewsScreen() {
        mainStep.goToNews();
        newsStep.checkNewsElements();
    }

    @Test
    @DisplayName("Переход в раздел Main с экрана News через Action menu (id7)" )
    @Description("Проверка отображения элементов экрана Main")
    public void testCheckTransitionToMainScreenFromNews() {
        mainStep.goToNews();
        newsStep.checkNewsElements();
        mainStep.goToMain();
        mainStep.checkMainElements();
    }

    @Test
    @DisplayName("Переход с экрана Main на экран Love is all по кнопке бабочка id11")
    @Description("Проверка отображения элементов экрана Love is all")
    public void testCheckTransitionToMissionScreenFromMain() {
        mainStep.clickMissionButton();
        loveStep.checkMissionElements();
    }

    @Test
    @DisplayName("Переход с экрана News на экран Love is all по кнопке бабочка id12")
    @Description("Проверка отображения элементов экрана Love is all")
    public void testCheckTransitionToMissionScreenFromNews() {
        mainStep.goToNews();
        newsStep.checkNewsElements();
        mainStep.clickMissionButton();
        loveStep.checkMissionElements();
    }

    @Test
    @DisplayName("Переход в раздел News по кнопке All News с экрана Main и возврат обратно на Main id18")
    @Description("Проверка отображения элементов экрана News, затем экрана Main")
    public void testCheckAllNewsButton() {
        mainStep.clickAllNews();
        newsStep.checkNewsElements();
        mainStep.goToMain();
        mainStep.checkMainElements();
    }

    @Test
    @DisplayName("Разворачивание/ сворачивание раздела новостей на экране Main id17")
    @Description("Блок новостей при нажатии сворачивается, при повторном нажатии - разворачивается")
    public void testExpandAndCollapseNewsBlock() {
        mainStep.checkAllNews();
        mainStep.allNewsNotDisplay();
        mainStep.checkAllNews();
        mainStep.allNewsDisplay();
    }

//    @Test
//    @DisplayName("Разворачивание/ сворачивание описания отдельной новости на экране Main id16")
//    @Description("Отдельная новость при нажатии разворачивается и отображается ее описание, " +
//            "при повторном нажатии сворачивается")
//    public void testExpandSeparateNewsItem() {
//        mainStep.checkOneNews(0);
//        mainStep.descriptionIsDisplay(0);
//        mainStep.checkOneNews(0);
//        mainStep.descriptionNotDisplay(0);
//    }
}