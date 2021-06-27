package login_fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.R;
import user_data_access.UserDao;
import user_data_access.UserDatabase;
import user_data_access.UserEntity;

import org.jetbrains.annotations.NotNull;

import interfaces.ActivityFragmentCommunication;
import kotlin.jvm.internal.Intrinsics;


public class RegisterFragment extends Fragment
{

    private ActivityFragmentCommunication activityFragmentCommunication;

    public RegisterFragment()
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

    public static RegisterFragment newInstance(String param1, String param2)
    {
        RegisterFragment fragment = new RegisterFragment();

        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        EditText userId = view.findViewById(R.id.userIdReg);
        EditText password = view.findViewById(R.id.passwordReg);
        EditText name = view.findViewById(R.id.nameReg);

        Button register = view.findViewById(R.id.registerButtonReg);

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                insertUserAccount(userId.getText().toString(), password.getText().toString(), name.getText().toString());
            }
        });
        return view;
    }

    private void insertUserAccount(String userId, String password, String name)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setPassword(password);
        userEntity.setName(name);
        if (validateInput(userEntity))
        {
            UserDatabase userDatabase = UserDatabase.getUserDatabase(getActivity().getApplicationContext());

            UserDao userDao = userDatabase.userDao();
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    userDao.registerUser(userEntity);
                    getActivity().runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Toast.makeText(getActivity().getApplicationContext(), "User Registered!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).start();
        } else
        {
            Toast.makeText(getActivity().getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean validateInput(UserEntity userEntity)
    {
        if (userEntity.getUserId().isEmpty() ||
                userEntity.getPassword().isEmpty() ||
                userEntity.getName().isEmpty())
            return false;
        return true;
    }
}