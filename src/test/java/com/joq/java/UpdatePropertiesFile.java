package com.joq.java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class UpdatePropertiesFile {

	public static void main(String[] args) {
		String propertyName = "executionEnvironment";
		String newValue = "local"; // The new value you want to set
		String filePath = "./src/test/resources/automation_test_config.properties"; // Replace with your file path

		try {
			// Update the value directly in the properties file
			updatePropertyValueInFile(filePath, propertyName, newValue);

			String propValue = "";
			Properties props = new Properties();
			FileInputStream input = new FileInputStream(filePath);
			props.load(input);
			input.close();
			propValue = props.getProperty(propertyName);
			System.out.println("Updated value of " + propertyName + " : " + propValue);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to update the value of property in the properties file without
	 * changing the order
	 * 
	 * @author Sanoj Swaminathan
	 * @since 04-10-2023
	 * @param filePath
	 * @param propertyName
	 * @param newValue
	 * @throws IOException
	 */
	private static void updatePropertyValueInFile(String filePath, String propertyName, String newValue)
			throws IOException {
		FileInputStream input = new FileInputStream(filePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String line;
		StringBuilder updatedFileContent = new StringBuilder();

		while ((line = reader.readLine()) != null) {
			if (line.startsWith(propertyName + "=")) {
				line = propertyName + "=" + newValue;
			}
			updatedFileContent.append(line).append(System.lineSeparator());
		}
		reader.close();
		input.close();

		FileWriter writer = new FileWriter(filePath);
		writer.write(updatedFileContent.toString());
		writer.close();
	}
}
