package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //LISTAR
    public List<User> getAll() {
        return userRepository.findAll();}

    //CRIAR
    public User save(User user) {
        return  userRepository.save(user);
    }

    //DELETAR
    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário com ID " + id + " não encontrado no banco.");
        }
    }
}
