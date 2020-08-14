package com.github.cutety.protectiveSuspension;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class Mailbox {
    private static final Map<Integer, GuardObject> boxes = new Hashtable<>();
    private static int id = 1;
    private static synchronized int generateId() {
        return id++;
    }
    public static GuardObject getGuardObject(int id) {
        return boxes.remove(id);
    }
    public static GuardObject createGuardObject() {
        GuardObject go = new GuardObject(generateId());
        boxes.put(go.getId(),go);
        return go;
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }
}
