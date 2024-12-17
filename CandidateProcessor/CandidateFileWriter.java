package CandidateProcessor;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CandidateFileWriter {
    private String outputFile;
    private List<Candidate> candidates;

    // Constructor to initialize output file and candidate list
    public CandidateFileWriter(String outputFile, List<Candidate> candidates) {
        this.outputFile = outputFile;
        this.candidates = candidates;
    }

    public CandidateFileWriter(List<Candidate> candidates) {
        this.candidates = candidates;
    }
        // No-argument constructor
        public CandidateFileWriter() {
            // Default constructor
        }

    // Gets the output file path
    public String getOutputFile() {
        return outputFile;
    }

    // Sets the output file path
    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    // Gets the list of candidates
    public List<Candidate> getCandidates() {
        return candidates;
    }

    // Sets the list of candidates
    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    // Writes the candidate data to the output file
    public boolean candidateWriter() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile)); // Open file for writing

        // Write the header row
        writer.write("Phone,Name,Height (cm),Weight (kg),Gender,BMI , BMICategory, BMI Status");
        writer.newLine(); // Move to the next line

        // Write each candidate's information to the file
        for (Candidate candidate : candidates) {
            writer.write(candidate.toString()); // Write candidate details as a string
            writer.newLine(); // Move to the next line after each candidate
        }

        writer.close(); // Close the file after writing
        return true; // Return true to indicate the writing was successful
    }
    public void writeMaleFemaleOutput(List<Candidate> candidates) throws IOException {
    List<Candidate> males = new ArrayList<>();
    List<Candidate> females = new ArrayList<>();

    for (Candidate candidate : candidates) {
        if (candidate.getGender().equalsIgnoreCase("Male")) {
            males.add(candidate);
        } else if (candidate.getGender().equalsIgnoreCase("Female")) {
            females.add(candidate);
        }
    }

    // Write male candidates
    CandidateFileWriter maleWriter = new CandidateFileWriter("output/output_m.csv", males);
    maleWriter.candidateWriter();

    // Write female candidates
    CandidateFileWriter femaleWriter = new CandidateFileWriter("output/output_f.csv", females);
    femaleWriter.candidateWriter();
}

public void writeSortedOutput(List<Candidate> candidates) throws IOException {
    candidates.sort(Comparator.comparingDouble(Candidate::calculateBMI)); // Sort by BMI in ascending order
    CandidateFileWriter sortedWriter = new CandidateFileWriter("output/candidate_bmi_sorted.csv", candidates);
    sortedWriter.candidateWriter();
}

}
