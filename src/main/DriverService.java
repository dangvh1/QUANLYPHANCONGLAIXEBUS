package main;

import FileIOShare.FileUtil;
import entity.Driver;
import main.MainRun;
import java.util.Scanner;

public class DriverService {
    public static void createNewDriver() {
        System.out.println("Nhập số lượng lái xe: ");
        int countDriver = 0;
        boolean check = true;
        do {
            try {
                countDriver = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (countDriver <= 0) {
                System.out.println("Số lượng lái xe phải lớn hơn 0! Nhập lại");
                check = false;
            }
        } while (!check);
        for (int i = 0; i < countDriver; i++) {
            Driver driver = new Driver();
            driver.inputInfo();
            MainRun.drivers.add(driver);
        }
        for (Driver driver : MainRun.drivers) {
            System.out.println(driver);
        }
        FileUtil.writeDataToFile(MainRun.drivers, "driverList.txt");
    }
}
