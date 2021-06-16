package net.dodogang.plume.datagen;

import java.io.IOException;
import java.util.function.Consumer;

public interface DataGenAccess {
    void run(Consumer<DataCacheAccess> configure) throws IOException;
}
