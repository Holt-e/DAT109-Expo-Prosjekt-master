package no.hvl.dat109.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * SessionHelper class for å legge til sessions til response objektet.
 */
public class SessionHelper {

    /**
     * @param request The request object where you want to create a new session serverside.
     * @param timeout Timeout time for the session.
     * @return HttpSession object, with timeout time.
     */

    public static HttpSession createNewSession(HttpServletRequest request, int timeout) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        session = request.getSession(true);
        session.setMaxInactiveInterval(timeout);

        return session;
    }

    /**
     * @param request The request object where you want to create a new session serverside.
     * @return HttpSession object, with 30 second timeout time.
     */
    public static HttpSession createNewSession(HttpServletRequest request) {
        return createNewSession(request, 604800);
    }

    /**
     * @param session HttpSession object
     * @param key Key to be set in the HttpSession object
     * @param value Value to be set in the HttpSession object
     * Sets the value if the key doesn't allready exist in the session
     */
    public static void setIfAbsent(HttpSession session, String key, Object value) {
        Object v = session.getAttribute(key);

        if (v == null) {
            session.setAttribute(key, value);
        }
    }
}