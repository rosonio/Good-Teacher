package review_data_access;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ReviewDao
{
    @Insert
    void postReview(ReviewEntity reviewEntity);

    @Query("SELECT * FROM reviews")
    ReviewEntity getReviews();
}
