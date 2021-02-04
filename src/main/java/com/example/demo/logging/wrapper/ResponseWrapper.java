package com.example.demo.logging.wrapper;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.Strings;
import org.springframework.http.MediaType;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @since       2021.01.22
 * @author      minam
 * @description response wrapper
 **********************************************************************************************************************/
public class ResponseWrapper extends ContentCachingResponseWrapper {

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public String getBody() {
        return ArrayUtils.isNotEmpty(getContentAsByteArray()) && StringUtils.containsAny(getContentType(), MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE) ? Strings.fromUTF8ByteArray(getContentAsByteArray()) : null;
    }

    public Collection<String> getHeaderKeys() {
        return getHeaderNames();
    }
    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        getHeaderNames().forEach(key -> headers.put(key, getHeader(key)));
        return headers;
    }
}
