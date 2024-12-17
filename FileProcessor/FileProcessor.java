package FileProcessor;
import java.io.*;
import java.util.*;
import CandidateProcessor.*;
import CustomException.*;

public class FileProcessor {
    
    private static final String INPUT_FOLDER = "../input";
    private static final String OUTPUT_FOLDER = "output/";
    
    public void processAllFiles() throws IOException {
        File inputDir = new File(INPUT_FOLDER);
        File[] files = inputDir.listFiles((dir, name) -> name.endsWith(".csv")); // Filter only CSV files
        
        if (files == null || files.length == 0) {
            System.err.println("No files found in the input directory.");
            return;
        }

        List<Candidate> allCandidates = new ArrayList<>();
        
        for (File file : files) {
            System.out.println("Processing file: " + file.getName());
            CandidateFileReader reader = new CandidateFileReader(file.getPath());
            try {
                List<Candidate> candidates = reader.candidateReader();
                allCandidates.addAll(candidates);
            } catch (ValidationException e) {
                logError("Error processing file: " + file.getName() + " - " + e.getMessage());
            }
        }

        // Sort candidates by BMI
        allCandidates.sort(Comparator.comparingDouble(Candidate::calculateBMI));
        
        // Write the sorted output to a single file
        writeSortedOutput(allCandidates);
    }

    private void writeSortedOutput(List<Candidate> candidates) throws IOException {
        CandidateFileWriter writer = new CandidateFileWriter(OUTPUT_FOLDER + "candidate_bmi_sorted.csv", candidates);
        writer.candidateWriter();
    }

    private void logError(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FOLDER + "unprocess.txt", true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to unprocess.txt: " + e.getMessage());
        }
    }
}
