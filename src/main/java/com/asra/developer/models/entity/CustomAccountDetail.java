package com.asra.developer.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomAccountDetail implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String userName;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private Set<Role> roles;

    public CustomAccountDetail(Long id, String userName, String password,
                               Collection<? extends GrantedAuthority> authorities, Set<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
        this.roles = roles;
    }

    public static CustomAccountDetail build(Account account) {
        List<GrantedAuthority> authorities = account.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

        return new CustomAccountDetail(account.getId(), account.getUserName(), account.getPassword(), authorities, account.getRoles());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        AccountDetailsImpl account = (AccountDetailsImpl) o;
//        return Objects.equals(id, account.id);
//    }
}
