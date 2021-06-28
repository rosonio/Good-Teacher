package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;

import java.util.ArrayList;
import java.util.List;

import review_data_access.ReviewEntity;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder>
{
    private Context context;
    private LiveData<List<ReviewEntity>> reviewEntity;

    public PostsAdapter(Context ct, LiveData<List<ReviewEntity>> reviewEntity)
    {
        this.context = ct;
        this.reviewEntity = reviewEntity;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.post_cell,parent,false);

        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position)
    {
        holder.name.setText(reviewEntity.getValue().get(position).getProfessorName());
        holder.description.setText(reviewEntity.getValue().get(position).getProfessorDescription());
        holder.grade.setText(reviewEntity.getValue().get(position).getProfessorGrade());
    }

    @Override
    public int getItemCount()
    {
        return reviewEntity.getValue().size();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView description;
        TextView grade;
        public PostsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.name=itemView.findViewById(R.id.prof_name_view);
            this.description=itemView.findViewById(R.id.prof_description_view);
            this.grade=itemView.findViewById(R.id.prof_grade_view);
        }
    }
}
