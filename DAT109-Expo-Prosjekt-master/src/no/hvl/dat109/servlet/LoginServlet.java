package no.hvl.dat109.servlet;

import no.hvl.dat109.EAO.UserEAO;
import no.hvl.dat109.constants.WebPatterns;
import no.hvl.dat109.helpers.LoginHelper;
import no.hvl.dat109.helpers.SessionHelper;
import no.hvl.dat109.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = WebPatterns.LOGIN)
public class LoginServlet extends HttpServlet {

	@EJB
	private UserEAO userEAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phoneNumber = request.getParameter("username");
		String password = request.getParameter("password");
		String countryCode = request.getParameter("countryCode");
		String phone = String.format("+%s%s", countryCode, phoneNumber);

		User user = userEAO.findUserByUsername(phone);

		//TODO: erstatt: bruker.getPin().equals(passord); med : PassordUtil.sjekkPassord(passord, bruker.getPin()
		// etter at kryptering er implementert ved lagring av passord
		if (LoginHelper.checkUser(user, password)) {
		    LoginHelper.verify(user, userEAO);
			LoginHelper.setUser(request, user);

			response.sendRedirect(String.format(".%s", LoginHelper.getUserRedirectSlug(user)));

		} else {
			request.setAttribute("loginMessage", "Invalid login");
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO: session checker, se om brukeren burde bli redirectet med en gang
		User user = LoginHelper.getUser(request, userEAO);

		if (user == null) {
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);

		} else {
			response.sendRedirect(String.format(".%s", LoginHelper.getUserRedirectSlug(user)));
		}
	}
}
