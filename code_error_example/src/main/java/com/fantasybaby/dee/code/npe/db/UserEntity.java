package com.fantasybaby.dee.code.npe.db;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 使用 Hibernate 的 @DynamicUpdate 注解实现更新 SQL 的动态生成，
 * 实现只更新修改后的字段，不过需要先查询一次实体，让 Hibernate 可以“跟踪”实体属性的当前状态，以确保有效。
 * @author Reid.Liu
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String nickName;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdDate;
}
