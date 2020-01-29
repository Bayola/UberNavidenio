package com.example.ubernavidenio.ui.register;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ubernavidenio.R;
import com.example.ubernavidenio.data.LoginRepository;
import com.example.ubernavidenio.data.Result;
import com.example.ubernavidenio.data.model.LoggedInUser;
import com.example.ubernavidenio.ui.register.RegisteredInUserView;
import com.example.ubernavidenio.ui.register.RegisterFormState;
import com.example.ubernavidenio.ui.register.RegisterResult;

import java.util.regex.Pattern;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<RegisterFormState> registerFormState = new MutableLiveData<>();
    private MutableLiveData<RegisterResult> registerResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    RegisterViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<RegisterFormState> getRegisterFormState() {
        return registerFormState;
    }

    LiveData<RegisterResult> getRegisterResult() {
        return registerResult;
    }

    public void register(String username,  String name, String lastName,
                         String phoneNumber,String password, String passwordChecker) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            registerResult.setValue(new RegisterResult(new RegisteredInUserView(data.getDisplayName())));
        } else {
            registerResult.setValue(new RegisterResult(R.string.login_failed));
        }
    }

    public void registerDataChanged(String username, String name, String lastName,
                                    String phoneNumber,String password, String passwordChecker) {
        if (!isUserNameValid(username)) {
            registerFormState.setValue(new RegisterFormState("username",R.string.invalid_username));
        } else if (!isNameOrLastNameValid(name)){
            registerFormState.setValue(new RegisterFormState("name", R.string.invalid_name));
        } else if (!isNameOrLastNameValid(lastName)){
            registerFormState.setValue(new RegisterFormState("lastName", R.string.invalid_last_name));
        } else if (!isPhoneNumberValid(phoneNumber)){
            registerFormState.setValue(new RegisterFormState("phoneNumber", R.string.invalid_phone_numer));
        } else if (!isPasswordValid(password)) {
            registerFormState.setValue(new RegisterFormState("password", R.string.invalid_password));
        } else if (!isPasswordCheckerValid(password, passwordChecker)){
            registerFormState.setValue(new RegisterFormState("passwordChecker", R.string.invalid_password_checker));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(username).matches();
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    private boolean isNameOrLastNameValid(String nameLastName){
        if (nameLastName == null){
            return false;
        }
        String REGEX_LETRAS = "^[a-zA-ZáÁéÉíÍóÓúÚñÑüÜ\\s]+$";
        Pattern patron = Pattern.compile(REGEX_LETRAS);
        return patron.matcher(nameLastName).matches();
    }

    private  boolean isPhoneNumberValid(String phoneNumber){
        if (phoneNumber == null){
            return false;
        } else if(phoneNumber.contains("+")){
            return Patterns.PHONE.matcher(phoneNumber).matches();
        } else if(phoneNumber.length() == 10){
            return  Patterns.PHONE.matcher(phoneNumber).matches();
        } else{
            return false;
        }

    }

    private  boolean isPasswordCheckerValid(String password, String passwordChecker){
        return password.equals(passwordChecker);
    }
}
