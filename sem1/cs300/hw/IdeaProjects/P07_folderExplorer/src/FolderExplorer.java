//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Folder Explorer
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
import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FolderExplorer {

    /**
     * Takes a given File object and gets the names of all the folders and files inside it without
     * going deeper, returning them in an ArrayList. If currentDirectory is empty, an empty ArrayList
     * must be returned. In cases where currentDirectory is NOT a directory, it must throw a
     * NotDirectoryException with a descriptive message.
     *
     * @param currentDirectory
     * @return
     * @throws NotDirectoryException
     */
    public static ArrayList<String> getContents(File currentDirectory)
        throws NotDirectoryException {
        // Returns a list of the names of all files and directories in
        // the the given folder currentDirectory.
        // Throws NotDirectoryException with a description error message if
        // the provided currentDirectory does not exist or if it is not a directory
        if (!currentDirectory.isDirectory()) {
            throw new NotDirectoryException("Directory does not exist.");
        }
        ArrayList<String> list = new ArrayList<>();
        String[] arr = currentDirectory.list();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    /**
     * getDeepContents displays ALL the contents in a directory, including the contents in its subfolders.
     * @param currentDirectory
     * @return
     * @throws NotDirectoryException
     */
    public static ArrayList<String> getDeepContents(File currentDirectory) throws NotDirectoryException {
        // Recursive method that lists the names of all the files (not directories)
        // in the given directory and its sub-directories.
        // Throws NotDirectoryException with a description error message if
        // the provided currentDirectory does not exist or if it is not a directory
        if (!currentDirectory.isDirectory()) {
            throw new NotDirectoryException("Directory does not exist.");
        }
        ArrayList<String> list = new ArrayList<>();
        String[] arr = currentDirectory.list();
        File[] arr2 = currentDirectory.listFiles();
        for (int i = 0; i < arr2.length; i++) {
            if (!arr2[i].isDirectory()) {
                String tmp = arr[i];
                list.add(tmp);
            } else if (arr2[i].isDirectory()) {
                list.addAll(getDeepContents(arr2[i]));
            }
        }

        return list;
    }

    /**
     * This is the actual lookupByName method, except put in another one to help facilitate error catching.
     * @param currentDirectory
     * @param fileName
     * @return
     */
    public static String helper(File currentDirectory, String fileName) {
        String[] arr = currentDirectory.list();
        File[] arr2 = currentDirectory.listFiles();
        for (int i = 0; i < arr2.length; i++) {
            if (fileName.equals(arr[i])) {
                return arr2[i].getPath();
            } else if (arr2[i].isDirectory()) {
                String e = helper(arr2[i], fileName);
                if (e != null) {
                    return helper(arr2[i], fileName);
                }

            }
        }
        return null;
    }

    /**
     * This uses the helper method above and throws an error if the method returns wrong.
     * @param currentDirectory
     * @param fileName
     * @return
     */
    public static String lookupByName(File currentDirectory, String fileName) {
        // Searches the given directory and all of its sub-directories for
        // an exact match to the provided fileName. This method must be
        // recursive or must use a recursive private helper method to operate.
        // This method returns a path to the file, if it exists.
        // Throws NoSuchElementException with a descriptive error message if the
        // search operation returns with no results found (including the case if
        // fileName is null or currentDirectory does not exist, or was not a directory)

            if (helper(currentDirectory, fileName) != null) {
                return helper(currentDirectory, fileName);
            } else {
                throw new NoSuchElementException("no such element found");
            }

    }
    public static ArrayList<String> lookupByKey(File currentDirectory, String key) throws NotDirectoryException {
        // Recursive method that searches the given folder and its sub-directories
        // for ALL files that contain the given key in part of their name.
        // Returns An arraylist of all the names of files that match and an empty
        // arraylist when the operation returns with no results found (including
        // the case where currentDirectory is not a directory).
        if (!currentDirectory.exists() | !currentDirectory.isDirectory()) {
            throw new NotDirectoryException("Directory does not exist.");
        }
        ArrayList<String> list = new ArrayList<>();
        String[] arr = currentDirectory.list();
        File[] arr2 = currentDirectory.listFiles();
        for (int i = 0; i < arr2.length; i++) {
            if (!arr2[i].isDirectory()) {
                if (arr[i].contains(key)) {
                    list.add(arr[i]);
                }
            } else if (arr2[i].isDirectory()) {
                list.addAll(lookupByKey(arr2[i], key));
            }
        }

        return list;
    }

    /**
     * This looks up files in a specified direcottry based on its size.
     * @param currentDirectory
     * @param sizeMin
     * @param sizeMax
     * @return
     * @throws NotDirectoryException
     */
        public static ArrayList<String> lookupBySize(File currentDirectory, long sizeMin,
        long sizeMax) throws NotDirectoryException {
            // Recursive method that searches the given folder and its sub-directories
            // for ALL files whose size is within the given max and min values, inclusive.
            // Returns an array list of the names of all files whose size are within
            // the boundaries and an empty arraylist if the search operation returns
            // with no results found (including the case where currentDirectory
            // is not a directory).
            if (!currentDirectory.exists() | !currentDirectory.isDirectory()) {
                throw new NotDirectoryException("Directory does not exist.");
            }
            ArrayList<String> list = new ArrayList<>();
            String[] arr = currentDirectory.list();
            File[] arr2 = currentDirectory.listFiles();
            for (int i = 0; i < arr2.length; i++) {
                if (!arr2[i].isDirectory()) {
                    if (arr2[i].length() < sizeMax && arr2[i].length() > sizeMin ) {
                        list.add(arr[i]);
                    }
                } else if (arr2[i].isDirectory()) {
                    list.addAll(lookupBySize(arr2[i], sizeMin, sizeMax));
                }
            }

            return list;
        }
    }

