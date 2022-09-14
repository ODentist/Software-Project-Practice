package com.odentist.service;

import com.odentist.dao.AdministratorsMapper;
import com.odentist.dao.EmployeeMapper;
import com.odentist.dao.RoleMapper;
import com.odentist.pojo.Administrators;
import com.odentist.pojo.Employee;
import com.odentist.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname AdministratorsService
 * @Date 2022/9/13 16:59
 * @Author jin
 */
@Service
public class AdministratorsService implements UserDetailsService {
    @Resource
    AdministratorsMapper administratorsMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Resource
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Administrators administrators = administratorsMapper.findAdministratorsByUserName(username);
        if (administrators != null){
            List<Role> roles = roleMapper.findRole(administrators.getRole());
            roles.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getPermission()));
            });
            return new User(administrators.getUsername(),administrators.getPassword(),authorities);
        }else {
            Employee emp = employeeMapper.findEmpByEmail(username);
            authorities.add(new SimpleGrantedAuthority("MIN"));
            return new User(emp.getEmail(),emp.getPassword(),authorities);
        }
    }
}
