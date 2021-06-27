package home_fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.user.R;

import org.jetbrains.annotations.NotNull;

import interfaces.ActivityFragmentCommunication;
import kotlin.jvm.internal.Intrinsics;
import login_fragments.LoginFragment;
import review_data_access.ReviewDao;
import review_data_access.ReviewDatabase;
import review_data_access.ReviewEntity;

public class AddFragment extends Fragment
{
    private ActivityFragmentCommunication activityFragmentCommunication;

    public AddFragment()
    {

    }

    public void onAttach(@NotNull Context context)
    {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        if (context instanceof ActivityFragmentCommunication)
        {
            this.activityFragmentCommunication = (ActivityFragmentCommunication) context;
        }
    }

    public static AddFragment newInstance()
    {
        AddFragment fragment = new AddFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.add_fragment, container, false);

        EditText profName = view.findViewById(R.id.prof_name_edit);
        EditText profDescription = view.findViewById(R.id.description_prof_edit);
        EditText profGrade = view.findViewById(R.id.grade_prof_edit);

        Button post = view.findViewById(R.id.post_button);

        post.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                postReview(profName.getText().toString(), profDescription.getText().toString(), profGrade.getText().toString());
            }
        });
        return view;
    }



    private void postReview(String profName, String profDescription, String profGrade)
    {
        ReviewEntity reviewEntity = new ReviewEntity();

        reviewEntity.setProfessorName(profName);
        reviewEntity.setProfessorDescription(profDescription);
        reviewEntity.setProfessorGrade(profGrade);

        reviewEntity.setUserEntity(LoginFragment.loggedUser);

        if (validateInput(reviewEntity))
        {
            ReviewDatabase reviewDatabase = ReviewDatabase.getReviewDatabase(getActivity().getApplicationContext());

            ReviewDao reviewDao = reviewDatabase.reviewDao();
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    reviewDao.postReview(reviewEntity);
                    getActivity().runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Toast.makeText(getActivity().getApplicationContext(), "Published post !", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).start();
        } else
        {
            Toast.makeText(getActivity().getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean validateInput(ReviewEntity reviewEntity)
    {
        if (reviewEntity.getProfessorName().isEmpty() ||
                reviewEntity.getProfessorDescription().isEmpty() ||
                reviewEntity.getProfessorGrade().isEmpty())
            return false;
        return true;
    }
}
