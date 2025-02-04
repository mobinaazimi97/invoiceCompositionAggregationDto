package com.mftplus.aggregationcustomer.profile;

import java.util.List;

public interface ProfileService {
    Profile save(Profile profile);
    Profile update(Profile profile);
    void delete(Long id);
    List<Profile> findAll();
    Profile findById(Long id);
}
