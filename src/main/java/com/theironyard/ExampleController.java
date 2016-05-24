package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by doug on 5/24/16.
 */
@Controller
public class ExampleController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(path = "/createUser", method = RequestMethod.GET)
    public String addUser(String username, String password) throws PasswordStorage.CannotPerformOperationException {

        String hashedPass = PasswordStorage.createHash(password);

        User user = new User(username, hashedPass);
        userRepository.save(user);

        return "home";
    }

    @RequestMapping(path = "/")
    public String listUsers(Model model){
        Iterable<User> users = userRepository.findAll();

        model.addAttribute("users", users);

        return "users";
    }

    @RequestMapping(path = "/testpassword")
    public String testPass(Model model, String username, String password) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        User user = userRepository.findByUsername(username);

        model.addAttribute("validPass", PasswordStorage.verifyPassword(password, user.password));

        return "testPass";

    }
}
