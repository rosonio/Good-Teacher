package review_data_access;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ReviewDao
{
    @Insert
    void postReview(ReviewEntity reviewEntity);

    @Query("SELECT * FROM reviews")
    LiveData<List<ReviewEntity>> getReviews();
}
