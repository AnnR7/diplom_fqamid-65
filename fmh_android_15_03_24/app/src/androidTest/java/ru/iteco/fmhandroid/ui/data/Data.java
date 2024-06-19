package ru.iteco.fmhandroid.ui.data;

import static ru.iteco.fmhandroid.ui.data.Helper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.data.Helper.getCurrentTime;

//import io.bloco.faker.Faker;
public class Data {
//    static Faker faker = new Faker();

    public static final String validLogin = "login2";
    public static final String  validPass = "password2";
    public static final String invalidLogin = "login";
    public static final String invalidPass = "password";
    public static final String emptyLogin = "";
    public static final String emptyPass = "";

    public String quoteText = "Все сотрудники хосписа - это адвокаты пациента, его прав и потребностей. " +
            "Поиск путей решения различных задач - это и есть хосписный индивидуальный подход к паллиативной помощи.";

    public String dateOfPublic = getCurrentDate();
    public String timeOfPublic = getCurrentTime();
    public String dateNonNews = "09.09.1999";

    public String descriptOnCyr = "Другое описание новости";
    public String descriptSymb = "='+*&^%$#@~";
    public String titleCyr = "Заголовок новости";
    public String titleSymb = "'+*&^%$#@~";
    public String descriptionEmptyText = "";
    public String newTitleEdit = "Отредактированный заголовок";
    public String newDescriptionEdit = "Отредактированное описание";
    public String newTitle = "Новый заголовок";
    public String newDescription = "Новое описание";
    public String editComment = "После редактировования";
}