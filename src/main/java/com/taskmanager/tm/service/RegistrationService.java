package com.taskmanager.tm.service;

import com.taskmanager.tm.model.Authority;
import com.taskmanager.tm.model.User;
import com.taskmanager.tm.repository.AuthorityRepository;
import com.taskmanager.tm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {

   private UserRepository userRepository;
   private AuthorityRepository authorityRepository;
   private PasswordEncoder passwordEncoder;

   @Autowired
   public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
   }

   @Autowired
   public void setAuthorityRepository(AuthorityRepository authorityRepository) {
      this.authorityRepository = authorityRepository;
   }

   @Autowired
   public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
      this.passwordEncoder = passwordEncoder;
   }

   public User getByUsername(String username){
      return userRepository.getByUsername(username);
   }

   public void add(String username, String password){

      User user = new User();

      user.setUsername(username);
      user.setPassword(passwordEncoder.encode(password));
      user.setEnabled(true);

      userRepository.save(user);

      Authority authority = new Authority();
      authority.setUsername(username);
      authority.setAuthority("user");

      authorityRepository.save(authority);

   }


}
