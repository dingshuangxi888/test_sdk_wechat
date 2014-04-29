package biz.bbtec.ncwc.service.ncws;

import com.bbtech.ncws.SearchResult;

import java.util.List;

/**
 * Created by Dean on 2014/4/29.
 */
public interface SearchDeviceService {
    public List<SearchResult> search(String session, String key);
}
