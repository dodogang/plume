package net.dodogang.plume.datagen;

import java.nio.file.Path;

@SuppressWarnings("unused")
public interface DataCacheAccess {
    void addCopyPath(Path out);
    void keepFiles(Path... keep);
}
