package com.alinab.taskOneGOFpatterns.creational.factoryMethod.factory;

import com.alinab.taskOneGOFpatterns.creational.factoryMethod.designers.Designer;
import com.alinab.taskOneGOFpatterns.creational.factoryMethod.designers.DesignerType;
import com.alinab.taskOneGOFpatterns.creational.factoryMethod.designers.GraphicalDesigner;
import com.alinab.taskOneGOFpatterns.creational.factoryMethod.designers.InteriorDesigner;

public class DesignerFactory {

    public Designer createDesigner(DesignerType designerType) {
        Designer designer;

        switch (designerType) {
            case GRAPHICAL_DESIGNER:
                designer = new GraphicalDesigner();
                break;
            case INTERIOR_DESIGNER:
                designer = new InteriorDesigner();
                break;
            default:
                throw new IllegalArgumentException("No such type of designers!");
        }

        return designer;
    }
}
