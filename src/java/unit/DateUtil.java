/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateUtil {
    public static List<Date> getDateRange(Date startDate, Date endDate) {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        while (!calendar.getTime().after(endDate)) {
            dates.add(new Date(calendar.getTimeInMillis()));
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }
}

