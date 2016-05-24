package de.iks.hotischeck.parsingData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	private int maxLength = 0;
	private String[] headlines = { "Last", "First", "Salary" };
	
	public static void main (String[] args) throws IOException {
		Main app = new Main();
		String[][] seperatedData = app.seperateData(app.readBigTextFile());
		app.printDataSets(seperatedData);
	}
	
	public List<String> readBigTextFile() throws IOException {
		List<String> lines = new ArrayList<>();
		
		Path path = Paths.get("C:/Users/iho/Desktop/test.txt");
		try (Scanner scanner = new Scanner(path, StandardCharsets.UTF_8.name())) {
			while(scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}
		}
		return lines;
	}
	
	public String[][] seperateData(List<String> lines) {
		String[][] data = new String[lines.size()][this.headlines.length];
		
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < this.headlines.length; j++) {
				data[i][j] = lines.get(i).split(",")[j];
				if (data[i][j].length() > this.maxLength) {
					this.maxLength = data[i][j].length(); 
				}
			}
		}
		return data;
	}
	
	public void printHeadlines() {
		
		for (String headline : headlines) {
			this.printDataWithSpaces(headline);
		}
		System.out.print("\n");
		
		for (int i = 0; i <= this.maxLength * this.headlines.length; i++) {
			System.out.print("-");
		}
		System.out.print("\n");
	}
	
	public void printDataSets(String[][] data) {
		this.printHeadlines();
		for (String[] line : data) {
			for (String entry : line) {
				this.printDataWithSpaces(entry);
			}
			System.out.print("\n");
		}
	}
	
	public void printDataWithSpaces(String data) {
		System.out.print(data);
		for (int i = 0; i < this.maxLength + 1 - data.length(); i++) {
			System.out.print(" ");
		}
	}
	
}



