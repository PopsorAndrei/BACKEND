package tech.getarrays.tutorialYtAngular.exeption;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String s) {
        super(s);
    }
}
