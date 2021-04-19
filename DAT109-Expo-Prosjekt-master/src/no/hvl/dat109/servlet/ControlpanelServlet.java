package no.hvl.dat109.servlet;

import no.hvl.dat109.EAO.ExpoEAO;
import no.hvl.dat109.EAO.StandEAO;
import no.hvl.dat109.EAO.UserEAO;
import no.hvl.dat109.constants.WebPatterns;
import no.hvl.dat109.helpers.LoginHelper;
import no.hvl.dat109.helpers.RequestHelper;
import no.hvl.dat109.helpers.expoTimeHelper;
import no.hvl.dat109.model.Expo;
import no.hvl.dat109.model.Stand;
import no.hvl.dat109.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "ControlpanelServlet", urlPatterns = WebPatterns.CONTROL_PANEL)
public class ControlpanelServlet extends HttpServlet {

	@EJB
	private StandEAO standEAO;

	@EJB
	private ExpoEAO expoEAO;

	@EJB
	private UserEAO userEAO;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Check if user is logged in
		User user = LoginHelper.getUser(request, userEAO);
		if(user == null) {
			response.sendRedirect(String.format(".%s?invalidLogin", WebPatterns.LOGIN));
		}

		if(user == null || !user.isAdmin()) {
			response.sendRedirect(String.format(".%s?invalidLogin", WebPatterns.LOGIN));

		} else {
			HttpSession session = request.getSession(false);

			String type = RequestHelper.getPropertyUTF8(request, "type");

			switch(type) {
				case "add_expo": {
					String expoName = RequestHelper.getPropertyUTF8(request, "expo");

					Timestamp startT = expoTimeHelper.getTime("start", request);
					Timestamp endT = expoTimeHelper.getTime("end", request);

					if(startT != null && endT != null) {
						Expo expo = new Expo(expoName, startT, endT);

						expoEAO.addExpo(expo);
					} else {
						session.setAttribute("aexpoErrorMessage", "One of both of the Timestamps were null, please try again.");
					}

					break;
				}
				case "delete_expo": {
					String expo = RequestHelper.getPropertyUTF8(request, "deleteExpoName");
					boolean success = expoEAO.deleteExpo(expo);
					if(!success) {
						session.setAttribute("expoErrorMessage", "Could not delete the Expo");
					}

					break;
				}
				case "add_stand": {
					String expoName = RequestHelper.getPropertyUTF8(request, "expoName");
					String standName = RequestHelper.getPropertyUTF8(request, "standServlet");

					Expo expo = expoEAO.findExpo(expoName);
					if(expo != null) {

						session.setAttribute("selectedExpo", expoName);
						Stand stand = new Stand(standName, expo);

						standEAO.addStand(stand);
						session.setAttribute("standAdded", "Stand was added successfully!");
					}
					break;
				}
				case "delete_stand":
					//TODO
					break;
				case "add_jury":

					String number = RequestHelper.getPropertyUTF8(request, "JPnumber");

					if(number.matches("^[0-9]{8}")) {
						User jury = new User("+47" + number, true, false, false);
						userEAO.addUser(jury);
					} else {
						session.setAttribute("juryErrorMessage", "The entered number was not valid, must be only 8 digits.");
					}

					break;
				case "delete_user":
					//TODO
					break;
				case "edit_user":
					//TODO
					break;
			}
			response.sendRedirect(String.format(".%s", WebPatterns.CONTROL_PANEL));
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = LoginHelper.getUser(request, userEAO);

		if(user == null || !user.isAdmin()) {
			response.sendRedirect(String.format(".%s", WebPatterns.LOGIN + "?invalidLogin"));

		} else {

			HttpSession session = request.getSession(false);
			String aexpoErrorMsg = (String) session.getAttribute("aexpoErrorMessage");
			session.removeAttribute("aexpoErrorMessage");
			String expoErrorMsg = (String) session.getAttribute("expoErrorMessage");
			session.removeAttribute("expoErrorMessage");
			String juryErrorMsg = (String) session.getAttribute("juryErrorMessage");
			session.removeAttribute("juryErrorMessage");
			String selectedExpo = (String) session.getAttribute("selectedExpo");
			List<Expo> expos = expoEAO.getAllExpos();

			request.setAttribute("aexpoErrorMessage", aexpoErrorMsg);
			request.setAttribute("juryErrorMessage", juryErrorMsg);
			request.setAttribute("expoErrorMessage", expoErrorMsg);
			request.setAttribute("selectedExpo", selectedExpo);
			request.setAttribute("expos", expos);
			request.getRequestDispatcher("WEB-INF/controlpanel.jsp").forward(request, response);
		}
	}
}
