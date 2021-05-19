package net.dodogang.plume.ash;

import me.shedaniel.architectury.annotations.ExpectPlatform;

public final class Environment {
    private Environment() { }

    @ExpectPlatform
    public static boolean isDevelopmentEnvironment() {
        throw new AssertionError();
    }

    /**
     * Gets the platform during runtime.
     * Quilt will be supported in the future.
     *
     * @return the runtime platform
     */
    @ExpectPlatform
    public static Platform getPlatform() {
        throw new AssertionError();
    }
}
