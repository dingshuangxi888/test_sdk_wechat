package biz.bbtec.ncwc.service.ncws;

import java.util.List;

/**
 * Created by Dean on 2014/5/6.
 */
public interface ShortUrlService {

    public String toShortUrl(String longUrl);

    public List<String> toShortUrls(List<String> longUrls);
}
