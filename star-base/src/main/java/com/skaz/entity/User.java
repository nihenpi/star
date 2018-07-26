package com.skaz.entity;

import com.skaz.jpa.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author jungle
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "sys_user")
public class User extends BaseEntity {

    private String username;

    private String password;
}
