# План проверки мобильного приложения "Мобильный хоспис" ("вхосписе")

---

## "Границы" приложения и реализованный функционал:

1. Страница авторизации (Authorization)

- Поле Login для ввода логина
- Поле Password для ввода пароля
- Кнопка SIGN IN для входа

2. Главный экран

2.1. Объекты верхней панели (слева направо)
  - Меню, состоит из 3х разделов (Main, News, About)
  - Вкладка Love is all с цитатами о хосписе
  - Кнопка Logout для выхода из аккаунта

2.2. Основной экран 

2.2.1. По умолчанию открывается раздел Main  
  - Поле News с кнопкой сворачивания списка новостей и неполным списком новостей с текущей датой публикации
  - Кнопка ALL NEWS для перехода в раздел News ко всем новостям и их редактированию

2.2.2. Раздел News
  - Кнопки: сортировка, фильтр по категории, редактировать
  - Каждая новость имеет кнопку разворачивания описания
  - После тапа по кнопке редактировать - control panel с кнопками: сортировка, фильтр по категории, добавить
  - Новость можно создать, отредактировать, удалить

2.2.3 Раздел About
  - версия
  - ссылка на политику конфиденциальности
  - ссылка на использованные темы

---
## Тест-план для проверки приложения:

 #### Проверка формы авторизации

 #### Проверка навигации приложения

 #### Проверка экрана Main

 #### Проверка экрана News, в т.ч. через переход в ALL NEWS

 #### Проверка экрана About

 #### Проверка экрана Love is all с цитатами
      
 #### Проверка выхода из приложения

---

## Виды тестирования:

 - Ручное
 - Автоматизированное

 - Функциональное:
   - все проверки по тест-кейсу
 - Нефункциональное:
   - интерфейс
   - стабильность
   - офлайн-режим
   - темная тема
   - прерывания
   - безопасность
   - Usability тестирование
---

## Окружение:

Ноутбук: ROG Strix G733PZ, процессор AMD Ryzen 9 7945HX with Radeon Graphics 2.50 GHz, оперативная память 32гб
Операционная система: Windows 11 Pro Версия	23H2 Сборка ОС	22631.3672
Мобильное устройство: 5.5" Эмулятор смартфона Pixel 3a API 29/ Версия Android: 10 / Разрешение: 1080x2220.