package com.mftplus.aggregationcustomer.profile;

import com.github.mfathi91.time.PersianDate;

import java.time.LocalDate;

public class ProfileUserDto {
    private Long profileId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    private Long userId;
    private String username;
    private String password;

    public Profile getProfile() {
        return Profile.builder().id(profileId).birthDate(birthDate).lastName(lastName).firstName(firstName).build();
    }

    public User getUser() {
        return User.builder().id(userId).username(username).password("********").build();
    }

    public String getPersianBirthDate() {
        return PersianDate.fromGregorian(birthDate).toString();

    }

    public void setPersianDate(String persianBirthDate) {
        this.birthDate = PersianDate.parse(persianBirthDate).toGregorian();
    }

}
