package br.com.gerenciamento.controller;

import br.com.gerenciamento.repository.UsuarioRepository;
import br.com.gerenciamento.dao.ProdutoQuery;
import br.com.gerenciamento.exception.EmailExistsException;
import br.com.gerenciamento.exception.ServiceExc;
import br.com.gerenciamento.exception.UserExistsException;
import br.com.gerenciamento.model.Logado;
import br.com.gerenciamento.model.Produto;
import br.com.gerenciamento.model.Usuario;
import br.com.gerenciamento.service.ServiceUsuario;
import br.com.gerenciamento.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/login");
        modelAndView.addObject("usuario", new Usuario());
        return modelAndView;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home/index");
        modelAndView.addObject("produto", new Produto());
        return modelAndView;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usuario", new Usuario());
        modelAndView.setViewName("login/cadastro");
        return modelAndView;
    }

    @PostMapping("/salvarUsuario")
    public ModelAndView cadastrar(Usuario usuario) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        try {
            serviceUsuario.salvarUsuario(usuario);
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        } catch(EmailExistsException e) {
            modelAndView.addObject("msg1","Este email já está cadastrado!");
            modelAndView.setViewName("login/cadastro");
            return modelAndView;
        } catch(UserExistsException i) {
            modelAndView.addObject("msg2","Este usuário já está cadastrado!");
            modelAndView.setViewName("login/cadastro");
            return modelAndView;
        }
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid Usuario usuario, BindingResult br,
                              HttpSession session) throws NoSuchAlgorithmException, ServiceExc, SQLException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usuario", new Usuario());
        if(br.hasErrors()) {
            modelAndView.addObject("msg","Usuario não encontrado. Tente novamente");
            modelAndView.setViewName("login/login");
            return modelAndView;
        }

        Usuario userLogin = serviceUsuario.loginUser(usuario.getUser(), Util.md5(usuario.getSenha()));
        if(userLogin == null) {
            modelAndView.addObject("msg","Usuario e/ou senha incorretos. Tente novamente");
            modelAndView.setViewName("login/login");
            return modelAndView;
        } else {
            session.setAttribute("usuarioLogado", userLogin);
            Logado online = new Logado();
            online.setOnline(usuario.getUser());
            ProdutoQuery prodQuery = new ProdutoQuery();
            prodQuery.UserAtual(usuario.getUser());
            return index();
        }

        //return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return login();
    }
}
