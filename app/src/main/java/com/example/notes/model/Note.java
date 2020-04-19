package com.example.notes.model;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Note  implements Parcelable {


    @ColumnInfo(name = "title_colum")
    private String title;
    @ColumnInfo(name = "content_colum")
    private String content;
    @ColumnInfo(name = "timestamp_colum")
    private String timestamp;
    @PrimaryKey(autoGenerate = true)
    private  int  id  ;

    protected Note(Parcel in) {
        title = in.readString();
        content = in.readString();
        timestamp = in.readString();
        id = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Note( String title, String content,  String timestamp) {
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
    }

  @Ignore
  public Note() { }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(timestamp);
        dest.writeInt(id);
    }


    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", id=" + id +
                '}';
    }
}
