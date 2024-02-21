package com.example.application.views;

import com.example.application.views.list.ListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout(){
        createHeader();
        createDrawer();
    }

    private void createHeader() {
//        H1 logo = new H1("Vaadin CRM");
        Image logo = new Image("frontend/img/logo.png", "Logo");
        logo.addClassNames("text-l","m-m");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        //logo takes the extra space
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0","px-m");

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink listView = new RouterLink("List", ListView.class);

        //ensures that the link is highlighted when the user is on the same location
        listView.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listView,
                new RouterLink("Dashboard", DashboardView.class)
        ));
    }
}
