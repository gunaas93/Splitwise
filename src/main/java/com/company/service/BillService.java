package com.company.service;

import com.company.data.SplitType;

import java.util.Map;

/**
 * Created by manishsharma on 08/01/18.
 */
public interface BillService {

    void addSplitter(SplitType splitType, BillSplitter splitter);

    void registerBill(Integer groupId, Double totalAmount, Map<Integer,Double> paidMap, Map<Integer, Double> participantMap, SplitType splitType);

    double getBalance(Integer groupId, Integer userId);

    double getBalance(Integer userId);
}
