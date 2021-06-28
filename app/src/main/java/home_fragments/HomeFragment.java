package home_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;

import activities.HomeActivity;
import adapters.PostsAdapter;
import review_data_access.ReviewDao;
import review_data_access.ReviewDatabase;
import user_data_access.UserEntity;

public class HomeFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        ReviewDatabase reviewDatabase = ReviewDatabase.getReviewDatabase(getActivity().getApplicationContext());
        ReviewDao reviewDao = reviewDatabase.reviewDao();

        PostsAdapter postsAdapter = new PostsAdapter(getActivity().getApplicationContext(), reviewDao.getReviews());

        RecyclerView recyclerView = view.findViewById(R.id.all_post_recyclerView);

        recyclerView.setAdapter(postsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        return view;
    }

    private void pupulatePostsRecyclerView()
    {

    }
}
