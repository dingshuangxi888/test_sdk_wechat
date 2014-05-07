package biz.bbtec.ncwc.service.ncws;

import com.bbtech.ncws.LatLng;
import com.bbtech.ncws.OffsetResult;

import java.util.List;

/**
 * Created by Administrator on 2014/4/29.
 */
public interface OffsetService {

    public OffsetResult getGoogleOffset(float lat, float lon);

    public List<OffsetResult> getGoogleMultiOffset(List<LatLng> latLons);
}
