package modelViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.user.R;

import interfaces.ActivityFragmentCommunication;

public class LoginActivity extends AppCompatActivity implements ActivityFragmentCommunication
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        onReplaceFragment(LoginFragment.class.getSimpleName());
    }

    @Override
    public void onReplaceFragment(String TAG)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment newFragment;

        switch (TAG)
        {
            case "RegisterFragment":
                newFragment = new RegisterFragment();
                break;
            default:
                newFragment = new LoginFragment();
                break;
        }
        transaction.replace(R.id.fl_container, newFragment);

        transaction.addToBackStack(null);

        transaction.commit();
    }
}