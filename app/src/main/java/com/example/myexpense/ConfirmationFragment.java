package com.example.myexpense;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;

public class ConfirmationFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.confirmation_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText confirm = (EditText) view.findViewById(R.id.editTextConfirmationCode);

        view.findViewById(R.id.confirmationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String cc = confirm.getText().toString();
                String usrname = ConfirmationFragmentArgs.fromBundle(getArguments()).getUsrName();

                Log.i("user", usrname);
                Log.i("ConfirmationCode", cc);

                Amplify.Auth.confirmSignUp(
                usrname,
                cc,
                result -> NavHostFragment.findNavController(ConfirmationFragment.this).navigate(R.id.action_ConfirmationFragment_to_SigninFragment),
                error -> Log.e("AuthQuickstart", error.toString())
                );

                Context context  = getActivity();
                String txtMsg = "Confirmation Complete";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, txtMsg, duration);
                toast.show();
            }


        });
    }
}

