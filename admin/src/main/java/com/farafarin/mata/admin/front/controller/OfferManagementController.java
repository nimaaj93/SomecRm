package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.common.dto.OfferDto;
import com.farafarin.mata.common.model.OfferUpdateModel;
import com.farafarin.mata.admin.back.model.UserOfferModel;
import com.farafarin.mata.common.searchobject.OfferSO;
import com.farafarin.mata.admin.back.service.OfferManagementService;
import com.farafarin.mata.common.util.DateUtil;
import com.farafarin.mata.common.util.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by Asus on 11/20/2017.
 */

@RestController
@RequestMapping(value = "s/useroffer")
public class OfferManagementController {

    @Autowired
    private OfferManagementService offerManagementService;

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public void submitUserOffer(@RequestBody @Valid UserOfferModel userOfferModel) {
        offerManagementService.insertUserOffer(userOfferModel);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<OfferDto> getOffers(@RequestParam(value = "fromDate", required = false) String fromDate,
                                    @RequestParam(value = "toDate", required = false) String toDate) {
        OfferSO offerSO = prepareOfferSO(fromDate, toDate);
        return offerManagementService.getOffers(offerSO);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long getOfferCount(@RequestParam(value = "fromDate", required = false) String fromDate,
                              @RequestParam(value = "toDate", required = false) String toDate){
        OfferSO offerSO = prepareOfferSO(fromDate, toDate);
        return offerManagementService.getOfferCount(offerSO);
    }

    @RequestMapping(value = "/activecount", method = RequestMethod.GET)
    public Long getActiveOffersCount(){
        return offerManagementService.getActiveOffersCount();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateOffer(@RequestBody @Valid OfferUpdateModel offerUpdateModel){
        offerManagementService.updateOffer(offerUpdateModel);
    }

    private OfferSO prepareOfferSO(String fromDate, String toDate) {
        OfferSO offerSO = new OfferSO();
        Date fromDateGregorian = null;
        Date toDateGregorian = null;
        if (Empty.isNotEmpty(fromDate)) {
            fromDateGregorian = DateUtil.convertStringJalahiDateToGregorianDate(fromDate);
        }
        if (Empty.isNotEmpty(toDate)) {
            toDateGregorian = DateUtil.convertStringJalahiDateToGregorianDate(toDate);
        }
        offerSO.setFromDate(fromDateGregorian);
        offerSO.setToDate(toDateGregorian);
        return offerSO;
    }
}
