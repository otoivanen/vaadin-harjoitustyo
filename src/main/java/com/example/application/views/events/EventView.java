package com.example.application.views.events;

import com.example.application.data.Event;
import com.example.application.services.EventService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import org.springframework.web.client.RestTemplate;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.awt.font.LineBreakMeasurer;
import java.util.Arrays;
import java.util.List;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Events")
@Menu(order = 1, icon = LineAwesomeIconUrl.COLUMNS_SOLID)
@AnonymousAllowed
public class EventView extends VerticalLayout {
    private Grid<Event> grid = new Grid<>(Event.class);
    private TextField filterField = new TextField("Filter by Name");
    private List<Event> allEvents = List.of();
    private final EventService eventService;



    public EventView(EventService eventService) {
        this.eventService = eventService;

        fetchEvents();
        H1 header = new H1("Event listing service");
        HorizontalLayout toolbar = createToolbar();
        filterField.addValueChangeListener(e -> filterGrid(e.getValue()));

        add(header, toolbar, grid);
    }


    //Create the toolbar for search and edit functions
    private HorizontalLayout createToolbar() {
        filterField = new TextField();
        filterField.setPlaceholder("Search events...");
        filterField.setClearButtonVisible(true);
        filterField.setPrefixComponent(new Icon(VaadinIcon.SEARCH)); // Search icon

        Button addButton = new Button("Add", new Icon(VaadinIcon.PLUS_CIRCLE));
        addButton.addClickListener(e -> openAddEventDialog());

        Button deleteButton = new Button("Delete", new Icon(VaadinIcon.TRASH));
        deleteButton.addClickListener(e -> deleteSelectedEvent());

        // Style buttons
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

        // Align items in a row
        HorizontalLayout toolbar = new HorizontalLayout(filterField, addButton, deleteButton);
        toolbar.setAlignItems(FlexComponent.Alignment.CENTER);
        toolbar.setWidthFull(); // Full width
        toolbar.setSpacing(true); // Adds space between elements

        return toolbar;
    }

    private void deleteSelectedEvent() {
        Event selected = grid.asSingleSelect().getValue();
        if (selected != null) {
            eventService.deleteEvent(selected.getId()); // Replace with your delete logic
            fetchEvents(); // Refresh grid
        } else {
            Notification.show("Select an event to delete!");
        }
    }
    // Dialog
    private void openAddEventDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Add New Event"); // ✅ Set title

        TextField eventNameField = new TextField("Event Name");
        DatePicker eventDateField = new DatePicker("Event Date");

        // ✅ Save button with click event
        Button saveButton = new Button("Save", e -> {
            if (eventNameField.isEmpty() || eventDateField.isEmpty()) {
                Notification.show("Please fill in all fields", 3000, Notification.Position.MIDDLE);
                return;
            }

            Event newEvent = new Event();
            newEvent.setName(eventNameField.getValue());
            newEvent.setDate(eventDateField.getValue().toString()); // ✅ Uses LocalDate

            eventService.saveEvent(newEvent); // ✅ Save via service
            fetchEvents(); // ✅ Refresh grid
            Notification.show("Event added!", 3000, Notification.Position.TOP_CENTER);
            dialog.close();
        });

        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button cancelButton = new Button("Cancel", e -> dialog.close());

        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
        buttonLayout.setSpacing(true);

        VerticalLayout dialogLayout = new VerticalLayout(eventNameField, eventDateField, buttonLayout);
        dialogLayout.setSpacing(true);

        dialog.add(dialogLayout);
        dialog.open();
    }




    // Data fetching
    private void fetchEvents() {
//        RestTemplate restTemplate = new RestTemplate();
//        Event[] events = restTemplate.getForObject("http://localhost:8080/api/events", Event[].class);
//        allEvents = Arrays.asList(events);
//        grid.setItems(events != null ? Arrays.asList(events) : List.of());
        try {
            // Use the injected eventService instead of RestTemplate
            List<Event> events = eventService.getAllEvents(); // Assuming you have this method

            allEvents = events;
            grid.setItems(events);

        } catch (Exception e) {
            Notification.show("Error loading events: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
            e.printStackTrace();
        }
    }

    private void filterGrid(String filter) {
        if (filter == null || filter.trim().isEmpty()) {
            grid.setItems(allEvents); // ✅ Restore original event list when empty
        } else {
            grid.setItems(allEvents.stream()
                    .filter(event -> event.getName().toLowerCase().contains(filter.toLowerCase()))
                    .toList());
        }
    }
}