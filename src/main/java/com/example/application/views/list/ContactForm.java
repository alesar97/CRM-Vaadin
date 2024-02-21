package com.example.application.views.list;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Contacts | Vaadin CRM")
@Route(value = "")
public class ListView extends VerticalLayout {

    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();

    public ListView() {
        //css class name
        addClassName("list-view");
        // view the same size as the entire browser window
        setSizeFull();

        configureGrid();

        add(
                getToolbar(),
                grid
        );
//        Button button = new Button("click me");
//        TextField name = new TextField("Name");
//
//        HorizontalLayout hl = new HorizontalLayout(name,button);
//        hl.setDafaultVerticalComponentAlignment(Alignment.BASELINE);
//
//        button.addClickListner(click->Notification.show("Hallo, " + name.getValue()));
//
//        add(hl);
    }

    private Component getToolbar(){
        filterText.setPlaceholder("Filter by name..");
        filterText.setCleanButtonVisible(True);
        //wait for the user to stop typing for a little while
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addContactButton = new Button("click me");
        HorizontalLayout toolbar = new HorizontalLayout(filterText,addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void configureGrid(){
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email");
        //because company is an object
        grid.addColumn(contact->contact.getStatus().getName()).setHeader("Status");
        grid.addColumn(contact->contact.getCompany().getName()).setHeader("Company");
        // all columns sized to fit the content inside of them
        grid.getColumns.forEach(col.setAutoWidth(true));

    }

}
