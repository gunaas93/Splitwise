package com.company.service;

import com.company.data.SplitType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manishsharma on 10/03/18.
 */
@Component
public class EqualBillSplitter implements BillSplitter {

    @Autowired
    private BillService billService;

    @Override
    @PostConstruct
    public void register() {
        billService.addSplitter(SplitType.EQUAL, this);
    }

    @Override
    public Map<Integer, Double> splitBill(double totalAmount, Map<Integer, Double> paidMap, Map<Integer, Double> participantMap) {
        double eachUserOwningAmount = totalAmount/participantMap.size();
        Map<Integer, Double> balanceMap = new HashMap<>();
        for(Map.Entry<Integer, Double> participantEntry : participantMap.entrySet()) {
            Integer userId = participantEntry.getKey();

            Double paidAmountByUser = paidMap.get(userId);
            if(paidAmountByUser == null) {
                paidAmountByUser = 0.0;
            }
            balanceMap.put(userId, paidAmountByUser - eachUserOwningAmount);
        }
        return balanceMap;
    }
}
