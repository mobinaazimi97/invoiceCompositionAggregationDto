package com.mftplus.aggregationcustomer.profile;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileUserController {

    private final ProfileService profileService;
    private final UserService userService;

    public ProfileUserController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }


    @GetMapping
    public String profileForm(Model mode) {
        mode.addAttribute("profileUserDto", new ProfileUserDto());
        return "profile";
    }

    @Transactional(rollbackOn = Exception.class)
    public String saveProfile(ProfileUserDto profileUserDto) {
        Profile profile = profileUserDto.getProfile();
        User user = profile.getUser();
        profileService.save(profile);

        user.setProfile(profile);
        userService.save(profileUserDto.getUser());

        profile.setUser(user);
        profileService.update(profile);

        return "redirect:/profile";
    }
}
