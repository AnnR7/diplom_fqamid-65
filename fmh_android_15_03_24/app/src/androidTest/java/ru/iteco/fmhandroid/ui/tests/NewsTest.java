package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.action.ViewActions.swipeDown;
import static org.junit.Assert.assertEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomCategory;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.filters.LargeTest;
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
import ru.iteco.fmhandroid.ui.data.Data;
import ru.iteco.fmhandroid.ui.screenElement.PanelElement;
import ru.iteco.fmhandroid.ui.screenElement.NewsElement;
import ru.iteco.fmhandroid.ui.steps.AuthStep;
import ru.iteco.fmhandroid.ui.steps.GeneralStep;
import ru.iteco.fmhandroid.ui.steps.PanelSteps;
import ru.iteco.fmhandroid.ui.steps.CreateNewsStep;
import ru.iteco.fmhandroid.ui.steps.EditNewsStep;
import ru.iteco.fmhandroid.ui.steps.FilterNewsStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.NewsStep;
import ru.iteco.fmhandroid.ui.steps.SplashStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsTest {

    AuthStep authStep = new AuthStep();
    NewsStep newsStep = new NewsStep();
    MainStep mainStep = new MainStep();
    PanelSteps panelSteps = new PanelSteps();
    PanelElement panelElement = new PanelElement();
    FilterNewsStep filterScreen = new FilterNewsStep();
    Data data = new Data();
    CreateNewsStep createNewsStep = new CreateNewsStep();
    GeneralStep generalStep = new GeneralStep();
    EditNewsStep editNewsStep = new EditNewsStep();
    NewsElement newsElement = new NewsElement();
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
            mainStep.clickAllNews();
        }
    }

    @Test
    @DisplayName("Сортировка новостей на экране News сначала свежие id20")
    @Description("Меняется порядок отображения новостей по дате добавления")
    public void testSortNews() {
        String firstNewsTitle = newsStep.getFirstNewsTitle(0);
        newsStep.clickSortButton();
        newsElement.allNewsBlock.perform(swipeDown());
        newsStep.clickSortButton();
        newsElement.allNewsBlock.perform(swipeDown());
        newsStep.newsListLoad();
        String firstNewsTitleAfterSecondSorting = newsStep.getFirstNewsAfterSecondSort(0);
        assertEquals(firstNewsTitle, firstNewsTitleAfterSecondSorting);
    }

    @Test
    @DisplayName("Фильтрация новостей без выбора категории с валидными датами публикации, " +
            "когда нет новостей на выбранную дату id36")
    @Description("Если нет новостей для этой даты - надпись There is nothing here yet")
    public void testNothingToShowScreen() {
        newsStep.openFilter();
        filterScreen.checkFilterElements();
        filterScreen.fillInStartDateField(data.dateNonNews);
        filterScreen.fillInEndDateField(data.dateNonNews);
        filterScreen.clickFilter();
        generalStep.checkNewsButterfly();
        generalStep.checkNothingToShow();
    }

    @Test
    @DisplayName("Отмена фильтрации кнопкой Cancel после выбора категории и дат id39")
    @Description("Выход из фильтра без фильтрации новостей")
    public void testCancelFilter() {
        newsStep.openFilter();
        filterScreen.checkFilterElements();
        filterScreen.fillInStartDateField(data.dateOfPublic);
        generalStep.clickCancelButton();
        newsStep.checkNewsElements();
    }

    @Test
    @DisplayName("Переход с экрана News в Control panel по кнопке Редактировать " +
            "(листок и карандаш) (id45)")
    @Description("Нажать листок с карандашом")
    public void testGoToControlPanel() {
        newsStep.clickEditButton();
        panelSteps.checkPanelElements();
    }

    @Test
    @DisplayName("Проверка чек-боксов расширенного фильтра")
    @Description("При нажатии чек-боксы становятся неактивными")
    public void testCheckingCheckboxesFilter() {
        newsStep.clickEditButton();
        panelSteps.openNewsFilter();
        filterScreen.clickActiveCheckBox();
        filterScreen.checkBoxStatusActive(false);
        filterScreen.clickNotActiveCheckBox();
        filterScreen.checkBoxStatusNotActive(false);
    }

    @Test
    @DisplayName("Фильтрация новостей без выбора категории и дат с чекбоксом " +
            "Activ в Control panel id53")
    @Description("При фильтре новостей по статусу Active в списке новостей отображаются " +
            "только новости с этим статусом")
    public void testCheckFilterActive() {
        newsStep.clickEditButton();
        panelSteps.openNewsFilter();
        filterScreen.clickNotActiveCheckBox();
        filterScreen.clickFilter();
        newsStep.newsListLoad();
        panelSteps.checkStatusActive();
    }

    @Test
    @DisplayName("Фильтрация новостей без выбора категории и дат с чекбоксом " +
            "Not Activ в Control panel id54")
    @Description("При фильтре новостей по статусу Not Active в списке новостей отображаются " +
            "только новости с этим статусом")
    public void testCheckFilterNotActive() {
        newsStep.clickEditButton();
        panelSteps.openNewsFilter();
        filterScreen.clickActiveCheckBox();
        filterScreen.clickFilter();
        newsStep.newsListLoad();
        panelSteps.checkStatusNotActive();
    }

    @Test
    @DisplayName("Отмена фильтра новостей через панель управления")
    @Description("Выход из фильтра с помощью кнопки отмена")
    public void testCancelFilteringNews() {
        newsStep.clickEditButton();
        panelSteps.openNewsFilter();
        generalStep.clickCancelButton();
        panelSteps.checkPanelElements();
    }

    @Test
    @DisplayName("Создание новости в категории Объявление в Control panel на кириллице id63")
    @Description("Заполнение полей данным на кириллице")
    public void testCreateNewsCyrillic() {
        newsStep.clickEditButton();
        panelSteps.clickCreateNewsButton();
        createNewsStep.checkNewsScreenElements();
        createNewsStep.createNews(randomCategory(), data.titleCyr, data.dateOfPublic,
                data.timeOfPublic, data.descriptOnCyr);
        generalStep.clickSaveButton();
        newsStep.newsListLoad();
        mainStep.goToNews();
        newsElement.allNewsBlock.perform(swipeDown());
        newsStep.checkOpenNews(0);
        String createdDescription = newsStep.getCreateNewsDescription(0);
        assertEquals(data.descriptOnCyr, createdDescription);
    }

    @Test
    @DisplayName("Создание новости в Control panel спецсимволами (id64)")
    @Description("При заполнении полей - категория, заголовок и описание спец символами - " +
            "новость не должна создаваться")
    public void testCreateNewsWithSymbols() {
        newsStep.clickEditButton();
        panelSteps.clickCreateNewsButton();
        createNewsStep.checkNewsScreenElements();
        createNewsStep.createNews(data.categorySymb, data.titleSymb, data.dateOfPublic,
                data.timeOfPublic, data.descriptSymb);
        generalStep.clickSaveButton();
        generalStep.checkInvalidData("Saving failed. Try again later", true);
    }

    @Test
    @DisplayName("Создание Новости с незаполненными полями в Control panel id73")
    @Description("Уведомление о необходимости заполнить поля полях")
    public void testCreateNewsWithEmptyFields() {
        newsStep.clickEditButton();
        panelSteps.clickCreateNewsButton();
        createNewsStep.checkNewsScreenElements();
        generalStep.clickSaveButton();
        generalStep.checkEmptyFieldError();
    }

    @Test
    @DisplayName("Отмена создания новости в Control panel id72")
    @Description("Нажать и подтвердить отмену - новость не создается")
    public void testCancelNewsCreate() {
        newsStep.clickEditButton();
        panelSteps.clickCreateNewsButton();
        createNewsStep.checkNewsScreenElements();
        createNewsStep.fillInPublicDateField(data.dateOfPublic);
        createNewsStep.fillInTimeField(data.timeOfPublic);
        generalStep.clickCancelButton();
        generalStep.clickOkButton();
        panelSteps.checkPanelElements();
    }

    @Test
    @DisplayName("Редактирование заголовка новости в Control panel (id87)")
    @Description("Новость с новым заголовком")
    public void testEditNewsTitle() {
        newsStep.clickEditButton();
        panelSteps.clickCreateNewsButton();
        createNewsStep.createNews(randomCategory(), data.titleCyr, data.dateOfPublic,
                data.timeOfPublic, data.descriptOnCyr);
        generalStep.clickSaveButton();
        newsStep.newsListLoad();
        panelSteps.clickEditNews(0);
        editNewsStep.checkEditNewsElements();
        editNewsStep.changeTitle(data.newTitleEdit);
        generalStep.clickSaveButton();
        panelSteps.clickOneNewsItem(0);
        assertEquals(data.newTitleEdit, panelSteps.getEditNewsTitle(0));
    }

    @Test
    @DisplayName("Редактирование описания новости в Control panel (id90)")
    @Description("Новость с новым описанием")
    public void testEditNewsDescription() {
        newsStep.clickEditButton();
        panelSteps.clickCreateNewsButton();
        createNewsStep.createNews(randomCategory(), data.titleCyr, data.dateOfPublic,
                data.timeOfPublic, data.descriptOnCyr);
        generalStep.clickSaveButton();
        newsStep.newsListLoad();
        panelSteps.clickEditNews(0);
        editNewsStep.checkEditNewsElements();
        editNewsStep.changeDescription(data.newDescriptionEdit);
        generalStep.clickSaveButton();
        panelSteps.clickOneNewsItem(0);
        assertEquals(data.newDescriptionEdit, panelSteps.getEditNewsDescription(0));
    }

    @Test
    @DisplayName("Отмена сохранения изменений при редактировании новости в Control panel id84")
    @Description("Нажать кнопку отмены и подтвердить, новость не изменится")
    public void testCancelEditNews() {
        newsStep.clickEditButton();
        panelSteps.clickCreateNewsButton();
        createNewsStep.createNews(randomCategory(), data.titleCyr,
                data.dateOfPublic, data.timeOfPublic, data.descriptOnCyr);
        generalStep.clickSaveButton();
        newsStep.newsListLoad();
        panelSteps.clickEditNews(0);
        editNewsStep.checkEditNewsElements();
        editNewsStep.changeTitle(data.newTitle);
        editNewsStep.changeDescription(data.newDescription);
        generalStep.clickCancelButton();
        generalStep.clickOkButton();
        panelSteps.checkPanelElements();
        panelElement.newsList.perform(swipeDown());
        panelSteps.clickOneNewsItem(0);
        assertEquals(data.newTitleEdit, panelSteps.getEditNewsTitle(0));
    }
}