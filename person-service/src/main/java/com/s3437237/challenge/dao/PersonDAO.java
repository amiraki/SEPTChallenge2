package com.s3437237.challenge.dao;

import com.s3437237.challenge.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonDAO extends JpaRepository<Person, Long> {
}
