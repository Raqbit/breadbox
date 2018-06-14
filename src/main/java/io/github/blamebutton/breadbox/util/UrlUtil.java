package io.github.blamebutton.breadbox.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlUtil {

    private static final Logger logger = LoggerFactory.getLogger(UrlUtil.class);

    /**
     * Private constructor so it cannot be constructed.
     */
    private UrlUtil() {
        // Private constructor
    }

    public static String encode(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("Formatting was not recognized", e);
            return null;
        }
    }
}
