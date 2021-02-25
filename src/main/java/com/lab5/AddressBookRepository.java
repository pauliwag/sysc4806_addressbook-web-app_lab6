package com.lab5;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressBookRepository extends PagingAndSortingRepository<AddressBook, Long> {

    AddressBook findById(long id);

}