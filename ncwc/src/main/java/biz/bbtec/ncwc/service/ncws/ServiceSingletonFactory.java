package biz.bbtec.ncwc.service.ncws;

import biz.bbtec.ncwc.service.ncws.impl.*;

/**
 * Created by Administrator on 2014/5/7.
 */
public class ServiceSingletonFactory {

    private DeviceService deviceService;

    private GeoService geoService;

    private LocationService locationService;

    private LoginService loginService;

    private OffsetService offsetService;

    private SearchDeviceService searchDeviceService;

    private ShortUrlService shortUrlService;

    private WechatUserService wechatUserService;

    public static DeviceService getDeviceService() {
        return DeviceServiceSingletonHolder.instance;
    }

    public static GeoService getGeoService() {
        return GeoServiceSingletonHolder.instance;
    }

    public static LocationService getLocationService() {
        return LocationServiceSingletonHolder.instance;
    }

    public static LoginService getLoginService() {
        return LoginServiceSingletonHolder.instance;
    }

    public static OffsetService getOffsetService() {
        return OffsetServiceSingletonHolder.instance;
    }

    public static SearchDeviceService getSearchDeviceService() {
        return SearchDeviceServiceSingletonHolder.instance;
    }

    public static ShortUrlService getShortUrlService() {
        return ShortUrlServiceSingletonHolder.instance;
    }

    public static WechatUserService getWechatUserService() {
        return WechatUserServiceSingletonHolder.instance;
    }

    private static class DeviceServiceSingletonHolder {
        private static DeviceService instance = new DeviceServiceImpl();
    }

    private static class GeoServiceSingletonHolder {
        private static GeoService instance = new GeoServiceImpl();
    }

    private static class LocationServiceSingletonHolder {
        private static LocationService instance = new LocationServiceImpl();
    }

    private static class LoginServiceSingletonHolder {
        private static LoginService instance = new LoginServiceImpl();
    }

    private static class OffsetServiceSingletonHolder {
        private static OffsetService instance = new OffsetServiceImpl();
    }

    private static class SearchDeviceServiceSingletonHolder {
        private static SearchDeviceService instance = new SearchDeviceServiceImpl();
    }

    private static class ShortUrlServiceSingletonHolder {
        private static ShortUrlService instance = new ShortUrlServiceImpl();
    }

    private static class WechatUserServiceSingletonHolder {
        private static WechatUserService instance = new WechatUserServiceImpl();
    }
}
