package com.company.controller;

import com.company.data.SplitType;
import com.company.data.User;
import com.company.data.UserGroup;
import com.company.service.BillService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by manishsharma on 08/01/18.
 */
@Controller
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "api/v1/splitwise/run", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Double>> registerUser() {
        System.out.println("############### Starting #################");

        User mudit = userService.addUser(new User("Mudit"));
        User sourav = userService.addUser(new User("Sourav"));
        User souvik = userService.addUser(new User("Souvik"));

        UserGroup userGroup = new UserGroup("Friends");
        userGroup.addUser(mudit);
        userGroup.addUser(sourav);
        userGroup.addUser(souvik);
        UserGroup createdGroup = userService.addGroup(userGroup);

        Map<Integer, Double> paidMap = new HashMap<>();
        paidMap.put(mudit.getId(), 250.0);
        paidMap.put(souvik.getId(), 50.0);

        Map<Integer, Double> participantMap = new HashMap<>();
        participantMap.put(mudit.getId(), 0.0);
        participantMap.put(sourav.getId(), 0.0);
        participantMap.put(souvik.getId(), 0.0);

        billService.registerBill(createdGroup.getId(), 300.0, paidMap, participantMap, SplitType.EQUAL);

        Map<String,Double> outputMap = new HashMap<>();
        for(Map.Entry<Integer, Double> balanceEntry : userGroup.getUserBalanceMap().entrySet()) {
            Integer userId = balanceEntry.getKey();
            Double balance = balanceEntry.getValue();
            outputMap.put(userService.getUser(userId).getName(), balance);
        }
        System.out.println(outputMap);
        return new ResponseEntity<Map<String, Double>>(outputMap, HttpStatus.OK);
    }


}
