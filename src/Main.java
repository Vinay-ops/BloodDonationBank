import java.util.Scanner;

class Donor {
    String DonorName;
    String BloodGroup;
    int DonorID;

    Donor(String DonorName, String BloodGroup, int DonorID) {
        this.DonorName = DonorName;
        this.BloodGroup = BloodGroup;
        this.DonorID = DonorID;
    }
}

class Patient {
    String PatientName;
    String BloodGroupP;
    int PatientID;

    Patient(String PatientName, String BloodGroupP, int PatientID) {
        this.PatientName = PatientName;
        this.BloodGroupP = BloodGroupP;
        this.PatientID = PatientID;
    }
}

class Hospital {
    String NameH;

    Hospital(String NameH) {
        this.NameH = NameH;
    }
}

class BloodBank {
    BloodBank() {
        System.out.println("\nBlood Bank System ");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BloodBank BB = new BloodBank();

        // Donor Input
        System.out.print("Donor ID: ");
        int DonorID = sc.nextInt();
        sc.nextLine(); // consume leftover newline
        System.out.print("Enter Donor Name: ");
        String DonorName = sc.nextLine();
        System.out.print("Enter Donor Blood Group: ");
        String BloodGroup = sc.nextLine();
        Donor donor = new Donor(DonorName, BloodGroup, DonorID);

        // Patient Input
        System.out.print("Patient ID: ");
        int PatientID = sc.nextInt();
        sc.nextLine(); // consume leftover newline
        System.out.print("Enter Patient Name: ");
        String PatientName = sc.nextLine();
        System.out.print("Enter Patient Blood Group: ");
        String BloodGroupP = sc.nextLine();
        Patient pc = new Patient(PatientName, BloodGroupP, PatientID);

        // Hospital Input
        System.out.print("Enter Hospital Name: ");
        String HospitalName = sc.nextLine();
        Hospital hospital = new Hospital(HospitalName);

        // Display Details
        System.out.println("\n=== Details ===");
        System.out.println("Donor ID: " + DonorID + "\t\tDonor: " + donor.DonorName + "\t\tBlood Group: " + donor.BloodGroup);
        System.out.println("Patient ID: " + PatientID + "\t\tPatient: " + pc.PatientName + "\t\tBlood Group: " + pc.BloodGroupP);
        System.out.println("Hospital: " + hospital.NameH);


    }
}
