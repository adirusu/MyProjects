import java.util.ArrayList;
import java.util.List;

class GuestsList {
    private int capacity;
    private ArrayList<Guest> guestsList;
    private ArrayList<Guest> waitingList;

    public GuestsList(int guestsCapacity) {
        this.capacity = guestsCapacity;
        guestsList = new ArrayList<>();
        waitingList = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Guest> getGuestsList() {
        return guestsList;
    }

    public ArrayList<Guest> getWaitingList() {
        return waitingList;
    }

    public Guest search(String firstName, String lastName ) {
        for (Guest guest : guestsList){
            if (guest.getLastName().equalsIgnoreCase(lastName) && guest.getFirstName().equalsIgnoreCase(firstName))  {
                return guest;
            }
        }
        for (Guest waitGuest : waitingList){
            if (waitGuest.getLastName().equalsIgnoreCase(lastName) && waitGuest.getFirstName().equalsIgnoreCase(firstName))  {
                return waitGuest;
            }
        }
        return null;
    }

    public Guest search(int opt, String emailOrPhone) {
        switch (opt) {
            case 2 :
                for (Guest guest : guestsList) {
                    if (guest.getEmail().equalsIgnoreCase(emailOrPhone) ) {
                        return guest;
                    }
                }
                for (Guest waitGuest : waitingList) {
                    if (waitGuest.getEmail().equalsIgnoreCase(emailOrPhone) ) {
                        return waitGuest;
                    }
                }
                break;
            case 3 :
                for (Guest guest : guestsList) {
                    if ( guest.getPhoneNumber().equalsIgnoreCase(emailOrPhone)) {
                        return guest;
                    }
                }
                for (Guest waitGuest : waitingList) {
                    if ( waitGuest.getPhoneNumber().equalsIgnoreCase(emailOrPhone)) {
                        return waitGuest;
                    }
                }
                break;
            default:
                // System.out.println(" Not a correct choice");
                break;

        }

        return null;
    }

    //1. adăugarea unei noi persoane (înscrise)
    public int add(Guest guest) {

        if (search(guest.getFirstName(),guest.getLastName()) != null){
            return -1;
        }
        if (search(2,guest.getEmail()) != null || search(3,guest.getPhoneNumber()) != null){
            return -1;
        }

        if (guestsList.size() < capacity) {
            guestsList.add(guest);
            System.out.println("[" + guest.fullName() + "]" + " Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
            return 0;
        } else {
            waitingList.add(guest);
            System.out.println("[" + guest.fullName() + "]" + " Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine " + waitingList.size() +  ". Te vom notifica daca un loc devine disponibil.");
            return waitingList.size(); // Added to waiting list
        }
    }

    //2. determină dacă o persoană este înscrisă la eveniment (în oricare listă)
    public boolean isOnTheListAlready(Guest g) {
        // TO DO:
        if (add(g) == -1){
            return false;
        }
        return true;
    }

    //3. eliminarea unei persoane (înscrise)
    public boolean remove(String firstName, String lastName) {
        // TO DO:
        Guest guestRemove = search(firstName,lastName);
        if (doTheRemoveFromList(guestRemove)) {
            return true;
        }
        return false;
    }

    public boolean remove(int opt, String match) {
        // TO DO:

        switch(opt){
            case 2 :
                Guest guestRemoveEmail = search(opt,match);
                if (doTheRemoveFromList(guestRemoveEmail)){
                    return true;
                }
                break;
            case 3 :
                Guest guestRemovePhone = search(opt,match);
                if(doTheRemoveFromList(guestRemovePhone)){
                    return true;
                }
                break;
            default:
                //  System.out.println(" Not a correct choice");
                break;
        }

        return false;
    }

    public boolean doTheRemoveFromList(Guest guestRemove){

        Guest temp;
        if (guestRemove != null){
            if (guestsList.contains(guestRemove)){
                guestsList.remove(guestRemove);
                temp = waitingList.get(0);
                guestsList.add(temp);
                waitingList.remove(0);
                System.out.println("[" +temp.fullName() + "]" + " Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
                return true;
            }
            if (waitingList.contains(guestRemove)){
                waitingList.remove(guestRemove);
                return true;
            }
        }
        return false;
    }

    //4. actualizarea detaliilor unei persoane înscrise

    public void updateByChoose(int ch, Guest guest,String updateField){

        switch(ch){
            case 1:
                guest.setLastName(updateField);
                break;
            case 2:
                guest.setFirstName(updateField);
                break;
            case 3:
                guest.setEmail(updateField);
                break;
            case 4:
                guest.setPhoneNumber(updateField);
                break;
            default:
                //   System.out.println("Nu ati facut o alegere corecta");
                break;
        }
    }

    //5. obținerea listei de persoane care au loc la eveniment (i.e. lista de participare)
    public void showGuestsList() {
        // TO DO:
        if (guestsList.isEmpty()) {
            System.out.println("Lista este goala...");
        } else {
            for (Guest guest : guestsList) {
                System.out.println(guestsList.indexOf(guest) + 1 + ". " + guest);
            }
        }
    }

    //6. obținerea listei de persoane din lista de așteptare
    public void showWaitingList() {
        if (waitingList.isEmpty()){
            System.out.println("Lista de asteptare este goala...");
        } else {
            for (Guest guest : waitingList) {
                System.out.println(waitingList.indexOf(guest) + 1 + ". " + guest);
            }
        }
    }

    //7. obținerea numărului de locuri disponibile
    public int numberOfAvailableSpots() {
        return capacity - guestsList.size();
    }

    //8. obținerea numărului de persoane participante (i.e. aflate în lista de participare)
    public int numberOfGuests() {
        return guestsList.size();
    }

    //9. obținerea numărului de persoane din lista de așteptare
    public int numberOfPeopleWaiting() {
        return waitingList.size();
    }

    //10. obținerea numărului total de persoane
    public int numberOfPeopleTotal() {
        // TO DO:
        return guestsList.size() + waitingList.size();
    }
    //11. căutare parțială, după un subșir de caractere:
    public List<Guest> partialSearch(String match) {
        // TO DO:
        List<Guest> result = new ArrayList<>();
        match = match.toLowerCase();
        for (Guest guest : guestsList) {
            if (guest.getLastName().toLowerCase().contains(match) ||
                    guest.getFirstName().toLowerCase().contains(match) ||
                    guest.getEmail().toLowerCase().contains(match) ||
                    guest.getPhoneNumber().toLowerCase().contains(match)) {
                result.add(guest);
            }
        }
        for (Guest guest : waitingList) {
            if (guest.getLastName().toLowerCase().contains(match) ||
                    guest.getFirstName().toLowerCase().contains(match) ||
                    guest.getEmail().toLowerCase().contains(match) ||
                    guest.getPhoneNumber().toLowerCase().contains(match)) {
                result.add(guest);
            }
        }
        return result;
    }
}


