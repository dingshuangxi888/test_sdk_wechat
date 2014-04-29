package biz.bbtec.ncwc.controller;

import biz.bbtec.ncwc.service.ncws.OffsetService;
import com.bbtech.ncws.LatLng;
import com.bbtech.ncws.LatLon;
import com.bbtech.ncws.OffsetResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Dean on 2014/4/29.
 */
@Controller
@RequestMapping("/offset")
public class MapOffsetController {

    private OffsetService offsetService;

    @RequestMapping(value = "google", method = RequestMethod.POST)
    @ResponseBody
    public OffsetResult googleOffset(LatLon latLon) {
        OffsetResult result = null;

        return result;
    }
}
