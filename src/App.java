import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        /**
         * 1- Rastgele 30 soru
         * 2- Rastgele 50 soru
         * 3- Rastgele 100 soru
         * 4- Rastgele soru
         * 5- Son 10 Yanlış Bilinen Soru
         * 6- Son 30 Yanlış Bilinen Soru
         * 7- Son 50 Yanlış Bilinen Soru
         * 8- Rastgele Yanlış Bilinen Soru
         * 9- Rastgele 30 Yanlış Bilinen Soru
         * 10- Rastgele 50 Yanlış Bilinen Soru
         * e- Kelime Ekle
         * s- Kelime Sil
         * o- Öğrenilen Son 10 Kelime
         * y- Öğrenilen Son 20 Kelime
         * k- Öğrenilen Son 40 Kelime
         * d- Yanlış Bilinen Kelimeleri Sıfırla
         */
        /**Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String url = "jdbc:sqlite:C:/Users/Kürşat/Desktop/English Word Test/wordlist.db";
        String query = "SELECT * FROM wordlist";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to English Word Test App");
        System.out.println("" +
                "               İşlem Menüsü\n" +
                "         * 1- Rastgele 30 soru\n" +
                "         * 2- Rastgele 50 soru\n" +
                "         * 3- Rastgele 100 soru\n" +
                "         * 4- Rastgele soru\n" +
                "         * 5- Son 10 Yanlış Bilinen Soru\n" +
                "         * 6- Son 30 Yanlış Bilinen Soru\n" +
                "         * 7- Son 50 Yanlış Bilinen Soru\n" +
                "         * 8- Rastgele Yanlış Bilinen Soru\n" +
                "         * 9- Rastgele 30 Yanlış Bilinen Soru\n" +
                "         * 10- Rastgele 50 Yanlış Bilinen Soru\n" +
                "         * e- Kelime Ekle\n" +
                "         * s- Kelime Sil\n" +
                "         * o- Öğrenilen Son 10 Kelime\n" +
                "         * y- Öğrenilen Son 20 Kelime\n" +
                "         * k- Öğrenilen Son 40 Kelime\n" +
                "         * d- Yanlış Bilinen Kelimeleri Sıfırla");
        System.out.println("Lütfen İşlem Giriniz: ");
        String islem = scanner.nextLine();
        switch (islem) {
            case "1":
                try {
                    Random randomNumber = new Random();
                    List<Integer> randomNumberList = new ArrayList<>();

                    connection = DriverManager.getConnection(url);
                    preparedStatement = connection.prepareStatement("SELECT COUNT(id) FROM wordlist;");
                    resultSet = preparedStatement.executeQuery();

                    while(randomNumberList.size() < 5) {
                        int id = randomNumber.nextInt(resultSet.getInt(1));
                        if (randomNumberList.contains(id) == false) {
                            randomNumberList.add(id);
                        }
                    }
                    for (int i: randomNumberList) {
                        System.out.println(i);
                    }

                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
        }

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.print(resultSet.getString("spelling") + ": ");
                System.out.print(resultSet.getString("meaning"));
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/


        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            AppInterface appInterface = new AppInterface();
            appInterface.setVisible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }




























    }
}
