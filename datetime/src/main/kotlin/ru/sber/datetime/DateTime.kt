package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters
import java.util.*
import kotlin.collections.ArrayList

// 1. Получить сет часовых поясов, которые используют смещение от UTC не в полных часах.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds().filter { TimeZone.getTimeZone(it).rawOffset % 36000000 != 0 }.toSet()
}

// 2. Для заданного года вывести список, каким днем недели был последний день в месяце.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val lastInMonthDayWeekList = ArrayList<String>()

    for (month in 1..12)
        lastInMonthDayWeekList.add(LocalDate.of(year, Month.of(month), 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString())

    return lastInMonthDayWeekList
}

// 3. Для заданного года вывести количество дней, выпадающих на пятницу 13-ое.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var days = 0

    for (month in 1..12)
        if (LocalDate.of(year, month, 13).dayOfWeek == DayOfWeek.FRIDAY)
            days++

    return days
}

// 4. Вывести заданную дату в формате "01 Aug 2021, 23:39", в котором дата локализована для вывода в США (US).
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.ENGLISH).format(dateTime)
}