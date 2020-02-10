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
                         String password, String passwordChecker) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            registerResult.setValue(new RegisterResult(new RegisteredInUserView(data.getDisplayName())));
        } else {
            registerResult.setValue(new RegisterResult(R.string.login_failed));
        }
    }
    public void register(String username,  String password, String verifyCode) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            registerResult.setValue(new RegisterResult(new RegisteredInUserView(data.getDisplayName())));
        } else {
            registerResult.setValue(new RegisterResult(R.string.login_failed));
        }
    }

    public void register(String username, String password,
                         String identityCard,String birthDate,String cardNumber,
                         String CVV){
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            registerResult.setValue(new RegisterResult(new RegisteredInUserView(data.getDisplayName())));
        } else {
            registerResult.setValue(new RegisterResult(R.string.login_failed));
        }
    }
    public void register(String username, String password,
                         String identityCard,String birthDate,
                         String sector, String street,
                         String cardNumber,
                         String CVV){
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            registerResult.setValue(new RegisterResult(new RegisteredInUserView(data.getDisplayName())));
        } else {
            registerResult.setValue(new RegisterResult(R.string.login_failed));
        }
    }
    public void register(String username, String password, String storeName,
                         String sector, String street,
                         String cardNumber,
                         String CVV){
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
                                    String password, String passwordChecker) {
        if (!isUserNameValid(username)) {
            registerFormState.setValue(new RegisterFormState("username",R.string.invalid_username));
        } else if (!isNameOrLastNameValid(name)){
            registerFormState.setValue(new RegisterFormState("name", R.string.invalid_name));
        } else if (!isNameOrLastNameValid(lastName)){
            registerFormState.setValue(new RegisterFormState("lastName", R.string.invalid_last_name));
        } else if (!isPasswordValid(password)) {
            registerFormState.setValue(new RegisterFormState("password", R.string.invalid_password));
        } else if (!isPasswordCheckerValid(password, passwordChecker)){
            registerFormState.setValue(new RegisterFormState("passwordChecker", R.string.invalid_password_checker));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
        }
    }
    public void registerDataChanged(String storeName, String sector, String street,
                                    String cardNumber,
                                    String CVV, int dcStore) {
        if (!isStoreNameValid(storeName)) {
            registerFormState.setValue(new RegisterFormState("storeName",R.string.invalid_store_name));
        } else if (!isSectorValid(sector)){
            registerFormState.setValue(new RegisterFormState("sector", R.string.invalid_sector));
        } else if (!isStreetValid(street)){
            registerFormState.setValue(new RegisterFormState("street", R.string.invalid_street));
        } else if (!isCardNumberValid(cardNumber)){
            registerFormState.setValue(new RegisterFormState("cardNumber", R.string.invalid_card_number));
        } else if (!isCVVValid(CVV)) {
            registerFormState.setValue(new RegisterFormState("CVV", R.string.invalid_CVV));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
        }
    }
    public void registerDataChanged(String identityCard,String birthDate,String cardNumber,
                        String CVV) {
        if (!isIdentityCardValid(identityCard)) {
            registerFormState.setValue(new RegisterFormState("identityCard",R.string.invalid_identity_card));
        } else if (!isBirthDateValid(birthDate)){
            registerFormState.setValue(new RegisterFormState("birthDate", R.string.invalid_birth_date));
        } else if (!isCardNumberValid(cardNumber)){
            registerFormState.setValue(new RegisterFormState("cardNumber", R.string.invalid_card_number));
        } else if (!isCVVValid(CVV)) {
            registerFormState.setValue(new RegisterFormState("CVV", R.string.invalid_CVV));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
        }
    }
    public void registerDataChanged(String identityCard,String birthDate,
                                    String sector, String street, String cardNumber,
                                    String CVV) {
        if (!isIdentityCardValid(identityCard)) {
            registerFormState.setValue(new RegisterFormState("identityCard",R.string.invalid_identity_card));
        } else if (!isBirthDateValid(birthDate)){
            registerFormState.setValue(new RegisterFormState("birthDate", R.string.invalid_birth_date));
        } else if (!isSectorValid(sector)){
            registerFormState.setValue(new RegisterFormState("sector", R.string.invalid_sector));
        } else if (!isStreetValid(street)){
            registerFormState.setValue(new RegisterFormState("street", R.string.invalid_street));
        } else if (!isCardNumberValid(cardNumber)){
            registerFormState.setValue(new RegisterFormState("cardNumber", R.string.invalid_card_number));
        } else if (!isCVVValid(CVV)) {
            registerFormState.setValue(new RegisterFormState("CVV", R.string.invalid_CVV));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
        }
    }
    public void registerDataChanged(String verifyCode) {
        if (!isVerificationCodeValid(verifyCode)) {
            registerFormState.setValue(new RegisterFormState("identityCard",R.string.invalid_identity_card));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
        }
    }

    //A place holder verification code check
    private boolean isVerificationCodeValid(String verifyCode){
        return verifyCode.equals("0000");
    }
    // A place holder store name check
    private boolean isStoreNameValid(String storeName){
        return  isNameOrLastNameValid(storeName);
    }

    // A place holder street check
    private  boolean isStreetValid(String street){
        return isNameOrLastNameValid(street);
    }

    // A place holder sector check
    private boolean isSectorValid(String sector){
        return isNameOrLastNameValid(sector);
    }

    // A place holder CVV check
    private boolean isCVVValid(String CVV){
        String REGEX_CVV = "^[0-9]{3,4}$";
        Pattern pattern = Pattern.compile(REGEX_CVV);
        return pattern.matcher(CVV).matches();
    }

    // A place holder card number check
    private boolean isCardNumberValid(String cardNumber){
        String REGEX_CARD = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";
        Pattern pattern = Pattern.compile(REGEX_CARD);
        return pattern.matcher(cardNumber).matches();
    }

    // A placeholder birth date check
    private boolean isBirthDateValid(String birthDate){
        String REGEX_DATE = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        Pattern pattern = Pattern.compile(REGEX_DATE);
        return pattern.matcher(birthDate).matches();
    }

    // A placeholder identity card check
    private boolean isIdentityCardValid(String identityCard){

        if(identityCard.length() != 10){
            return false;
        }
        boolean lastDigit=false;
        int sumaDigImpares=0;
        int sumaDigPares=0;
        int digImpar=0;
        int digPar=0;
        int digVerif = Integer.parseInt(identityCard.substring(9));
        int decProx=0;

        for(int i=0;i<8;i+=2){
            digPar=(Integer.parseInt(identityCard.substring(i+1, i+2)));
            sumaDigPares+=digPar;
        }

        for(int i=-1;i<8;i+=2){
            digImpar=(Integer.parseInt(identityCard.substring(i+1,i+2)))*2;
            if(digImpar>9){
                digImpar%=9;
            }
            sumaDigImpares+=digImpar;
        }

        decProx=(((((sumaDigImpares+sumaDigPares)/10))*10)+10)-(sumaDigImpares + sumaDigPares);

        if(decProx==digVerif)
        {
            return true;
        }
        return false;
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

    // A placeholder name or lastname check
    private boolean isNameOrLastNameValid(String nameLastName){
        if (nameLastName == null){
            return false;
        }
        String REGEX_LETRAS = "^[a-zA-ZáÁéÉíÍóÓúÚñÑüÜ\\s]+$";
        Pattern patron = Pattern.compile(REGEX_LETRAS);
        return patron.matcher(nameLastName).matches();
    }

    // A placeholder phone numer check
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

    // A placeholder password checker check
    private  boolean isPasswordCheckerValid(String password, String passwordChecker){
        return password.equals(passwordChecker);
    }
}
