package me.lwan.algorithm.lab;

import me.lwan.algorithm.lab14.C14_MeetingSchedule;

public class C14_MeetingScheduleTest extends BaseTest {

    private final C14_MeetingSchedule meetingSchedule = new C14_MeetingSchedule();

    @Override
    public void runTest() {
        C14_MeetingSchedule.Meeting[] meetings = generateMeetings(12, 24);
        if (getMaxCount(meetings) != meetingSchedule.getMaxCount(meetings)) {
            throw new IllegalStateException();
        }
    }

    private int getMaxCount(C14_MeetingSchedule.Meeting[] meetings) {
        return getMaxCount(meetings, 0, 0);
    }

    /**
     * 返回最多能安排的会议数量
     * @param meetings
     * @param done 已经安排了的会议数量
     * @param timeEnd 当前会议结束时间点
     * @return
     */
    private int getMaxCount(C14_MeetingSchedule.Meeting[] meetings, int done, int timeEnd) {
        if (meetings.length == 0) {
            return done;
        }
        int count = done;
        for (int i = 0; i < meetings.length; i++) {
            C14_MeetingSchedule.Meeting m = meetings[i];
            if (m.start >= timeEnd) { // 可以安排
                C14_MeetingSchedule.Meeting[] ms = copyExcept(meetings, i);
                int c = getMaxCount(ms, done + 1, m.end); // 剩下的会议中最多可安排多少场
                count = Math.max(count, c);
            }
        }
        return count;
    }

    private C14_MeetingSchedule.Meeting[] copyExcept(C14_MeetingSchedule.Meeting[] meetings, int exceptIndex) {
        C14_MeetingSchedule.Meeting[] dump = new C14_MeetingSchedule.Meeting[meetings.length - 1];
        int n = 0;
        for (int i = 0; i < meetings.length; i++) {
            if (exceptIndex == i) continue;
            dump[n++] = meetings[i];
        }
        return dump;
    }

    private C14_MeetingSchedule.Meeting[] generateMeetings(int count, int timeEnd) {
        C14_MeetingSchedule.Meeting[] meetings = new C14_MeetingSchedule.Meeting[nextInt(1, count)];
        for (int i = 0; i < meetings.length; i++) {
            int t1 = nextInt(1, timeEnd);
            int t2 = nextInt(1, timeEnd);
            if (t1 == t2) {
                t2++;
            }
            meetings[i] = new C14_MeetingSchedule.Meeting(Math.min(t1, t2), Math.max(t1, t2));
        }
        return meetings;
    }

}
