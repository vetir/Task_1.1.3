//package web.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import web.dao.UserDao;
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    public UserDetailsServiceImpl() {
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return userDao.getUserByName(s);
//    }
//}
//    // «Пользователь» – это просто Object. В большинстве случаев он может быть
//    //  приведен к классу UserDetails.
//    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
