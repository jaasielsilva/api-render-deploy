package com.api.app.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map; // Importando a classe Map

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.api.app.model.User;
import com.api.app.repository.UserRepository;
import com.api.app.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Mapeia a URL '/register' para a página de cadastro de usuários
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Nome da view, que será o arquivo register.html
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.saveUser(user); // Salva o usuário
        return "redirect:/home"; // Redireciona para a página de login
    }

    // Outros métodos...
    @PostMapping("/cadastrar")
    public User cadastrar(@RequestBody User user) {
        return userService.saveUser(user); // Chama o serviço para salvar o usuário
    }

    private Map<String, Object> getDashboardInfo() {
        Map<String, Object> dashboardInfo = new HashMap<>();
        long totalPessoas = userService.getTotalPessoasCadastradas();
        LocalDateTime lastActivity = userService.getLastActivity();
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        List<User> newUsersList = userService.getNewUsersSince(oneWeekAgo);

        int newUsers = newUsersList.size();
        String lastActivityMessage = "Sem atividade recente";
        if (lastActivity != null) {
            long minutesAgo = ChronoUnit.MINUTES.between(lastActivity, LocalDateTime.now());
            lastActivityMessage = minutesAgo + " minutos atrás";
        }

        dashboardInfo.put("totalPessoas", totalPessoas);
        dashboardInfo.put("lastActivityMessage", lastActivityMessage);
        dashboardInfo.put("newUsers", newUsers);
        return dashboardInfo;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAllAttributes(getDashboardInfo());
        return "home";
    }

    @GetMapping("/relatorios")
    public String showRelatorios(Model model) {
        model.addAllAttributes(getDashboardInfo());
        return "relatorios";
    }
}
