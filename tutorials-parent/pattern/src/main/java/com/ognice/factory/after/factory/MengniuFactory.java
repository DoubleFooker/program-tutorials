package com.ognice.factory.after.factory;

import com.ognice.factory.domain.MengniuMilk;
import com.ognice.factory.domain.Milk;

public class MengniuFactory implements Factory {
    public Milk getMilk() {
        return new MengniuMilk();
    }
}
