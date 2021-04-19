package no.hvl.dat109.servlet;

import no.hvl.dat109.EAO.UserEAO;
import no.hvl.dat109.constants.WebPatterns;
import no.hvl.dat109.helpers.LoginHelper;
import no.hvl.dat109.helpers.PhoneNumberHelper;
import no.hvl.dat109.helpers.SessionHelper;
import no.hvl.dat109.helpers.TwilloHelper;
import no.hvl.dat109.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SignupServlet", urlPatterns = WebPatterns.SIGNUP)
public class SignupServlet extends HttpServlet {

	@EJB
	private UserEAO userEAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String countryCode = request.getParameter("countryCode");
		String phoneNumber = request.getParameter("phoneNumber");

		String phone = String.format("+%s%s", countryCode, phoneNumber);

		if(PhoneNumberHelper.phoneNumberValid(phoneNumber)) {
			User user = new User(phone, false, false, false);
			userEAO.addUser(user);

			TwilloHelper.sendPinMessage(user);

			response.sendRedirect(WebPatterns.LOGIN);

		} else {
			request.setAttribute("SignupMessage", "Invalid username");
			request.getRequestDispatcher("WEB-INF/signup.jsp").forward(request, response);
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO: session checker, se om brukeren burde bli redirectet med en gang
		User user = LoginHelper.getUser(request, userEAO);

		if (user == null) {
			request.getRequestDispatcher("WEB-INF/signup.jsp").forward(request, response);

		} else {
			response.sendRedirect(String.format(".%s", LoginHelper.getUserRedirectSlug(user)));
		}
	}
}
