package org.wj.prajumsook.condition.democondition.service;

import java.util.Arrays;
import java.util.List;

public class MySQLUserService implements UserService {

    @Override
    public List<String> getUser() {
        System.out.println("Getting user list from MySQL database.");
        return Arrays.asList("James", "Jim", "Jane");
    }
}
