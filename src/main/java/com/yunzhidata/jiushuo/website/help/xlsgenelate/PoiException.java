package com.yunzhidata.jiushuo.website.help.xlsgenelate;

public class PoiException extends RuntimeException {


    public PoiException(String message) {
        super(message);
    }

    public PoiException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoiException(Throwable cause) {
        super(cause);
    }

    public PoiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
