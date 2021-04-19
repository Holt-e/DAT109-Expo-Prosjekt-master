package no.hvl.dat109.helpers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {

    public void addSessionCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value); // URL-encoding of value
        cookie.setMaxAge(30); // 30 sekunds timer on the cookie
        response.addCookie(cookie);
    }

    public void addPersistentCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value); // URL-encoding of value
        response.addCookie(cookie);
    }

    public String getCookieFromRequest(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                  return cookie.getValue(); // URL-encoding of value
                }
            }
        }
        return null;
    }

    public void deleteSessionCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, "");//deleting value of cookie
        cookie.setMaxAge(0);//changing the maximum age to 0 seconds
        response.addCookie(cookie);//adding cookie in the response
    }
    public void deletePersistentCookie(HttpServletResponse response,String name) {
        Cookie cookie = new Cookie(name, "");//deleting value of cookie
        response.addCookie(cookie);//adding cookie in the response
    }
}
