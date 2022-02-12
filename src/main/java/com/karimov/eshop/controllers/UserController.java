package com.karimov.eshop.controllers;

import com.karimov.eshop.domain.User;
import com.karimov.eshop.dto.UserDTO;
import com.karimov.eshop.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;


@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userService.getAll());
        return "userList";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserDTO());
        return "user";
    }

    @PostMapping("/new")
    public String saveUser(UserDTO dto, Model model){
        if (userService.save(dto)){
            return "redirect:/users";
        } else{
            model.addAttribute("user", dto);
            return "user";
        }
    }

    @GetMapping("/profile")
    public String profileUser(Model model, Principal principal){
        if (principal == null){
            throw new RuntimeException("You are not authorized");
        }
        User user = userService.findByName(principal.getName());

        UserDTO userDTO = UserDTO.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();
        model.addAttribute("user", userDTO);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfileUser(UserDTO userDTO, Model model, Principal principal){
        if (principal == null || !Objects.equals(principal.getName(), userDTO.getUsername())) {
            throw new RuntimeException("You are not authorized");
        }
        if (userDTO.getPassword() != null
            && !userDTO.getPassword().isEmpty()
            && !Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())){
            model.addAttribute("user", userDTO);
            // нужно добавить какой то сообщения, будем в другой раз
            return "profile";
        }
        userService.updateProfile(userDTO);
        return "redirect:/users/profile";
    }

}
