package com.polykhel.search;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            String fileName = args[1];
            Repository repo = new Repository(fileName);

            do {
                System.out.println("=== Menu ====");
                System.out.println("1. Find a person");
                System.out.println("2. Print all people");
                System.out.println("0. Exit");
                int cmd = Integer.parseInt(scanner.nextLine());

                switch (cmd) {
                    case 0:
                        return;
                    case 1:
                        System.out.println("Select a matching strategy: ALL, ANY, NONE");
                        String strategy = scanner.nextLine();
                        repo.setSearchStrategy(selectSearchStrategy(strategy));

                        System.out.println("Enter a name or email to search all suitable people.");
                        String searchTerm = scanner.nextLine();

                        List<String> found = repo.search(searchTerm);
                        if (found.isEmpty()) {
                            System.out.println("No matching people found.");
                        } else {
                            System.out.println(found.size() + " persons found:");
                            System.out.println(String.join("\n", found));
                        }
                        break;
                    case 2:
                        repo.printAll();
                        break;
                }
            } while (true);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    private static SearchStrategy selectSearchStrategy(String option) {
        switch (option) {
            case "ALL":
                return new SearchAll();
            case "ANY":
                return new SearchAny();
            case "NONE":
                return new SearchNone();
            default:
                return null;
        }
    }
}
