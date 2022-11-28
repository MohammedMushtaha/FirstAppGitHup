package com.example.roomdatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = Student.class, version = 1)
public abstract class StudentRoomDataBase extends RoomDatabase {

    private static StudentRoomDataBase instance;

    public abstract StudentDao studentDao();

    public static synchronized StudentRoomDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), StudentRoomDataBase.class, "studentDataBase")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        return instance;

    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new getDataFromAsync(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private static class getDataFromAsync extends AsyncTask<Void, Void, Void> {

        StudentDao studentDao;

        public getDataFromAsync(StudentRoomDataBase studentRoomDataBase) {
            studentDao = studentRoomDataBase.studentDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.insert(new Student("mohamed", "asem","It"));
            studentDao.insert(new Student("Kareem", "Mohamed", "Ai"));
            studentDao.insert(new Student("Ahmed", "Moh", "Ai"));
            studentDao.insert(new Student("Haytham", "Ame", "It"));

            return null;
        }
    }

}
