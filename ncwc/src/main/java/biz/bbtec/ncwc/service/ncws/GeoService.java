package biz.bbtec.ncwc.service.ncws;

import com.bbtech.ncws.AddressResult;
import com.bbtech.ncws.LatLon;

import java.util.List;

/**
 * Created by Dean on 2014/4/29.
 */
public interface GeoService {

    public String getGeo(float lat, float lon);

    public List<AddressResult> getMultiGeo(List<LatLon> latLons);
}
