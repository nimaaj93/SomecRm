package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.common.dto.MessageDto;
import com.farafarin.mata.admin.back.model.UserMessageModel;
import com.farafarin.mata.common.searchobject.UserMessageSo;
import com.farafarin.mata.admin.back.service.UserMessageManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Asus on 11/19/2017.
 */

@RestController
@RequestMapping(value = "s/usermessage")
public class UserMessageManagementController {


    @Autowired
    private UserMessageManagementService userMessageManagementService;

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public void submitUserMessage(@RequestBody UserMessageModel userMessageModel) {
        userMessageManagementService.submitUserMessage(userMessageModel);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<MessageDto> getUserMessages(@RequestParam(value = "nationalCode", required = false) String nationalCode,
                                            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                            @RequestParam("page") @NotNull Integer pageNumber,
                                            @RequestParam("size") @NotNull Integer pageSize) {
        UserMessageSo userMessageSo = prepareUserMessageSo(nationalCode, phoneNumber, pageNumber, pageSize);
        return userMessageManagementService.getUserMessages(userMessageSo);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long getUserMessageCount(@RequestParam(value = "nationalCode", required = false) String nationalCode,
                                    @RequestParam(value = "phoneNumber", required = false) String phoneNumber){
        return userMessageManagementService.getUserMessageCount(nationalCode,phoneNumber);
    }

    private UserMessageSo prepareUserMessageSo(String receiverNationalCode, String receiverPhoneNumber,
                                               Integer pageNumber, Integer pageSize) {
        UserMessageSo userMessageSo = new UserMessageSo();
        userMessageSo.setReceiverNationalCode(receiverNationalCode);
        userMessageSo.setReceiverPhoneNumber(receiverPhoneNumber);
        userMessageSo.setPageNumber(pageNumber);
        userMessageSo.setPageSize(pageSize);
        return userMessageSo;
    }
}
