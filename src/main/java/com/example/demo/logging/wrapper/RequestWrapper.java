package com.example.demo.logging.wrapper;

import lombok.SneakyThrows;
import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.Strings;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @since       2021.01.22
 * @author      minam
 * @description request wrapper
 **********************************************************************************************************************/
public class RequestWrapper extends ContentCachingRequestWrapper {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String getUrl() {
        return StringUtils.isBlank(getQueryString()) ? Objects.toString(getRequestURL()) : getRequestURL().append("?").append(getQueryString()).toString();
    }

    @SneakyThrows
    public String getBody() {
        return ArrayUtils.isEmpty(outputStream.toByteArray()) ? null : Strings.fromUTF8ByteArray(outputStream.toByteArray());
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        Collections.list(getHeaderNames())
                .forEach(key -> headers.put(key, getHeader(key)));
        return headers;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new ServletInputStream() {

            private TeeInputStream inputStream = new TeeInputStream(RequestWrapper.super.getInputStream(), outputStream);

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {}
        };
    }
}
