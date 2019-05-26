package com.company.data;

import com.company.exception.SplitwiseException;
import com.company.util.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manishsharma on 10/03/18.
 */
@Data
public class UserGroup {

    private int id;

    private String name;

    private Map<Integer, Double> userBalanceMap;

    private Date createdTime;

    public UserGroup(String name) {
        this.name = name;
        this.userBalanceMap = new HashMap<>();
        createdTime = new Date();
    }

    public UserGroup() {
        this.userBalanceMap = new HashMap<>();
        createdTime = new Date();
    }

    public double getUserBalance(Integer userId) {
        Double balance = userBalanceMap.get(userId);
        if(balance == null) {
            throw new SplitwiseException(Constants.ErrorMessage.NO_USER_FOUND);
        }
        return balance;
    }

    public void addUser(Integer userId) {
        if(userBalanceMap.containsKey(userId)) {
            throw new SplitwiseException("User already exists");
        }

        userBalanceMap.put(userId, 0.0);
    }

    public void addUser(User user) {
        if(userBalanceMap.containsKey(user.getId())) {
            throw new SplitwiseException("User already exists");
        }

        userBalanceMap.put(user.getId(), 0.0);
    }

    public void addUserBalance(Integer userId, double balance) {
        Double existingBalance = userBalanceMap.get(userId);
        if(existingBalance == null) {
            existingBalance = 0.0;
        }
        userBalanceMap.put(userId, existingBalance + balance);
    }

}
