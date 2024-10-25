package com.example.teamproject.services;

import com.example.teamproject.enums.Roles;
import com.example.teamproject.exceptions.EmailException;
import com.example.teamproject.exceptions.PasswordException;
import com.example.teamproject.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.teamproject.entity.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private static final Pattern rfc2822 = Pattern.compile(
            "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
    );

    public Users save(Users user) throws PasswordException, EmailException {
        if(user.getEmail() == null || !rfc2822.matcher(user.getEmail()).matches()){
            throw new EmailException();
        }
        if(user.getPassword() == null || user.getPassword().length() < 8){
            throw new PasswordException();
        }
        if(user.getUsername() == null){
            throw new NullPointerException();
        }
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Roles.USER);
        user.setActive(true);
        user.setCreatedDate(new Date());
        return usersRepository.save(user);
    }

    public Users findByUserName(String username) {
        return usersRepository.findByUsernameAndActive(username,true);
    }

    public void update(Users users) {
        this.usersRepository.save(users);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.usersRepository.findByUsernameAndActive(username,true);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
