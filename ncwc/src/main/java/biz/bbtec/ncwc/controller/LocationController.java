package biz.bbtec.ncwc.controller;

import biz.bbtec.ncwc.model.DeviceOpen;
import biz.bbtec.ncwc.model.LastUpdateAndOffsetAndAddress;
import biz.bbtec.ncwc.service.ncws.AddressService;
import biz.bbtec.ncwc.service.ncws.LocationService;
import biz.bbtec.ncwc.service.ncws.OffsetService;
import biz.bbtec.ncwc.service.ncws.WechatUserService;
import com.bbtech.ncws.LastUpdate2;
import com.bbtech.ncws.OffsetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2014/4/29.
 */
@Controller
@RequestMapping("/loc")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private OffsetService offsetService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private WechatUserService wechatUserService;

    @RequestMapping(value = "last", method = RequestMethod.POST)
    @ResponseBody
    public LastUpdateAndOffsetAndAddress getLastUpdate(DeviceOpen deviceOpen) {
        LastUpdateAndOffsetAndAddress result = null;
        String session = wechatUserService.getSession(deviceOpen.getOpenid());
        LastUpdate2 lastUpdate = locationService.getLastUpdate(deviceOpen.getDeviceid(), session);
        if (lastUpdate != null) {
            OffsetResult offsetResult = offsetService.getGoogleOffset((float)lastUpdate.getLatitude(), (float)lastUpdate.getLongitude());
            String address = addressService.getAddress((float)lastUpdate.getLatitude(), (float)lastUpdate.getLongitude());
            result = new LastUpdateAndOffsetAndAddress(lastUpdate, offsetResult, address);
        }
        return result;
    }
}
