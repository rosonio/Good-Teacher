package modelViews;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.goodteacher.R;

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

    public void onAttach(@NotNull Context context)
    {
        Intrinsics.checkParameterIsNotNull(context, "context");
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

        Button registerButton = view.findViewById(R.id.goToRegister);
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                activityFragmentCommunication.onReplaceFragment(RegisterFragment.class.getSimpleName());
            }
        });

        return view;
    }
}
