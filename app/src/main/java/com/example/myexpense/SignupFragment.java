package com.example.myexpense;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Amplify;
import androidx.fragment.app.FragmentActivity;

public class SignupFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.signup_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText etUser = (EditText) view.findViewById(R.id.editTextUsernameSignup);
        EditText etPass = (EditText) view.findViewById(R.id.editTextPasswordSignup);
        EditText etEmail = (EditText) view.findViewById(R.id.editTextEmailSignup);

        view.findViewById(R.id.signupButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Amplify SignUp", "SignUp Started");

                final String username = etUser.getText().toString();
                final String password = etPass.getText().toString();
                final String email = etEmail.getText().toString();

                Log.i("username", username);
                Log.i("pass", password);
                Log.i("email", email);

                Amplify.Auth.signUp(
                username,
                password,
                AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), email).build(),
                result -> Log.i("AmplifySignup", "Result: " + result.toString()),
                error -> Log.e("AmplifySingup", "Sign up failed", error)
                );

                //Pass usrName to Confirmation Fragment
                SignupFragmentDirections.ActionSignupFragmentToConfirmationFragment action =
                        SignupFragmentDirections.actionSignupFragmentToConfirmationFragment(username);

                Navigation.findNavController(view).navigate(action);
            }
        });
    }

}