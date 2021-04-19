package no.hvl.dat109.helpers;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Helper class for creating expos.
 */
public class expoTimeHelper {

	/**
	 * @param time "start" or "end" to get the appropriate time
	 * @param request the HttpServletRequest
	 * @return the appropriate Timestamp, or a null if the parameters were not proper
	 *
	 * Returns a Timestamp of the appropriate time
	 */
	public static Timestamp getTime(String time, HttpServletRequest request) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		if("start".equals(time)) {
			String startDate = RequestHelper.getPropertyUTF8(request, "startDate");
			String startTime = RequestHelper.getPropertyUTF8(request, "startTime");
				String sdt = startDate + " " + startTime;

				LocalDateTime startDT = LocalDateTime.parse(sdt, formatter);

				return Timestamp.valueOf(startDT);


		} else if("end".equals(time)) {
			String endDate = RequestHelper.getPropertyUTF8(request, "endDate");
			String endTime = RequestHelper.getPropertyUTF8(request, "endTime");

				String edt = endDate + " " + endTime;

				LocalDateTime endDT = LocalDateTime.parse(edt, formatter);
				return Timestamp.valueOf(endDT);

		}
		return null;
	}

}