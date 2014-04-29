package biz.bbtec.ncwc.service.ncws;

import com.bbtech.ncws.LastUpdate2;

/**
 * Created by Administrator on 2014/4/29.
 */
public interface LocationService {

    public LastUpdate2 getLastUpdate(long deviceid, String session);
}
