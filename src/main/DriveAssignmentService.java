package main;

import DrivingManagement.BusLineList;
import DrivingManagement.DriverAssignment;
import FileIOShare.DataUtil;
import FileIOShare.FileUtil;
import entity.BusLine;
import entity.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.MainRun;

public class DriveAssignmentService {

    public static boolean isValidDiverAndBusLine() {
        return !DataUtil.isEmptyCollection(MainRun.drivers) && !DataUtil.isEmptyCollection(MainRun.busLines);
    }

    public static void assignmentList() {
        if (!isValidDiverAndBusLine()) {
            System.out.println("Cần nhập danh sách lái xe và tuyến đường trước khi phân công");
            return;
        }
        boolean check = true;
        System.out.println("Nhập số lượng lái xe muốn phân công: ");
        int driverNumber = 0;
        do {
            try {
                driverNumber = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài chữ:");
                check = false;
                continue;
            }
            if (driverNumber <= 0 || driverNumber > MainRun.drivers.size()) {
                System.out.println("Số lượng lái xe muốn phân công phải lớn hơn 0 và nhỏ hơn tổng lái xe: ");
                check = false;
            }
        } while (!check);
        for (int i = 0; i < driverNumber; i++) {
            int countBusLine = 0;
            float distance = 0;
            MainRun.checkBusLineID = new ArrayList<>();
            System.out.println("Nhập id lái xe thứ " + (i + 1) + " muốn phân công: ");
            Driver driver = inputDriverID();
            int tmp = 0;
            assert driver != null;
            System.out.println("Phân công cho lái xe " + driver.getName());
            System.out.println("Nhập số tuyến đường lái xe " + driver.getName() + " được phân công ");
            int busLineNumber = inputBusLineNumber();

            List<BusLineList> busLineLists = new ArrayList<>();
            for (int j = 0; j < busLineNumber; j++) {
                countBusLine++;
                System.out.println("Nhập id tuyến đường thứ " + (j + 1) + " mà lái xe " + driver.getName() + " được phân công: ");
                BusLine busLine = inputBusLineID();
                System.out.println(busLine);
                System.out.println("Nhập số lượt lái xe " + driver.getName() + " đi tuyến đường này: ");
                int turnNumber = 0;
                do {
                    try {
                        turnNumber = new Scanner(System.in).nextInt();
                        check = true;
                    } catch (Exception e) {
                        System.out.println("Không được có ký tự khác ngoài số! Nhập lại: ");
                        check = false;
                        continue;
                    }
                    if (turnNumber < 0) {
                        System.out.print("Số lượt lái phải lớn hơn 0! Nhập lại: ");
                        check = false;
                        continue;
                    }
                    tmp += turnNumber;
                    distance += busLine.getDistance() * turnNumber;
                    if (tmp > 15) {
                        tmp = 0;
                        System.out.println("Tổng lượt lái của lái xe lớn hơn 15! Nhập lại: ");
                        check = false;
                    }
                } while (!check);
                busLineLists.add(new BusLineList(busLine, tmp));
            }
            DriverAssignment driverAssignment = new DriverAssignment(driver, busLineLists);
            driverAssignment.setBusLineSum(countBusLine);
            driverAssignment.setDistanceSum(distance);
            MainRun.driverAssignments.add(driverAssignment);
        }
        for (DriverAssignment driverAssignment : MainRun.driverAssignments) {
            System.out.println(driverAssignment);
        }
        FileUtil.writeDataToFile(MainRun.driverAssignments, "diverAssignmentList.txt");
    }

    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    public static Driver inputDriverID() {
        int tmpID = 0;
        boolean check = true;
        Driver driver = null;
        do {
            try {
                tmpID = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("không được có ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            driver = searchDriverId(tmpID);
            if (isEmpty(driver)) {
                System.out.print("Không có id lái xe vừa nhập! Nhập lại: ");
                check = false;
            }
            for (Integer integer : MainRun.checkDriverID) {
                if (integer == tmpID) {
                    System.out.println("Lái xe đã tồn tại! Nhập lại: ");
                    check = false;
                    break;
                }
            }
        } while (!check);
        MainRun.checkDriverID.add(tmpID);
        System.out.println(MainRun.checkDriverID);
        return driver;
    }

    public static Driver searchDriverId(int tmpID) {
        for (Driver driver : MainRun.drivers) {
            if (driver.getId() == tmpID) {
                return driver;
            }
        }
        return null;
    }

    public static BusLine inputBusLineID() {
        int tmpID = 0;
        boolean check = true;
        BusLine busLine = null;
        do {
            try {
                tmpID = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("không được có ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            busLine = searchRouteId(tmpID);
            if (isEmpty(busLine)) {
                System.out.print("Không có id tuyến đường vừa nhập! Nhập lại: ");
                check = false;
            }
            for (Integer integer : MainRun.checkBusLineID) {
                if (integer == tmpID) {
                    System.out.println("Tuyến đường đã tồn tại! Nhập lại: ");
                    check = false;
                    break;
                }
            }
        } while (!check);
        MainRun.checkBusLineID.add(tmpID);
        System.out.println(MainRun.checkBusLineID);
        return busLine;
    }

    public static BusLine searchRouteId(int tmpID) {
        for (BusLine busLine : MainRun.busLines) {
            if (busLine.getId() == tmpID) {
                return busLine;
            }
        }
        return null;
    }

    public static int inputBusLineNumber() {
        int BusLineNumber = 0;
        boolean check = true;
        do {
            try {
                BusLineNumber = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (BusLineNumber < 0 || BusLineNumber > MainRun.busLines.size()) {
                System.out.print("Tuyến đường không được nhỏ hơn 0 và lớn hơn tổng số tuyến! Nhập lại: ");
                check = false;
            }
        } while (!check);
        return BusLineNumber;
    }
}
