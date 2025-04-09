package test;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DLLLoader implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {
    private static boolean started = false;

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        if (!started) {
            started = true;
            System.load("C:/Users/Valeriia/CLionProjects/game_logic/cmake-build-debug/libgame_logic.dll");
        }
    }

    @Override
    public void close(){

    }
}
