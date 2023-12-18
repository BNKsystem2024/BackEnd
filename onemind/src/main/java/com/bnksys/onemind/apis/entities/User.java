package com.bnksys.onemind.apis.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(name = "dept_id", nullable = false)
    private Integer deptId;

    @Column(name = "user_id", nullable = false, unique = true, length = 255)
    private String userId;

    @Column(name = "user_password", nullable = false, length = 255)
    private String userPassword;

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "birthday", nullable = false, length = 8, columnDefinition = "CHAR(8)")
    private String birthday;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Solved_problem> solvedProblems = new ArrayList<>();


}

