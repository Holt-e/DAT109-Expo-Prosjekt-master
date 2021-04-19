package no.hvl.dat109.helpers;

import no.hvl.dat109.EAO.UserEAO;
import no.hvl.dat109.constants.Regex;
import no.hvl.dat109.constants.WebPatterns;
import no.hvl.dat109.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Checks if a user is logged in, logs a user in if they are not.
 */
public class LoginHelper {

	public static User getUser(HttpServletRequest request, UserEAO userEAO) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            User targetUser = (User) session.getAttribute("user");

            if (targetUser != null) {
                return userEAO.findUserById(targetUser.getUserId());
            }
        }

        return null;
    }

    public static void setUser (HttpServletRequest request, User user){
        HttpSession session = SessionHelper.createNewSession(request);
        session.setAttribute("user", user);
    }

    public static boolean checkUser(User user, String password) {
        return user != null && user.getPin().equals(password);
    }

    public static String getUserRedirectSlug(User user) {
        if (user.isAdmin()) {
            return WebPatterns.CONTROL_PANEL;

        } else if (user.isJury()){
            return WebPatterns.RESULTLIST;

        } else {
            return WebPatterns.SCAN;
        }
    }

    public static void verify(User user, UserEAO userEAO) {
        if(!user.isVerified()) {
            user.setVerified(true);
            userEAO.updateUser(user);
        }
    }
}
