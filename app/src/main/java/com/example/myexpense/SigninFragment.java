package com.example.myexpense;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.amplifyframework.core.Amplify;

public class SigninFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.signin_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText etUser = (EditText) view.findViewById(R.id.editTextUsernameSignin);
        EditText etPass = (EditText) view.findViewById(R.id.editTextPasswordSignin);
        TextView msg = (TextView) view.findViewById(R.id.textViewSigninMsg);
        TextView txtLink = (TextView) view.findViewById(R.id.textViewSignin);

        txtLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SigninFragment.this).navigate(R.id.action_SigninFragment_to_SignupFragment);
            }
        });

        view.findViewById(R.id.buttonSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("Amplify SignIn", "SignUp Started");
                /*Todo todo = Todo.builder()
                        .name("My first todo2")
                        .description("todo description2")
                        .build();

                Amplify.API.mutate(
                        ModelMutation.create(todo),
                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );*/

                final String username = etUser.getText().toString();
                final String password = etPass.getText().toString();

                Log.i("username", username);
                Log.i("pass", password);

                Amplify.Auth.signIn(
                        username,
                        password,
                        Result -> NavHostFragment.findNavController(SigninFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment),
                        Error -> msg.setText("There's an error on login")
                );
            }
        });
    }
}