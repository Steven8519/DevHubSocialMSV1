package com.devhubsocial.api.core.recruiter;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RecruiterService {

    /**
     * Sample usage:
     *
     * curl -X POST $HOST:$PORT/recruiter \
     *   -H "Content-Type: application/json" --data \
     *   '{"developerId":123,"recruiterId":456,"recruiterName":"Chris Hamble","companyRating":3, "companyName":"Job Spring Partners"}'
     *
     * @param body
     * @return
     */
    @PostMapping(
            value    = "/recruiter",
            consumes = "application/json",
            produces = "application/json")
    Recruiter createRecruiter(@RequestBody Recruiter body);

    /**
     * Sample usage: curl $HOST:$PORT/recruiter?developerId=1
     *
     * @param developerId
     * @return
     */
    @GetMapping(
        value    = "/recruiter",
        produces = "application/json")
    List<com.devhubsocial.api.core.recruiter.Recruiter> getRecruiters(@RequestParam(value = "developerId", required = true) int developerId);

    /**
     * Sample usage:
     *
     * curl -X DELETE $HOST:$PORT/recruiter?developerId=1
     *
     * @param developerId
     */
    @DeleteMapping(value = "/recruiter")
    void deleteRecruiters(@RequestParam(value = "developerId", required = true)  int developerId);
}
