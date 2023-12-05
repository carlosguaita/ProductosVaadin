package com.producto.views.productos;

import com.producto.models.Alimento;
import com.producto.models.Electrodomestico;
import com.producto.models.Producto;
import com.producto.models.Tecnologia;
import com.producto.utils.Utils;
import com.producto.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import jdk.jshell.execution.Util;
import org.springframework.format.datetime.DateFormatter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Nuevo Producto")
@Route(value = "nuevo-producto", layout = MainLayout.class)
@Uses(Icon.class)
public class NuevoProductoView extends Composite<VerticalLayout> {

    public NuevoProductoView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField nombre = new TextField();
        ComboBox comboBox = new ComboBox();
        NumberField cantidad = new NumberField();
        TextField precio = new TextField();
        TextField codigo = new TextField();
        TextField marca = new TextField();
        DatePicker fechaExpiracion = new DatePicker();
        DatePicker fechaElaboracion = new DatePicker();
        TextField fabricante = new TextField();
        NumberField voltaje = new NumberField();
        NumberField garantia = new NumberField();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button guardar = new Button();
        Button cancelar = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Información del Producto");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        nombre.setLabel("Nombre");
        comboBox.setLabel("Tipo");
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        cantidad.setLabel("Cantidad");
        cantidad.setWidth("min-content");
        precio.setLabel("Precio");
        codigo.setLabel("Código");
        marca.setLabel("Marca");
        marca.setWidth("min-content");
        fechaExpiracion.setLabel("Fecha Expiración");
        fechaElaboracion.setLabel("Fecha Elaboración");
        fechaElaboracion.setWidth("min-content");
        fabricante.setLabel("Fabricante");
        fabricante.setWidth("min-content");
        voltaje.setLabel("Voltaje");
        voltaje.setWidth("min-content");
        garantia.setLabel("Años Garantía");
        garantia.setWidth("min-content");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        guardar.setText("Save");
        guardar.setWidth("min-content");
        guardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        guardar.addClickListener(e -> {
            int selTipo = Integer.parseInt(((SampleItem)comboBox.getValue()).value());
            String tipo = ((SampleItem)comboBox.getValue()).label();
            System.out.println(selTipo);
            switch (selTipo){
                case 1:
                    Producto alimento = new Alimento(
                            tipo,
                            nombre.getValue(),
                            Double.parseDouble(precio.getValue()),
                            codigo.getValue(),
                            cantidad.getValue().intValue(),
                            marca.getValue(),
                            fechaElaboracion.getValue().toString(),
                            fechaExpiracion.getValue().toString(),
                            null
                    );
                    Utils.listaProductos.add(alimento);
                    break;
                case 2:
                    Producto tecnologia = new Tecnologia(
                            tipo,
                            nombre.getValue(),
                            Double.parseDouble(precio.getValue()),
                            codigo.getValue(),
                            cantidad.getValue().intValue(),
                            marca.getValue(),
                            fabricante.getValue(),
                            voltaje.getValue()
                    );
                    Utils.listaProductos.add(tecnologia);
                    break;
                case 3:
                    Producto electro = new Electrodomestico(
                            tipo,
                            nombre.getValue(),
                            Double.parseDouble(precio.getValue()),
                            codigo.getValue(),
                            cantidad.getValue().intValue(),
                            marca.getValue(),
                            fabricante.getValue(),
                            voltaje.getValue(),
                            garantia.getValue().intValue()
                    );
                    Utils.listaProductos.add(electro);

                    break;
            }


            guardar.getUI().ifPresent(ui ->
                    ui.navigate("productos"));
        });
        cancelar.setText("Cancel");
        cancelar.setWidth("min-content");
        cancelar.addClickListener(e -> {
            guardar.getUI().ifPresent(ui ->
                    ui.navigate("productos"));
        });
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(nombre);
        formLayout2Col.add(comboBox);
        formLayout2Col.add(cantidad);
        formLayout2Col.add(precio);
        formLayout2Col.add(codigo);
        formLayout2Col.add(marca);
        formLayout2Col.add(fechaExpiracion);
        formLayout2Col.add(fechaElaboracion);
        formLayout2Col.add(fabricante);
        formLayout2Col.add(voltaje);
        formLayout2Col.add(garantia);
        layoutColumn2.add(layoutRow);
        layoutRow.add(guardar);
        layoutRow.add(cancelar);
    }

    record SampleItem(String value, String label, Boolean disabled) {

    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("1", "Alimento", null));
        sampleItems.add(new SampleItem("2", "Tecnología", null));
        sampleItems.add(new SampleItem("3", "Electrodomestico", Boolean.TRUE));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
