import java.util.Scanner;
import java.sql.*;

class Donor {
    String DonorName;
    String BloodGroup;
    int DonorID;
    int BloodBankID; // Foreign key

    Donor(String DonorName, String BloodGroup, int DonorID, int BloodBankID) {
        this.DonorName = DonorName;
        this.BloodGroup = BloodGroup;
        this.DonorID = DonorID;
        this.BloodBankID = BloodBankID;
    }
}

class Patient {
    String PatientName;
    String BloodGroupP;
    int PatientID;
    int HospitalID; // Foreign key

    Patient(String PatientName, String BloodGroupP, int PatientID, int HospitalID) {
        this.PatientName = PatientName;
        this.BloodGroupP = BloodGroupP;
        this.PatientID = PatientID;
        this.HospitalID = HospitalID;
    }
}

class Hospital {
    String NameH;
    int HospitalID; // To store the inserted ID

    Hospital(String NameH, int HospitalID) {
        this.NameH = NameH;
        this.HospitalID = HospitalID;
    }
}

class BloodBank {
    String NameB;
    int BloodBankID; // To store the inserted ID

    BloodBank(String NameB, int BloodBankID) {
        this.NameB = NameB;
        this.BloodBankID = BloodBankID;
        System.out.println("\nBlood Bank System ");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/BloodBankDB";
        String user = "root";       // replace with your MySQL username
        String password = "vinay"; // replace with your MySQL password

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to Database.");

            // BloodBank Input
            System.out.print("Enter BloodBank Name: ");
            String BloodBankName = sc.nextLine();
            String bloodBankSQL = "INSERT INTO BloodBank(Name) VALUES (?)";
            PreparedStatement bankStmt = con.prepareStatement(bloodBankSQL, Statement.RETURN_GENERATED_KEYS);
            bankStmt.setString(1, BloodBankName);
            bankStmt.executeUpdate();
            ResultSet bankKeys = bankStmt.getGeneratedKeys();
            int bloodBankID = 0;
            if (bankKeys.next()) bloodBankID = bankKeys.getInt(1);
            BloodBank BB = new BloodBank(BloodBankName, bloodBankID);

            // Donor Input
            System.out.print("Donor ID: ");
            int DonorID = sc.nextInt();
            sc.nextLine(); // consume leftover newline
            System.out.print("Enter Donor Name: ");
            String DonorName = sc.nextLine();
            System.out.print("Enter Donor Blood Group: ");
            String BloodGroup = sc.nextLine();
            Donor donor = new Donor(DonorName, BloodGroup, DonorID, bloodBankID);

            // Insert Donor with BloodBankID
            String donorSQL = "INSERT INTO Donor(DonorID, DonorName, BloodGroup, BloodBankID) VALUES (?, ?, ?, ?)";
            PreparedStatement donorStmt = con.prepareStatement(donorSQL);
            donorStmt.setInt(1, DonorID);
            donorStmt.setString(2, DonorName);
            donorStmt.setString(3, BloodGroup);
            donorStmt.setInt(4, bloodBankID);
            donorStmt.executeUpdate();

            // Hospital Input
            System.out.print("Enter Hospital Name: ");
            String HospitalName = sc.nextLine();
            String hospitalSQL = "INSERT INTO Hospital(NameH) VALUES (?)";
            PreparedStatement hospitalStmt = con.prepareStatement(hospitalSQL, Statement.RETURN_GENERATED_KEYS);
            hospitalStmt.setString(1, HospitalName);
            hospitalStmt.executeUpdate();
            ResultSet hospitalKeys = hospitalStmt.getGeneratedKeys();
            int hospitalID = 0;
            if (hospitalKeys.next()) hospitalID = hospitalKeys.getInt(1);
            Hospital hospital = new Hospital(HospitalName, hospitalID);

            // Patient Input
            System.out.print("Patient ID: ");
            int PatientID = sc.nextInt();
            sc.nextLine(); // consume leftover newline
            System.out.print("Enter Patient Name: ");
            String PatientName = sc.nextLine();
            System.out.print("Enter Patient Blood Group: ");
            String BloodGroupP = sc.nextLine();
            Patient pc = new Patient(PatientName, BloodGroupP, PatientID, hospitalID);

            // Insert Patient with HospitalID
            String patientSQL = "INSERT INTO Patient(PatientID, PatientName, BloodGroupP, HospitalID) VALUES (?, ?, ?, ?)";
            PreparedStatement patientStmt = con.prepareStatement(patientSQL);
            patientStmt.setInt(1, PatientID);
            patientStmt.setString(2, PatientName);
            patientStmt.setString(3, BloodGroupP);
            patientStmt.setInt(4, hospitalID);
            patientStmt.executeUpdate();

            // Display Details
            System.out.println("\n=== Details ===");
            System.out.println("BloodBank: " + BB.NameB + " | ID: " + BB.BloodBankID);
            System.out.println("Donor ID: " + DonorID + "\tDonor: " + donor.DonorName + "\tBlood Group: " + donor.BloodGroup);
            System.out.println("Hospital: " + hospital.NameH + " | ID: " + hospital.HospitalID);
            System.out.println("Patient ID: " + PatientID + "\tPatient: " + pc.PatientName + "\tBlood Group: " + pc.BloodGroupP);

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}
