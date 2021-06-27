package review_data_access;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {ReviewEntity.class}, version = 1)
public abstract class ReviewDatabase extends RoomDatabase
{

    public static final String dbName = "review_data_access";
    public static ReviewDatabase reviewDatabase;

    public static synchronized ReviewDatabase getReviewDatabase(Context context)
    {
        if (reviewDatabase == null)
            reviewDatabase = Room.databaseBuilder(context, ReviewDatabase.class, dbName).
                    fallbackToDestructiveMigration().build();

        return reviewDatabase;
    }

    public abstract ReviewDao reviewDao();
}
