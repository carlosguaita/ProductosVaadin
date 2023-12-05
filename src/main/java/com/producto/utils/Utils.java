package com.producto.utils;

import com.producto.models.Alimento;
import com.producto.models.Electrodomestico;
import com.producto.models.Producto;
import com.producto.models.Tecnologia;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Producto> listaProductos = new ArrayList<>(
            List.of(
                    new Alimento("Alimento","Leche",1.2,"P001",100,"Vita","01/02/2023","01/03/2023",null),
                    new Tecnologia("Tecnologia","Audifonos",30,"P002",50,"JBL","JBL",5),
                    new Electrodomestico("Electro","Cocina",350,"P003",30,"Mabe","Mabe",120,2)
            )
    );
}
