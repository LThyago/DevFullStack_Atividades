package com.example.application.views.apartamentos;

import com.example.application.data.entity.Apartamentos;
import com.example.application.data.service.ApartamentosService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("Apartamentos")
@Route(value = "apartamentos/:apartamentosID?/:action?(edit)", layout = MainLayout.class)
public class ApartamentosView extends Div implements BeforeEnterObserver {

    private final String APARTAMENTOS_ID = "apartamentosID";
    private final String APARTAMENTOS_EDIT_ROUTE_TEMPLATE = "apartamentos/%s/edit";

    private final Grid<Apartamentos> grid = new Grid<>(Apartamentos.class, false);

    private TextField numero;
    private TextField andar;
    private TextField metragem;
    private TextField situacao;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private final BeanValidationBinder<Apartamentos> binder;

    private Apartamentos apartamentos;

    private final ApartamentosService apartamentosService;

    public ApartamentosView(ApartamentosService apartamentosService) {
        this.apartamentosService = apartamentosService;
        addClassNames("apartamentos-view");

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("numero").setAutoWidth(true);
        grid.addColumn("andar").setAutoWidth(true);
        grid.addColumn("metragem").setAutoWidth(true);
        grid.addColumn("situacao").setAutoWidth(true);
        grid.setItems(query -> apartamentosService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(APARTAMENTOS_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(ApartamentosView.class);
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Apartamentos.class);

        // Bind fields. This is where you'd define e.g. validation rules
        binder.forField(numero).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("numero");
        binder.forField(andar).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("andar");
        binder.forField(metragem).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("metragem");

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.apartamentos == null) {
                    this.apartamentos = new Apartamentos();
                }
                binder.writeBean(this.apartamentos);
                apartamentosService.update(this.apartamentos);
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(ApartamentosView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } catch (ValidationException validationException) {
                Notification.show("Failed to update the data. Check again that all values are valid");
            }
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> apartamentosId = event.getRouteParameters().get(APARTAMENTOS_ID).map(Long::parseLong);
        if (apartamentosId.isPresent()) {
            Optional<Apartamentos> apartamentosFromBackend = apartamentosService.get(apartamentosId.get());
            if (apartamentosFromBackend.isPresent()) {
                populateForm(apartamentosFromBackend.get());
            } else {
                Notification.show(
                        String.format("The requested apartamentos was not found, ID = %s", apartamentosId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(ApartamentosView.class);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        numero = new TextField("Numero");
        andar = new TextField("Andar");
        metragem = new TextField("Metragem");
        situacao = new TextField("Situacao");
        formLayout.add(numero, andar, metragem, situacao);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Apartamentos value) {
        this.apartamentos = value;
        binder.readBean(this.apartamentos);

    }
}
