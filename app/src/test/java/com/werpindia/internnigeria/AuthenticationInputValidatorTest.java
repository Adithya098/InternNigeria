package com.werpindia.internnigeria;

import com.werpindia.internnigeria.utils.AuthenticationInputValidator;

import org.junit.Test;

import androidx.databinding.ObservableField;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AuthenticationInputValidatorTest
{
    @Test
    public void validateLogin()
    {
        String result = AuthenticationInputValidator.validateLoginDetails(new ObservableField<>(""),new ObservableField<>(""));
        assertNotNull(result);
    }

    @Test
    public void validateSignUp()
    {
        String result = AuthenticationInputValidator.validateSignUpDetails(new ObservableField<>("g@gmail.com"),new ObservableField<>("654321")
                ,new ObservableField<>("654321"),new ObservableField<>("InternNigeria"));
        assertNull(result);
    }
}
