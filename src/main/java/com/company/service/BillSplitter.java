package com.company.service;

import com.company.data.SplitType;
import com.company.data.UserGroup;

import java.util.Map;

/**
 * Created by manishsharma on 10/03/18.
 */
public interface BillSplitter {

    void register();

    Map<Integer, Double> splitBill(double totalAmount, Map<Integer,Double> paidMap, Map<Integer, Double> participantMap);
}
