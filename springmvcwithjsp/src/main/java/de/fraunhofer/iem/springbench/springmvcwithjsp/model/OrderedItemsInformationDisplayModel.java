package de.fraunhofer.iem.springbench.springmvcwithjsp.model;

public class OrderedItemsInformationDisplayModel {
    private static String userName;
    private static String time;
    private static String moneyFormat;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        OrderedItemsInformationDisplayModel.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        OrderedItemsInformationDisplayModel.time = time;
    }

    public String getMoneyFormat() {
        return moneyFormat;
    }

    public void setMoneyFormat(String moneyFormat) {
        OrderedItemsInformationDisplayModel.moneyFormat = moneyFormat;
    }

    @Override
    public String toString() {
        return "User Name = " + userName + "</br>" +
                "Time = " + time + "</br>" +
                "Money Format = " + moneyFormat + "</br>";
    }
}
