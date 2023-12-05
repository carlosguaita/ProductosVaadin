package com.producto.views.productos;


import com.producto.models.Producto;
import com.producto.utils.Utils;
import com.producto.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("Productos")
@Route(value = "productos", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class ProductosView extends Composite<VerticalLayout> {
    public ProductosView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");

        Button nuevoProducto = new Button("Nuevo Producto");
        nuevoProducto.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        nuevoProducto.addClickListener(e -> {
            nuevoProducto.getUI().ifPresent(ui ->
                    ui.navigate("nuevo-producto"));
        });

        Grid<Producto> grid = new Grid<>(Producto.class, false);
        grid.addColumn(Producto::getCodigo).setHeader("Código").setAutoWidth(true);
        grid.addColumn(Producto::getNombre).setHeader("Nombre").setAutoWidth(true);
        grid.addColumn(Producto::getPrecio).setHeader("Precio").setAutoWidth(true);
        grid.addColumn(Producto::getMarca).setHeader("Marca").setAutoWidth(true);
        grid.addColumn(Producto::getCantidad).setHeader("Cantidad").setAutoWidth(true);
        grid.addColumn(
                new ComponentRenderer<>(producto -> {
                    Button botonBorrar = new Button();
                    botonBorrar.addThemeVariants(ButtonVariant.LUMO_ERROR);
                    botonBorrar.addClickListener(e -> {
                        Utils.listaProductos.remove(producto);
                        grid.getDataProvider().refreshAll();
                    });
                    botonBorrar.setIcon(new Icon(VaadinIcon.TRASH));

                    Button botonEditar = new Button();
                    botonEditar.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                    botonEditar.addClickListener(e -> {

                    });
                    botonEditar.setIcon(new Icon(VaadinIcon.EDIT));

                    Button botonVer = new Button();
                    botonVer.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
                    botonVer.addClickListener(e -> {

                    });
                    botonVer.setIcon(new Icon(VaadinIcon.EYE));

                    HorizontalLayout buttons = new HorizontalLayout(botonBorrar,botonEditar,botonVer);
                    return buttons;
                })).setHeader("Manage").setAutoWidth(true);

        List<Producto> productos = Utils.listaProductos;
        grid.setItems(productos);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        layoutColumn2.add(nuevoProducto,grid);


        getContent().add(layoutColumn2);
    }
}
