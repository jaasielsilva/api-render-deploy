package com.api.app.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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

    // Mapeia a URL '/home' para a página principal do painel
    @GetMapping("/home")
    public String home(Model model) {
        long totalPessoas = userService.getTotalPessoasCadastradas(); // Total de pessoas cadastradas
        LocalDateTime lastActivity = userService.getLastActivity(); // Última atividade
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1); // Data da última semana
        List<User> newUsersList = userService.getNewUsersSince(oneWeekAgo); // Novos usuários desde a última semana

        int newUsers = newUsersList.size(); // Conta o número de novos usuários
        String lastActivityMessage = "Sem atividade recente";
        if (lastActivity != null) {
            long minutesAgo = ChronoUnit.MINUTES.between(lastActivity, LocalDateTime.now());
            lastActivityMessage =  minutesAgo + " minutos atrás";
        }

        // Passa as informações para o modelo
        model.addAttribute("totalPessoas", totalPessoas);
        model.addAttribute("lastActivityMessage", lastActivityMessage);
        model.addAttribute("newUsers", newUsers); // Adiciona o número de novos usuários no modelo

        return "home"; // Retorna a página home.html
    }

    @GetMapping("/relatorios")
    public String showRelatorios(Model model) {
        long totalPessoas = userService.getTotalPessoasCadastradas(); // Total de pessoas cadastradas
        LocalDateTime lastActivity = userService.getLastActivity(); // Última atividade
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1); // Data da última semana
        List<User> newUsersList = userService.getNewUsersSince(oneWeekAgo); // Novos usuários desde a última semana

        int newUsers = newUsersList.size(); // Conta o número de novos usuários
        String lastActivityMessage = "Sem atividade recente";
        if (lastActivity != null) {
            long minutesAgo = ChronoUnit.MINUTES.between(lastActivity, LocalDateTime.now());
            lastActivityMessage =  minutesAgo + " minutos atrás";
        }

        // Passa as informações para o modelo
        model.addAttribute("totalPessoas", totalPessoas);
        model.addAttribute("lastActivityMessage", lastActivityMessage);
        model.addAttribute("newUsers", newUsers); // Adiciona o número de novos usuários no modelo

        return "relatorios"; // Retorna a página relatorios.html
    }
}
