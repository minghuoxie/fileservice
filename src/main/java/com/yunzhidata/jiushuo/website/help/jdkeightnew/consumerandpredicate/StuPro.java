package com.yunzhidata.jiushuo.website.help.jdkeightnew.consumerandpredicate;

public class StuPro {
    String firstName;
    String lastName;
    Double grade;
    Double feeDiscount = 0.0;
    Double baseFee = 20000.0;

    public StuPro(String firstName, String lastName, Double grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }

    public void printFee() {
        Double newFee = baseFee - ((baseFee * feeDiscount) / 100);
        System.out.println("The fee after discount: " + newFee);
    }
}
