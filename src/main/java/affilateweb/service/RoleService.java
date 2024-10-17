package affilateweb.service;

import affilateweb.model.entities.Role;
import affilateweb.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;


    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

}
