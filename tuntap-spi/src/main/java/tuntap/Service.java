package tuntap;

import tuntap.exception.NoSuchProviderException;

import java.util.Iterator;
import java.util.ServiceLoader;

public interface Service {

    String name();

    Interface create(Interface.Options option);

    Buffer allocate(long size);

    class Creator {

        private static final ServiceLoader<Service> LOADER = ServiceLoader.load(Service.class);

        public static Service create(String name) {
            Iterator<Service> iterator = LOADER.iterator();
            while (iterator.hasNext()) {
                Service service = iterator.next();
                if (service.name().equals(name)) {
                    return service;
                }
            }
            throw new NoSuchProviderException("Service provider for " + name + " not found!");
        }
    }
}
