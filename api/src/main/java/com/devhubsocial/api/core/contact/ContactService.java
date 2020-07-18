package com.devhubsocial.api.core.contact;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ContactService {


    /**
     * Sample usage:
     *
     * curl -X POST $HOST:$PORT/contact \
     *   -H "Content-Type: application/json" --data \
     *   '{"developerId":123,"contactId":456,"phoneNumber":"648-927-8100","email":"example@gmail.com"}'
     *
     * @param body
     * @return
     */
    @PostMapping(
            value    = "/contact",
            consumes = "application/json",
            produces = "application/json")
    Contact createContact(@RequestBody Contact body);


    /**
     * Sample usage: curl $HOST:$PORT/contact?developerId=1
     *
     * @param developerId
     * @return
     */
    @GetMapping(
        value    = "/contact",
        produces = "application/json")
    List<Contact> getContacts(@RequestParam(value = "developerId", required = true) int developerId);

    /**
     * Sample usage:
     *
     * curl -X DELETE $HOST:$PORT/review?developerId=1
     *
     * @param developerId
     */
    @DeleteMapping(value = "/contact")
    void deleteContacts(@RequestParam(value = "developerId", required = true)  int developerId);


}
