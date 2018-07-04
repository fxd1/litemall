package org.linlinjava.litemall.wx.util;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static JsonFactory jsonFactory = objectMapper.getFactory();

    public static String toJsonString(Object obj) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(out, JsonEncoding.UTF8);
            jsonGenerator.writeObject(obj);
            return new String(out.toByteArray(), "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("toJsonString exception", e);
        }
    }

    public static <T> T parseObject(String jsonStr, Class<T> clazz) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(jsonStr, clazz);
    }


}
