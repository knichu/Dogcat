package com.godminq.dogcat.data.converter

import androidx.room.TypeConverter
import java.util.Calendar

class Converters {
    @TypeConverter fun calendarToDatestamp(calendar: Calendar?): Long {
        var result = calendar?.timeInMillis
        if (result == null) {
            result = 0
        }
        return result
    }

//    @TypeConverter fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

//    @TypeConverter fun calendarToDatestamp(calendar: Calendar?): Long? = null

    @TypeConverter fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }
}
