package modelViews;

import android.content.Context;
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


public class RegisterFragment extends Fragment
{

    private EditText userId;
    private EditText password;
    private EditText name;
    private Button register;

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

        userId = view.findViewById(R.id.userIdInput);
        password = view.findViewById(R.id.passwordInput);
        name = view.findViewById(R.id.nameInput);
        register = view.findViewById(R.id.registerButton);

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserId(userId.getText().toString());
                userEntity.setPassword(password.getText().toString());
                userEntity.setName(name.getText().toString());
                if (validateInput(userEntity))
                {
                    UserDatabase userDatabase=UserDatabase.getUserDatabase(getActivity().getApplicationContext());
                    // TO DO:: Sa probez daca merge
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
                                    Toast.makeText(getActivity().getApplicationContext(),"User Registered!",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                } else
                {
                    Toast.makeText(getActivity().getApplicationContext(),"Fill all fields",Toast.LENGTH_SHORT).show();
                    // TO DO:: Sa probez daca merge
                }
            }
        });

        return view;
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