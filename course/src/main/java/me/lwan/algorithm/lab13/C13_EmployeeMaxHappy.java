package me.lwan.algorithm.lab13;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工最大开心总值(选取的员工不能为直接上下级)
 */
public class C13_EmployeeMaxHappy {

    public static class Employee {
        public int happy;
        public List<Employee> members; // 直接下属
        public Employee(int happy) {
            this.happy = happy;
            this.members = new ArrayList<>();
        }
    }

    public int getMaxHappy(Employee boss) {
        Metadata md = dp(boss);
        return Math.max(md.maxHappyWithSelf, md.maxHappyWithoutSelf);
    }

    private static class Metadata {
        // 包含自己时的最大开心值
        int maxHappyWithSelf;
        // 不包含自己时的最大开心值
        int maxHappyWithoutSelf;
        Metadata(int maxHappyWithSelf, int maxHappyWithoutSelf) {
            this.maxHappyWithSelf = maxHappyWithSelf;
            this.maxHappyWithoutSelf = maxHappyWithoutSelf;
        }
    }

    private Metadata dp(Employee e) {
        if (e == null) {
            return new Metadata(0, 0);
        }
        if (e.members == null) {
            return new Metadata(e.happy, 0);
        }
        int maxHappyWithSelf = e.happy;
        int maxHappyWithoutSelf = 0;
        for (Employee m : e.members) {
            Metadata md = dp(m);
            maxHappyWithSelf += md.maxHappyWithoutSelf;
            maxHappyWithoutSelf += Math.max(md.maxHappyWithSelf, md.maxHappyWithoutSelf);
        }
        return new Metadata(maxHappyWithSelf, maxHappyWithoutSelf);
    }


}
