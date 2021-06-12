package modelViews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.goodteacher.R;
import com.example.goodteacher.UserDao;
import com.example.goodteacher.UserDatabase;
import com.example.goodteacher.UserEntity;

import org.jetbrains.annotations.NotNull;

import interfaces.ActivityFragmentCommunication;
import kotlin.jvm.internal.Intrinsics;


public class LoginFragment extends Fragment
{

    private ActivityFragmentCommunication activityFragmentCommunication;

    public static LoginFragment newInstance()
    {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    public LoginFragment()
    {

    }

    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof ActivityFragmentCommunication)
        {
            this.activityFragmentCommunication = (ActivityFragmentCommunication) context;
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button registerButton = view.findViewById(R.id.registerButtonLog);
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                activityFragmentCommunication.onReplaceFragment(RegisterFragment.class.getSimpleName());
            }
        });


        EditText userId = view.findViewById(R.id.userIdLog);
        EditText password = view.findViewById(R.id.passwordLog);

        Button loginButton = view.findViewById(R.id.loginButtonLog);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                validateCredentials(userId.getText().toString(), password.getText().toString());
            }
        });

        return view;
    }

    private void validateCredentials(String userId, String password)
    {
        // Verify if exists any account with this input and then switch the activity

        if (userId.isEmpty() || password.isEmpty())
            Toast.makeText(getActivity().getApplicationContext(), "Fill all fields!", Toast.LENGTH_SHORT).show();
        else
        {
            UserDatabase userDatabase = UserDatabase.getUserDatabase(getActivity().getApplicationContext());
            UserDao userDao = userDatabase.userDao();
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    UserEntity userEntity = userDao.login(userId, password);
                    if (userEntity == null)
                    {
                        getActivity().runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Invalid Credentials", Toast.LENGTH_SHORT);
                            }
                        });
                    } else
                    {
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                    }
                }
            }).start();
        }
    }
}
