package com.asra.developer.services.impl;

import com.asra.developer.models.entity.Role;
import com.asra.developer.repository.RoleRepository;
import com.asra.developer.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

}
