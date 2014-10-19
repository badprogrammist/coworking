/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dodrde.coworking.ui.user;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dodrde.coworking.application.UserRegistrationService;
import ru.dodrde.coworking.domain.user.User;
import ru.dodrde.coworking.domain.user.UserDescription;
import ru.dodrde.coworking.ui.user.dto.UserListData;
import ru.dodrde.coworking.ui.user.dto.UserEditData;
import ru.dodrde.coworking.ui.user.dto.UserViewData;

/**
 *
 * @author Ильдар
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Inject
    private UserRegistrationService registrationService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserListData> getUsers() {
        List<UserListData> users = new ArrayList<>();
        for (User user : registrationService.getAll()) {
            users.add(new UserListData(user.getId(), user.getDescription().getName(), user.getDescription().getLastname(), user.getDescription().getPatronymic()));
        }
        return users;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addUser(@RequestBody UserEditData data) {
        UserDescription description = new UserDescription(data.getName(), data.getLastname(), data.getPatronymic());
        registrationService.registerMember(description);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserViewData getUser(@PathVariable("id") Long id) {
        User user = registrationService.get(id);
        if (user != null) {
            UserViewData data = new UserViewData(user.getId(), user.getDescription().getName(), user.getDescription().getLastname(), user.getDescription().getPatronymic());
            return data;
        }
        return new UserViewData();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteUser(@PathVariable("id") Long id) {
        registrationService.remove(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void updateUser(@RequestBody UserViewData data) {
        User member = registrationService.get(data.getId());
        if (member != null) {
            member.getDescription().setName(data.getName());
            member.getDescription().setLastname(data.getLastname());
            member.getDescription().setPatronymic(data.getPatronymic());
            registrationService.update(member);
        }
    }

    
}
