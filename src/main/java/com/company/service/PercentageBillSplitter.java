package com.company.service;

import com.company.data.SplitType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by manishsharma on 10/03/18.
 */
public class PercentageBillSplitter implements BillSplitter {

    @Autowired
    private BillService billService;

    @Override
    public void register() {
        billService.addSplitter(SplitType.PERCENTAGE, this);
    }

    @Override
    public Map<Integer, Double> splitBill(double totalAmount, Map<Integer, Double> paidMap, Map<Integer, Double> participantMap) {
        Map<Integer, Double> balanceMap = new HashMap<>();
        for(Map.Entry<Integer, Double> participantEntry : participantMap.entrySet()) {
            Integer userId = participantEntry.getKey();
            double userOwningPercentage = participantEntry.getValue();
            double userOwningAmount = (totalAmount * userOwningPercentage)/100;
            Double paidAmountByUser = paidMap.get(userId);
            if(paidAmountByUser == null) {
                paidAmountByUser = 0.0;
            }
            balanceMap.put(userId, paidAmountByUser - userOwningAmount);
        }
        return balanceMap;
    }
}
