package CandidateProcessor;

import java.io.*;
import java.util.*;

import CustomException.ValidationException;
import InputValidation.Validation;

public class CandidateFileReader {
    private String inputFile;

    // Constructor to initialize the input file path
    public CandidateFileReader(String inputFile) {
        this.inputFile = inputFile;
    }

    public CandidateFileReader() {
        //TODO Auto-generated constructor stub
    }

    // Gets the input file path
    public String getInputFile() {
        return inputFile;
    }

    // Sets the input file path
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    // Reads the candidate data from the input file
    public List<Candidate> candidateReader() throws IOException, ValidationException {
        List<Candidate> candidates = new ArrayList<>(); // List to hold the candidates
        BufferedReader reader = new BufferedReader(new FileReader(inputFile)); // Reading from the input file
        String line;

        // Skip the header row
        reader.readLine();
        int lineNumber = 2; // Start tracking line numbers, as the first line is the header

        // Read each line from the file
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            
            // Check if each line has exactly 4 parts (phone, name, height, weight)
            if (parts.length != 5 || parts[2].trim().isEmpty() || parts[3].trim().isEmpty()) {            
                    throw new ValidationException("Invalid input format."); // Error if format is wrong
            }

            // Extract and trim each part of the line
            String phone = parts[0].trim();
            String name = parts[1].trim();
            int height = Integer.parseInt(parts[2].trim());
            int weight = Integer.parseInt(parts[3].trim());
            String gender = parts[4].trim(); // Get gender

            try {
                // Validate phone, height, and weight
                Validation.validateAll(phone, height, weight);
                
                // If validation is successful, create and add the candidate to the list
                candidates.add(new Candidate(phone, name, height, weight,gender));
            } catch (ValidationException e) {
                // If validation fails, print the error and the line number
                System.err.println("Validation errors for line " + lineNumber + ": " + e.getMessage());
            }
            lineNumber++; // Move to the next line number
        }
        reader.close(); // Close the file after reading
        return candidates; // Return the list of candidates
    }

    public List<Candidate> processAllFiles(String inputFolderPath) throws IOException {
        File inputDir = new File(inputFolderPath);
        File[] files = inputDir.listFiles((dir, name) -> name.endsWith(".csv")); // Filter only CSV files
    
        if (files == null || files.length == 0) {
            System.err.println("No files found in the input directory.");
            return Collections.emptyList(); // Return empty list if no files are found
        }
    
        List<Candidate> allCandidates = new ArrayList<>();
        
        for (File file : files) {
            System.out.println("Processing file: " + file.getName());
            CandidateFileReader reader = new CandidateFileReader(file.getPath());
            try {
                List<Candidate> candidates = reader.candidateReader(); // Read and validate each file
                allCandidates.addAll(candidates);
            } catch (ValidationException e) {
                logError(file.getName(), e.getMessage()); // Handle error logging
            }
        }
    
        return allCandidates; // Return the combined list of all candidates
    }
    
    private void logError(String fileName, String errorMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output/unprocess.txt", true))) {
            writer.write("Error in file " + fileName + ": " + errorMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to unprocess.txt: " + e.getMessage());
        }
    }
    
}
