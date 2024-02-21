package com.example.application.views;

import com.example.application.services.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Dashboard | Vaadin CRM")
@PermitAll
public class DashboardView extends VerticalLayout {
private CrmService service;

public DashboardView(CrmService service){
    this.service = service;
    addClassName("dashboard-view");
    setDefaultHorizontalComponentAlignment(Alignment.CENTER);
//    add(getContactState(), getCompaniesChart());
}

//    private Component getContactState(){
//    }
//
//    private Component getCompaniesChart(){
//
//    }
}
