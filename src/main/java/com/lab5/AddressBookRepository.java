package com.lab5;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Paul Roode
 */
@RepositoryRestResource(collectionResourceRel = "addressBooks", path = "addressBooks")
public interface AddressBookRepository extends PagingAndSortingRepository<AddressBook, Long> {

    AddressBook findById(long id);

}