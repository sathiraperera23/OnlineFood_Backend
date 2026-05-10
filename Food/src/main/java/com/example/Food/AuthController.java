package com.example.Food;

import com.example.Food.entity.Cart;
import com.example.Food.entity.User;
import com.example.Food.repository.UserRepository;
import com.example.Food.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

//    @PostMapping("/register")
//    public String register(@RequestBody User user) {
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        userRepository.save(user);
//
//        return "User registered successfully";
//    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);

        userRepository.save(user);

        return "User registered successfully";
    }

//    @PostMapping("/login")
//    public String login(@RequestBody User user) {
//
//        User dbUser = userRepository.findByUsername(user.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
//            return jwtUtil.generateToken(user.getUsername());
//        }
//
//        throw new RuntimeException("Invalid credentials");
//    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User dbUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            return jwtUtil.generateToken(dbUser.getUsername());
        }

        throw new RuntimeException("Invalid credentials");
    }
}
