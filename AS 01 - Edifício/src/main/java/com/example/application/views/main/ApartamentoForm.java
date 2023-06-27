package com.example.application.views.main;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

public class ApartamentoForm extends FormLayout {
    public NumberField numero = new NumberField("Número");
    public NumberField andar = new NumberField("Andar");
    public NumberField metragem = new NumberField("Metragem");
    public TextField status = new TextField("Status");
    public TextField nomeMorador = new TextField("Nome do Morador");
    public TextField telefoneMorador = new TextField("Telefone do Morador");
    public TextField cpfMorador = new TextField("CPF do Morador");

    public Button save = new Button("Salvar");
    public Button cancel = new Button("Cancelar");

    public double getNumeroValue(){
        return this.numero.getValue();
    }

    public double getAndarValue(){
        return this.andar.getValue();
    }

    public double getMetragemValue(){
        return this.metragem.getValue();
    }

    public String getStatusValue(){
        return this.status.getValue();
    }

    public String getNomeMoradorValue(){
        return this.nomeMorador.getValue();
    }

    public String getTelefoneMoradorValue(){
        return this.telefoneMorador.getValue();
    }

    public String getCpfMoradorValue(){
        return this.cpfMorador.getValue();
    }

    public Button getCancel() {
        return cancel;
    }

    public Button getSave() {
        return save;
    }

    public ApartamentoForm(){
        addClassName("apartamento-form");

        add(numero, andar, metragem, status, nomeMorador, cpfMorador, telefoneMorador, createButtonLayout());
    }

    private Component createButtonLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, cancel);
    }

}
