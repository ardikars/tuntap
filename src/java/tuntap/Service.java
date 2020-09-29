package tuntap;

import tuntap.exception.NoSuchProviderException;

import java.util.Iterator;
import java.util.ServiceLoader;

public interface Service {

    String name();

    <O extends Interface.Option> Interface create(O option);

    class Loader {

        private static final ServiceLoader<Service> LOADER = ServiceLoader.load(Service.class);

        public static Service load(String name) {
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
