package com.szczypiorofix.sweetrolls.game.main;

import com.szczypiorofix.sweetrolls.game.objects.GameObject;

import java.util.HashMap;
import java.util.Random;

public class Registry {

    private static Registry instance;
    private final HashMap<Long, GameObject> registry = new HashMap();
    private static Random generator;

    public static synchronized Registry getInstance() {
        if (instance == null) {
            instance = new Registry();
        }
        generator = new Random();
        return instance;
    }

    private boolean isRegistered(final Long id) {
        return registry.containsKey(id);
    }

    public synchronized long register(final GameObject object) {
        long r;
        do {
            r = generator.nextLong();
        } while(registry.containsKey(r));
        registry.put(r, object);
        return r;
    }

    public synchronized HashMap<Long, GameObject> getRegistry() {
        return registry;
    }

}
