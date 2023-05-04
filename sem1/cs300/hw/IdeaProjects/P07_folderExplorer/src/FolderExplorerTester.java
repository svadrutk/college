//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Folder Explorer: Tester Class
// Course:   CS 300 Fall 2021
//
// Author:   Svadrut Kukunooru
// Email:    kukunooru@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FolderExplorerTester {
    /**
     * This tests the getContents method.
     * @param folder
     * @return
     */
    public static boolean testGetContents(File folder) {
        try {
            // Scenario 1
            // list the basic contents of the cs300 folder
            ArrayList<String> listContent = FolderExplorer.getContents(folder);
            // expected output must contain "exams preparation", "grades",
            // "lecture notes", "programs", "reading notes", "syllabus.txt",
            // and "todo.txt" only.
            String[] contents = new String[] {"exams preparation", "grades",
                "lecture notes", "programs", "reading notes", "syllabus.txt",
                "todo.txt"};
            List<String> expectedList = Arrays.asList(contents);
            // check the size and the contents of the output
            if (listContent.size() != 7) {
                System.out.println("Problem detected: cs300 folder must contain 7 elements.");
                return false;
            }
            for (int i = 0; i < expectedList.size(); i++) {
                if (!listContent.contains(expectedList.get(i))) {
                    System.out.println("Problem detected: " + expectedList.get(i)
                        + " is missing from the output of the list contents of cs300 folder.");
                    return false;
                }
            }
            // Scenario 2 - list the contents of the grades folder
            File f = new File(folder.getPath() + File.separator + "grades");
            listContent = FolderExplorer.getContents(f);
            if (listContent.size() != 0) {
                System.out.println("Problem detected: grades folder must be empty.");
                return false;
            }
            // Scenario 3 - list the contents of the p02 folder
            f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
            listContent = FolderExplorer.getContents(f);
            if (listContent.size() != 1 || !listContent.contains("FishTank.java")) {
                System.out.println(
                    "Problem detected: p02 folder must contain only "
                        + "one file named FishTank.java.");
                return false;
            }
            // Scenario 4 - List the contents of a file
            f = new File(folder.getPath() + File.separator + "todo.txt");
            try {
                listContent = FolderExplorer.getContents(f);
                System.out.println("Problem detected: Your FolderExplorer.getContents() must "
                    + "throw a NotDirectoryException if it is provided an input which is not"
                    + "a directory.");
                return false;
            } catch (NotDirectoryException e) { // catch only the expected exception
                // no problem detected
            }
            // Scenario 5 - List the contents of not found directory/file
            f = new File(folder.getPath() + File.separator + "music.txt");
            try {
                listContent = FolderExplorer.getContents(f);
                System.out.println("Problem detected: Your FolderExplorer.getContents() must "
                    + "throw a NotDirectoryException if the provided File does not exist.");
                return false;
            } catch (NotDirectoryException e) {
                // behavior expected
            }
        } catch (Exception e) {
            System.out.println("Problem detected: Your FolderExplorer.getContents() has thrown"
                + " a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This tests the getDeepContents method.
     * @param folder
     * @return
     */
    public static boolean testDeepGetContentsBaseCase(File folder) {
        try {
            // Scenario 1: List the contents of a folder without any folders inside it
            ArrayList<String> listContent = FolderExplorer.getDeepContents(folder);
            // expected output must include "ExceptionHandling.txt", "proceduralProgramming.txt", and
            // "UsingObjects.txt"
            String[] contents = new String[] {"ExceptionHandling.txt", "proceduralProgramming.txt",
            "UsingObjects.txt"};
            List<String> expectedList = Arrays.asList(contents);
            // compare the sizes
            if (listContent.size() != 3) {
                System.out.println("Problem detected: unit1 folder must contain 3 elements.");
                return false;
            }
            for (int i = 0; i < expectedList.size(); i++) {
                if (!listContent.contains(expectedList.get(i))) {
                    System.out.println("Problem detected: " + expectedList.get(i)
                        + " is missing from the output of the list contents of unit1 folder.");
                    return false;
                }
            }
        } catch(Exception e) {
            System.out.println("Problem detected: Your FolderExplorer.getContents() has thrown"
                + " a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This tests the getDeepContents method.
     * @param folder
     * @return
     */
    public static boolean testDeepListRecursiveCase(File folder) {
        try {
            // Scenario 1: List the contents of a folder without any folders inside it
            ArrayList<String> listContent = FolderExplorer.getDeepContents(folder);
            // expected output must include "ExceptionHandling.txt", "proceduralProgramming.txt", and
            // "UsingObjects.txt"
            String[] contents = new String[] {"ExceptionHandling.txt", "proceduralProgramming.txt",
                "UsingObjects.txt", "CreatingClasses.txt", "Generics.txt", "Inheritance.txt", "AlgorithmAnalysis.txt", "Recursion.txt",
            "Sorting.txt"};
            List<String> expectedList = Arrays.asList(contents);
            // compare the sizes
            if (listContent.size() != 9) {
                System.out.println("Problem detected: lecture notes folder must contain 3 elements.");
                return false;
            }
            for (int i = 0; i < expectedList.size(); i++) {
                if (!listContent.contains(expectedList.get(i))) {
                    System.out.println("Problem detected: " + expectedList.get(i)
                        + " is missing from the output of the list contents of lecture notes folder.");
                    return false;
                }
            }
        } catch(Exception e) {
            System.out.println("Problem detected: Your FolderExplorer.getContents() has thrown"
                + " a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Tests the lookupByFileName method.
     * @param folder
     * @return
     */
    public static boolean testLookupByFileName(File folder) {
        try {
            String a = "cs300/syllabus.txt";
            String result = FolderExplorer.lookupByName(new File("cs300"), "syllabus.txt");
            if(!a.equals(result)) {
                return false;
            }
            a = "cs300/lecture notes/unit2/CreatingClasses.txt";
            result = FolderExplorer.lookupByName(new File("cs300"), "CreatingClasses.txt");
            if(!a.equals(result)) {
                return false;
            }
        } catch(Exception e) {
            System.out.println("Problem detected: Your FolderExplorer.getContents() has thrown"
                + " a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Tests the lookupByBaseCase method.
     * @param folder
     * @return
     */
    public static boolean testLookupByBaseCase(File folder) {
        try {
            ArrayList<String> listContent = FolderExplorer.lookupByKey(folder, ".txt");
            String[] contents = new String[] {"zyBooksCh1.txt", "zyBooksCh2.txt", "zyBooksCh3.txt", "zyBooksCh4.txt"};
            List<String> expectedList = Arrays.asList(contents);

            if (listContent.size() != 4) {
                System.out.println("Problem detected: cs300 folder must contain 4 elements.");
                return false;
            }
            for (int i = 0; i < expectedList.size(); i++) {
                if (!listContent.contains(expectedList.get(i))) {
                    System.out.println("Problem detected: " + expectedList.get(i)
                        + " is missing from the output of the list contents of cs300 folder.");
                    return false;
                }
            }
        } catch(Exception e) {
            System.out.println("Problem detected: Your FolderExplorer.getContents() has thrown"
                + " a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void main(String[] args) throws NotDirectoryException {
        System.out.println("testGetContents: " + testGetContents(new File("cs300")));
        System.out.println("testGetDeepContents: " + testDeepGetContentsBaseCase(new File("cs300" + File.separator + "lecture notes" + File.separator + "unit1")));
        System.out.println("testGetDeepRecursiveContents: " + testDeepListRecursiveCase(new File("cs300" + File.separator + "lecture notes")));
        System.out.println("testLookupByFileName: " + testLookupByFileName(new File("cs300")));
        System.out.println("testLookupByKeyBaseCase: " + testLookupByBaseCase(new File("cs300/reading notes")));
    }
}
