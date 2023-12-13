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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Nuevo Producto")
@Route(value = "nuevo-producto", layout = MainLayout.class)
@Uses(Icon.class)
public class NuevoProductoView extends Composite<VerticalLayout> implements HasUrlParameter<String>{

    VerticalLayout layoutColumn2 = new VerticalLayout();
    H3 h3 = new H3();
    FormLayout formLayout2Col = new FormLayout();
    TextField nombre = new TextField();
    ComboBox tipoProducto = new ComboBox();
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
    List<SampleItem> sampleItems = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    boolean isNew = true;
    Producto productoEditar;
    SampleItem sampleItem;
    int selTipo, cantidadProducto, garantiaProducto;
    String tipo, nombreProducto, codigoProducto, marcaProducto, fabricanteProducto, fElaboracionProducto, fExpiracionProducto;

    double precioProducto, voltajeProducto;
    public NuevoProductoView() {


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
        tipoProducto.setLabel("Tipo");
        tipoProducto.setWidth("min-content");
        tipoProducto.addValueChangeListener(event -> {
            int selTipo = Integer.parseInt(((SampleItem) event.getValue()).value());
            switch (selTipo){
                case 1:
                    fechaElaboracion.setEnabled(true);
                    fechaExpiracion.setEnabled(true);
                    fabricante.setEnabled(false);
                    voltaje.setEnabled(false);
                    garantia.setEnabled(false);
                    break;
                case 2:
                    fechaElaboracion.setEnabled(false);
                    fechaExpiracion.setEnabled(false);
                    fabricante.setEnabled(true);
                    voltaje.setEnabled(true);
                    garantia.setEnabled(false);
                    break;
                case 3:
                    fechaElaboracion.setEnabled(false);
                    fechaExpiracion.setEnabled(false);
                    fabricante.setEnabled(true);
                    voltaje.setEnabled(true);
                    garantia.setEnabled(true);
                    break;
            }
        });
        setComboBoxSampleData(tipoProducto);
        cantidad.setLabel("Cantidad");
        cantidad.setWidth("min-content");
        precio.setLabel("Precio");
        codigo.setLabel("Código");
        codigo.setRequired(true);
        codigo.setErrorMessage("Debe ingresar el código del producto");
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

                selTipo = Integer.parseInt(((SampleItem) tipoProducto.getValue()).value());
                tipo = ((SampleItem) tipoProducto.getValue()).label();
                nombreProducto = nombre.getValue();
                precioProducto = Double.parseDouble(precio.getValue());
                codigoProducto = codigo.getValue();
                cantidadProducto = cantidad.getValue().intValue();
                marcaProducto = marca.getValue();
                System.out.println(selTipo);
                if (isNew)  {
                    if(!codigo.isEmpty()) {
                        switch (selTipo) {
                            case 1:
                                fElaboracionProducto = fechaElaboracion.getValue().format(formatter);
                                fExpiracionProducto = fechaExpiracion.getValue().format(formatter);
                                Producto alimento = new Alimento(
                                        tipo,
                                        nombreProducto,
                                        precioProducto,
                                        codigoProducto,
                                        cantidadProducto,
                                        marcaProducto,
                                        fElaboracionProducto,
                                        fExpiracionProducto,
                                        null
                                );
                                Utils.listaProductos.add(alimento);
                                break;
                            case 2:
                                fabricanteProducto = fabricante.getValue();
                                voltajeProducto = voltaje.getValue();
                                Producto tecnologia = new Tecnologia(
                                        tipo,
                                        nombreProducto,
                                        precioProducto,
                                        codigoProducto,
                                        cantidadProducto,
                                        marcaProducto,
                                        fabricanteProducto,
                                        voltajeProducto
                                );
                                Utils.listaProductos.add(tecnologia);
                                break;
                            case 3:
                                fabricanteProducto = fabricante.getValue();
                                voltajeProducto = voltaje.getValue();
                                garantiaProducto = garantia.getValue().intValue();
                                Producto electro = new Electrodomestico(
                                        tipo,
                                        nombreProducto,
                                        precioProducto,
                                        codigoProducto,
                                        cantidadProducto,
                                        marcaProducto,
                                        fabricanteProducto,
                                        voltajeProducto,
                                        garantiaProducto
                                );
                                Utils.listaProductos.add(electro);

                                break;
                        }
                        guardar.getUI().ifPresent(ui ->
                                ui.navigate("productos"));
                    }else{
                        Notification.show("Error: Debe proporcionar un código de producto");
                    }
                } else if (!isNew){
                    productoEditar.setNombre(nombreProducto);
                    productoEditar.setPrecio(precioProducto);
                    productoEditar.setCantidad(cantidadProducto);
                    productoEditar.setMarca(marcaProducto);
                    switch (Integer.parseInt(sampleItem.value)) {
                        case 1:
                            fElaboracionProducto = fechaElaboracion.getValue().format(formatter);
                            fExpiracionProducto = fechaExpiracion.getValue().format(formatter);
                            ((Alimento) productoEditar).setFechaElaboracion(fElaboracionProducto);
                            ((Alimento) productoEditar).setFechaExpiracion(fExpiracionProducto);
                            break;
                        case 2:
                            fabricanteProducto = fabricante.getValue();
                            voltajeProducto = voltaje.getValue();
                            ((Tecnologia) productoEditar).setFabricante(fabricanteProducto);
                            ((Tecnologia) productoEditar).setVoltaje(voltajeProducto);
                            break;
                        case 3:
                            fabricanteProducto = fabricante.getValue();
                            voltajeProducto = voltaje.getValue();
                            garantiaProducto = garantia.getValue().intValue();
                            ((Electrodomestico) productoEditar).setFabricante(fabricanteProducto);
                            ((Electrodomestico) productoEditar).setVoltaje(voltajeProducto);
                            ((Electrodomestico) productoEditar).setAniosGarantia(garantiaProducto);
                            break;
                    }
                    guardar.getUI().ifPresent(ui ->
                            ui.navigate("productos"));
                }

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
        formLayout2Col.add(tipoProducto);
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

    @Override
    public void setParameter(BeforeEvent beforeEvent,
                             @OptionalParameter String codigoProducto) {
        System.out.println(codigoProducto);
        if (codigoProducto!=null){
            codigo.setEnabled(false);
            tipoProducto.setEnabled(false);
            isNew=false;
             productoEditar = Utils.listaProductos.stream()
                    .filter(x->x.getCodigo().equals(codigoProducto))
                    .findAny()
                    .orElseThrow();
            nombre.setValue(productoEditar.getNombre());
            sampleItem = sampleItems.stream()
                    .filter(x->x.label.equals(productoEditar.getTipo()))
                    .findAny()
                    .orElseThrow();
            tipoProducto.setValue(sampleItem);
            precio.setValue(String.valueOf(productoEditar.getPrecio()));
            codigo.setValue(productoEditar.getCodigo());
            cantidad.setValue((double)productoEditar.getCantidad());
            marca.setValue(productoEditar.getMarca());
            if (productoEditar instanceof Alimento){
                fechaElaboracion.setValue(LocalDate.parse(((Alimento) productoEditar).getFechaElaboracion(),formatter));
                fechaExpiracion.setValue(LocalDate.parse(((Alimento) productoEditar).getFechaExpiracion(),formatter));
            } else if (productoEditar instanceof Electrodomestico) {
                fabricante.setValue(((Electrodomestico) productoEditar).getFabricante());
                voltaje.setValue(((Electrodomestico) productoEditar).getVoltaje());
                garantia.setValue((double)((Electrodomestico) productoEditar).getAniosGarantia());
            }else if (productoEditar instanceof Tecnologia){
                fabricante.setValue(((Tecnologia) productoEditar).getFabricante());
                voltaje.setValue(((Tecnologia) productoEditar).getVoltaje());
            }
        }else{
            isNew=true;
        }

    }

    record SampleItem(String value, String label, Boolean disabled) {

    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        sampleItems.add(new SampleItem("1", "Alimento", null));
        sampleItems.add(new SampleItem("2", "Tecnología", null));
        sampleItems.add(new SampleItem("3", "Electrodomestico", Boolean.TRUE));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
