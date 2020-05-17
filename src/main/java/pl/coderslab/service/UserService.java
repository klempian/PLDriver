package pl.coderslab.service;

import pl.coderslab.model.User;

public interface UserService {
    User findByUserName(String name);
    User findByUsernameAndRoleName(String name, String roleName);

    void saveUser(User user);

    void saveAdmin(User admin);
}
