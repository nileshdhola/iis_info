package iis.iissurat.com.iissurat;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;


public class RegisterPageActivity extends AppCompatActivity {

    private EditText edtUserName, edtMobileNumber, edtEmailId, edtPassword, edtRepassword;
    private TextInputLayout inputLayoutName, inputlayoutMobile, inputlayoutEmailId, inputlayoutPassword, inputlayoutRepasswrod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        init();
    }

    private void init() {
        inputLayoutName = (TextInputLayout) findViewById(R.id.inputLayoutName);
        inputlayoutMobile = (TextInputLayout) findViewById(R.id.inputlayoutMobile);
        inputlayoutEmailId = (TextInputLayout) findViewById(R.id.inputlayoutEmailId);
        inputlayoutPassword = (TextInputLayout) findViewById(R.id.inputlayoutPassword);
        inputlayoutRepasswrod = (TextInputLayout) findViewById(R.id.inputlayoutRepasswrod);

        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtMobileNumber = (EditText) findViewById(R.id.edtMobileNumber);
        edtEmailId = (EditText) findViewById(R.id.edtEmailId);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtRepassword = (EditText) findViewById(R.id.edtRepassword);


        edtUserName.addTextChangedListener(new MyTextWatcher(edtUserName));
        edtMobileNumber.addTextChangedListener(new MyTextWatcher(edtMobileNumber));
        edtEmailId.addTextChangedListener(new MyTextWatcher(edtEmailId));
        edtPassword.addTextChangedListener(new MyTextWatcher(edtPassword));
        edtRepassword.addTextChangedListener(new MyTextWatcher(edtRepassword));
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.edtUserName: // validate Name
                    validateName();
                    break;
                case R.id.edtMobileNumber:
                    validateMobileNumber();
                    break;
                case R.id.edtEmailId:
                    validateEmail();
                    break;
                case R.id.edtPassword:
                    validatePassword();
                    break;
                case R.id.edtRepassword:
                    validateRepassword();
                    break;
            }
        }
    }

    private boolean validateMobileNumber() {
        if (edtMobileNumber.getText().toString().trim().length() < 10) {
            inputlayoutMobile.setError("Enter 10 digits Mobile Number");
            requestFocus(edtMobileNumber);
            return false;
        } else {
            inputlayoutMobile.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = edtEmailId.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputlayoutEmailId.setError("Please Enter Proper Email Id");
            requestFocus(edtEmailId);
            return false;
        } else {
            inputlayoutEmailId.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validatePassword() {
        if (edtPassword.getText().toString().length() < 6) {
            inputlayoutPassword.setError("Password Minimum 6 character");
            requestFocus(edtPassword);
            return false;
        } else {
            inputlayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateRepassword() {
        if (!edtPassword.getText().toString().equalsIgnoreCase(edtRepassword.getText().toString())) {
            inputlayoutRepasswrod.setError("Password not match");
            requestFocus(edtRepassword);
            return false;
        } else {
            inputlayoutRepasswrod.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateName() {
        if (edtUserName.getText().toString().trim().length() < 6) {
            inputLayoutName.setError("Please Enter Name minimum 6 character");
            requestFocus(edtUserName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }


    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
