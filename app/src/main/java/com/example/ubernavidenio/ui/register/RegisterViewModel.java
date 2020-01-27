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

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<RegisterFormState> RegisterFormState = new MutableLiveData<>();
    private MutableLiveData<RegisterResult> RegisterResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    RegisterViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<RegisterFormState> getLoginFormState() {
        return RegisterFormState;
    }

    LiveData<RegisterResult> getLoginResult() {
        return RegisterResult;
    }

    public void register(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            RegisterResult.setValue(new RegisterResult(new RegisteredInUserView(data.getDisplayName())));
        } else {
            RegisterResult.setValue(new RegisterResult(R.string.login_failed));
        }
    }

    public void registerDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            RegisterFormState.setValue(new RegisterFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            RegisterFormState.setValue(new RegisterFormState(null, R.string.invalid_password));
        } else {
            RegisterFormState.setValue(new RegisterFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
