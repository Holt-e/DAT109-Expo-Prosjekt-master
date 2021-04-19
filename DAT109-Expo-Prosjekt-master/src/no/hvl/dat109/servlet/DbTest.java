package no.hvl.dat109.servlet;

import no.hvl.dat109.EAO.ExpoEAO;
import no.hvl.dat109.EAO.StandEAO;
import no.hvl.dat109.EAO.UserEAO;
import no.hvl.dat109.EAO.VoteEAO;
import no.hvl.dat109.model.Expo;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DbTest", urlPatterns = "/dbtest")
public class DbTest extends HttpServlet {

	@EJB
	private ExpoEAO expoEAO;

	@EJB
	private StandEAO standEAO;

	@EJB
	private UserEAO userEAO;

	@EJB
	private VoteEAO voteEAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//		LocalDateTime ldtToday = LocalDateTime.now();
		//		LocalDateTime ldtTommorow = LocalDateTime.of(2019, 3, 20, 12, 00);
		//		LocalDateTime ldtToAlt = ldtToday.plusDays(1);
		//
		//		Timestamp today = Timestamp.valueOf(ldtToday);
		//		Timestamp tomorrow = Timestamp.valueOf(ldtTommorow);
		//
		//		Expo expo = new Expo("Timetestexpo", today, tomorrow);
		//
		//		expoEAO.addExpo(expo);
		//
		//		Stand stand = new Stand("Timeteststand", expo);
		//
		//		standEAO.addStand(stand);
		List<Expo> expos = expoEAO.getAllExpos();

		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();

		expos.forEach(out::println);
		//		User user = new User("AdrianBrukeren", false, false, false);
		//
		//		userEAO.addUser(user);

		//		Vote vote = new Vote(4, user, stand);
		//
		//		voteEAO.addVote(vote);
		//
		//		System.out.println(voteEAO.findVote(user, stand));

	}
}
