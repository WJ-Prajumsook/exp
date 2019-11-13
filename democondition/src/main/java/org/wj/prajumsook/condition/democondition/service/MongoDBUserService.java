package org.wj.prajumsook.condition.democondition.service;

import java.util.Arrays;
import java.util.List;

public class MongoDBUserService implements UserService {

    @Override
    public List<String> getUser() {
        System.out.println("Getting user list from MongoDB database");
        return Arrays.asList("Davide", "Dave", "Dane");
    }
}
