package com.farafarin.mata.common.constant;

/**
 * Created by Asus on 11/22/2017.
 */
public enum  UserMessageSeenStatus {
    NEW(500L),
    SEEN(501L);

    UserMessageSeenStatus(Long seenStatus) {
        this.seenStatus = seenStatus;
    }

    private Long seenStatus;

    public Long getSeenStatus() {
        return seenStatus;
    }


}
