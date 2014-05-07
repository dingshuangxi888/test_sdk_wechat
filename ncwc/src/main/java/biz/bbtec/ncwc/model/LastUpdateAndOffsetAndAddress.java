package biz.bbtec.ncwc.model;

import com.bbtech.ncws.LastUpdate2;
import com.bbtech.ncws.OffsetResult;

/**
 * Created by Dean on 2014/4/29.
 */
public class LastUpdateAndOffsetAndAddress {
    private LastUpdate2 lastUpdate;
    private OffsetResult offsetResult;
    private String address;

    public LastUpdateAndOffsetAndAddress(LastUpdate2 lastUpdate, OffsetResult offsetResult, String address) {
        this.lastUpdate = lastUpdate;
        this.offsetResult = offsetResult;
        this.address = address;
    }

    public LastUpdate2 getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LastUpdate2 lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public OffsetResult getOffsetResult() {
        return offsetResult;
    }

    public void setOffsetResult(OffsetResult offsetResult) {
        this.offsetResult = offsetResult;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
