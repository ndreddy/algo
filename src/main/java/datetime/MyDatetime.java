package datetime;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;

public class MyDatetime {

    /**
     * Gets a map of date id metadata.
     *
     * @param dateTimeStr - Format is yyyy-MM-dd HH:mm:ss.SSS
     * @return Map with date id as key and its value.
     * <p>
     * Example "starttime_utc": “2021-08-18 18:23:42.4600”,
     * date_id: 20210818
     * date_hour_id: 2021081818
     * fifteen_min_bucket: 18:15-18:30
     * thirty_min_bucket: 18:00-18:30
     */
    public static Map<String, String> getDateMetadata(String dateTimeStr, String dateTimeFormat) {
        HashMap<String, String> map = new HashMap<>(4);
        String[] tokens = dateTimeFormat.split("\\.");

        DateTimeFormatter formatter =
                new DateTimeFormatterBuilder().appendPattern(tokens[0])
                        .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true).toFormatter();

        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            log(0l, "Input " + dateTimeStr + " format mismatch:" + dateTimeFormat, e);
            return map;
        }
        LocalTime localTime = localDateTime.toLocalTime();
        DateTimeFormatter dateIdFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter dateHrIdFormatter = DateTimeFormatter.ofPattern("yyyyMMddHH");

        map.put("date_id", localDateTime.format(dateIdFormatter));
        map.put("date_hour_id", localDateTime.format(dateHrIdFormatter));
        map.put("fifteen_min_bucket", getIntervalBucket(localTime, 15));
        map.put("thirty_min_bucket", getIntervalBucket(localTime, 30));
        return map;

    }


    /**
     * Gets interval bucket string based on the time.
     * Example for date 2021-08-18 18:33:42.4600 it returns "18:30-19:00" for 30 minute interval
     *
     * @param localTime     local time object
     * @param intervalInMin interval in minutes
     * @return string representing the bucket interval. Example 15 or 30.
     */
    public static String getIntervalBucket(LocalTime localTime, int intervalInMin) {
        LocalTime curr = LocalTime.MIN; //00:00
        LocalTime prev = LocalTime.MIN;

        // When curr rollover happens, E.g. 23:45 to 00:00 for 15 min interval,
        // need the check curr.compareTo(prev) >= 0 to break the infinite loop.
        while (curr.compareTo(localTime) <= 0 && curr.compareTo(prev) >= 0) {
            prev = curr;
            curr = curr.plusMinutes(intervalInMin);
        }
        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return prev.format(outFormatter) + "-" + curr.format(outFormatter);
    }

    /**
     * Converts epoc time to date time string
     *
     * @param dateTimePattern
     * @param dateField
     * @return
     */
    public String convertLongTimeUTC(long epocSec, String dateTimePattern, String dateField) {
        try {
            // Replaces legacy SimpleDateFormatter as not threadsafe.
            DateTimeFormatter dfFormatter = DateTimeFormatter.ofPattern(dateTimePattern).withZone(ZoneOffset.UTC);
            String dateStr = dfFormatter.format(java.time.Instant.ofEpochMilli(epocSec * 1000));
            return dateStr;

        } catch (Exception e) {
            log(0l, "Exception while convertLongTime: " + dateField, e);
        }
        return null;
    }

    /**
     * Converts epoc time to date time string
     * @param message
     * @param dateTimePattern
     * @param dateField
     * @return
     */
    public static byte[] convertLongTimeUTC(byte[] message, String dateTimePattern, String dateField) {
        JsonObject json = new Gson().fromJson(new String(message), JsonObject.class);
        try {
            JsonElement elem = json.get(dateField);
            if (elem != null && !elem.isJsonNull()) {
                long epochSec = elem.getAsLong();

                // Replaces legacy SimpleDateFormatter as not threadsafe.
                DateTimeFormatter dfFormatter = DateTimeFormatter.ofPattern(dateTimePattern).withZone(ZoneOffset.UTC);
                String dateStr = dfFormatter.format(java.time.Instant.ofEpochMilli(epochSec * 1000));

                json.remove(dateField);
                json.addProperty(dateField, dateStr);
            }
        } catch (Exception e) {
            log(0l, "Exception while convertLongTime: " + dateField, e);
        }
        return json.toString().getBytes();
    }

    /**
     * Converts array date time to date time string
     *
     * @param message
     * @param dateTimePattern
     * @param dateField
     * @return
     */
    public static byte[] convertArrayTimeUTC(byte[] message, String dateTimePattern, String dateField) {
        JsonObject json = new Gson().fromJson(new String(message), JsonObject.class);
        JsonElement elem = json.get(dateField);
        if (elem != null && elem.isJsonArray()) {
            try {
                // [2021,12,1,5,42,3,771725300]
                JsonArray arr = elem.getAsJsonArray();
                int year = arr.get(0).getAsInt();
                int month = arr.get(1).getAsInt();
                int day = arr.get(2).getAsInt();
                int hour = arr.get(3).getAsInt();
                int minute = arr.get(4).getAsInt();
                int second = arr.get(5).getAsInt();
                int nano = 0;
                if (arr.size() > 6) nano = arr.get(6).getAsInt();

                ZonedDateTime zdt = ZonedDateTime.of(year, month, day, hour, minute, second, nano, ZoneOffset.UTC);
                DateTimeFormatter dfFormatter = DateTimeFormatter.ofPattern(dateTimePattern).withZone(ZoneOffset.UTC);
                String dateStr = dfFormatter.format(zdt);

                json.remove(dateField);
                json.addProperty(dateField, dateStr);
            } catch (Exception e) {
                log(0l, "Exception while convertArrayTimeUTC: " + dateField, e);
            }
        }
        return json.toString().getBytes();
    }

    private static void log(long l, String s, Exception e) {
        System.out.println(s);
    }

}