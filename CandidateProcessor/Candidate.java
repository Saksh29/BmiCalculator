package CandidateProcessor;

public class Candidate {
    private String phone;
    private String name;
    private int height; // in cm
    private int weight; // in kg
    private String gender; // New field for gender

    // Constructor to initialize the candidate's phone, name, height, weight, and gender
    public Candidate(String phone, String name, int height, int weight, String gender) {
        this.phone = phone;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.gender = gender; // Initialize gender
    }

    // Getters and setters for all fields
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Method to calculate the BMI value
    public double calculateBMI() {
        double heightInMeters = height / 100.0; // Convert height to meters
        return weight / (heightInMeters * heightInMeters); // Calculate BMI
    }

    // Method to determine the BMI category based on the calculated BMI
    public String getBMICategory(double bmi) {
        if (bmi <= 18.4) {
            return "Underweight";
        } else if (bmi > 18.4 && bmi <= 24.9) {
            return "Normal";
        } else if (bmi > 24.9 && bmi <= 39.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    // Method to return BMI category as an integer
    public int getBMI(double bmi) {
        if (bmi <= 18.4) {
            return -1; // Underweight
        } else if (bmi > 18.4 && bmi <= 24.9) {
            return 0; // Normal
        } else if (bmi > 24.9 && bmi <= 39.9) {
            return 1; // Overweight
        } else {
            return 2; // Obese
        }
    }

    // Override the toString method to display candidate details with BMI value and category
    @Override
    public String toString() {
        double bmiValue = calculateBMI();
        String bmiCategory = getBMICategory(bmiValue);
        return phone + "," + name + "," + height + "," + weight + "," + gender + "," + String.format("%.2f", bmiValue) + "," + bmiCategory + "," + getBMI(bmiValue); 
    }
}
