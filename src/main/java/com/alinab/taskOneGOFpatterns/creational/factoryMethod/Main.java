package com.alinab.taskOneGOFpatterns.creational.factoryMethod;

import com.alinab.taskOneGOFpatterns.creational.factoryMethod.designers.Designer;
import com.alinab.taskOneGOFpatterns.creational.factoryMethod.designers.DesignerType;
import com.alinab.taskOneGOFpatterns.creational.factoryMethod.factory.DesignerFactory;

public class Main {

    public static void main(String[] args) {
        DesignerFactory designerFactory = new DesignerFactory();

        Designer graphicalDesigner = designerFactory.createDesigner(DesignerType.GRAPHICAL_DESIGNER);
        graphicalDesigner.createSketch();

        Designer interiorDesigner = designerFactory.createDesigner(DesignerType.INTERIOR_DESIGNER);
        interiorDesigner.createSketch();
    }
}
