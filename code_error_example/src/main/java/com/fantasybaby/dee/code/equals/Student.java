package com.fantasybaby.dee.code.equals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.Objects;

/**
 * 对于自定义的类型，如果要实现 Comparable，请记得 equals、hashCode、compareTo 三者逻辑一致。
 * @author fantasybaby
 * @Description
 * @create 2021-01-14 22:42
 */
@Data
@AllArgsConstructor
@Slf4j
public class Student implements Comparable<Student> {
    private int id;
    private String name;

//    @Override
//    public int compareTo(Student other) {
//        int result = Integer.compare(other.id, id);
//        if (result == 0)
//            log.info("this {} == other {}", this, other);
//        return result;
//    }


    @Override
    public int compareTo(Student o) {
        return Comparator.comparingInt(Student::getId).thenComparing(Student::getName).compare(this,o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
