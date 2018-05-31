package com.farafarin.mata.admin.back.dto;

/**
 * Created by K550 VX on 11/25/2017.
 */
public class HomePageSummaryDto {

    private Long newOrdersCount;
    private Long newRegistrationRequestsCount;
    private Long activeUsersCount;
    private Long activeOffersCount;

    public Long getNewOrdersCount() {
        return newOrdersCount;
    }

    public void setNewOrdersCount(Long newOrdersCount) {
        this.newOrdersCount = newOrdersCount;
    }

    public Long getNewRegistrationRequestsCount() {
        return newRegistrationRequestsCount;
    }

    public void setNewRegistrationRequestsCount(Long newRegistrationRequestsCount) {
        this.newRegistrationRequestsCount = newRegistrationRequestsCount;
    }

    public Long getActiveUsersCount() {
        return activeUsersCount;
    }

    public void setActiveUsersCount(Long activeUsersCount) {
        this.activeUsersCount = activeUsersCount;
    }

    public Long getActiveOffersCount() {
        return activeOffersCount;
    }

    public void setActiveOffersCount(Long activeOffersCount) {
        this.activeOffersCount = activeOffersCount;
    }
}
