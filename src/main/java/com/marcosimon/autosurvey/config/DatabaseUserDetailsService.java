package com.marcosimon.autosurvey.config;

import com.marcosimon.autosurvey.user.UserDbRepository;
import com.marcosimon.autosurvey.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDbRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userModel = userRepository.findUserModelByUsername(username);
        return userModel.map(UserModelUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User [%s] not found", username)));
    }


}
