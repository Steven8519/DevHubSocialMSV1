package com.devhubsocial.api.core.contact;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ContactService {
    /**
     * Sample usage: curl $HOST:$PORT/contact?developerId=1
     *
     * @param developerId
     * @return
     */
    @GetMapping(
        value    = "/contact",
        produces = "application/json")
    List<Contact> getcontacts(@RequestParam(value = "developerId", required = true) int developerId);
}
