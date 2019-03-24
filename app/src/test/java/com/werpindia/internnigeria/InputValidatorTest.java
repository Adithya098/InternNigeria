package com.werpindia.internnigeria;

import com.werpindia.internnigeria.utils.InputValidator;

import org.junit.Test;

import androidx.databinding.ObservableField;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class InputValidatorTest
{
    @Test
    public void validateLogin()
    {
        String result = InputValidator.validateLoginDetails(new ObservableField<>(""),new ObservableField<>(""));
        assertNotNull(result);
    }

    @Test
    public void validateSignUp()
    {
        String result = InputValidator.validateSignUpDetails(new ObservableField<>("g@gmail.com"),new ObservableField<>("654321")
                ,new ObservableField<>("654321"),new ObservableField<>("InternNigeria"));

        assertNull(result);
    }
}
