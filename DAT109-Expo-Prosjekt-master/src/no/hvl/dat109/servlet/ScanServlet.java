/*
 * Copyright (c) 2019 Grim Thomas Hammerstad (182722@stud.hvl.no)
 * Ørjan Enes (180337@stud.hvl.no)
 * Joakim Johesan (182719@stud.hvl.no)
 * Eirik Alvestav (180339@stud.hvl.no)
 * Adrian Holte (182714@stud.hvl.no)
 * Kjetil Hunshammer (182718@stud.hvl.no)
 *
 * All Rights Reserved.
 *
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential.
 * Distribution for testing purposes is only permitted within the
 * Kronstad campus of the Western Norway University of
 * Applied Sciences (Høgskulen på Vestlandet, HVL) in Bergen, Norway.
 *
 */

package no.hvl.dat109.servlet;

import no.hvl.dat109.EAO.ExpoEAO;
import no.hvl.dat109.EAO.StandEAO;
import no.hvl.dat109.EAO.UserEAO;
import no.hvl.dat109.EAO.VoteEAO;
import no.hvl.dat109.constants.WebPatterns;
import no.hvl.dat109.helpers.LoginHelper;
import no.hvl.dat109.helpers.VotingHelper;
import no.hvl.dat109.model.Expo;
import no.hvl.dat109.model.Stand;
import no.hvl.dat109.model.User;
import no.hvl.dat109.model.Vote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ScanServlet", urlPatterns = WebPatterns.SCAN)
public class ScanServlet extends HttpServlet {

	@EJB
	private VoteEAO voteEAO;

	@EJB
	private StandEAO standEAO;

	@EJB
	private ExpoEAO expoEAO;

	@EJB
	private UserEAO userEAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = LoginHelper.getUser(request, userEAO);

        if (user != null) {
			String standName = request.getParameter("standName");
			String expoName = request.getParameter("expoName");
			int score = VotingHelper.getScore(request);

			Stand stand = standEAO.findStand(standName);
			Expo expo = expoEAO.findExpo(expoName);

			if(stand != null && expo != null && score != -1) {
				if(stand.getExpo().getExpoId() == expo.getId()) {
					if(VotingHelper.isExpoActive(expo)) {
						Vote vote = voteEAO.findVote(user, stand);

						if(vote != null) {
							// TODO - Update properly

							vote.setScore(score);
							voteEAO.updateVote(vote);

						} else {
							Vote v = new Vote(score, user, stand);
							voteEAO.addVote(v);
						}
					}
				} else {
					request.setAttribute("loginMessage", "Expo doesn't have stand");
				}

			} else {
				request.setAttribute("loginMessage", "Stand eller Expo finnes ikke");
			}
		}

		request.getRequestDispatcher("WEB-INF/scan.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = LoginHelper.getUser(request, userEAO);

        if (user == null) {
			response.sendRedirect(String.format(".%s", WebPatterns.LOGIN));

		} else {
			request.getRequestDispatcher("WEB-INF/scan.jsp").forward(request, response);
		}
	}
}
