package biz.bbtec.ncwc.handler.common;

import biz.bbtec.ncwc.service.ncws.ServiceSingletonFactory;
import biz.bbtec.ncwc.util.Configuration;
import com.bbtech.ncws.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/5/7.
 */
public class DeviceListMessageFormat {

    public static String format(List<DeviceList2> list) {
        StringBuilder sb = new StringBuilder();

        List<LatLon> latLons = new ArrayList<>();
        List<LatLng> latLngs = new ArrayList<>();
        for (DeviceList2 deviceList : list) {
            latLons.add(new LatLon(deviceList.getLatitude(), deviceList.getLongitude()));
            latLngs.add(new LatLng(deviceList.getLatitude(), deviceList.getLongitude()));
        }
        //Get address for Device list
        List<AddressResult> addressResults = ServiceSingletonFactory.getGeoService().getMultiGeo(latLons);
        //Get offset for Device list
        List<OffsetResult> offsetResults = ServiceSingletonFactory.getOffsetService().getGoogleMultiOffset(latLngs);

        // Convert long urls to short urls
        List<String> longUrls = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            DeviceList2 deviceList2 = list.get(i);
            StringBuilder urlSb = null;
            try {
                urlSb = new StringBuilder(Configuration.NCWC_URL_DNS)
                        .append("resources/map.html?")
                        .append("deviceid=").append(deviceList2.getDeviceId())
                        .append("&name=").append(deviceList2.getName())
                        .append("&time=").append(deviceList2.getTime())
                        .append("&coordinate=").append(deviceList2.getLatitude()).append(",").append(deviceList2.getLongitude())
                        .append(",").append(deviceList2.getAltitude()).append(",").append(deviceList2.getAccuracy())
                        .append("&offset=").append(deviceList2.getLatitude() + offsetResults.get(i).getLatitudeOffset())
                        .append(",").append(deviceList2.getLongitude() + offsetResults.get(i).getLongitudeOffset())
                        .append("&address=").append(URLEncoder.encode(addressResults.get(i).getAddress(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            longUrls.add(urlSb.toString());
        }
        List<String> shortUrls = ServiceSingletonFactory.getShortUrlService().toShortUrls(longUrls);

        // format message content
        DeviceList2 deviceListVO = null;
        for (int i = 0; i < list.size(); i++) {
            deviceListVO = list.get(i);
            sb.append(deviceListVO.getDeviceId()).append("\n")
                    .append(deviceListVO.getName()).append("\n")
                    .append(deviceListVO.getTime()).append("\n");
            sb.append("<a href=\"").append(shortUrls.get(i)).append("\">")
                    .append(addressResults.get(i).getAddress()).append("</a>").append("\n");
            sb.append("------------------------------------------------\n");
        }
        return sb.toString();
    }
}
