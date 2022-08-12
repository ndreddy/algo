package datetime;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MyDatetimeTest {
    @ParameterizedTest
    @CsvSource({
            "2021-08-18 18:23:42.4600, 15, 18:15-18:30", "2021-08-18 18:23:42.4600, 30, 18:00-18:30",
            "2021-08-18 18:30:00.0000, 15, 18:30-18:45", "2021-08-18 18:30:00.0000, 30, 18:30-19:00",
            "2021-08-18 23:45:00.0000, 15, 23:45-00:00", "2021-08-18 23:45:00.0000, 30, 23:30-00:00",
            "2021-08-18 00:00:00.0000, 15, 00:00-00:15", "2021-08-18 00:00:00.0000, 30, 00:00-00:30"})
    public void testGetIntervalBucket(String dateStr, int interval, String expected) throws Exception {
        String bucketStr = MyDatetime.getIntervalBucket(getLocalTime(dateStr), interval);
        System.out.println(dateStr + " > " + interval + "min_bucket > " + bucketStr);
        assertTrue(bucketStr.equals(expected));
    }

    @ParameterizedTest
    @CsvSource({
            "2021-08-18 18:23:42.460, yyyy-MM-dd HH:mm:ss.SSS, 20210818, 2021081818",
            "2021-08-18 18:23:42.46, yyyy-MM-dd HH:mm:ss.SSS, 20210818, 2021081818",
            "2021-08-18 18:23:42, yyyy-MM-dd HH:mm:ss.SSS, 20210818, 2021081818"
    })
    public void testGetDateMetadata(String dateStr, String dateTimeFormat, String dateId, String dateHrId) {
        Map<String, String> map = MyDatetime.getDateMetadata(dateStr, dateTimeFormat);
        assertTrue(map.size() == 4);
        System.out.println(dateStr + " > date_id=" + map.get("date_id") + ", date_hour_id=" + map.get("date_hour_id"));
        assertTrue(map.get("date_id").equals(dateId));
        assertTrue(map.get("date_hour_id").equals(dateHrId));
    }

    private LocalTime getLocalTime(String dateStr) {
        DateTimeFormatter formatter =
                new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm:ss")
                        .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true).toFormatter();
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, formatter);
        LocalTime localTime = localDateTime.toLocalTime();
        return localTime;
    }

    @Test
    public void testConvertLongTimeUTC() throws Exception {
        String jsonStr = "{'starttime_utc':1631682000}";
        byte[] bytes = MyDatetime.convertLongTimeUTC(jsonStr.getBytes(), "yyyy-MM-dd HH:mm:ss.SSS", "starttime_utc");
        System.out.println(new String(bytes));
    }

    @Test
    public void testConvertArrayTimeUTC() throws Exception {
        String jsonStr = "{'receivedTime':[2021, 12, 9, 7, 48, 29, 226024100]}";
        byte[] bytes = MyDatetime.convertArrayTimeUTC(jsonStr.getBytes(), "yyyy-MM-dd HH:mm:ss.SSS", "receivedTime");
        String str = new String(bytes);
        System.out.println(str);
        assertTrue(str.contains("2021-12-09 07:48:29.226"));
    }

}