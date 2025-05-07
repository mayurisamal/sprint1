// Package declaration for the AllOperations class
package com.event1;

// Import statements for required classes and interfaces
import com.event1.entity.*;          // Import all entity classes from the entity package
import com.event1.Service.*;        // Import all service interfaces from the Service package
import com.event1.ServiceImpl.*;    // Import all service implementation classes
import java.util.List;              // Import List interface for collections
import java.util.Scanner;           // Import Scanner for user input
import java.util.regex.Pattern;     // Import Pattern for regular expressions

// Import Hibernate exception for constraint violation handling
import org.hibernate.exception.ConstraintViolationException;

// Main class for the Event Management System operations
public class AllOperations {

    // Static Scanner object for reading user input from console
    static Scanner sc = new Scanner(System.in);

    // Service layer instances for different domain operations
    static EventService eventService = new EventServiceImpl();          // Event service instance
    static VenueService venueService = new VenueServiceImpl();          // Venue service instance
    static OrganizerService organizerService = new OrganizerServiceImpl(); // Organizer service
    static VendorService vendorService = new VendorServiceImpl();       // Vendor service instance
    static ParticipantsService participantsService = new ParticipantsServiceImpl(); // Participant service

    // Regular expression patterns for input validation:
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10}$");          // 10-digit phone
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$"); // Email format
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$"); // YYYY-MM-DD date
    private static final Pattern TIME_PATTERN = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$"); // HH:MM time

    // Main method - entry point of the application
    public static void main(String[] args) {
        // Infinite loop to keep showing menu until user chooses to exit
        while(true) {
            // Display the main menu header
            System.out.println("\n=======================================");
            System.out.println("   🎪 EVENT MANAGEMENT SYSTEM MAIN MENU   ");
            System.out.println("=======================================");
            
            // Display menu options
            System.out.println("1️  Press 1 for Event Operations");       // Option 1 for events
            System.out.println("2️  Press 2 for Venue Operations");       // Option 2 for venues
            System.out.println("3️  Press 3 for Organizer Operations");  // Option 3 for organizers
            System.out.println("4️  Press 4 for Vendor Operations");     // Option 4 for vendors
            System.out.println("5️  Press 5 for Participant Operations");// Option 5 for participants
            System.out.println("6️  Press 6 to Exit");                  // Option 6 to exit program
            
            // Prompt for user input
            System.out.print("👉 Enter your choice: ");

            // Get validated integer input between 1 and 6
            int choice = getIntInput(1, 6);
            // Consume the leftover newline character
            sc.nextLine(); 

            // Switch statement to handle user choice
            switch(choice) {
                case 1: 
                    eventOperations(); // Call event operations menu
                    break;
                case 2: 
                    venueOperations(); // Call venue operations menu
                    break;
                case 3: 
                    organizerOperations(); // Call organizer operations menu
                    break;
                case 4: 
                    vendorOperations(); // Call vendor operations menu
                    break;
                case 5: 
                    participantOperations(); // Call participant operations menu
                    break;
                case 6: 
                    // Exit message and terminate program
                    System.out.println("👋 Exiting system. Goodbye!");
                    System.exit(0);
                default: 
                    // Handle invalid choices
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }

    // ==================== VALIDATION METHODS ====================

    // Validates phone number input against 10-digit pattern
    private static String validatePhone(String prompt) {
        // Keep asking until valid input is received
        while (true) {
            // Show prompt to user
            System.out.print(prompt);
            // Read user input
            String phone = sc.nextLine();
            // Check if input matches phone pattern
            if (PHONE_PATTERN.matcher(phone).matches()) {
                // Return valid phone number
                return phone;
            }
            // Show error for invalid input
            System.out.println("❌ Invalid phone number! Please enter 10 digits only.");
        }
    }

    // Validates email input against email pattern
    private static String validateEmail(String prompt) {
        // Keep asking until valid input is received
        while (true) {
            // Show prompt to user
            System.out.print(prompt);
            // Read user input
            String email = sc.nextLine();
            // Check if input matches email pattern
            if (EMAIL_PATTERN.matcher(email).matches()) {
                // Return valid email
                return email;
            }
            // Show error for invalid input
            System.out.println("❌ Invalid email format! Please try again.");
        }
    }

    // Validates date input against YYYY-MM-DD pattern
    private static String validateDate(String prompt) {
        // Keep asking until valid input is received
        while (true) {
            // Show prompt to user
            System.out.print(prompt);
            // Read user input
            String date = sc.nextLine();
            // Check if input matches date pattern
            if (DATE_PATTERN.matcher(date).matches()) {
                // Return valid date
                return date;
            }
            // Show error for invalid input
            System.out.println("❌ Invalid date format! Please use YYYY-MM-DD.");
        }
    }

    // Validates time input against HH:MM pattern
    private static String validateTime(String prompt) {
        // Keep asking until valid input is received
        while (true) {
            // Show prompt to user
            System.out.print(prompt);
            // Read user input
            String time = sc.nextLine();
            // Check if input matches time pattern
            if (TIME_PATTERN.matcher(time).matches()) {
                // Return valid time
                return time;
            }
            // Show error for invalid input
            System.out.println("❌ Invalid time format! Please use HH:MM (24-hour format).");
        }
    }

    // Gets and validates integer input within specified range
    private static int getIntInput(int min, int max) {
        // Keep asking until valid input is received
        while (true) {
            try {
                // Read integer input
                int input = sc.nextInt();
                // Check if within valid range
                if (input >= min && input <= max) {
                    // Return valid input
                    return input;
                }
                // Show range error
                System.out.printf("❌ Please enter a number between %d and %d: ", min, max);
            } catch (Exception e) {
                // Handle non-integer input
                System.out.printf("❌ Invalid input! Please enter a number between %d and %d: ", min, max);
                // Clear invalid input from scanner
                sc.next(); 
            }
        }
    }

    // Gets and validates double input
    private static double getDoubleInput(String prompt) {
        // Keep asking until valid input is received
        while (true) {
            try {
                // Show prompt
                System.out.print(prompt);
                // Parse double from input
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                // Handle non-numeric input
                System.out.println("❌ Invalid number! Please try again.");
            }
        }
    }
    // ================================
    // 🎉 EVENT OPERATIONS
    // ================================

    /**
     * Displays and handles the event operations menu
     */
    public static void eventOperations() {
        // Loop to keep showing event menu until user goes back
        while(true) {
            // Display event operations menu header
            System.out.println("\n=======================================");
            System.out.println("         📅 EVENT OPERATIONS MENU       ");
            System.out.println("=======================================");
            
            // Display all event operation options
            System.out.println("1️  Add Event");                // Option to add new event
            System.out.println("2️  View Event");               // Option to view single event
            System.out.println("3️  View All Events");         // Option to view all events
            System.out.println("4️  Update Event");             // Option to update event
            System.out.println("5️  Delete Event");             // Option to delete event
            System.out.println("6️  Get Events by Type");      // Option to filter by type
            System.out.println("7️  Get Events by Date");      // Option to filter by date
            System.out.println("8️  Back to Main Menu");        // Option to return to main menu
            
            // Prompt for user choice
            System.out.print("👉 Enter your choice: ");

            // Get validated input between 1-8
            int choice = getIntInput(1, 8);
            // Consume leftover newline
            sc.nextLine(); 

            // Handle user choice
            switch(choice) {
                case 1: 
                    addEvent();     // Call add event method
                    break;
                case 2: 
                    getEvent();     // Call view single event method
                    break;
                case 3: 
                    getAllEvents(); // Call view all events method
                    break;
                case 4: 
                    updateEvent();  // Call update event method
                    break;
                case 5: 
                    deleteEvent();  // Call delete event method
                    break;
                case 6: 
                    getEventsByType(); // Call filter by type method
                    break;
                case 7: 
                    getEventsByDate(); // Call filter by date method
                    break;
                case 8: 
                    return;         // Return to main menu
                default: 
                    System.out.println("❌ Invalid choice!"); // Handle invalid input
            }
        }
    }

    /**
     * Adds a new event to the system
     */
    private static void addEvent() {
        // Display add event header
        System.out.println("\n📅 Adding a New Event...");

        // Create new Event object
        Event event = new Event();

        // Get event ID from user
        System.out.print("🔹 Enter Event ID: ");
        event.setE_id(sc.nextLine());

        // Get event title from user
        System.out.print("🔹 Enter Event Title: ");
        event.setE_title(sc.nextLine());

        // Get event type from user
        System.out.print("🔹 Enter Event Type: ");
        event.setE_Type(sc.nextLine());

        // Get and validate event date
        event.setE_Date(validateDate("🔹 Enter Event Date (YYYY-MM-DD): "));
        // Get and validate event time
        event.setE_Time(validateTime("🔹 Enter Event Time (HH:MM): "));

        // Get venue ID and validate venue exists
        System.out.print("🔹 Enter Venue ID: ");
        Venue venue = venueService.getVenue(sc.nextLine());
        if(venue == null) {
            System.out.println("❌ Venue not found!");
            return; // Abort if venue doesn't exist
        }
        event.setVenue(venue); // Set valid venue

        // Get organizer ID and validate organizer exists
        System.out.print("🔹 Enter Organizer ID: ");
        Organizer organizer = organizerService.getOrganizer(sc.nextLine());
        if(organizer == null) {
            System.out.println("❌ Organizer not found!");
            return; // Abort if organizer doesn't exist
        }
        event.setOrganizer(organizer); // Set valid organizer

        // Create event through service layer
        eventService.createEvent(event);
        // Confirm successful creation
        System.out.println("✅ Event added successfully!");
    }

    /**
     * Retrieves and displays a single event by ID
     */
    private static void getEvent() {
        // Prompt for event ID
        System.out.print("\n🔎 Enter Event ID to view: ");
        // Get event from service layer
        Event event = eventService.getEvent(sc.nextLine());
        // Display event if found, or error message
        System.out.println(event != null ? event : "❌ Event not found!");
    }

    /**
     * Retrieves and displays all events in the system
     */
    private static void getAllEvents() {
        // Display header
        System.out.println("\n📋 All Events:");
        // Get all events from service layer
        List<Event> events = eventService.getAllEvents();

        // Handle case when no events exist
        if (events == null || events.isEmpty()) {
            System.out.println("No events found!");
            return;
        }

        // Iterate through all events and display details
        for (Event event : events) {
            try {
                // Display basic event info
                System.out.println("Event ID: " + event.getE_id());
                System.out.println("Title: " + event.getE_title());
                System.out.println("Type: " + event.getE_Type());
                System.out.println("Date: " + event.getE_Date());
                System.out.println("Time: " + event.getE_Time());

                // Handle venue display (check for null)
                if (event.getVenue() != null) {
                    System.out.println("Venue: " + event.getVenue().getV_Name());
                } else {
                    System.out.println("Venue: Not assigned");
                }

                // Handle organizer display (check for null)
                if (event.getOrganizer() != null) {
                    System.out.println("Organizer: " + event.getOrganizer().getO_Name());
                } else {
                    System.out.println("Organizer: Not assigned");
                }

                // Separator between events
                System.out.println("---------------------");
            } catch (Exception e) {
                // Handle errors displaying individual events
                System.out.println("Error displaying event with ID " + event.getE_id() + ": " + e.getMessage());
                System.out.println("---------------------");
            }
        }
    }

    /**
     * Updates an existing event's details
     */
    private static void updateEvent() {
        // Prompt for event ID to update
        System.out.print("\n✏️ Enter Event ID to update: ");
        // Get event from service layer
        Event event = eventService.getEvent(sc.nextLine());

        // Handle case when event doesn't exist
        if (event == null) {
            System.out.println("❌ Event not found!");
            return;
        }

        // Update title if provided (non-empty)
        System.out.print("🔹 New Event Title (" + event.getE_title() + "): ");
        String title = sc.nextLine();
        if (!title.isEmpty()) event.setE_title(title);

        // Update type if provided
        System.out.print("🔹 New Event Type (" + event.getE_Type() + "): ");
        String type = sc.nextLine();
        if (!type.isEmpty()) event.setE_Type(type);

        // Update date with validation
        System.out.print("🔹 New Event Date (" + event.getE_Date() + "): ");
        String date = sc.nextLine();
        if (!date.isEmpty()) {
            // Validate date format
            while (!DATE_PATTERN.matcher(date).matches()) {
                System.out.println("❌ Invalid date format! Please use YYYY-MM-DD.");
                System.out.print("🔹 New Event Date (" + event.getE_Date() + "): ");
                date = sc.nextLine();
                if (date.isEmpty()) break; // Allow empty to skip update
            }
            if (!date.isEmpty()) event.setE_Date(date);
        }

        // Update time with validation
        System.out.print("🔹 New Event Time (" + event.getE_Time() + "): ");
        String time = sc.nextLine();
        if (!time.isEmpty()) {
            // Validate time format
            while (!TIME_PATTERN.matcher(time).matches()) {
                System.out.println("❌ Invalid time format! Please use HH:MM.");
                System.out.print("🔹 New Event Time (" + event.getE_Time() + "): ");
                time = sc.nextLine();
                if (time.isEmpty()) break; // Allow empty to skip update
            }
            if (!time.isEmpty()) event.setE_Time(time);
        }

        // Update venue if provided
        System.out.print("🔹 New Venue ID (" + event.getVenue().getV_id() + "): ");
        String venueId = sc.nextLine();
        if (!venueId.isEmpty()) {
            Venue venue = venueService.getVenue(venueId);
            if (venue != null) {
                event.setVenue(venue); // Set new venue if valid
            } else {
                System.out.println("❌ Venue not found! Keeping the old venue.");
            }
        }

        // Update organizer if provided
        System.out.print("🔹 New Organizer ID (" + event.getOrganizer().getO_id() + "): ");
        String organizerId = sc.nextLine();
        if (!organizerId.isEmpty()) {
            Organizer organizer = organizerService.getOrganizer(organizerId);
            if (organizer != null) {
                event.setOrganizer(organizer); // Set new organizer if valid
            } else {
                System.out.println("❌ Organizer not found! Keeping the old organizer.");
            }
        }

        // Save updates through service layer
        eventService.updateEvent(event);
        // Confirm successful update
        System.out.println("✅ Event updated successfully!");
    }

    /**
     * Deletes an event from the system
     */
    private static void deleteEvent() {
        // Prompt for event ID to delete
        System.out.print("\n🗑️ Enter Event ID to delete: ");
        String id = sc.nextLine();

        try {
            // Get event details first for confirmation
            Event event = eventService.getEvent(id);
            if (event == null) {
                System.out.println("❌ Event not found!");
                return;
            }

            // Display event details
            System.out.println("\nEvent Details:");
            System.out.println(event);

            // Warn about dependent records (vendors, participants)
            System.out.println("\n⚠️ Warning: This will delete this event!");

            // Get confirmation from user
            System.out.print("\nAre you sure you want to delete this event? (yes/no): ");
            String confirmation = sc.nextLine().toLowerCase();
            if (!confirmation.equals("yes")) {
                System.out.println("🚫 Event deletion cancelled.");
                return;
            }

            // Attempt deletion
            try {
                eventService.deleteEvent(id);
                System.out.println("✅ Event deleted successfully!");
            } catch (Exception e) {
                // Handle constraint violations
                System.out.println("❌ Error deleting event: " + e.getMessage());
                if (e.getCause() instanceof ConstraintViolationException) {
                    System.out.println("This event cannot be deleted because it's still referenced by other records.");
                }
            }
        } catch (Exception e) {
            // Handle other errors
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Retrieves events filtered by type
     */
    private static void getEventsByType() {
        // Prompt for event type to filter by
        System.out.print("\n🔍 Enter Event Type to search: ");
        String eventType = sc.nextLine();
        // Get filtered events from service layer
        List<Event> events = eventService.getEventsByType(eventType);
        // Display results or "no events" message
        if (events.isEmpty()) {
            System.out.println("No events found!");
        } else {
            events.forEach(System.out::println);
        }
    }

    /**
     * Retrieves events filtered by date
     */
    private static void getEventsByDate() {
        // Get and validate date input
        String date = validateDate("\n📅 Enter Date (YYYY-MM-DD) to search: ");
        // Get filtered events from service layer
        List<Event> events = eventService.getEventsByDate(date);
        // Display results or "no events" message
        if(events.isEmpty()) {
            System.out.println("No events found!");
        } else {
            events.forEach(System.out::println);
        }
    }
    // ================================
    // 🏢 VENUE OPERATIONS
    // ================================

    /**
     * Displays and handles the venue operations menu
     */
    public static void venueOperations() {
        // Loop to keep showing venue menu until user goes back
        while(true) {
            // Display venue operations menu header
            System.out.println("\n=======================================");
            System.out.println("         🏢 VENUE OPERATIONS MENU       ");
            System.out.println("=======================================");
            
            // Display all venue operation options
            System.out.println("1️  Add Venue");                 // Option to add new venue
            System.out.println("2️  View Venue");                // Option to view single venue
            System.out.println("3️  View All Venues");           // Option to view all venues
            System.out.println("4️  Update Venue");              // Option to update venue
            System.out.println("5️  Delete Venue");              // Option to delete venue
            System.out.println("6️  Get Venues by Owner");      // Option to filter by owner
            System.out.println("7️  Get Venues by Address");    // Option to filter by address
            System.out.println("8️  Back to Main Menu");         // Option to return to main menu
            
            // Prompt for user choice
            System.out.print("👉 Enter your choice: ");

            // Get validated input between 1-8
            int choice = getIntInput(1, 8);
            // Consume leftover newline
            sc.nextLine(); 

            // Handle user choice
            switch(choice) {
                case 1: 
                    addVenue();         // Call add venue method
                    break;
                case 2: 
                    getVenue();         // Call view single venue method
                    break;
                case 3: 
                    getAllVenues();     // Call view all venues method
                    break;
                case 4: 
                    updateVenue();      // Call update venue method
                    break;
                case 5: 
                    deleteVenue();      // Call delete venue method
                    break;
                case 6: 
                    getVenuesByOwner(); // Call filter by owner method
                    break;
                case 7: 
                    getVenuesByAddress(); // Call filter by address method
                    break;
                case 8: 
                    return;             // Return to main menu
                default: 
                    System.out.println("❌ Invalid choice!"); // Handle invalid input
            }
        }
    }

    /**
     * Adds a new venue to the system
     */
    private static void addVenue() {
        // Display add venue header
        System.out.println("\n🏢 Adding a New Venue...");

        // Create new Venue object
        Venue venue = new Venue();

        // Get venue ID from user
        System.out.print("🔹 Enter Venue ID: ");
        venue.setV_id(sc.nextLine());

        // Get venue name from user
        System.out.print("🔹 Enter Venue Name: ");
        venue.setV_Name(sc.nextLine());

        // Get venue address from user
        System.out.print("🔹 Enter Venue Address: ");
        venue.setV_Address(sc.nextLine());

        // Get and validate venue contact number
        venue.setV_Contact(validatePhone("🔹 Enter Venue Contact (10 digits): "));

        // Get venue owner name from user
        System.out.print("🔹 Enter Venue Owner: ");
        venue.setV_Owner(sc.nextLine());

        // Create venue through service layer
        venueService.createVenue(venue);
        // Confirm successful creation
        System.out.println("✅ Venue added successfully!");
    }

    /**
     * Retrieves and displays a single venue by ID
     */
    private static void getVenue() {
        // Prompt for venue ID
        System.out.print("\n🔎 Enter Venue ID to view: ");
        // Get venue from service layer
        Venue venue = venueService.getVenue(sc.nextLine());
        // Display venue if found, or error message
        System.out.println(venue != null ? venue : "❌ Venue not found!");
    }

    /**
     * Retrieves and displays all venues in the system
     */
    private static void getAllVenues() {
        // Display header
        System.out.println("\n📋 All Venues:");
        // Get all venues from service layer
        List<Venue> venues = venueService.getAllVenues();
        // Handle case when no venues exist
        if(venues.isEmpty()) {
            System.out.println("No venues found!");
        } else {
            // Print each venue using toString()
            venues.forEach(System.out::println);
        }
    }

    /**
     * Updates an existing venue's details
     */
    private static void updateVenue() {
        // Prompt for venue ID to update
        System.out.print("\n✏️ Enter Venue ID to update: ");
        // Get venue from service layer
        Venue venue = venueService.getVenue(sc.nextLine());

        // Handle case when venue doesn't exist
        if (venue == null) {
            System.out.println("❌ Venue not found!");
            return;
        }

        // Update name if provided (non-empty)
        System.out.print("🔹 New Venue Name (" + venue.getV_Name() + "): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) venue.setV_Name(name);

        // Update address if provided
        System.out.print("🔹 New Venue Address (" + venue.getV_Address() + "): ");
        String address = sc.nextLine();
        if (!address.isEmpty()) venue.setV_Address(address);

        // Update contact with validation
        System.out.print("🔹 New Venue Contact (" + venue.getV_Contact() + "): ");
        String contact = sc.nextLine();
        if (!contact.isEmpty()) {
            // Validate phone format
            while (!PHONE_PATTERN.matcher(contact).matches()) {
                System.out.println("❌ Invalid phone number! Please enter 10 digits only.");
                System.out.print("🔹 New Venue Contact (" + venue.getV_Contact() + "): ");
                contact = sc.nextLine();
                if (contact.isEmpty()) break; // Allow empty to skip update
            }
            if (!contact.isEmpty()) venue.setV_Contact(contact);
        }

        // Update owner if provided
        System.out.print("🔹 New Venue Owner (" + venue.getV_Owner() + "): ");
        String owner = sc.nextLine();
        if (!owner.isEmpty()) venue.setV_Owner(owner);

        // Save updates through service layer
        venueService.updateVenue(venue);
        // Confirm successful update
        System.out.println("✅ Venue updated successfully!");
    }

    /**
     * Deletes a venue from the system
     */
    private static void deleteVenue() {
        // Prompt for venue ID to delete
        System.out.print("\n🗑️ Enter Venue ID to delete: ");
        String id = sc.nextLine();

        try {
            // Get venue details first for confirmation
            Venue venue = venueService.getVenue(id);
            if (venue == null) {
                System.out.println("❌ Venue not found!");
                return;
            }

            // Display venue details
            System.out.println("\nVenue Details:");
            System.out.println(venue);

            // Get confirmation from user
            System.out.print("\nAre you sure you want to delete this venue? (yes/no): ");
            String confirmation = sc.nextLine().toLowerCase();
            if (!confirmation.equals("yes")) {
                System.out.println("🚫 Venue deletion cancelled.");
                return;
            }

            // Attempt deletion
            try {
                venueService.deleteVenue(id);
                System.out.println("✅ Venue deleted successfully!");
            } catch (Exception e) {
                // Handle constraint violations (venue assigned to events)
                if (e.getCause() instanceof ConstraintViolationException) {
                    System.out.println("❌ Cannot delete venue - it's assigned to one or more events.");
                    System.out.println("Please remove or reassign these events first.");
                } else {
                    System.out.println("❌ Error deleting venue: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            // Handle other errors
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Retrieves venues filtered by owner name
     */
    private static void getVenuesByOwner() {
        // Prompt for owner name to filter by
        System.out.print("\n👤 Enter Owner Name to search: ");
        // Get filtered venues from service layer
        List<Venue> venues = venueService.getVenuesByOwner(sc.nextLine());
        // Display results or "no venues" message
        if(venues.isEmpty()) {
            System.out.println("No venues found!");
        } else {
            venues.forEach(System.out::println);
        }
    }

    /**
     * Retrieves venues filtered by address
     */
    private static void getVenuesByAddress() {
        // Prompt for address to filter by
        System.out.print("\n📍 Enter Address to search: ");
        // Get filtered venues from service layer
        List<Venue> venues = venueService.getVenuesByAddress(sc.nextLine());
        // Display results or "no venues" message
        if(venues.isEmpty()) {
            System.out.println("No venues found!");
        } else {
            venues.forEach(System.out::println);
        }
    }
    // ================================
    // 👔 ORGANIZER OPERATIONS
    // ================================

    /**
     * Displays and handles the organizer operations menu
     */
    public static void organizerOperations() {
        // Loop to keep showing organizer menu until user goes back
        while (true) {
            // Display organizer operations menu header
            System.out.println("\n=======================================");
            System.out.println("       👔 ORGANIZER OPERATIONS MENU     ");
            System.out.println("=======================================");
            
            // Display all organizer operation options
            System.out.println("1️  Add Organizer");             // Option to add new organizer
            System.out.println("2️  View Organizer");            // Option to view single organizer
            System.out.println("3️  View All Organizers");       // Option to view all organizers
            System.out.println("4️  Update Organizer");          // Option to update organizer
            System.out.println("5️  Delete Organizer");          // Option to delete organizer
            System.out.println("6️  Get Organizers by Role");    // Option to filter by role
            System.out.println("7️  Get Organizers by Email");   // Option to filter by email
            System.out.println("8️  Back to Main Menu");         // Option to return to main menu
            
            // Prompt for user choice
            System.out.print("👉 Enter your choice: ");

            // Get validated input between 1-8
            int choice = getIntInput(1, 8);
            // Consume leftover newline
            sc.nextLine(); 

            // Handle user choice
            switch (choice) {
                case 1: 
                    addOrganizer();     // Call add organizer method
                    break;
                case 2: 
                    getOrganizer();     // Call view single organizer method
                    break;
                case 3: 
                    getAllOrganizers(); // Call view all organizers method
                    break;
                case 4: 
                    updateOrganizer();  // Call update organizer method
                    break;
                case 5: 
                    deleteOrganizer();  // Call delete organizer method
                    break;
                case 6: 
                    getOrganizersByRole(); // Call filter by role method
                    break;
                case 7: 
                    getOrganizersByEmail(); // Call filter by email method
                    break;
                case 8: 
                    return;             // Return to main menu
                default: 
                    System.out.println("❌ Invalid choice!"); // Handle invalid input
            }
        }
    }

    /**
     * Adds a new organizer to the system
     */
    private static void addOrganizer() {
        // Display add organizer header
        System.out.println("\n👔 Adding a New Organizer...");

        // Create new Organizer object
        Organizer organizer = new Organizer();
        
        // Get organizer ID from user
        System.out.print("🔹 Enter Organizer ID: ");
        organizer.setO_id(sc.nextLine());

        // Get organizer name from user
        System.out.print("🔹 Enter Organizer Name: ");
        organizer.setO_Name(sc.nextLine());

        // Get organizer address from user
        System.out.print("🔹 Enter Organizer Address: ");
        organizer.setO_Address(sc.nextLine());

        // Get and validate organizer email
        organizer.setO_Email(validateEmail("🔹 Enter Organizer Email: "));

        // Get organizer role from user
        System.out.print("🔹 Enter Organizer Role: ");
        organizer.setO_Role(sc.nextLine());

        // Create organizer through service layer
        organizerService.createOrganizer(organizer);
        // Confirm successful creation
        System.out.println("✅ Organizer added successfully!");
    }

    /**
     * Retrieves and displays a single organizer by ID
     */
    private static void getOrganizer() {
        // Prompt for organizer ID
        System.out.print("\n🔎 Enter Organizer ID to view: ");
        // Get organizer from service layer
        Organizer organizer = organizerService.getOrganizer(sc.nextLine());
        // Display organizer if found, or error message
        System.out.println(organizer != null ? organizer : "❌ Organizer not found!");
    }

    /**
     * Retrieves and displays all organizers in the system
     */
    private static void getAllOrganizers() {
        // Display header
        System.out.println("\n📋 All Organizers:");
        // Get all organizers from service layer
        List<Organizer> organizers = organizerService.getAllOrganizers();
        // Handle case when no organizers exist
        if (organizers.isEmpty()) {
            System.out.println("No organizers found!");
        } else {
            // Print each organizer using toString()
            organizers.forEach(System.out::println);
        }
    }

    /**
     * Updates an existing organizer's details
     */
    private static void updateOrganizer() {
        // Prompt for organizer ID to update
        System.out.print("\n✏️ Enter Organizer ID to update: ");
        // Get organizer from service layer
        Organizer organizer = organizerService.getOrganizer(sc.nextLine());

        // Handle case when organizer doesn't exist
        if (organizer == null) {
            System.out.println("❌ Organizer not found!");
            return;
        }

        // Update name if provided (non-empty)
        System.out.print("🔹 New Organizer Name (" + organizer.getO_Name() + "): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) organizer.setO_Name(name);

        // Update address if provided
        System.out.print("🔹 New Organizer Address (" + organizer.getO_Address() + "): ");
        String address = sc.nextLine();
        if (!address.isEmpty()) organizer.setO_Address(address);

        // Update email with validation
        System.out.print("🔹 New Organizer Email (" + organizer.getO_Email() + "): ");
        String email = sc.nextLine();
        if (!email.isEmpty()) {
            // Validate email format
            while (!EMAIL_PATTERN.matcher(email).matches()) {
                System.out.println("❌ Invalid email format! Please try again.");
                System.out.print("🔹 New Organizer Email (" + organizer.getO_Email() + "): ");
                email = sc.nextLine();
                if (email.isEmpty()) break; // Allow empty to skip update
            }
            if (!email.isEmpty()) organizer.setO_Email(email);
        }

        // Update role if provided
        System.out.print("🔹 New Organizer Role (" + organizer.getO_Role() + "): ");
        String role = sc.nextLine();
        if (!role.isEmpty()) organizer.setO_Role(role);

        // Save updates through service layer
        organizerService.updateOrganizer(organizer);
        // Confirm successful update
        System.out.println("✅ Organizer updated successfully!");
    }

    /**
     * Deletes an organizer from the system
     */
    private static void deleteOrganizer() {
        // Prompt for organizer ID to delete
        System.out.print("\n🗑️ Enter Organizer ID to delete: ");
        String id = sc.nextLine();

        try {
            // Get organizer details first for confirmation
            Organizer organizer = organizerService.getOrganizer(id);
            if (organizer == null) {
                System.out.println("❌ Organizer not found!");
                return;
            }

            // Display organizer details
            System.out.println("\nOrganizer Details:");
            System.out.println(organizer);

            // Get confirmation from user
            System.out.print("\nAre you sure you want to delete this organizer? (yes/no): ");
            String confirmation = sc.nextLine().toLowerCase();
            if (!confirmation.equals("yes")) {
                System.out.println("🚫 Organizer deletion cancelled.");
                return;
            }

            // Attempt deletion
            try {
                organizerService.deleteOrganizer(id);
                // Verify deletion was successful
                if (organizerService.getOrganizer(id) == null) {
                    System.out.println("✅ Organizer deleted successfully!");
                } else {
                    System.out.println("❌ Organizer still exists (may be assigned to events)");
                }
            } catch (Exception e) {
                // Handle constraint violations (organizer assigned to events)
                if (e.getCause() instanceof ConstraintViolationException) {
                    System.out.println("❌ Cannot delete organizer - they're assigned to one or more events.");
                    System.out.println("Please remove or reassign these events first.");
                } else {
                    System.out.println("❌ Error deleting organizer: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            // Handle other errors
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Retrieves organizers filtered by role
     */
    private static void getOrganizersByRole() {
        // Prompt for role to filter by
        System.out.print("\n💼 Enter Role to search: ");
        String role = sc.nextLine();

        // Validate non-empty role
        if (role.isEmpty()) {
            System.out.println("❌ Role cannot be empty!");
            return;
        }

        // Get filtered organizers from service layer
        List<Organizer> organizers = organizerService.getOrganizersByRole(role);

        // Display results or "no organizers" message
        if (organizers.isEmpty()) {
            System.out.println("No organizers found!");
        } else {
            organizers.forEach(System.out::println);
        }
    }

    /**
     * Retrieves organizers filtered by email
     */
    private static void getOrganizersByEmail() {
        // Prompt for email to filter by
        System.out.print("\n📧 Enter Email to search: ");
        // Get filtered organizers from service layer
        List<Organizer> organizers = organizerService.getOrganizersByEmail(sc.nextLine());
        // Display results or "no organizers" message
        if (organizers.isEmpty()) {
            System.out.println("No organizers found!");
        } else {
            organizers.forEach(System.out::println);
        }
    }
    // ================================
    // 📦 VENDOR OPERATIONS
    // ================================

    /**
     * Displays and handles the vendor operations menu
     */
    public static void vendorOperations() {
        // Loop to keep showing vendor menu until user goes back
        while (true) {
            // Display vendor operations menu header
            System.out.println("\n=======================================");
            System.out.println("        📦 VENDOR OPERATIONS MENU       ");
            System.out.println("=======================================");
            
            // Display all vendor operation options
            System.out.println("1️  Add Vendor");                // Option to add new vendor
            System.out.println("2️  View Vendor");               // Option to view single vendor
            System.out.println("3️  View All Vendors");          // Option to view all vendors
            System.out.println("4️  Update Vendor");             // Option to update vendor
            System.out.println("5️  Delete Vendor");             // Option to delete vendor
            System.out.println("6️  Get Vendors by Fee Range");  // Option to filter by fee range
            System.out.println("7️  Back to Main Menu");         // Option to return to main menu
            
            // Prompt for user choice
            System.out.print("👉 Enter your choice: ");

            // Get validated input between 1-7
            int choice = getIntInput(1, 7);
            // Consume leftover newline
            sc.nextLine(); 

            // Handle user choice
            switch (choice) {
                case 1: 
                    addVendor();         // Call add vendor method
                    break;
                case 2: 
                    getVendor();         // Call view single vendor method
                    break;
                case 3: 
                    getAllVendors();     // Call view all vendors method
                    break;
                case 4: 
                    updateVendor();      // Call update vendor method
                    break;
                case 5: 
                    deleteVendor();      // Call delete vendor method
                    break;
                case 6: 
                    getVendorsByFeeRange(); // Call filter by fee range method
                    break;
                case 7: 
                    return;             // Return to main menu
                default: 
                    System.out.println("❌ Invalid choice! Please try again."); // Handle invalid input
            }
        }
    }

    /**
     * Adds a new vendor to the system
     */
    private static void addVendor() {
        // Display add vendor header
        System.out.println("\n📦 Adding a New Vendor...");

        // Create new Vendor object
        Vendor vendor = new Vendor();

        // Get vendor ID from user
        System.out.print("🔹 Enter Vendor ID: ");
        vendor.setVend_Id(sc.nextLine());

        // Get vendor name from user
        System.out.print("🔹 Enter Vendor Name: ");
        vendor.setVend_Name(sc.nextLine());

        // Get and validate vendor contact number
        vendor.setVend_Contact(validatePhone("🔹 Enter Vendor Contact (10 digits): "));

        // Get and validate vendor fee (must be numeric)
        vendor.setVend_Fee(getDoubleInput("🔹 Enter Vendor Fee: "));

        // Get vendor details/description from user
        System.out.print("🔹 Enter Vendor Details: ");
        vendor.setVend_Details(sc.nextLine());

        // Get associated event ID and validate event exists
        System.out.print("🔹 Enter Event ID: ");
        Event event = eventService.getEvent(sc.nextLine());
        if (event == null) {
            System.out.println("❌ Event not found! Vendor creation aborted.");
            return; // Abort if event doesn't exist
        }
        vendor.setEvent(event); // Set valid event

        // Create vendor through service layer
        vendorService.createVendor(vendor);
        // Confirm successful creation
        System.out.println("✅ Vendor added successfully!");
    }

    /**
     * Retrieves and displays a single vendor by ID
     */
    private static void getVendor() {
        // Prompt for vendor ID
        System.out.print("\n🔎 Enter Vendor ID to view: ");
        // Get vendor from service layer
        Vendor vendor = vendorService.getVendor(sc.nextLine());
        // Display vendor if found, or error message
        System.out.println(vendor != null ? vendor : "❌ Vendor not found!");
    }

    /**
     * Retrieves and displays all vendors in the system
     */
    private static void getAllVendors() {
        // Display header
        System.out.println("\n📋 All Vendors:");
        // Get all vendors from service layer
        List<Vendor> vendors = vendorService.getAllVendors();
        // Handle case when no vendors exist
        if (vendors.isEmpty()) {
            System.out.println("⚠️ No vendors found!");
        } else {
            // Print each vendor using toString()
            vendors.forEach(System.out::println);
        }
    }

    /**
     * Updates an existing vendor's details
     */
    private static void updateVendor() {
        // Prompt for vendor ID to update
        System.out.print("\n✏️ Enter Vendor ID to update: ");
        // Get vendor from service layer
        Vendor vendor = vendorService.getVendor(sc.nextLine());

        // Handle case when vendor doesn't exist
        if (vendor == null) {
            System.out.println("❌ Vendor not found!");
            return;
        }

        // Update name if provided (non-empty)
        System.out.print("🔹 New Vendor Name (" + vendor.getVend_Name() + "): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) vendor.setVend_Name(name);

        // Update contact with validation
        System.out.print("🔹 New Vendor Contact (" + vendor.getVend_Contact() + "): ");
        String contact = sc.nextLine();
        if (!contact.isEmpty()) {
            // Validate phone format
            while (!PHONE_PATTERN.matcher(contact).matches()) {
                System.out.println("❌ Invalid phone number! Please enter 10 digits only.");
                System.out.print("🔹 New Vendor Contact (" + vendor.getVend_Contact() + "): ");
                contact = sc.nextLine();
                if (contact.isEmpty()) break; // Allow empty to skip update
            }
            if (!contact.isEmpty()) vendor.setVend_Contact(contact);
        }

        // Update fee if provided
        System.out.print("🔹 New Vendor Fee (" + vendor.getVend_Fee() + "): ");
        String feeInput = sc.nextLine();
        if (!feeInput.isEmpty()) {
            try {
                // Parse and set new fee value
                vendor.setVend_Fee(Double.parseDouble(feeInput));
            } catch (NumberFormatException e) {
                // Handle invalid number format
                System.out.println("❌ Invalid fee format. Fee remains unchanged.");
            }
        }

        // Update details if provided
        System.out.print("🔹 New Vendor Details (" + vendor.getVend_Details() + "): ");
        String details = sc.nextLine();
        if (!details.isEmpty()) vendor.setVend_Details(details);

        // Update associated event if provided
        System.out.print("🔹 New Event ID (" + (vendor.getEvent() != null ? vendor.getEvent().getE_id() : "None") + "): ");
        String eventId = sc.nextLine();
        if (!eventId.isEmpty()) {
            // Validate new event exists
            Event event = eventService.getEvent(eventId);
            if (event != null) {
                vendor.setEvent(event); // Set new event
            } else {
                System.out.println("❌ Event not found! Event remains unchanged.");
            }
        }

        // Save updates through service layer
        vendorService.updateVendor(vendor);
        // Confirm successful update
        System.out.println("✅ Vendor updated successfully!");
    }

    /**
     * Deletes a vendor from the system
     */
    private static void deleteVendor() {
        // Prompt for vendor ID to delete
        System.out.print("\n🗑️ Enter Vendor ID to delete: ");
        // Get vendor ID from user
        String vendorId = sc.nextLine();
        // Delete vendor through service layer
        vendorService.deleteVendor(vendorId);
        // Confirm successful deletion
        System.out.println("✅ Vendor deleted successfully!");
    }

    /**
     * Retrieves vendors filtered by fee range
     */
    private static void getVendorsByFeeRange() {
        // Get minimum fee value from user
        double min = getDoubleInput("\n💰 Enter Minimum Fee: ");
        // Get maximum fee value from user
        double max = getDoubleInput("💰 Enter Maximum Fee: ");

        // Get filtered vendors from service layer
        List<Vendor> vendors = vendorService.getVendorsByFeeRange(min, max);
        // Display results or "no vendors" message
        if (vendors.isEmpty()) {
            System.out.println("⚠️ No vendors found in this fee range!");
        } else {
            // Print each vendor in fee range
            vendors.forEach(System.out::println);
        }
    }
    // ================================
    // 🎟️ PARTICIPANT OPERATIONS  
    // ================================

    /**
     * Displays and handles the participant operations menu
     */
    public static void participantOperations() {
        // Loop to keep showing participant menu until user goes back
        while (true) {
            // Display participant operations menu header
            System.out.println("\n=======================================");
            System.out.println("      🎟️ PARTICIPANT OPERATIONS MENU    ");
            System.out.println("=======================================");
            
            // Display all participant operation options  
            System.out.println("1️  Add Participant");           // Option to add new participant
            System.out.println("2️  View Participant");          // Option to view single participant
            System.out.println("3️  View All Participants");     // Option to view all participants
            System.out.println("4️  Update Participant");        // Option to update participant
            System.out.println("5️  Delete Participant");        // Option to delete participant
            System.out.println("6️  Get Participants by Status");// Option to filter by status
            System.out.println("7️  Back to Main Menu");         // Option to return to main menu
            
            // Prompt for user choice
            System.out.print("👉 Enter your choice: ");

            // Get validated input between 1-7
            int choice = getIntInput(1, 7);
            // Consume leftover newline
            sc.nextLine(); 

            // Handle user choice
            switch (choice) {
                case 1: 
                    addParticipant();       // Call add participant method
                    break;
                case 2: 
                    getParticipant();      // Call view single participant method
                    break;
                case 3: 
                    getAllParticipants();  // Call view all participants method
                    break;
                case 4: 
                    updateParticipant();    // Call update participant method
                    break;
                case 5: 
                    deleteParticipant();   // Call delete participant method
                    break;
                case 6: 
                    getParticipantsByStatus(); // Call filter by status method
                    break;
                case 7: 
                    return;                // Return to main menu
                default: 
                    System.out.println("❌ Invalid choice! Please try again."); // Handle invalid input
            }
        }
    }

    /**
     * Adds a new participant to the system
     */
    private static void addParticipant() {
        // Display add participant header
        System.out.println("\n🎟️ Adding a New Participant...");

        // Create new Participants object
        Participants participant = new Participants();
        
        // Get participant ID from user
        System.out.print("🔹 Enter Participant ID: ");
        participant.setP_Id(sc.nextLine());

        // Get participant name from user  
        System.out.print("🔹 Enter Participant Name: ");
        participant.setP_Name(sc.nextLine());

        // Get and validate participant contact number
        participant.setP_Contact(validatePhone("🔹 Enter Participant Contact (10 digits): "));

        // Get participant status from user
        System.out.print("🔹 Enter Participant Status: ");
        participant.setP_Status(sc.nextLine());

        // Get participant performance details from user
        System.out.print("🔹 Enter Participant Performance: ");
        participant.setP_Performance(sc.nextLine());

        // Get associated event ID and validate event exists
        System.out.print("🔹 Enter Event ID: ");
        Event event = eventService.getEvent(sc.nextLine());
        if (event == null) {
            System.out.println("❌ Event not found! Participant creation aborted.");
            return; // Abort if event doesn't exist
        }
        participant.setEvent(event); // Set valid event

        // Create participant through service layer
        participantsService.createParticipant(participant);
        // Confirm successful creation
        System.out.println("✅ Participant added successfully!");
    }

    /**
     * Retrieves and displays a single participant by ID
     */
    private static void getParticipant() {
        // Prompt for participant ID
        System.out.print("\n🔎 Enter Participant ID to view: ");
        // Get participant from service layer
        Participants participant = participantsService.getParticipant(sc.nextLine());
        // Display participant if found, or error message
        System.out.println(participant != null ? participant : "❌ Participant not found!");
    }

    /**
     * Retrieves and displays all participants in the system
     */
    private static void getAllParticipants() {
        // Display header
        System.out.println("\n📋 All Participants:");
        // Get all participants from service layer
        List<Participants> participants = participantsService.getAllParticipants();
        // Handle case when no participants exist
        if (participants.isEmpty()) {
            System.out.println("⚠️ No participants found!");
        } else {
            // Print each participant using toString()
            participants.forEach(System.out::println);
        }
    }

    /**
     * Updates an existing participant's details
     */
    private static void updateParticipant() {
        // Prompt for participant ID to update
        System.out.print("\n✏️ Enter Participant ID to update: ");
        // Get participant from service layer
        Participants participant = participantsService.getParticipant(sc.nextLine());

        // Handle case when participant doesn't exist
        if (participant == null) {
            System.out.println("❌ Participant not found!");
            return;
        }

        // Update name if provided (non-empty)
        System.out.print("🔹 New Participant Name (" + participant.getP_Name() + "): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) participant.setP_Name(name);

        // Update contact with validation
        System.out.print("🔹 New Participant Contact (" + participant.getP_Contact() + "): ");
        String contact = sc.nextLine();
        if (!contact.isEmpty()) {
            // Validate phone format
            while (!PHONE_PATTERN.matcher(contact).matches()) {
                System.out.println("❌ Invalid phone number! Please enter 10 digits only.");
                System.out.print("🔹 New Participant Contact (" + participant.getP_Contact() + "): ");
                contact = sc.nextLine();
                if (contact.isEmpty()) break; // Allow empty to skip update
            }
            if (!contact.isEmpty()) participant.setP_Contact(contact);
        }

        // Update status if provided
        System.out.print("🔹 New Participant Status (" + participant.getP_Status() + "): ");
        String status = sc.nextLine();
        if (!status.isEmpty()) participant.setP_Status(status);

        // Update performance if provided
        System.out.print("🔹 New Participant Performance (" + participant.getP_Performance() + "): ");
        String performance = sc.nextLine();
        if (!performance.isEmpty()) participant.setP_Performance(performance);

        // Update associated event if provided
        System.out.print("🔹 New Event ID (" + (participant.getEvent() != null ? participant.getEvent().getE_id() : "None") + "): ");
        String eventId = sc.nextLine();
        if (!eventId.isEmpty()) {
            // Validate new event exists
            Event event = eventService.getEvent(eventId);
            if (event != null) {
                participant.setEvent(event); // Set new event
            } else {
                System.out.println("❌ Event not found! Event remains unchanged.");
            }
        }

        // Save updates through service layer
        participantsService.updateParticipant(participant);
        // Confirm successful update
        System.out.println("✅ Participant updated successfully!");
    }

    /**
     * Deletes a participant from the system
     */
    private static void deleteParticipant() {
        // Prompt for participant ID to delete
        System.out.print("\n🗑️ Enter Participant ID to delete: ");
        // Get participant ID from user
        String id = sc.nextLine();
        // Delete participant through service layer
        participantsService.deleteParticipant(id);
        // Confirm successful deletion
        System.out.println("✅ Participant deleted successfully!");
    }

    /**
     * Retrieves participants filtered by status
     */
    private static void getParticipantsByStatus() {
        // Prompt for status to filter by
        System.out.print("\n📊 Enter Status to search: ");
        // Get filtered participants from service layer
        List<Participants> participants = participantsService.getParticipantsByStatus(sc.nextLine());
        // Display results or "no participants" message
        if (participants.isEmpty()) {
            System.out.println("⚠️ No participants found with this status!");
        } else {
            // Print each participant with matching status
            participants.forEach(System.out::println);
        }
    }
}