package com.marc.login.Service;

import com.marc.login.model.Login;
import com.marc.login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public List<Login> fetchAll() {
        return loginRepository.findAll();
    }

    public Login find(long id) throws Exception {
        Login login = loginRepository.findById(id).orElse(null);
        if (login == null) throw new Exception("Login id doesn't exists");
        return login;
    }


    public Login finduser(String username, String password) throws Exception {
        Login login = loginRepository.findByUserandPass(username, password);
        if (login == null) throw new Exception("User doesn't exists");
        return login;
    }

    public Login save(Login login) throws Exception {
        try{
            return loginRepository.saveAndFlush(login);
        } catch (Exception exception) {
            Throwable t = exception.getCause();
            while (t.getCause() != null) t = t.getCause();
            throw new Exception(t.getLocalizedMessage());
        }
    }

    public Login update(Login login) throws Exception {
        try {
            return loginRepository.save(login);
        } catch (Exception exception) {
            Throwable t = exception.getCause();
            while (t.getCause() != null) t = t.getCause();
            throw new Exception(t.getLocalizedMessage());
        }
    }

    public void delete(Long id) throws Exception {
        try {
            Login contact = this.find(id);
            loginRepository.delete(contact);
        } catch (Exception exception) {
            Throwable t = exception.getCause();
            while (t.getCause() != null) t = t.getCause();
            throw new Exception(t.getLocalizedMessage());
        }
    }

}
