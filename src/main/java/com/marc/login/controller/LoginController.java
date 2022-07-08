package com.marc.login.controller;


import com.marc.login.Service.LoginService;
import com.marc.login.model.ErrorHandler;
import com.marc.login.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/users")
    public ResponseEntity fetchALl() {
        List<Login> users = loginService.fetchAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity find(@PathVariable Long id, HttpServletRequest request) {
        URI uri = URI.create(request.getRequestURI());
        try {
            return ResponseEntity.ok().body(loginService.find(id));
        } catch (Exception e) {
            ErrorHandler error = new ErrorHandler(
                    new Date(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(),
                    e.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/fetch")
    public ResponseEntity fetch(@RequestBody Login login, HttpServletRequest request) {
        URI uri = URI.create(request.getRequestURI());
        try {
            String username = login.getUsername();
            String password = login.getPassword();
            return ResponseEntity.created(uri).body(loginService.finduser(username, password));
        } catch (Exception e) {
            ErrorHandler error = new ErrorHandler(
                    new Date(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(),
                    e.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/user")
    public ResponseEntity save(@RequestBody Login login, HttpServletRequest request) {
        URI uri = URI.create(request.getRequestURI());
        try {
            login.setId(null);
            return ResponseEntity.created(uri).body(loginService.save(login));
        } catch (Exception e) {
            ErrorHandler error = new ErrorHandler(
                    new Date(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(),
                    e.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Login login, HttpServletRequest request){
        URI uri = URI.create(request.getRequestURI());
        try {
            login.setId(id);
            return ResponseEntity.ok().body(loginService.update(login));
        } catch (Exception e) {
            ErrorHandler error = new ErrorHandler(
                    new Date(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(),
                    e.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity delete(@PathVariable Long id, HttpServletRequest request){
        URI uri = URI.create(request.getRequestURI());
        try {
            loginService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            ErrorHandler error = new ErrorHandler(
                    new Date(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(),
                    e.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity.badRequest().body(error);
        }
    }


}
