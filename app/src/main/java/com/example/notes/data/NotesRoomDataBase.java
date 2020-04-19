package com.example.notes.data;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.notes.model.Note;
@Database(entities = {Note.class},version = 1)
public  abstract   class NotesRoomDataBase  extends RoomDatabase {

private  static  String DATAABASE_NAME="Notes";
private  static  NotesRoomDataBase instance ;


    public static NotesRoomDataBase getInstance(Context context) {

        if  (instance==null){

            instance=Room.databaseBuilder(context.getApplicationContext() ,NotesRoomDataBase.class,DATAABASE_NAME).build() ;

        }
        return instance;
    }

    public abstract  NotesDao getNoteDao();
}
