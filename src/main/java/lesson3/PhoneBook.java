package lesson3;

public class PhoneBook {

    private String surname;

    private String phoneNumber;

    public PhoneBook(String surname, String phoneNumber) {
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
