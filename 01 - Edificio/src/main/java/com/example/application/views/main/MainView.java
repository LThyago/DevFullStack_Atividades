package com.example.application.views.main;


import com.example.application.data.Apartamento;
import com.example.application.data.Edificio;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;

@Route("")
@PageTitle("Edifício")
public class MainView extends VerticalLayout {
    Grid<Apartamento> grid = new Grid<>(Apartamento.class);
    ApartamentoForm form;
    Edificio edificio = new Edificio("Compenhage", "Praça T-25");

    public MainView() {
        addClassName("main-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getContent());
    }

    private Component getContent(){
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();

        return content;
    }



    private void configureGrid(){
        grid.addClassName("grid-edificio");
        grid.setSizeFull();
        grid.setColumns("andar", "numero", "metragem", "situacao");
        grid.addColumn(apartamento -> apartamento.getMorador().getNome()).setHeader("Morador");
        grid.addColumn(apartamento -> apartamento.getMorador().getTelefone()).setHeader("Telefone");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));





    }

    private void configureForm(){
        form = new ApartamentoForm();
        form.setWidth("25em");

        form.save.addClickListener(click -> {
            edificio.adicionarApartamento((int)form.getNumeroValue(), (int)form.getAndarValue(), form.getMetragemValue(), form.getStatusValue());

            edificio.getLastApartamento().cadastrarMorador(form.getNomeMoradorValue(), form.getCpfMoradorValue(), form.getTelefoneMoradorValue());

            form.andar.clear();
            form.numero.clear();
            form.metragem.clear();
            form.status.clear();
            form.nomeMorador.clear();
            form.cpfMorador.clear();
            form.telefoneMorador.clear();

            grid.setItems(edificio.getApartamentos());
        });
    }


}