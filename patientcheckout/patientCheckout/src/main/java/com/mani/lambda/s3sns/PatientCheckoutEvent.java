package com.mani.lambda.s3sns;

public class PatientCheckoutEvent {
    public String firstName;
    public String lastName;
    public String middleName;

    @Override
    public String toString() {
        return "PatientCheckoutEvent{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", ssn='" + ssn + '\'' +
                '}';
    }

    public PatientCheckoutEvent(String firstName, String lastName, String middleName, String ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.ssn = ssn;
    }

    public PatientCheckoutEvent() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String ssn;

}
