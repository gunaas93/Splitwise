package com.company.service;

import com.company.data.SplitType;
import com.company.data.User;
import com.company.data.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manishsharma on 08/01/18.
 */
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private UserService userService;

    Map<SplitType, BillSplitter> splitterMap = new EnumMap<>(SplitType.class);

    @Override
    public void addSplitter(SplitType splitType, BillSplitter splitter) {
        splitterMap.put(splitType, splitter);
    }

    @Override
    public void registerBill(Integer groupId, Double totalAmount, Map<Integer, Double> paidMap, Map<Integer, Double> participantMap, SplitType splitType) {
        UserGroup userGroup = userService.getUserGroup(groupId);
        BillSplitter billSplitter = splitterMap.get(splitType);
        Map<Integer,Double> currentBillBalanceMap = billSplitter.splitBill(totalAmount, paidMap, participantMap);
        for (Map.Entry<Integer, Double> entry : currentBillBalanceMap.entrySet()) {
            Integer userId = entry.getKey();
            Double currentBalance = entry.getValue();

            User user = userService.getUser(userId);
            user.setBalance(user.getBalance() + currentBalance);

            userGroup.addUserBalance(userId, currentBalance);
        }
    }

    @Override
    public double getBalance(Integer groupId, Integer userId) {
        UserGroup userGroup = userService.getUserGroup(groupId);
        return userGroup.getUserBalance(userId);
    }

    @Override
    public double getBalance(Integer userId) {
        User user = userService.getUser(userId);
        return user.getBalance();
    }
}
