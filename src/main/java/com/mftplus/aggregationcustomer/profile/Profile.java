package com.mftplus.aggregationcustomer.profile;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "profileEntity")
@Table(name = "profile_tbl")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @OneToOne
    private User user;



    //@Transient        -> not need
//    private PersianDate persianBirthDate;

            //its Before Use the separate DtoService Sample
//    @OneToMany(mappedBy = "profile")
//    private List<User> userList;
//
//    public void addUser(User user) {
//        if (userList == null) {
//            userList = new ArrayList<>();
//        }
//        userList.add(user);
//    }
//
//    public void addAllUser(User... users) {
//        if (userList == null) {
//            userList = new ArrayList<>();
//        }
//        Collections.addAll(userList, users);
//    }

}
