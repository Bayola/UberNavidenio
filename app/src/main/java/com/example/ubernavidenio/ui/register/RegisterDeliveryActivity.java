package com.example.ubernavidenio.ui.register;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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

import java.math.BigInteger;
import java.util.ArrayList;

public class RegisterDeliveryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private RegisterViewModel registerViewModel;
    EditText identityCardEditText, birthDateEditText, cardNumberEditText,
            sectorEditText,streetEditText,CVVEditText, usernameEditText, passwordEditText;
    Button btnAceptar;
    String strMonth, strYear;
    public static final String REGEX_NUMEROS = "^[0-9]+$";

    public static final String REGEX_EMAIL ="^[a-zA-Z0-9\\._-]+@[a-zA-Z0-9-]{2,}[.][a-zA-Z]{2,4}$";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_delivery);
        registerViewModel = ViewModelProviders.of(this, new RegisterViewModelFactory())
                .get(RegisterViewModel.class);

        this.identityCardEditText = findViewById(R.id.num_cedula);
        this.birthDateEditText = findViewById(R.id.set_birthday);
        this.sectorEditText = findViewById(R.id.sector);
        this.streetEditText = findViewById(R.id.street);
        this.cardNumberEditText = findViewById(R.id.num_tarjeta);
        this.CVVEditText = findViewById(R.id.CVV);
        //this.passwordCheckerEditText = findViewById(R.id.password_checker);
        final Button nextButton = findViewById(R.id.btn_next);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        registerViewModel.getRegisterFormState().observe(this, new Observer<RegisterFormState>() {
            @Override
            public void onChanged(@Nullable RegisterFormState registerFormState) {
                if (registerFormState == null) {
                    return;
                }
                nextButton.setEnabled(registerFormState.isDataValid());
                if (registerFormState.getIdentityCardError() != null) {
                    identityCardEditText.setError(getString(registerFormState.getIdentityCardError()));
                }
                if (registerFormState.getBirthDateError() != null){
                    birthDateEditText.setError(getString(registerFormState.getBirthDateError()));
                }
                if (registerFormState.getCardNumberError() != null){
                    cardNumberEditText.setError(getString(registerFormState.getCardNumberError()));
                }
                if (registerFormState.getCVVError() != null) {
                    CVVEditText.setError(getString(registerFormState.getCVVError()));
                }
                if (registerFormState.getSectorError() != null){
                    sectorEditText.setError(getString(registerFormState.getSectorError()));
                }
                if (registerFormState.getStreetError() != null){
                    streetEditText.setError(getString(registerFormState.getStreetError()));
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
                registerViewModel.registerDataChanged(identityCardEditText.getText().toString(),
                        birthDateEditText.getText().toString(),
                        sectorEditText.getText().toString(),
                        streetEditText.getText().toString(),
                        cardNumberEditText.getText().toString(),
                        CVVEditText.getText().toString());
            }
        };
        try{
            createEditTextListeners(afterTextChangedListener);

            CVVEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        registerViewModel.register(usernameEditText.getText().toString(),
                                passwordEditText.getText().toString(),
                                identityCardEditText.getText().toString(),
                                birthDateEditText.getText().toString(),
                                sectorEditText.getText().toString(),
                                streetEditText.getText().toString(),
                                cardNumberEditText.getText().toString(),
                                CVVEditText.getText().toString());
                    }
                    return false;
                }
            });


            // Spinner element
            Spinner spMonth, spYear;
            spMonth = (Spinner) findViewById(R.id.spinner_month);

            // Spinner click listener
            spMonth.setOnItemSelectedListener(this);
            // Creating adapter for spinner
            //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
            ArrayAdapter<CharSequence> dataAdapterMonth = ArrayAdapter.createFromResource(this,
                    R.array.Meses, android.R.layout.simple_spinner_item);

            // Drop down layout style - list view with radio button
            dataAdapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spMonth.setAdapter(dataAdapterMonth);

            spYear = (Spinner) findViewById(R.id.spinner_year);

            // Spinner click listener
            spYear.setOnItemSelectedListener(this);
            // Creating adapter for spinner
            //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
            ArrayAdapter<CharSequence> dataAdapterYear = ArrayAdapter.createFromResource(this,
                    R.array.Años, android.R.layout.simple_spinner_item);

            // Drop down layout style - list view with radio button
            dataAdapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spYear.setAdapter(dataAdapterYear);
        } catch (Exception error){
            System.out.println("ERROR");
        }

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

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void createEditTextListeners(TextWatcher afterTextChangedListener){
        this.identityCardEditText.addTextChangedListener(afterTextChangedListener);
        this.birthDateEditText.addTextChangedListener(afterTextChangedListener);
        this.cardNumberEditText.addTextChangedListener(afterTextChangedListener);
        this.CVVEditText.addTextChangedListener(afterTextChangedListener);
        this.sectorEditText.addTextChangedListener(afterTextChangedListener);
        this.streetEditText.addTextChangedListener(afterTextChangedListener);
    }
}
