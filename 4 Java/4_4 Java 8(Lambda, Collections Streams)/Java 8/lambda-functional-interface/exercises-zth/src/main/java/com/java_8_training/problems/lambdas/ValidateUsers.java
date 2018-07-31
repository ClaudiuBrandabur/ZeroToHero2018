package com.java_8_training.problems.lambdas;

import com.java_8_training.problems.lambdas.func_interface.UserFieldValidator;
import com.java_8_training.problems.lambdas.model.User;

import java.util.ArrayList;
import java.util.List;

public class ValidateUsers {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        users.add(new User("john@yahoo.com", "12345", "12345", "0723952TST"));
        users.add(new User("some.name@yahoo.com", "12345", "diffpassword", "0723421255"));
        users.add(new User("billy.boy@yahoo.com", "PassWord", "PassWord", "0733592124"));
        users.add(new User("someInvalidEmail", "12345", "12345", "0723952666"));
        users.add(new User("billy.boy@yahoo.com", "PassWord", "PassWord", "0733592124"));

        validate(users);
    }

    private static void validate(List<User> users) {
        // TODO use the functional interface UserFieldValidator to validate each field from User:
        // email must be unique
        // passwords must match
        // phone number must contain only numbers

        // The errors list must contain all the errors resulted from the validators.
        // Apply each validator on each user, and print the errors.
        List<String> errors = new ArrayList<>();

        UserFieldValidator emailValidator = null; // TODO use lambda to create and email validator
        UserFieldValidator passwordValidator = null; // TODO use lambda to create a password validator
        UserFieldValidator phoneValidator = null; // TODO use lambda to create a phone validator

        // TODO use the validators to validate each user
    }
}

class WrongEmail implements UserFieldValidator{

    private List<User> myList = new ArrayList<>();

    public WrongEmail(List<User> myList){
        this.myList = myList;
    }

    @Override
    public String validate(User user) {

        int ok = 1;
        for (User anotherUser : myList)
            if(anotherUser.getEmail().equals(user.getEmail()))
                ok = 0;

        if(ok == 0)
            return "Emailul nu e valid";

        return "emailul e valid";
    }
}

class WrongPassword implements UserFieldValidator{

    @Override
    public String validate(User user) {

        if(user.getPassword().equals(user.getConfirmPassword()))
            return "Parola OK";
        return "Parola nu se potriveste";
    }
}

class WrongPhone implements UserFieldValidator{

    @Override
    public String validate(User user) {

        int ok = 1;
        for(int i = 0; i < user.getPhone().length(); i++)
            if(!Character.isDigit(user.getPhone().charAt(i)))
                ok = 0;

        if(ok == 0){
            return "Telefonul nu e OK";
        }
        return "Telefonul e OK";
    }
}