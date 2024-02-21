package com.example.application.views.list;

import com.example.application.data.Contact;
import com.example.application.services.CrmService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Contacts | Vaadin CRM")
//loaded inside MainLayout
@Route(value = "", layout = MainLayout.class)
@PermitAll
public class ListView extends VerticalLayout {

    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();
    ContactForm form;
    private CrmService service;

    public ListView(CrmService service) {
        this.service = service;
        //css class name
        addClassName("list-view");
        // view the same size as the entire browser window
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getToolbar(),
                getContent()
        );

        //update List of contact
        updateList();
        closeEditor();

    }

    private void closeEditor(){
        form.setContact(null);
        form.setVisible(false);
        removeClassName("editing");

    }
    private void updateList() {
        grid.setItems(service.findAllContacts(filterText.getValue()));
    }

    private Component getContent(){
        HorizontalLayout content = new HorizontalLayout(grid,form);
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1,form);
        content.setClassName("content");
        content.setSizeFull();

        return content;
    }

    private void configureForm(){
        form = new ContactForm(service.findAllCompanies(), service.findAllStatuses());
        form.setWidth("25em");

        form.addSaveListener(this::saveContact);
        form.addDeleteListener(this::deleteContact);
        form.addCloseListener(e -> closeEditor());
    }

    private void saveContact(ContactForm.SaveEvent event) {
        service.saveContact(event.getContact());
        updateList();
        closeEditor();
    }

    private void deleteContact(ContactForm.DeleteEvent event) {
        service.deleteContact(event.getContact());
        updateList();
        closeEditor();
    }

    private Component getToolbar(){
        filterText.setPlaceholder("Filter by name..");
        filterText.setClearButtonVisible(true);
        //wait for the user to stop typing for a little while
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        //update during filtering
        filterText.addValueChangeListener(e->updateList());

        Button addContactButton = new Button("Add Contact");
        addContactButton.addClickListener(e->addContact());

        HorizontalLayout toolbar = new HorizontalLayout(filterText,addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addContact() {
        grid.asSingleSelect().clear();
        editContact(new Contact());
    }

    private void configureGrid(){
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email");
        //because company is an object
        grid.addColumn(contact->contact.getStatus().getName()).setHeader("Status");
        grid.addColumn(contact->contact.getCompany().getName()).setHeader("Company");
        // all columns sized to fit the content inside of them
        grid.getColumns().forEach(col->col.setAutoWidth(true));

        //select one Contact
        grid.asSingleSelect().addValueChangeListener(e->editContact(e.getValue()));
    }

    private void editContact(Contact contact) {
        //deselect a selected contact
        if(contact == null){
            closeEditor();
        }else{
            form.setContact(contact);
            form.setVisible(true);
            addClassName("editing");
        }
    }

}
