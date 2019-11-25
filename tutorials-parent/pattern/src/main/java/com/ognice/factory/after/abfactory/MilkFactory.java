package com.ognice.factory.after.abfactory;

import com.ognice.factory.after.factory.MengniuFactory;
import com.ognice.factory.after.factory.TelunsuFactory;
import com.ognice.factory.domain.Milk;

public class MilkFactory extends AbstractFactory {
    public Milk getMengniuMilk() {
        return new MengniuFactory().getMilk();
    }

    public Milk getTelunsuMilk() {
        return new TelunsuFactory().getMilk();
    }
}
