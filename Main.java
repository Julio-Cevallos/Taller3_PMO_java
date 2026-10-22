import java.util.*;

class Student {

    private String id;
    private String name;
    private final List<Double> grades;

    public Student(String id, String name) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del estudiante no puede estar vacio.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante no puede estar vacio.");
        }
        this.id = id.trim();
        this.name = name.trim();
        this.grades = new ArrayList<>();
    }

    public boolean addGrade(double grade) {
        if (grade >= 0.0 && grade <= 100.0) {
            grades.add(grade);
            System.out.println("[EXITO] Nota " + grade + " agregada para " + name + ".");
            return true;
        } else {
            System.out.println("[ERROR] La nota " + grade + " es invalida. Debe estar entre 0.0 y 100.0.");
            return false;
        }
    }

    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (double g : grades) {
            total += g;
        }
        return total / grades.size();
    }

    public String getLetterGrade() {
        if (grades.isEmpty()) {
            return "N/A";
        }
        double avg = calculateAverage();
        if (avg >= 90.0) {
            return "A";
        } else if (avg >= 80.0) {
            return "B";
        } else if (avg >= 70.0) {
            return "C";
        } else if (avg >= 60.0) {
            return "D";
        } else {
            return "F";
        }
    }

    public boolean isPassed() {
        return !grades.isEmpty() && calculateAverage() >= 60.0;
    }

    public boolean isOnHonorRoll() {
        return !grades.isEmpty() && calculateAverage() >= 90.0;
    }

    public boolean removeGradeByIndex(int index) {
        if (index >= 0 && index < grades.size()) {
            double removed = grades.remove(index);
            System.out.println("[EXITO] Nota " + removed + " eliminada en el índice " + index + ".");
            return true;
        } else {
            System.out.println("[ERROR] El indice " + index + " esta fuera de rango.");
            return false;
        }
    }

    public boolean removeGradeByValue(double value) {
        if (grades.remove(value)) { // Autoboxing automático sin código redundante
            System.out.println("[EXITO] Nota " + value + " eliminada.");
            return true;
        } else {
            System.out.println("[ERROR] La nota " + value + " no fue encontrada.");
            return false;
        }
    }

    public void generateReportCard() {
        double avg = calculateAverage();
        String letter = getLetterGrade();
        String status = isPassed() ? "Aprobado" : "Reprobado";
        String honor = isOnHonorRoll() ? "Si" : "No";

        System.out.println("========================================");
        System.out.println("      BOLETIN DE NOTAS DEL ESTUDIANTE   ");
        System.out.println("========================================");
        System.out.println("ID del Estudiante : " + id);
        System.out.println("Nombre            : " + name);
        System.out.println("Cantidad de Notas : " + grades.size());
        System.out.printf("Promedio          : %.2f%n", avg);
        System.out.println("Calificacion      : " + letter);
        System.out.println("Estado            : " + status);
        System.out.println("Cuadro de Honor   : " + honor);
        System.out.println("========================================");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Double> getGrades() {
        return Collections.unmodifiableList(grades);
    }
}

public class Main {

    public static void main() {
        try {
            Student student = new Student("STU-2025", "Carlos Perez");

            student.addGrade(95.0);
            student.addGrade(88.5);
            student.addGrade(92.0);

            student.addGrade(-10.0);
            student.addGrade(110.0);

            student.removeGradeByIndex(9); 
            student.removeGradeByValue(88.5); 

            student.generateReportCard();

        } catch (IllegalArgumentException e) {
            System.out.println("[FATAL] Error de inicialización: " + e.getMessage());
        }
    }
}