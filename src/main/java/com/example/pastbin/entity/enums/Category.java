package com.example.pastbin.entity.enums;

import java.util.List;

public enum Category {
        store("store", List.of(
                "Магазины",
                "Продуктовые магазины",
                "Другие магазины",
                "Авто магазины",
                "Цветочные магазины",
                "Handmade магазин",
                "Магазины одежды")),
        medicine("medicine", List.of(
                "Медицина",
                "Аптеки",
                "Стоматологи",
                "Психотерапевты",
                "Кардиологии",
                "Педиатры",
                "Урологи",
                "Гастроэнтерологи",
                "Окулисты | Офтальмологи",
                "Реабилитологи",
                "Семейные врачи | Терапевты",
                "Гинекологи",
                "Оториноларингологи (ЛОРы)",
                "Хирурги",
                "Пластические хирурги",
                "Ветеринары",
                "Мануальные терапевты",
                "Онкология",
                "Неврологи",
                "Логопеды",
                "Ортопеды",
                "Альтернативная медицина"
        )),
        car_service("car_service", List.of(
                "Автоуслуги",
                "Автодилеры",
                "Автолизинг",
                "Автошколы",
                "Прокат автомобилей",
                "Детейлинг | Автомойка",
                "Товинг | Эвакуторы",
                "Автомагазины",
                "Автострахование",
                "Кузовные мастерские",
                "Мастерские",
                "Автоэлектрики",
                "Дорожные юристы",
                "Помощь на дороге"
        )),
        insurance("insurance", List.of(
                "Страхование"
        )),
        home_and_repair("home_and_repair", List.of(
                "Дом и ремонт",
                "Архитектура | Дизайн",
                "HVAC Кондиционеры | Отопления",
                "Кровельные | Фасадные работы",
                "Малярные работы",
                "Окна | Дверы | Балконы",
                "Ремонт и обслуживание техники",
                "Сантехнические работы",
                "Строительство новых домов",
                "Укладка плитки и полов",
                "Хендимен",
                "Электромонтажные работы",
                "Система безопасности | Умные дома",
                "Вывоз мусора",
                "Мебель",
                "Сайдинг",
                "Внешние работы",
                "Внутренние работы",
                "Гараж | Ворота"
        )),
        legal_service("legal_service", List.of(
                "Юридичиеские услуги",
                "Семейное право",
                "DWI DUI",
                "Травмы на работе",
                "Бизнес услуги",
                "Иммиграционные адвокаты",
                "Нотариусы | Переводы документов",
                "Адвокаты по ДТП",
                "Дорожные юристы"
        )),
        accounting_service_taxes("accounting_service_taxes", List.of(
                "Бухгалтерские услуги, налоги"
        )),
        beauty_and_health("beauty_and_health", List.of(
                "Красота и здоровье",
                "Визажисты",
                "Косметологи",
                "Массажисты",
                "Мастера-бровисты",
                "Парикмахерские услуги",
                "Мастера ногтевого сервиса",
                "Салоны красоты | СПА",
                "Тату-мастера",
                "Фитнес-тренера",
                "Леймекеры",
                "Мастера перманентного макияжа",
                "Барбершопы",
                "Йога",
                "Аппаратная коррекция фигуры"
        )),
        realtors("realtors", List.of(
                "Риэлторы"
        )),
        media_mass_media("media_mass_media", List.of(
                "Медиа | СМИ"
        )),
        it_computer_service("it_computer_service", List.of(
                "IT | Компьютерные услуги"
        )),
        cleaning_service("cleaning_service", List.of(
                "Клининговые услуги"
        )),
        buying_and_selling_business("buying_and_selling_business", List.of(
                "Покупка и продажа бизнеса"
        )),
        for_children("for_children", List.of(
                "Для детей",
                "Садики",
                "Репетиторские услуги",
                "Кружки",
                "Школы танцев и пения",
                "Школы",
                "Няни",
                "Спортивные секции"
        )),
        education_and_courses("education_and_courses", List.of(
                "Образования и курсы",
                "Автошколы",
                "Школы для драйверов | диспетчеров",
                "Языковые курсы",
                "Музыкальные курсы",
                "Репетиторские услуги",
                "Спорт",
                "Танцы",
                "Образовательные курсы",
                "Другое обучение"
        )),
        recreation_and_leisure("recreation_and_leisure", List.of(
                "Отдых и досуг",
                "Рестораны",
                "Экскурсии | Туристические услуги",
                "Что посмотреть",
                "Хостелы | Отели | Виллы",
                "Ночные клубы",
                "Сауны | Бани",
                "Прокаты яхт",
                "Бары",
                "Кафе",
                "Караоке"
        )),
        other("other", List.of(
                "Прочее"
        ));


    private final String displayName;
    private final List<String> subCategories;

    Category(String displayName, List<String> subCategories) {
        this.displayName = displayName;
        this.subCategories = subCategories;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<String> getSubCategories() {
        return subCategories;
    }

    public static Category fromDisplayName(String displayName) {
        for (Category category : Category.values()) {
            if (category.getDisplayName().equalsIgnoreCase(displayName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No enum constant with display name " + displayName);
    }
}