package bg.softuni.authenticationservice.service.impl;


import bg.softuni.userservice.models.entity.authorisation.Permission;
import bg.softuni.userservice.models.entity.authorisation.Role;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().name()));
            for (Permission permission : role.getPermissions()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getUserSecurity().getPassword().getPasswordHash(), grantedAuthorities);
    }
}
