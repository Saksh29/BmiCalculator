import java.io.IOException;
import java.util.List;

import CandidateProcessor.Candidate;
import CandidateProcessor.CandidateFileReader;
import CandidateProcessor.CandidateFileWriter;
import Logger.Logger;


public class Test {
    public static void main(String[] args) {
        try {
            String inputFolderPath = "input";
            CandidateFileReader reader = new CandidateFileReader();
            List<Candidate> allCandidates = reader.processAllFiles(inputFolderPath); // Process all input files

            // Check if any valid candidates exist after validation
            if (allCandidates.isEmpty()) {
                System.err.println("No valid candidates found.");
                return;
            }

            // Write male and female candidates separately
            CandidateFileWriter writer = new CandidateFileWriter();
            writer.writeMaleFemaleOutput(allCandidates);

            // Write all candidates in sorted order by BMI
            writer.writeSortedOutput(allCandidates);

            Logger.log("BMI calculations completed successfully! " + allCandidates.size() + " candidates processed.");

        } catch (IOException e) {
            Logger.log("File error: " + e.getMessage());
        }
    }
}
