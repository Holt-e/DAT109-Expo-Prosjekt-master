package no.hvl.dat109.helpers;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RequestHelper {

    /**
     * @param request The relevant request
     * @param key The key we are looking for
     * @return If the request has the given parameter
     */
    public static boolean parameterAbsent(HttpServletRequest request, String key) {
        return request.getParameter(key) == null;
    }

    /**
     * @param request The relevant request
     * @param key The key we are looking for
     * @return If the request has the given attribute
     */
    public static boolean attributeAbsent(HttpServletRequest request, String key) {
        return request.getAttribute(key) == null;
    }

    /**
     * @param request The relevenat request
     * @param key The key we want to potentially set
     * @param value The value we want to potentially set
     * Sets the key, value pair only if it is not allready set
     */
    public static void setAttributeIfAbsent(HttpServletRequest request, String key, Object value) {
        if (attributeAbsent(request, key)) {
            request.setAttribute(key, value);
        }
    }

    /**
     * @param request The relevant request
     * @param attributes The new attributes
     * Sets all the key, value pairs from the map into the request
     */
    public static void mergeAttributes(HttpServletRequest request, Map<String, Object> attributes) {
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
    }
    /**
     * @param request The relevant request
     * @param attributes The new attributes
     * Sets all the absent key, value pairs from the map into the request
     */
    public static void mergeAbsentAttributes(HttpServletRequest request, Map<String, Object> attributes) {
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (attributeAbsent(request, key)) {
                request.setAttribute(key, value);
            }
        }
    }

    /**
     * @param request The relevant request
     * @param key The key
     * @return The parameter data as UTF8
     */
    public static String getPropertyUTF8(HttpServletRequest request, String key) {
        String raw = request.getParameter(key);
        byte[] bytes = raw.getBytes(StandardCharsets.ISO_8859_1);

        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * @param request The relvant request
     * @param param The parameter key to fetch from
     * @param attr The attribute key to store at
     */
    public static void persistParamToAttr(HttpServletRequest request, String param, String attr) {
        request.setAttribute(attr, getPropertyUTF8(request, param));
    }
}
