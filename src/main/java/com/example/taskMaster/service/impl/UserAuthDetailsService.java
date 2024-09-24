package com.example.taskMaster.service.impl;

import com.example.taskMaster.entity.User;
import com.example.taskMaster.entity.UserPrincipal;
import com.example.taskMaster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user == null){
            System.out.println("USER NOT FOUND!");
            throw  new UsernameNotFoundException("USER NOT FOUND!");
        }

        return new UserPrincipal(user);
    }
}
