package com.werpindia.internnigeria.utils;

import androidx.databinding.ObservableField;

public final class AuthenticationInputValidator
{
    private AuthenticationInputValidator(){}

    public static String validateLoginDetails(ObservableField<String> email, ObservableField<String> password)
    {
        if (email.get() == null || password.get() == null) return "One Of The Fields Is Empty";
        else if (email.get().trim().isEmpty() || password.get().trim().isEmpty()) return "One Of The Fields Is Empty";
        return null;
    }

    public static String validateSignUpDetails(ObservableField<String> email,ObservableField<String> password,
                                        ObservableField<String> confirmPassword,ObservableField<String> companyName)
    {
        if (email.get() == null || password.get() == null || confirmPassword.get() == null || companyName.get() == null) return "One Of The Fields Is Empty";
        else if (email.get().trim().isEmpty() || password.get().trim().isEmpty() || confirmPassword.get().isEmpty() || companyName.get().trim().isEmpty()) return "One Of The Fields Is Empty";
        else if (!password.get().equals(confirmPassword.get())) return "Your Password Doesn't Match";
        return null;
    }
}
