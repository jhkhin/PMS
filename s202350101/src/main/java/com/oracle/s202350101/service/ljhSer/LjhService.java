package com.oracle.s202350101.service.ljhSer;

import java.util.HashMap;
import java.util.List;

import com.oracle.s202350101.model.Meeting;
import com.oracle.s202350101.model.PrjInfo;
import com.oracle.s202350101.model.PrjMemList;

public interface LjhService {

	PrjInfo 			getProject(int project_id);
	List<Meeting> 		getMeetingList(int project_id);
	List<Meeting> 		getMeetingRead(int meeting_id);
	List<Meeting> 		getMeetingReportList(int project_id);
	List<Meeting> 		getMeetingDate(int project_id);
	List<PrjMemList>	getPrjMember(int project_id);
	int 				insertMeetingDate(Meeting meeting);
	int 				updateMeetingReport(Meeting meeting);
	int 				deleteMeetingMember(Meeting meeting);
	int 				insertMeetingMember(Meeting meeting);
	int 				deleteMeetingReport(Meeting meeting);
	int 				meetingReportUpdate(Meeting meeting);
	int 				insertMeeting(Meeting meeting);
	int 				insertMeetingReport(Meeting meeting);
	int 				insertReport(Meeting meeting);
	List<Meeting> 		getUserMeeting(HashMap<String, String> map);


}