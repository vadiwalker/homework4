package ru.ifmo.droid2016.rzddemo.cache;

import android.content.Context;
<<<<<<< HEAD
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
=======
>>>>>>> 06ad3cfd5039a2742a32892215208dcb84fea708
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import java.io.FileNotFoundException;
<<<<<<< HEAD
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static ru.ifmo.droid2016.rzddemo.cache.Constraints.*;
=======
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
>>>>>>> 06ad3cfd5039a2742a32892215208dcb84fea708

import ru.ifmo.droid2016.rzddemo.model.TimetableEntry;

import static ru.ifmo.droid2016.rzddemo.Constants.LOG_DATE_FORMAT;
<<<<<<< HEAD
import static ru.ifmo.droid2016.rzddemo.cache.DataSchemeVersion.*;
=======
>>>>>>> 06ad3cfd5039a2742a32892215208dcb84fea708

/**
 * Кэш расписания поездов.
 *
 * Ключом является комбинация трех значений:
 * ID станции отправления, ID станции прибытия, дата в москомском часовом поясе
 *
 * Единицей хранения является список поездов - {@link TimetableEntry}.
 */

public class TimetableCache {

    @NonNull
    private final Context context;

    /**
     * Версия модели данных, с которой работает кэщ.
     */
    @DataSchemeVersion
    private final int version;
<<<<<<< HEAD
    private SQLiteDatabase db;
=======
>>>>>>> 06ad3cfd5039a2742a32892215208dcb84fea708

    /**
     * Создает экземпляр кэша с указанной версией модели данных.
     *
     * Может вызываться на лююбом (в том числе UI потоке). Может быть создано несколько инстансов
     * {@link TimetableCache} -- все они должны потокобезопасно работать с одним физическим кэшом.
     */
    @AnyThread
    public TimetableCache(@NonNull Context context,
                          @DataSchemeVersion int version) {
        this.context = context.getApplicationContext();
        this.version = version;
    }

    /**
     * Берет из кэша расписание - список всех поездов, следующих по указанному маршруту с
     * отправлением в указанную дату.
     *
     * @param fromStationId ID станции отправления
     * @param toStationId   ID станции прибытия
     * @param dateMsk       дата в московском часовом поясе
     *
     * @return - список {@link TimetableEntry}
     *
     * @throws FileNotFoundException - если в кэше отсуствуют запрашиваемые данные.
     */
    @WorkerThread
    @NonNull
    public List<TimetableEntry> get(@NonNull String fromStationId,
                                    @NonNull String toStationId,
                                    @NonNull Calendar dateMsk)
            throws FileNotFoundException {
<<<<<<< HEAD
        SQLiteDatabase db = TimetableDbHelper.getInstance(context,version).getReadableDatabase();//dbHelper.getReadableDatabase();
        List<TimetableEntry> result = new ArrayList<>();
        Cursor cursor = null;
        String[] toFind;
        if (version == V1)
            toFind = new String[] {
                    DEPARTURE_DATE, DEPARTURE_STATION_ID,
                    DEPARTURE_STATION_NAME, DEPARTURE_TIME, ARRIVAL_STATION_ID,
                    ARRIVAL_STATION_NAME, ARRIVAL_TIME, TRAIN_ROUTE_ID, ROUTE_START_STATION_NAME,
                    ROUTE_END_STATION_NAME
            };
        else
            toFind = new String[] {
                    DEPARTURE_DATE, DEPARTURE_STATION_ID,
                    DEPARTURE_STATION_NAME, DEPARTURE_TIME, ARRIVAL_STATION_ID,
                    ARRIVAL_STATION_NAME, ARRIVAL_TIME, TRAIN_ROUTE_ID, ROUTE_START_STATION_NAME,
                    ROUTE_END_STATION_NAME, TRAIN_NAME
            };
        try {
            cursor = db.query(TABLE, toFind,
                    DEPARTURE_STATION_ID + "=? and " + ARRIVAL_STATION_ID + "=? and " + DEPARTURE_DATE + "=?",
                    new String[]{fromStationId, toStationId, dateMsk.toString() },
                    null, null, null);
            if (cursor.moveToFirst())
                for (; !cursor.isAfterLast(); cursor.moveToNext()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

                    String departureData = cursor.getString(0);
                    String departureStationId = cursor.getString(1);
                    String departureStationName = cursor.getString(2);

                    Calendar departureTime = Calendar.getInstance();

                    try {
                        departureTime.setTime(sdf.parse(cursor.getString(3)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String arrivalStationId = cursor.getString(4);
                    String arrivalStationName = cursor.getString(5);


                    Calendar arrivalTime = Calendar.getInstance();
                    try {
                        arrivalTime.setTime(sdf.parse(cursor.getString(6)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String trainRouteId = cursor.getString(7);
                    String routeStartStationName = cursor.getString(8);
                    String routeEndStationName = cursor.getString(9);
                    String trainName = null;
                    if (version == V2)
                        trainName = cursor.getString(10);
                    result.add(new TimetableEntry(departureStationId, departureStationName,
                            departureTime,
                            arrivalStationId, arrivalStationName,
                            arrivalTime,
                            trainRouteId, trainName, routeStartStationName, routeEndStationName
                    ));
                }
            else {
                throw new FileNotFoundException("No data in timetable cache for: fromStationId="
                        + fromStationId + ", toStationId=" + toStationId
                        + ", dateMsk=" + LOG_DATE_FORMAT.format(dateMsk.getTime()));
            }
        } finally {
            if (cursor != null)
                cursor.close();
            db.close();
        }
        return result;


        // TODO: ДЗ - реализовать кэш на основе базы данных SQLite с учетом версии модели данных
        /*throw new FileNotFoundException("No data in timetable cache for: fromStationId="
                + fromStationId + ", toStationId=" + toStationId
                + ", dateMsk=" + LOG_DATE_FORMAT.format(dateMsk.getTime()));*/
=======
        // TODO: ДЗ - реализовать кэш на основе базы данных SQLite с учетом версии модели данных
        throw new FileNotFoundException("No data in timetable cache for: fromStationId="
                + fromStationId + ", toStationId=" + toStationId
                + ", dateMsk=" + LOG_DATE_FORMAT.format(dateMsk.getTime()));
>>>>>>> 06ad3cfd5039a2742a32892215208dcb84fea708
    }

    @WorkerThread
    public void put(@NonNull String fromStationId,
                    @NonNull String toStationId,
                    @NonNull Calendar dateMsk,
                    @NonNull List<TimetableEntry> timetable) {
<<<<<<< HEAD

        SQLiteDatabase db = TimetableDbHelper.getInstance(context, version).getWritableDatabase();
        String list = listString1;
        if (version == V1) {
            list += ", " + TRAIN_NAME;
        }
        for (TimetableEntry entry : timetable) {
            db.execSQL("INSERT INTO " + TABLE + "(" + list + ")"
                    + " VALUES (" + entry.toString() + ")");
        }
=======
        // TODO: ДЗ - реализовать кэш на основе базы данных SQLite с учетом версии модели данных
>>>>>>> 06ad3cfd5039a2742a32892215208dcb84fea708
    }
}
