package no.hvl.dat109.servlet;

import no.hvl.dat109.EAO.ExpoEAO;
import no.hvl.dat109.constants.WebPatterns;
import no.hvl.dat109.helpers.RequestHelper;
import no.hvl.dat109.helpers.ResultListHelper;
import no.hvl.dat109.model.Expo;
import no.hvl.dat109.model.Stand;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ResultlistServlet", urlPatterns = WebPatterns.RESULTLIST)
public class ResultlistServlet extends HttpServlet {

	@EJB
	private ExpoEAO expoEAO;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Expo expo = expoEAO.findExpo(RequestHelper.getPropertyUTF8(request, "ExpoListItem"));
		HttpSession session = request.getSession(false);

		session.setAttribute("selectedExpo", expo.getExpoName());

		response.sendRedirect(String.format(".%s", WebPatterns.RESULTLIST));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Expo> expos = expoEAO.getAllExpos();
		request.setAttribute("expoList", expos);
		HttpSession session = request.getSession(false);

		if (false) {//må fikse noe sjekk for tilgang senere
			response.sendRedirect(String.format(".%s", WebPatterns.LOGIN));

		}else {
			if (session != null) {
				String expoName = (String) session.getAttribute("selectedExpo");
				if(expoName != null) { //putter dette inn her i if statement fordi session/cookie ikke er fikset skikkelig
					Expo expo = expoEAO.findExpo(expoName);
					List<Stand> stands = ResultListHelper.sortStandsByVotes(expo.getStands());

					request.setAttribute("stands", stands);
					request.setAttribute("selectedExpo", expoName);
				}

			}

			request.getRequestDispatcher("WEB-INF/resultlist.jsp").forward(request, response);
		}
	}
}
