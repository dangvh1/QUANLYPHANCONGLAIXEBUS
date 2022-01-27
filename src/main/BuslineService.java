package main;

import FileIOShare.FileUtil;
import entity.BusLine;
import main.MainRun;
import java.util.Scanner;

public class BuslineService {
    public static void createNewBusLine() {
        System.out.println("Nhập số lượng tuyến đường");
        int countBusLine = 0;
        boolean check = true;
        do {
            try {
                countBusLine = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại");
                check = false;
                continue;
            }
            if (countBusLine <= 0) {
                System.out.println("số lượng tuyến đường phải lớn hơn 0! Nhập lại:");
                check = false;
            }
        } while (!check);
        for (int i = 0; i < countBusLine; i++) {
            BusLine busLine = new BusLine();
            busLine.inputBusLineInfo();
            MainRun.busLines.add(busLine);
        }
        for (BusLine busLine : MainRun.busLines) {
            System.out.println(busLine);
        }
        FileUtil.writeDataToFile(MainRun.busLines, "busLineList.txt");
    }

}
