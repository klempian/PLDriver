package pl.coderslab.service;

import pl.coderslab.model.User;

public interface UserService {
    User findByUsername(String username);
    User findByUsernameAndRoleName(String name, String roleName);

    void saveUser(User user);

    void saveAdmin(User admin);
}
