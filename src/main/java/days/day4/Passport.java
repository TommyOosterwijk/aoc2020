package days.day4;

public class Passport {

    private String eyeColor;
    private String hairColor;
    private String height;
    private String birthYear;
    private String issueYear;
    private String expirationYear;
    private String passportID;
    private String countryID;

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public void setIssueYear(String issueYear) {
        this.issueYear = issueYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public boolean isValidPassport(){
        return eyeColor != null && hairColor != null && height != null && birthYear != null && issueYear != null && expirationYear != null && passportID != null;
    }
}
