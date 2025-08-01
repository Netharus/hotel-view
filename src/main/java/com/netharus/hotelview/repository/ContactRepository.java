package com.netharus.hotelview.repository;

import com.netharus.hotelview.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Boolean existsByEmailOrPhoneIgnoreCase(String email, String phone);
}
