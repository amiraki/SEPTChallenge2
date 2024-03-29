package com.s3437237.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "Persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column (name = "Name")
    private String name;
    @Column (name = "Address")
    private String address;
    @Column (name = "Post_code")
    private String postcode;
    @Column (name = "Age")
    private String age;
    @Column (name = "Job")
    private String job;
    @Column (name = "Email")
    private String email;
    @Column (name = "phone_no")
    private String phoneno;

    public Person(Long id, String name, String address, String postcode, String age, String job, String email, String phoneno) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.age = age;
        this.job = job;
        this.email = email;
        this.phoneno = phoneno;
    }

    public Person() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", age='" + age + '\'' +
                ", job='" + job + '\'' +
                ", email='" + email + '\'' +
                ", phoneno='" + phoneno + '\'' +
                '}';
    }
}
