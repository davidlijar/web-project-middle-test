package com.jvision.admin202335003.middletest.Service;

import com.jvision.admin202335003.middletest.Domain.Users;
import com.jvision.admin202335003.middletest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public Users getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }
    public Users createUser(String address, String tel, String name, String intro) {


        Users user = new Users();
        user.setAddress(address);
        user.setTel(tel);
        user.setName(name);
        user.setIntro(intro);

        return userRepository.save(user);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(String id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));


        userRepository.deleteById(id);
    }

    public Users updateUser(Users user) {
        return userRepository.save(user);
    }

}
