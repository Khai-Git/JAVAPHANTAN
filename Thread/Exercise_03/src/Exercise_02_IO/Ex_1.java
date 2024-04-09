package Exercise_02_IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Ex_1 {
	public static void main(String[] args) {
		randomNumber("\\randomNumber.txt");
		
		saveFile("\\randomNumber.txt", "randomNumberOutPut.txt");
	}
	
	private static void randomNumber(String file) {
		try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < 100; i++) {
                double randomNumber = Math.random() * 100;
                writer.write(String.valueOf(randomNumber) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private static void saveFile(String inputFilePath, String outputFilePath) {
        try (Scanner scanner = new Scanner(new File(inputFilePath));
             FileWriter writer = new FileWriter(outputFilePath)) {

            double[] numbers = new double[100];
            int count = 0;

            while (scanner.hasNextDouble()) {
                numbers[count] = scanner.nextDouble();
                count++;
            }
            
            Arrays.sort(numbers);
            
            for (double number : numbers) {
                writer.write(String.valueOf(number) + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
