package org.vaadin.crudui.form.impl;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Layout;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.VerticalLayout;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.form.AbstractAutoGeneratedCrudFormFactory;

import java.util.List;

/**
 * @author Alejandro Duarte
 */
public class GridLayoutCrudFormFactory<T> extends AbstractAutoGeneratedCrudFormFactory<T> {

    private int columns;
    private int rows;

    public GridLayoutCrudFormFactory(Class<T> domainType, int columns, int rows) {
        super(domainType);
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public Component buildNewForm(CrudOperation operation, T domainObject, boolean readOnly, Button.ClickListener cancelButtonClickListener, Button.ClickListener operationButtonClickListener) {
        GridLayout gridLayout = new GridLayout(columns, rows);
        gridLayout.setWidth("100%");
        gridLayout.setSpacing(true);

        BeanFieldGroup fieldGroup = new BeanFieldGroup<>(domainObject.getClass());
        List<Field> fields = buildAndBind(operation, domainObject, readOnly, fieldGroup);
        fields.stream().forEach(field -> gridLayout.addComponent(field));

        Layout footerLayout = buildFooter(operation, domainObject, cancelButtonClickListener, operationButtonClickListener, fieldGroup);

        VerticalLayout mainLayout = new VerticalLayout(gridLayout, footerLayout);
        mainLayout.setWidth("100%");
        mainLayout.setComponentAlignment(footerLayout, Alignment.BOTTOM_RIGHT);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        return mainLayout;
    }

}
