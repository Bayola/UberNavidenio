package com.example.ubernavidenio.ui.register;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ubernavidenio.R;
import com.example.ubernavidenio.ui.SplashScreenMain;

import java.math.BigInteger;
import java.util.ArrayList;


public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RegisterViewModel registerViewModel;
    EditText usernameEditText, nameEditText, lastNameEditText,
    passwordEditText, passwordCheckerEditText;
    Button nextButton = null;
    BigInteger valorMd5 = null;
    ArrayList<String> lista;

    public static final String REGEX_NUMEROS = "^[0-9]+$";

    public static final String REGEX_EMAIL ="^[a-zA-Z0-9\\._-]+@[a-zA-Z0-9-]{2,}[.][a-zA-Z]{2,4}$";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerViewModel = ViewModelProviders.of(this, new RegisterViewModelFactory())
                .get(RegisterViewModel.class);

        this.usernameEditText = findViewById(R.id.username);
        this.nameEditText = findViewById(R.id.name);
        this.lastNameEditText = findViewById(R.id.last_name);
        //this.phoneNumberEditText = findViewById(R.id.phone_number);
        this.passwordEditText = findViewById(R.id.password);
        this.passwordCheckerEditText = findViewById(R.id.password_checker);
        this.nextButton = findViewById(R.id.btn_next);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        registerViewModel.getRegisterFormState().observe(this, new Observer<RegisterFormState>() {
            @Override
            public void onChanged(@Nullable RegisterFormState registerFormState) {
                if (registerFormState == null) {
                    return;
                }
                nextButton.setEnabled(registerFormState.isDataValid());
                if (registerFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(registerFormState.getUsernameError()));
                }
                if (registerFormState.getNameError() != null){
                    nameEditText.setError(getString(registerFormState.getNameError()));
                }
                if (registerFormState.getLastNameError() != null){
                    lastNameEditText.setError(getString(registerFormState.getLastNameError()));
                }
                if (registerFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(registerFormState.getPasswordError()));
                }
                if (registerFormState.getPasswordCheckerError() != null){
                    passwordCheckerEditText.setError(getString(registerFormState.getPasswordCheckerError()));
                }
            }
        });

        registerViewModel.getRegisterResult().observe(this, new Observer<RegisterResult>() {
            @Override
            public void onChanged(@Nullable RegisterResult registerResult) {
                if (registerResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (registerResult.getError() != null) {
                    showLoginFailed(registerResult.getError());
                }
                if (registerResult.getSuccess() != null) {
                    updateUiWithUser(registerResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                registerViewModel.registerDataChanged(usernameEditText.getText().toString(),
                        nameEditText.getText().toString(), lastNameEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        passwordCheckerEditText.getText().toString());
            }
        };

        createEditTextListeners(afterTextChangedListener);

        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    registerViewModel.register(usernameEditText.getText().toString(),
                            nameEditText.getText().toString(), lastNameEditText.getText().toString(),
                            passwordEditText.getText().toString(),
                            passwordCheckerEditText.getText().toString());
                }
                return false;
            }
        });
        /*
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                registerViewModel.register(usernameEditText.getText().toString(),
                        nameEditText.getText().toString(),lastNameEditText.getText().toString(),
                        passwordEditText.getText().toString(), passwordCheckerEditText.getText().toString());
            }
        });*/
        // Spinner element
        Spinner spinner;
        spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this,
                R.array.TipoUsuario, android.R.layout.simple_spinner_item);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    private void updateUiWithUser(RegisteredInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if (item.equals("Cliente")){
            this.nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(RegisterActivity.this, RegisterClientActivity.class));
                }
            });
        } else if (item.equals("Repartidor")){
            this.nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(RegisterActivity.this, RegisterDeliveryActivity.class));
                }
            });
            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        } else if (item.equals("Establecimiento")) {
            this.nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(RegisterActivity.this, RegisterStoreActivity.class));
                }
            });
        } else if (item.equals("Administrador")){
            this.nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(RegisterActivity.this, RegisterAdminActivity.class));
                }
            });
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void createEditTextListeners(TextWatcher afterTextChangedListener){
        this.usernameEditText.addTextChangedListener(afterTextChangedListener);
        this.nameEditText.addTextChangedListener(afterTextChangedListener);
        this.lastNameEditText.addTextChangedListener(afterTextChangedListener);
        this.passwordEditText.addTextChangedListener(afterTextChangedListener);
        this.passwordCheckerEditText.addTextChangedListener(afterTextChangedListener);
    }
}
