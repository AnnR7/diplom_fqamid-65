package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import static ru.iteco.fmhandroid.ui.data.Helper.elementWaiting;
import static ru.iteco.fmhandroid.ui.data.Helper.waitForElement;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Helper;
import static ru.iteco.fmhandroid.ui.screenElement.MainElement.*;

public class MainStep {

    public void mainScreenLoad() {
        Allure.step("Загрузка страницы");
        elementWaiting(withText("News"), 10000);
    }

    public void checkMainElements() {
        Allure.step("Элементы экрана Main");
        trademark.check(matches(isDisplayed()));
        newsText.check(matches(isDisplayed()));
        newsList.check(matches(isDisplayed()));
    }

    public void checkAllNews() {
        Allure.step("Развернуть/свернуть блок новостей");
        newsBlockButton.check(matches(isDisplayed()));
        newsBlockButton.perform(click());
    }

    public void allNewsNotDisplay() {
        Allure.step("Блок новостей - свернут");
        allNewsButton.check(matches(not(isDisplayed())));
    }

    public void allNewsDisplay() {
        Allure.step("Отображение - All news");
        newsList.check(matches(isDisplayed()));
    }

    public void checkOneNews(int position) {
        Allure.step("Раскрыть выбранную новость");
        childNews.perform(actionOnItemAtPosition(position, click()));
    }

    public void descriptionIsDisplay(int position) {
        Allure.step("Отображение описания новостей");
        String descriptionText = Helper.Text.getText(onView(withIndex(withId
                (R.id.news_item_description_text_view), position)));
        ViewInteraction newsDescription = onView(allOf(withId(R.id.news_item_description_text_view),
                withText(descriptionText)));
        newsDescription.check(matches(isDisplayed()));
    }

    public void clickAllNews() {
        Allure.step("Нажать - all news");
        allNewsButton.check(matches(isDisplayed()));
        allNewsButton.perform(click());
    }

    public void clickMissionButton() {
        Allure.step("Нажать - Our Mission");
        missionButton.check(matches(isDisplayed()));
        missionButton.perform(click());
    }

    public void clickLogOutButton() {
        Allure.step("Нажать выход");
        logoutButton.check(matches(isDisplayed()));
        logoutButton.perform(click());
        onView(isRoot()).perform(waitForElement(withText("Log out"), 5000));
        logoutText.check(matches(isDisplayed()));
        logoutText.perform(click());
    }

    public void clickMenuButton() {
        Allure.step("Нажать на меню");
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());
    }

    public void checkMenuList() {
        Allure.step("Список бокового меню");
        mainText.check(matches(isDisplayed()));
        newsText.check(matches(isDisplayed()));
        aboutText.check(matches(isDisplayed()));
    }

    public void goToNews() {
        Allure.step("Нажать News в боковом списке");
        menuButton.perform(click());
        newsText.check(matches(isDisplayed()));
        newsText.perform(click());
    }

    public void goToAbout() {
        Allure.step("Нажать About в боковом списке");
        menuButton.perform(click());
        aboutText.check(matches(isDisplayed()));
        aboutText.perform(click());
    }

    public void goToMain() {
        Allure.step("Нажать Main в боковом списке");
        menuButton.perform(click());
        mainText.check(matches(isDisplayed()));
        mainText.perform(click());
    }
}