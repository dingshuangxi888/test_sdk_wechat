package biz.bbtec.ncwc.service.ncws;

import com.bbtech.ncws.DeviceList2;

import java.util.List;

/**
 * Created by Dean on 2014/4/30.
 */
public interface DeviceService {

    public List<DeviceList2> getDeviceList(String session, int start, int timezone, int pageSize);
}
