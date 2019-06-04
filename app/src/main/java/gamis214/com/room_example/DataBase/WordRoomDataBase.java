package gamis214.com.room_example.DataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import gamis214.com.room_example.DataBase.DAO.WordDao;
import gamis214.com.room_example.DataBase.Entity.Word;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDataBase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static volatile WordRoomDataBase INSTANCE;

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    public static WordRoomDataBase getDatBase(Context context){
        if(INSTANCE == null){
            synchronized (WordRoomDataBase.class){
                if(INSTANCE == null){
                    INSTANCE = Room
                            .databaseBuilder(context,WordRoomDataBase.class,"word_database")
                            .fallbackToDestructiveMigrationOnDowngrade()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDataBase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            mDao.insert(new Word("TEST"));
            mDao.insert(new Word(" WORLD"));
            return null;
        }
    }

}
