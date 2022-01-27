package main;

import DrivingManagement.DriverAssignment;

import java.util.Scanner;
import main.MainRun;
public class SortandDistance {
    public static void statisticalDistance() {
        for (DriverAssignment driverAssignment : MainRun.driverAssignments) {
            System.out.println("Thống kê khoảng cách cho lái xe " + driverAssignment.getDriver().getName());
            System.out.println(driverAssignment.getDistanceSum());
        }
    }

    public static void sortAssignmentList() {
        boolean check = true;
        if (MainRun.driverAssignments == null || MainRun.driverAssignments.size() == 0) {
            System.out.println("Bạn cần nhập danh sách lái xe và ");
            return;
        }
        do {
            int sortChoice = 0;
            System.out.println("---------- SẮP XẾP DANH SÁCH PHÂN CÔNG LÁI XE    ---------");
            System.out.println("1. Theo tên họ tên lái xe");
            System.out.println("2. Theo số lượng tuyến đảm nhận");
            System.out.println("3. Thoát chức năng sắp xếp.");
            System.out.print("Xin mời chọn chức năng: ");
            do {
                try {
                    sortChoice = new Scanner(System.in).nextInt();
                    check = true;
                } catch (Exception e) {
                    System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                    check = false;
                    continue;
                }
                if (sortChoice < 1 || sortChoice > 3) {
                    System.out.print("Nhập trong khoảng từ 1 đến 3! Nhập lại: ");
                    check = false;
                }
            } while (!check);
            switch (sortChoice) {
                case 1:
                    sortByDiverName();
                    break;
                case 2:
                    sortByBusLineNumber();
                    break;
                case 3:
                    return;
            }
        } while (true);
    }

    public static void sortByBusLineNumber() {
        for (int i = 0; i < MainRun.driverAssignments.size(); i++) {
            for (int j = i + 1; j < MainRun.driverAssignments.size(); j++) {
                if (MainRun.driverAssignments.get(i).getBusLineSum() < MainRun.driverAssignments.get(j).getBusLineSum()) {
                    DriverAssignment tmp = MainRun.driverAssignments.get(i);
                    MainRun.driverAssignments.set(i, MainRun.driverAssignments.get(j));
                    MainRun.driverAssignments.set(j, tmp);
                }
            }
        }
        for (DriverAssignment driverAssignment : MainRun.driverAssignments) {
            System.out.println(driverAssignment);
        }
    }

    public static void sortByDiverName() {
        for (int i = 0; i < MainRun.driverAssignments.size(); i++) {
            for (int j = i + 1; j < MainRun.driverAssignments.size(); j++) {
                if (MainRun.driverAssignments.get(i).getDriver().getName().compareTo(MainRun.driverAssignments.get(j).getDriver().getName()) > 0) {
                    DriverAssignment tmp = MainRun.driverAssignments.get(i);
                    MainRun.driverAssignments.set(i, MainRun.driverAssignments.get(j));
                    MainRun.driverAssignments.set(j, tmp);
                }
            }
        }
        for (DriverAssignment driverAssignment : MainRun.driverAssignments) {
            System.out.println(driverAssignment);
        }
    }

}
