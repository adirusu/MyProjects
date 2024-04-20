import java.util.List;
import java.util.Scanner;

class Main {
    private static void showCommands() {
        System.out.println("help         - Afiseaza aceasta lista de comenzi");
        System.out.println("add          - Adauga o noua persoana (inscriere)");
        System.out.println("check        - Verifica daca o persoana este inscrisa la eveniment");
        System.out.println("remove       - Sterge o persoana existenta din lista");
        System.out.println("update       - Actualizeaza detaliile unei persoane");
        System.out.println("guests       - Lista de persoane care participa la eveniment");
        System.out.println("waitlist     - Persoanele din lista de asteptare");
        System.out.println("available    - Numarul de locuri libere");
        System.out.println("guests_no    - Numarul de persoane care participa la eveniment");
        System.out.println("waitlist_no  - Numarul de persoane din lista de asteptare");
        System.out.println("subscribe_no - Numarul total de persoane inscrise");
        System.out.println("search       - Cauta toti invitatii conform sirului de caractere introdus");
        System.out.println("save         - Salveaza lista cu invitati");
        System.out.println("restore      - Completeaza lista cu informatii salvate anterior");
        System.out.println("reset        - Sterge informatiile salvate despre invitati");
        System.out.println("quit         - Inchide aplicatia");
    }

    private static void addNewGuest(Scanner sc, GuestsList list) {
        // TO DO:
        String lastName = sc.nextLine();
        String firstName = sc.nextLine();
        String email = sc.nextLine();
        String phoneNumber = sc.nextLine();
        Guest newGuest = new Guest(lastName, firstName, email, phoneNumber);
        if(list.isOnTheListAlready(newGuest)){
            list.add(newGuest);
        } else {
            System.out.println("Persoana deja inscrisa");
        }

    }


    private static void checkGuest(Scanner sc, GuestsList list) {
        // TO DO:
        // System.out.println("Alegeți opțiunea de căutare:");
        // System.out.println("1 - Nume și prenume");
        //  System.out.println("2 - Email");
        //  System.out.println("3 - Număr de telefon");
        int option = sc.nextInt();
        sc.nextLine();
        String match;
        switch (option) {
            case 1:
                //  System.out.println("Introduceți numele:");
                String lastName = sc.nextLine();
                //   System.out.println("Introduceți prenumele:");
                String firstName = sc.nextLine();
                Guest foundByName = list.search(firstName, lastName);
                if (foundByName != null) {
                    System.out.println(foundByName);
                } else {
                    System.out.println("Acest nume nu exista in lista");
                }
                break;
            case 2:
                // System.out.println("Introduceți adresa de email:");
                match = sc.nextLine();
                Guest foundByEmail = list.search(2, match);
                if (foundByEmail != null) {
                    System.out.println(foundByEmail);
                } else {
                    System.out.println("Acest email nu exista in lista");
                }
                break;
            case 3:
                //  System.out.println("Introduceți numărul de telefon:");
                match = sc.nextLine();
                Guest foundByPhone = list.search(3, match);
                if (foundByPhone != null) {
                    System.out.println(foundByPhone);
                } else {
                    System.out.println("Acest numar de telefon  nu exista in lista");
                }
                break;
            default:
                System.out.println("Opțiunea introdusă nu este validă.");
                break;
        }
    }

    private static void removeGuest(Scanner sc, GuestsList list) {
        // TO DO:
        //System.out.println("Alegeți opțiunea de căutare:");
        // System.out.println("1 - Nume și prenume");
        // System.out.println("2 - Email");
        // System.out.println("3 - Număr de telefon");
        int option = sc.nextInt();
        sc.nextLine();
        String match;
        switch (option) {
            case 1:
                //  System.out.println("Introduceți numele:");
                String lastName = sc.nextLine();
                //  System.out.println("Introduceți prenumele:");
                String firstName = sc.nextLine();
                boolean removedByName = list.remove(firstName, lastName);
                if (removedByName) {
                    // System.out.println("Persoana a fost ștearsă cu succes.");
                } else {
                    System.out.println("Persoana nu exista in lista");
                }
                break;
            case 2:
                // System.out.println("Introduceți adresa de email:");
                match = sc.nextLine();
                boolean removedByEmail = list.remove(2, match);
                if (removedByEmail) {
                    // System.out.println("Persoana a fost ștearsă cu succes.");
                } else {
                    System.out.println("Persoana nu exista in lista");
                }

                break;
            case 3:
                //  System.out.println("Introduceți numărul de telefon:");
                match = sc.nextLine();
                boolean removedByPhone = list.remove(3, match);
                if (removedByPhone) {
                    //  System.out.println("Persoana a fost ștearsă cu succes.");
                } else {
                    System.out.println("Persoana nu exista in lista");
                }
                break;
            default:
                  System.out.println("Opțiunea introdusă nu este validă.");
                break;
        }
    }

    private static void updateGuest(Scanner sc, GuestsList list) {
        // TO DO:
        // System.out.println("Alegeți opțiunea de căutare:");
        // System.out.println("1 - Nume și prenume");
        //  System.out.println("2 - Email");
        // System.out.println("3 - Număr de telefon");
        int option = sc.nextInt();
        sc.nextLine();
        Guest g = null;
        switch (option) {
            case 1:
                // System.out.println("Introduceți numele:");
                String lastName = sc.nextLine();
                // System.out.println("Introduceți prenumele:");
                String firstName = sc.nextLine();

                //  System.out.println("Introduceti campul ce trebuie modificat(1: First Name; 2.Last Name; 3.Email; 4. Phone):");
                if (!list.isOnTheListAlready(list.search(firstName,lastName))){
                    g = list.search(firstName,lastName);


                }
                break;
            case 2:
                // System.out.println("Introduceți adresa de email");
                String email = sc.nextLine();
                if(!list.isOnTheListAlready(list.search(option,email))){
                   g = list.search(option,email);
                }
                break;
            case 3:
                //  System.out.println("Introduceți numarul de telefon");
                String phone = sc.nextLine();
                if(!list.isOnTheListAlready(list.search(option,phone))){
                    g = list.search(option,phone);
                }
                break;
            default:
                // System.out.println("Opțiunea introdusă nu este validă.");
                break;
        }

        int opt = sc.nextInt();
        sc.nextLine();
        String newVal = sc.nextLine();
        list.updateByChoose(opt,g,newVal);


    }

    private static void searchList(Scanner sc, GuestsList list) {
        // TO DO:
        // System.out.println("Introduceți șirul de caractere pentru căutare:");
        String match = sc.nextLine();
        List<Guest> searchResults = list.partialSearch(match);
        if (!searchResults.isEmpty()) {
            //  System.out.println("Rezultatele căutării:");
            for (Guest guest : searchResults) {
                System.out.println(guest);
            }
        } else {
            System.out.println("Nothing found");
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti numarul de persoane de pe lista");
        int size = scanner.nextInt();
        scanner.nextLine();

        GuestsList list = new GuestsList(size);

        boolean running = true;
        while (running) {
            String command = scanner.nextLine();

            switch (command) {
                case "help":
                    showCommands();
                    break;
                case "add":
                    addNewGuest(scanner, list);
                    break;
                case "check":
                    checkGuest(scanner, list);
                    break;
                case "remove":
                    removeGuest(scanner, list);
                    break;
                case "update":
                    updateGuest(scanner, list);
                    break;
                case "guests":
                    list.showGuestsList();
                    break;
                case "waitlist":
                    list.showWaitingList();
                    break;
                case "available":
                    System.out.println("Numarul de locuri ramase: " + list.numberOfAvailableSpots());
                    break;
                case "guests_no":
                    System.out.println("Numarul de participanti: " + list.numberOfGuests());
                    break;
                case "waitlist_no":
                    System.out.println("Dimensiunea listei de asteptare: " + list.numberOfPeopleWaiting());
                    break;
                case "subscribe_no":
                    System.out.println("Numarul total de persoane: " + list.numberOfPeopleTotal());
                    break;
                case "search":
                    searchList(scanner, list);
                    break;
                case "quit":
                    System.out.println("Aplicatia se inchide...");
                    scanner.close();
                    running = false;
                    break;
                default:
                    // System.out.println("Comanda introdusa nu este valida.");
                    //  System.out.println("Incercati inca o data.");

            }
        }
    }
}