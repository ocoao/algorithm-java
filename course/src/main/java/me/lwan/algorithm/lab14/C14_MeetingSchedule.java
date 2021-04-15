package me.lwan.algorithm.lab14;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法<br/>
 * 会议安排问题<br/>
 * 给定一系列会议数组，会议有开始，结束时间。求最多能安排多少场会议
 */
public class C14_MeetingSchedule {

    public static class Meeting {
        // 会议开始，结束时间(>0)
        public int start;
        public int end;

        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int getMaxCount(Meeting[] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a.end));
        int end = 0;
        int count = 0;
        for (Meeting m : meetings) {
            if (m.start >= end) {
                count++;
                end = m.end;
            }
        }
        return count;
    }

}
