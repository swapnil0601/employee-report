package com.employeereport.service;

import com.employeereport.entity.User;
import com.employeereport.repository.UserRepository;
import jakarta.inject.Singleton;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

@Singleton
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){this.userRepository=userRepository;}

    @Override
    public User register(User user){
        String hashedPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10));
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }
    @Override
    public User login(String email,String password) throws Exception{
        try{
            User user;
            System.out.println(email);
            try{
                user= userRepository.findByEmail(email);
                System.out.println(user);
            }
            catch (Exception e){
                throw new Exception("Email Id doesn't not exist!");
            }
            if(!BCrypt.checkpw(password,user.getPassword())){
                throw new Exception("Invalid Credentials");
            }
            return getUserById(user.getId());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(null);
    }
    @Override
    public List<User> getUsersWithNoManager() {
        try {
            return userRepository.findByNoManager();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<User> getUsersByManager(String manager) {
        try {
            return userRepository.findByManager(manager);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    @Override
    public User updateUser(Integer id, User user) {

        User prevUser = getUserById(id);

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));

        prevUser.setFirstName(user.getFirstName());
        prevUser.setLastName(user.getLastName());
        prevUser.setEmail(user.getEmail());
        prevUser.setPassword(hashedPassword);
        prevUser.setWorkStartHour(user.getWorkStartHour());
        prevUser.setWorkEndHour(user.getWorkEndHour());

        return userRepository.update(prevUser);
    }
    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
