package com.mftplus.aggregationcustomer.profile;

import java.util.List;

public interface UserService {
    User save(User user);
    User update(User user);
    void delete(Long id);
    List<User> findAll();
    User findById(Long id);
}
