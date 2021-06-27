package review_data_access;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import user_data_access.UserEntity;


@Entity(tableName = "reviews")
public class ReviewEntity
{
    @PrimaryKey(autoGenerate = true)
    Integer rewId;

    @Embedded
    UserEntity userEntity;

    @ColumnInfo(name = "professorName")
    String professorName;

    @ColumnInfo(name = "professorDescription")
    String professorDescription;

    @ColumnInfo(name = "professorGrade")
    String professorGrade;

    public Integer getRewId()
    {
        return rewId;
    }

    public String getProfessorName()
    {
        return professorName;
    }

    public String getProfessorDescription()
    {
        return professorDescription;
    }

    public String getProfessorGrade()
    {
        return professorGrade;
    }

    public void setRewId(Integer rewId)
    {
        this.rewId = rewId;
    }

    public void setProfessorName(String professorName)
    {
        this.professorName = professorName;
    }

    public void setProfessorDescription(String professorDescription)
    {
        this.professorDescription = professorDescription;
    }

    public void setProfessorGrade(String professorGrade)
    {
        this.professorGrade = professorGrade;
    }

    public UserEntity getUserEntity()
    {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity)
    {
        this.userEntity = userEntity;
    }
}
