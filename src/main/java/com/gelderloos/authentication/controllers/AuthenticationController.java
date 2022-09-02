package com.gelderloos.authentication.controllers;

import com.gelderloos.authentication.models.AppUser;
import com.gelderloos.authentication.repositories.AppUserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/login")
        public String getLoginPage() {
        return("/login");
    }

    @GetMapping("/loginWithSecret")
        public String getLoginWithSecretPage() {
        return("/loginWithSecret");
    }

    @GetMapping("/signup")
        public String getSignupPage() {
        return("/signup");
    }

    @GetMapping("/logout")
        public String getIndexOnLogout() {
        return("/index");
    }

    //POST loginWithSecret THIS WAS AFTER OUR IN CLASS DEMO
    @PostMapping("/loginWithSecret")
    public RedirectView logInUserWithSecret(HttpServletRequest request, String username, String password){
        AppUser userFromDb = appUserRepository.findByUsername(username);
        if ((userFromDb == null)
                || (!BCrypt.checkpw(password, userFromDb.getPassword())))
        {
            return new RedirectView("/index");
        }

        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        return new RedirectView("/indexWithSecret");

    }

    //POST logoutWithSecret THIS WAS AFTER OUR IN CLASS DEMO
    @PostMapping("/logoutWithSecret")
    public RedirectView logOutUserWithSecret(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.invalidate();

        return new RedirectView("/logout");
    }

    //POST logoutWithSecret THIS WAS AFTER OUR IN CLASS DEMO
    @PostMapping("/logout")
    public RedirectView logOutUser(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.invalidate();

        return new RedirectView("/logout");
    }

    // POST signup
    @PostMapping("/signup")
    public RedirectView signUpUser(String username, String password){
        //hash given PW
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        // create new user with given credentials(hashed PW)
        AppUser newUser = new AppUser(username, hashedPassword);
        // save the user to the DB --> UserRepo
        appUserRepository.save(newUser);
        return new RedirectView("/loginWithSecret");
    }
}
