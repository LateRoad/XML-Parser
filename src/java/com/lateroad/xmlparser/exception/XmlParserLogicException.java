package com.lateroad.xmlparser.exception;

public class XmlParserLogicException extends Exception {
    public XmlParserLogicException() {
    }

    public XmlParserLogicException(String message) {
        super(message);
    }

    public XmlParserLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlParserLogicException(Throwable cause) {
        super(cause);
    }

    public XmlParserLogicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
