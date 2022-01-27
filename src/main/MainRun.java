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

public class MainRun {
    public static List<Driver> drivers = new ArrayList<>();
    public static List<BusLine> busLines = new ArrayList<>();
    public static List<DriverAssignment> driverAssignments = new ArrayList<>();
    public static List<Integer> checkDriverID = new ArrayList<>();
    public static List<Integer> checkBusLineID = new ArrayList<>();

    public static BuslineService buslineService = new BuslineService();
    public static DriverService driverService = new DriverService();
    public static DriveAssignmentService driverAssignmentService = new DriveAssignmentService();
    public static SortandDistance sortandDistance = new SortandDistance();


    public static void main(String[] args) {
        System.out.println("-------------Danh sách lái xe----------");
        drivers = (List<Driver>) FileUtil.readDataFromFile("driverList.txt");
        System.out.println(drivers);
        System.out.println("-------------Danh sách tuyến đường-------------");
        busLines = (List<BusLine>) FileUtil.readDataFromFile("busLineList.txt");
        System.out.println(busLines);
        System.out.println("______________Danh sách phân công______________");
        driverAssignments = (List<DriverAssignment>) FileUtil.readDataFromFile("driverAssignmentList.txt");
        System.out.println(driverAssignments);
        menu();
    }
    private static int functionChoice() {
        System.out.println("-----QUẢN LÝ PHÂN CÔNG LÁI XE BUS-------");
        System.out.println("1.Nhập danh sách lái xe");
        System.out.println("2.Nhập danh sách tuyến đường");
        System.out.println("3.Danh sách phân công lái xe");
        System.out.println("4.Sắp xếp danh sách phân công");
        System.out.println("5.Kê khai tổng khoảng cách chay xe cho mỗi lái xe");
        System.out.println("6.Thoát");
        System.out.print("Nhập sự lựa chọn của bạn: ");
        int functionChoice = 0;
        boolean checkFunction = true;
        do {
            try {
                functionChoice = new Scanner(System.in).nextInt();
                checkFunction = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                continue;
            }
            if (functionChoice <= 0 || functionChoice > 6) {
                System.out.print("Nhập số trong khoảng từ 1 đến 6! Nhập lại: ");
                checkFunction = false;
            } else break;
        } while (!checkFunction);
        return functionChoice;
    }

    private static void menu() {
        do {
            int function = functionChoice();
            switch (function) {
                case 1:
                    driverService.createNewDriver();
                    break;
                case 2:
                    buslineService.createNewBusLine();
                    break;
                case 3:
                    driverAssignmentService.assignmentList();
                    break;
                case 4:
                    sortandDistance.sortAssignmentList();
                    break;
                case 5:
                    sortandDistance.statisticalDistance();
                    break;
                case 6:
                    System.exit(0);
            }
        } while (true);
    }



}
